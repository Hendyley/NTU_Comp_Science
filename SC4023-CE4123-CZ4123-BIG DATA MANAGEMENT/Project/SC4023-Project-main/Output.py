import pandas as pd
import statistics
import os
import datetime

town_dict = { '0': "ANG MO KIO", '1': "BEDOK", '2': "BUKIT BATOK", '3': "CLEMENTI", '4': "CHOA CHU KANG", '5': "HOUGANG", '6': "JURONG WEST", '7': "PUNGGOL", '8':"WOODLANDS", '9':"YISHUN"}
month_dict = { '0': "10", '1': "01", '2': "02", '3': "03", '4': "04", '5': "05", '6': "06", '7': "07", '8': "08", '9': "09"}

# Output as Files
def generate_output(data, output_filename):
    with open(output_filename, 'w') as file:
        file.write("Year,Month,Town,Category,Value\n")
        for key, value in data.items():
            for category, category_value in value.items():
                file.write(f"{value['Year']},{value['Month']},{value['Town']},{category},{category_value}\n")

def get_column_for_output(year, month, town):
    resale_data = pd.read_csv("Data/ResalePricesSingapore.csv")
    years = resale_data["month"].str[:4]
    filtered_years = years[years.str[-1] == str(year)]
    unique_years = filtered_years.unique()
    year_value = ",".join(map(str, unique_years))
    
    month_value = month_dict[str(month)]

    return year_value,month_value,town_dict[str(town)]

def get_column_value(year, month, town):
    month_value = month_dict[str(month)]
    if len(year) == 1:  
        year_value = "20" + ("0" + year if int(year) < 10 else year)  
    else:
        year_value = year

    return year_value, month_value, town_dict[str(town)]

# Query
def process_query(column_store, town, month, year_digit):
    current_year = datetime.datetime.now().year
    matching_indices = []
    
    for year in range(2014, current_year + 1):
        if str(year)[-1] == year_digit: 
            year_value, month_value, town_value = get_column_value(str(year), month, town)
            matching_indices += get_indexes(year_value, month, town)
    
    if not matching_indices:
        return None, None
    return matching_indices, default_scan(matching_indices)

# Sorted Query (Enhancement) 
def ss_process_query(column_store, town, month, year_digit):
    current_year = datetime.datetime.now().year
    matching_indices = []
    
    for year in range(2014, current_year + 1):
        if str(year)[-1] == year_digit: 
            year_value, month_value, town_value = get_column_value(str(year), month, town)
            matching_indices += get_indexes(year_value, month, town)
    
    if not matching_indices:
        return None, None
    return matching_indices, shared_scan(matching_indices)

# Zone Mapping
def zm_process_query(column_store, town, month, year_digit, zones):
    current_year = datetime.datetime.now().year
    matching_indices = []
    
    for year in range(2014, current_year + 1):
        if str(year)[-1] == year_digit:  
            if year <= 2023:
                new_indices = get_zone_indexes(str(year), month, town, zones)
            else:
                new_indices = get_indexes(str(year), month, town)
            matching_indices += new_indices
    
    if not matching_indices:
        return None, None
    return matching_indices, default_scan(matching_indices)

# Zone Mapping + Sorted Query
def zmss_process_query(column_store, town, month, year_digit, zones):
    current_year = datetime.datetime.now().year
    matching_indices = []
    
    for year in range(2014, current_year + 1):
        if str(year)[-1] == year_digit: 
            if year <= 2023:
                new_indices = get_zone_indexes(str(year), month, town, zones)
            else:
                new_indices = get_indexes(str(year), month, town)
            matching_indices += new_indices
    
    if not matching_indices:
        return None, None
    return matching_indices, shared_scan(matching_indices)

def default_scan(matching_indices):
    matching_price_data = []
    matching_area_data = []
    min_price = 9999999.9
    total_price = 0.0
    min_area = 9999999.9
    total_area = 0.0
    # Directory containing files
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')
    price_file_path = os.path.join(column_store_directory, "resale_price.store")
    area_file_path = os.path.join(column_store_directory, "floor_area_sqm.store")
    
    # Get price data using the previous indices
    if os.path.exists(price_file_path):
        with open(price_file_path, 'r') as price_file:
            price_data = price_file.readlines()
            index_length = len(matching_indices)
            for index in matching_indices:
                if index < len(price_data):
                    #Total
                    temp_price = float(price_data[index].strip())
                    total_price = total_price + temp_price
                    if temp_price < min_price:
                        min_price = temp_price
                    matching_price_data.append(float(price_data[index].strip()))
            for index in matching_indices:
                if index < len(price_data):
                    # Min
                    temp_price = float(price_data[index].strip())
                    if temp_price < min_price:
                        min_price = temp_price
            for index in matching_indices:
                if index < len(price_data):
                    #S.D
                    matching_price_data.append(float(price_data[index].strip()))
    else:
        print("Price File does not exist.")
        return None
    
    # Get area data using the previous indices
    if os.path.exists(area_file_path):
        with open(area_file_path, 'r') as area_file:
            area_data = area_file.readlines()
            index_length = len(matching_indices)
            for index in matching_indices:
                if index < len(area_data):
                    # Total
                    temp_area = float(area_data[index].strip())
                    total_area = total_area + temp_area
            for index in matching_indices:
                if index < len(area_data):
                    # Min
                    temp_area = float(area_data[index].strip())
                    if temp_area < min_area:
                        min_area = temp_area
            for index in matching_indices:
                if index < len(area_data):
                    # S.D
                    matching_area_data.append(float(area_data[index].strip()))
    else:
        print("Area File does not exist.")
        return None
    avg_price = round(total_price/index_length, 2)
    avg_area = round(total_area/index_length, 2)
    sd_price = round(statistics.stdev(matching_price_data),2)
    sd_area = round(statistics.stdev(matching_area_data),2)
    return [min_price, min_area, avg_price, avg_area, sd_price, sd_area]

def shared_scan(matching_indices):
    matching_price_data = []
    matching_area_data = []
    min_price = 9999999.9
    total_price = 0.0
    min_area = 9999999.9
    total_area = 0.0
    # Directory containing files
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')
    price_file_path = os.path.join(column_store_directory, "resale_price.store")
    area_file_path = os.path.join(column_store_directory, "floor_area_sqm.store")
    
    # Get price and area data using the previous indices
    # Shared Scan (Enhancement)
    if os.path.exists(price_file_path) and os.path.exists(area_file_path):
        with open(price_file_path, 'r') as price_file:
            price_data = price_file.readlines()
            with open(area_file_path, 'r') as area_file:
                area_data = area_file.readlines()
                index_length = len(matching_indices)
                for index in matching_indices:
                    if index < len(price_data):
                        # Min, Total, S.D
                        temp_price = float(price_data[index].strip())
                        total_price = total_price + temp_price
                        if temp_price < min_price:
                            min_price = temp_price
                        matching_price_data.append(float(price_data[index].strip()))
                        
                    if index < len(area_data):
                        # Min, Total, S.D
                        temp_area = float(area_data[index].strip())
                        total_area = total_area + temp_area
                        if temp_area < min_area:
                            min_area = temp_area
                        matching_area_data.append(float(area_data[index].strip()))
    else:
        print("Price or Area File does not exist.")
        return None
    avg_price = round(total_price/index_length, 2)
    avg_area = round(total_area/index_length, 2)
    sd_price = round(statistics.stdev(matching_price_data),2)
    sd_area = round(statistics.stdev(matching_area_data),2)
    return [min_price, min_area, avg_price, avg_area, sd_price, sd_area]

def get_indexes(year, month, town):
    year_query, month_query, town_query = get_column_value(year, month, town)
    time_query = year_query + "-" + month_query
    # Directory containing files
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')
    month_file_path = os.path.join(column_store_directory, 'month.store')
    town_file_path = os.path.join(column_store_directory, "town.store")
    # Get index of data which matches year_query
    if os.path.exists(month_file_path):
        # Open the file for reading
        with open(month_file_path, 'r') as file:
            # Read the contents of the file
            data = file.readlines()
            matching_indices = [index for index, line in enumerate(data) if time_query in line]
            
    else:
        print("Month File does not exist.")
    
    # Get index of data which matches town_query and previous indexes
    if os.path.exists(town_file_path):
        with open(town_file_path, 'r') as file:
            # Read the contents of the file
            town_data = file.readlines()
            matching_indices = [index for index in matching_indices if index < len(town_data) and town_query in town_data[index]]
            return matching_indices

    else:
        print("Town File does not exist.")
        
    return None

def get_zone_indexes(year, month, town, zones):
    matching_indices = []
    year_query, month_query, town_query = get_column_value(year, month, town)
    zones_year = zones.get(year_query)
    time_query = year_query + "-" + month_query
    # Directory containing files
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')
    month_file_path = os.path.join(column_store_directory, 'month.store')
    town_file_path = os.path.join(column_store_directory, "town.store")
    # Use Zones to get starting point
    if os.path.exists(month_file_path):
        # Open the file for reading
        with open(month_file_path, 'r') as file:
            # Read the contents of the file
            data = file.readlines()
            for i in range(zones_year['start'], zones_year['end'] + 1): 
                if time_query in data[i]:
                    matching_indices.append(i)
    else:
        print("Month File does not exist.")
    
    # Get index of data which matches town_query and previous indexes
    if os.path.exists(town_file_path):
        with open(town_file_path, 'r') as file:
            # Read the contents of the file
            town_data = file.readlines()
            matching_indices = [index for index in matching_indices if index < len(town_data) and town_query in town_data[index]]
            return matching_indices

    else:
        print("Town File does not exist.")
        
    return None