### Proto Sample
```proto
package com.example.proto;

//option java_package = "com.example.proto";
option java_outer_classname = "SampleProto";

message Person {
  required string name = 1;
  required int32 id = 2;
  optional string email = 3;

  repeated string numbers = 4;
}

message AddressBook {
  repeated Person people = 1;
}
```

---
### Protobuf Serialize by protoc

```bash
cd ./src/main/java

protoc -I=. --java_out=. .\com\example\proto\sample.proto
```
