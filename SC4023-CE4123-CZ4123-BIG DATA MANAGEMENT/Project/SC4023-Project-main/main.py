import pandas as pd
from ColumnStore import ColumnStore
from Output import *
import time
import Compression
import ZoneMap

print("The Program Start.")
# Load the CSV file into a pandas DataFrame
resale_data = pd.read_csv("Data/ResalePricesSingapore.csv")


start_time = time.time()
# Create a column-oriented store from the DataFrame
column_store = ColumnStore(resale_data)
# Save the columns to individual files
column_store.save_to_files("ColumnStore")
# Save the columns in compressed format
# Compression.compress_column_store()
# Split month.store into Zones
zones = ZoneMap.ZoneMapping()
end_time = time.time()
print("The program took",round((end_time-start_time),2),"seconds to store the data. \n")

# Check the dataframe based on position
def filter_dataframe_by_indexes(indexes):
    filtered_df = resale_data.iloc[indexes]
    return filtered_df

def Query(matric_id):

    print("Querying for ", matric_id)

    # Declare parameters
    year = matric_id[7]
    month = matric_id[6]
    town = matric_id[5]
    best_time,query_result = {},{}
    
    # Default
    start_time = time.time()
    pos,result_data = process_query(column_store, town, month, year)
    end_time = time.time()
    print("Default Query Result :")
    # print("Positions :",pos)
    # print(filter_dataframe_by_indexes(pos))
    print("min_price, min_area, avg_price, avg_area, sd_price, sd_area : ",result_data)
    best_time["Default"] = end_time-start_time
    query_result["Default"] = resale_data
    print("The program took",end_time-start_time,"seconds to query via default. \n")

    # Shared Scanning
    start_time = time.time()
    pos,result_data_ss = ss_process_query(column_store, town, month, year)
    end_time = time.time()
    print("Shared Scan Query Result :")
    # print("Positions :",pos)
    # print(filter_dataframe_by_indexes(pos))
    print("min_price, min_area, avg_price, avg_area, sd_price, sd_area : ",result_data_ss)
    best_time["Shared Scan"] = end_time-start_time
    query_result["Shared Scan"] = result_data_ss
    print("The program took",end_time-start_time,"seconds to query using Shared Scan. \n")

    # Zone Mapping
    start_time = time.time()
    pos,result_data_zm = zm_process_query(column_store, town, month, year, zones)
    end_time = time.time()
    print("ZoneMap Query Result :")
    # print("Positions :",pos)
    # print(filter_dataframe_by_indexes(pos))
    print("min_price, min_area, avg_price, avg_area, sd_price, sd_area : ",result_data_zm)
    best_time["ZoneMap"] = end_time-start_time
    query_result["ZoneMap"] = result_data_zm
    print("The program took",end_time-start_time,"seconds to query using Zone Mapping. \n")


    # Zone Mapping + Shared Scanning
    start_time = time.time()
    pos,result_data_zmss = zmss_process_query(column_store, town, month, year, zones)
    end_time = time.time()
    print("ZoneMap + Shared Scan Query Result :")
    # print("Positions :",pos)
    # print(filter_dataframe_by_indexes(pos))
    print("min_price, min_area, avg_price, avg_area, sd_price, sd_area : ",result_data_zmss)
    best_time["ZoneMap + Shared Scan"] = end_time-start_time
    query_result["ZoneMap + Shared Scan"] = result_data_zmss
    print("The program took",end_time-start_time,"seconds to query using Zone Mapping and Shared Scanning. \n")


    # print(best_time)
    # Find the key with the smallest value
    min_key = min(best_time, key=best_time.get)
    print("Best time : ",min_key, " : ",best_time[min_key])

    # Generate output file
    output_filename = "ScanResult_"+matric_id+".csv"
    result = query_result[min_key]
    Final_df = pd.DataFrame(columns=["Year","Month","Town","Category","Value"])
    Y,M,T = get_column_for_output(year,month,town)
    Final_df.loc[0] = [str(Y),str(M),str(T),"Minimum Price",str(result[0])]
    Final_df.loc[1] = [str(Y),str(M),str(T),"Minimum Area",str(result[1])]
    Final_df.loc[2] = [str(Y),str(M),str(T),"Average Price",str(result[2])]
    Final_df.loc[3] = [str(Y),str(M),str(T),"Average Area",str(result[3])]
    Final_df.loc[4] = [str(Y),str(M),str(T),"Standard Deviation of Price",str(result[4])]
    Final_df.loc[5] = [str(Y),str(M),str(T),"Standard Deviation of Area",str(result[5])]

    print("Statistical result : ")
    print(Final_df)

    Final_df.to_csv(os.path.join("Result", output_filename), index=False)
    print("\n")


# Process query
matric_id1 = "U2122287H"
Query(matric_id1)
matric_id2 = "U2122559J"
Query(matric_id2)
matric_id3 = "U2022363E"
Query(matric_id3)
# matric_id4 = "U2122214H"
# Query(matric_id4)
