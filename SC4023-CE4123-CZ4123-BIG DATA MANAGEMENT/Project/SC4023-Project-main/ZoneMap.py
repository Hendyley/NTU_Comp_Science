import os

def split_data_into_zones(data):
    zones = {}
    
    prev_end_index = None
    for line in data.split('\n'):
        if line[:4].isdigit():  # Check if the first 4 characters are numerical
            index = line.split('-')[0]
            if prev_end_index is None:
                current_zone_start = 1
            else:
                current_zone_start = prev_end_index + 1
            
            key = line[:4]
            if key not in zones:
                zones[key] = {'start': current_zone_start, 'end': None}
            zones[key]['end'] = current_zone_start  # Update the end index of the current zone
            prev_end_index = current_zone_start  # Update the end index for the next iteration

    return zones

def ZoneMapping():
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')
    month_file_path = os.path.join(column_store_directory, 'month.store')
    
    if os.path.exists(month_file_path):
        # Read the contents of the file
        with open(month_file_path, 'r') as file:
            file_data = file.read()
        
        # Split the data into zones and track start and end indexes
        zones = split_data_into_zones(file_data)
        
        return zones
    else:
        print("Month File does not exist.")


