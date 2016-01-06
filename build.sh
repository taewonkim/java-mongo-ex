#!/bin/bash
javac -encoding utf8 -cp ./libs/bson-3.0.4.jar;./libs/mongodb-driver-3.0.4.jar;./libs/mongodb-driver-core-3.0.4.jar;. -s ./main -d ./build ./test/Unit1.java
java -cp ./libs/bson-3.0.4.jar;./libs/mongodb-driver-3.0.4.jar;./libs/mongodb-driver-core-3.0.4.jar;./build Unit1

