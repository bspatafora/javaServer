# JavaServer

A basic HTTP server written in Java.

Includes `cobspec` package for passing 8th Light’s [Cob Spec][] HTTP server acceptance test suite.

## Requirements
  * JDK 8
  * [IntelliJ IDEA][]
  * [Cob Spec][]

## Running the server
  1. Build a JAR using IntelliJ
      * Build > Build Artifacts... > Build
  2. Go to the `java_server` root directory, then use `java -jar ./out/artifacts/java_server_jar/java_server.jar -p 5000 -d path/to/cob_spec/public`

## Running the tests
  1. Select the “All” run/debug configuration
  2. Hit run

[Cob Spec]: https://github.com/8thlight/cob_spec
[IntelliJ IDEA]: https://www.jetbrains.com/idea/