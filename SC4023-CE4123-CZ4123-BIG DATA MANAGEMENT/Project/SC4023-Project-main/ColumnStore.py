import os
import pandas as pd
import functools

class ColumnStore:
    def __init__(self, dataframe):
        """
        Initialize the ColumnStore object with the provided DataFrame and an empty cache.

        Args:
        - dataframe: pandas DataFrame containing the data to be stored and processed.
        """
        self.dataframe = dataframe
        self.cache = {}  # Initialize an empty cache to store column data

        # Define expected data types for each column
        self.expected_data_types = {
            'month': 'object',
            'town': 'object',
            'flat_type': 'object',
            'block': 'object',
            'street_name': 'object',
            'storey_range': 'object',
            'floor_area_sqm': 'float64',
            'flat_model': 'object',
            'lease_commence_date': 'int64',
            'resale_price': 'float64'
        }

    @functools.lru_cache(maxsize=None)  # Use caching for loading data
    def load_data_from_disk(self, file_path):
        """
        Load data from a CSV file on disk using caching to avoid redundant reads.

        Args:
        - file_path: Path to the CSV file containing the data.

        Returns:
        - DataFrame: pandas DataFrame containing the loaded data.
        """
        try:
            return pd.read_csv(file_path)
        except FileNotFoundError:
            print(f"Error: File '{file_path}' not found.")
            return pd.DataFrame()  # Return an empty DataFrame if file not found


    def save_to_files(self, directory):
        """
        Save each column of the DataFrame to separate .store files in the ./ColumnStore directory.

        Args:
        - directory: Path to the directory where the files will be saved.
        """
        if not os.path.exists(directory):
            os.makedirs(directory)

        for column_name in self.dataframe.columns:
            file_path = os.path.join(directory, f"{column_name}.store")
            try:
                # Replace empty qualified entries with "@#*NULL@#*"
                column_data = self.dataframe[column_name].fillna("@#*NULL@#*")
                column_data.to_csv(file_path, index=False)
                if (column_data == '@#*NULL@#*').any():  # Check if there are replaced values
                    print(f"Warning: Empty qualified entries detected in column '{column_name}' and replaced with '@#*NULL@#*'")

                # Check for data type mismatches before saving
                expected_dtype = self.expected_data_types.get(column_name)
                if expected_dtype:
                    actual_dtype = self.dataframe[column_name].dtype.name
                    if expected_dtype != actual_dtype and not ((expected_dtype == 'float64' and actual_dtype == 'int64') or (expected_dtype == 'int64' and actual_dtype == 'float64')):
                        print(f"Warning: Data type mismatch detected in column '{column_name}'. Expected data type: {expected_dtype}, Actual data type: {actual_dtype}")
                

            except Exception as e:
                print(f"Error saving column '{column_name}': {e}")

    def filter_by_column(self, column_name, predicate, position=None):
        """
        Filter the DataFrame by a specified column using the provided predicate function.

        Args:
        - column_name: Name of the column to filter.
        - predicate: Function used to filter values in the column.
        - position: Optional list of indices to filter the data further.

        Returns:
        - List: Indices of rows in the DataFrame that satisfy the filtering condition.
        """
        if column_name not in self.dataframe.columns:
            print(f"Column '{column_name}' not found.")
            return []

        if column_name in self.cache:  # Check if data is in cache
            values = self.cache[column_name]
        else:
            values = list(self.dataframe[column_name])
            self.cache[column_name] = values  # Store data in cache

        filtered_indices = [i for i, value in enumerate(values) if predicate(value)]
        if position is not None:
            filtered_indices = [idx for idx in filtered_indices if idx in position]
        return filtered_indices
    
    def get_filtered_values_df(self, column_name, condition):
        """
        Get a DataFrame containing filtered values from a specified column.

        Args:
        - column_name: Name of the column to filter.
        - condition: Function or condition to filter values in the column.

        Returns:
        - DataFrame: pandas DataFrame containing the filtered values.
        """
        filtered_indices = self.filter_by_column(column_name, condition)
        if not filtered_indices:
            return pd.DataFrame()  # Return an empty DataFrame if no matching values found

        filtered_values = [self.dataframe[column_name][i] for i in filtered_indices]
        return pd.DataFrame({column_name: filtered_values})
