import os
import zlib

def compress_file(file_path):
    with open(file_path, 'r') as file:
        data = file.read()
    compressed_data = zlib.compress(data.encode())
    return compressed_data

def write_compressed_data(compressed_data, compressed_file_path):
    with open(compressed_file_path, 'wb') as compressed_file:
        compressed_file.write(compressed_data)

def compress_column_store():
    # Directory containing files to compress
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')

    # Iterate over all files in the directory
    for filename in os.listdir(column_store_directory):
        if filename.endswith('.store'):  # Check if it's a file with the extension '.store'
            file_path = os.path.join(column_store_directory, filename)
            compressed_file_path = os.path.join(column_store_directory, filename.replace('.store', '_compressed.store'))
            
            # Compress the file
            compressed_data = compress_file(file_path)
            
            # Write compressed data to a new file
            write_compressed_data(compressed_data, compressed_file_path)
            
            print(f"Compressed data has been written to: {compressed_file_path}")


#decompress
def decompress_file(compressed_file_path):
    with open(compressed_file_path, 'rb') as compressed_file:
        compressed_data = compressed_file.read()
    decompressed_data = zlib.decompress(compressed_data).decode()
    return decompressed_data

def decompress_column_store():
    # Directory containing compressed files
    current_directory = os.path.dirname(__file__)
    column_store_directory = os.path.join(current_directory, 'ColumnStore')

    # Iterate over all compressed files in the directory
    for filename in os.listdir(column_store_directory):
        if filename.endswith('_compressed.store'):  # Check if it's a compressed file
            compressed_file_path = os.path.join(column_store_directory, filename)
            decompressed_file_path = os.path.join(column_store_directory, filename.replace('_compressed.store', '_decompressed.store'))
            
            # Decompress the file
            decompressed_data = decompress_file(compressed_file_path)
            
            # Write decompressed data to a new file
            with open(decompressed_file_path, 'w') as decompressed_file:
                decompressed_file.write(decompressed_data)
            
            print(f"Decompressed data has been written to: {decompressed_file_path}")

