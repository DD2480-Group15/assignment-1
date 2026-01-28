# DECIDE

This assignment is part of the course DD2480 Software Engineering Fundamentals and is an implementation of a Launch Interceptor Program, that determines whether a series of radar data points satisfy various “Logical Integration Criteria” (LIC) to authorize a launch. 

The system processes 15 distinct LICs based on geometric conditions of input coordinates, combines them using a Logical Connector Matrix (LCM) and evaluates them against a Preliminary Unlocking Vector (PUV) to generate a final "YES" or "NO" decision. 

## Dependencies

To build and run this software, you need the following versions installed:

    Java: JDK 24 or higher? 

    Maven: Version 3.9.0 or higher? (for dependency management and building).

    JUnit: Version 5.8.1 (used for the automated test suite).

## How to run the program

The primary way to interact with it is through the test suite or the main entry point. ???The main deciding logic is implemented in the  We have configured the default value of the input parameters in the main function 

1. compile and build
mvn compile

2. run all tests
mvn test

3. run the main application
mvn exec:java -Dexec.mainClass="se.kth.dd2480.group15.Decide"

## How to generate documentation for the source code

#### 1. Compile

```
mvn compile
```

#### 2. Generate 

```
mvn javadoc:javadoc
```

#### 3. View


## Statement of contributions

* 
