[![Build Status](https://travis-ci.org/bspatafora/javaServer.svg?branch=master)](https://travis-ci.org/bspatafora/javaServer)

# javaServer

A basic HTTP server written in Java.

Includes `cobspec` package for passing 8th Light’s [Cob Spec][] HTTP server acceptance test suite.

## Requirements
  * JDK 8

## Running the server
  1. Build the JAR using `gradle build` from the `javaServer` root directory
  2. Start the server with `java -jar build/libs/javaServer.jar -p PORT -d PATH/TO/COB_SPEC/PUBLIC`
      * If you don’t specify a port or directory, port defaults to 5000 and directory to the relative path to the Cob Spec `public` directory, included at `javaServer/src/main/resources/cobspec/`

## Running the tests
  1. Run the tests using `gradle check` from the `javaServer` root directory

[Cob Spec]: https://github.com/8thlight/cob_spec