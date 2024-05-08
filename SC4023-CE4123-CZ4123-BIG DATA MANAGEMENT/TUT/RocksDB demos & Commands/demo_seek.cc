// Copyright (c) 2011-present, Facebook, Inc.  All rights reserved.
//  This source code is licensed under both the GPLv2 (found in the
//  COPYING file in the root directory) and Apache 2.0 License
//  (found in the LICENSE.Apache file in the root directory).

#include <cstdio>
#include <string>
#include <iostream>

#include "rocksdb/db.h"
#include "rocksdb/options.h"
#include "rocksdb/slice.h"

using ROCKSDB_NAMESPACE::DB;
using ROCKSDB_NAMESPACE::Options;
using ROCKSDB_NAMESPACE::PinnableSlice;
using ROCKSDB_NAMESPACE::ReadOptions;
using ROCKSDB_NAMESPACE::Status;
using ROCKSDB_NAMESPACE::WriteBatch;
using ROCKSDB_NAMESPACE::WriteOptions;

#if defined(OS_WIN)
std::string kDBPath = "C:\\Windows\\TEMP\\rocksdb_simple_example";
#else
std::string kDBPath = "/tmp/rocksdb_simple_example";
#endif


int main() {
  DB* db;
  Options options;
  // Set RocksDB option
  options.OptimizeLevelStyleCompaction();
  options.level_compaction_dynamic_level_bytes = false;
  // create the DB if it's not already present
  options.create_if_missing = true;

  // open DB
  Status s = DB::Open(options, kDBPath, &db);
  assert(s.ok());

  // Put key-value
  s = db->Put(rocksdb::WriteOptions(), "Key 3", "value 0003");
  s = db->Put(rocksdb::WriteOptions(), "Key 4", "value 0004");
  s = db->Put(rocksdb::WriteOptions(), "Key 5", "value 0005");
  s = db->Put(rocksdb::WriteOptions(), "Key 6", "value 0006");
  s = db->Put(rocksdb::WriteOptions(), "Key 7", "value 0007");
  s = db->Put(rocksdb::WriteOptions(), "Key 8", "value 0008");
  s = db->Put(rocksdb::WriteOptions(), "Key 9", "value 0009");

  // Read all k-v pairs
  rocksdb::Iterator* it = db->NewIterator(rocksdb::ReadOptions());

  std::cout << "\nRead all k-v pairs: " << std::endl;

  for (it->SeekToFirst(); it->Valid(); it->Next()) {
    std::cout << it->key().ToString() << ": " << it->value().ToString() << std::endl;
  }

  // Check for any errors found during the scan
  assert(it->status().ok());

  // Read all k-v pairs within a window
  std::string start = "Key 4";
  std::string limit = "Key 8";

  std::cout << "\nRead all k-v pairs within [Key 4, Key 8]: " << std::endl;

  for (it->Seek(start);
       it->Valid() && it->key().ToString() < limit;
       it->Next()) {
        std::cout << it->key().ToString() << ": " << it->value().ToString() << std::endl;
  }

  assert(it->status().ok());

  // Print all k-v pairs in reverse order
  std::cout << "\nPrint all k-v pairs in reverse order: " << std::endl;

  for (it->SeekToLast(); it->Valid(); it->Prev()) {
    std::cout << it->key().ToString() << ": " << it->value().ToString() << std::endl;
  }

  assert(it->status().ok());

  delete it;

  db->Close();
  delete db;

  return 0;
}
