# DECIDE

This assignment is part of the course DD2480 Software Engineering Fundamentals and is an implementation of a Launch Interceptor Program, that determines whether a series of radar data points satisfy various “Logical Integration Criteria” (LIC) to authorize a launch. 

The system processes 15 distinct LICs based on geometric conditions of input coordinates, combines them using a Logical Connector Matrix (LCM) and evaluates them against a Preliminary Unlocking Vector (PUV) to generate a final "YES" or "NO" decision. 

## Dependencies

To build and run this software, you need the following versions installed:

    Java: JDK 24 or higher

    Maven: Version 3.9.0 or higher (for dependency management and building).

    JUnit: Version 5.8.1 (used for the automated test suite).

## How to run the program

The primary way to interact with it is through the test suite or the main entry point. ???The main deciding logic is implemented in the  We have configured the default value of the input parameters in the main function 

1. compile and build
```
mvn compile
```

2. run all tests
```
mvn test
```

3. run the main application
```
mvn exec:java -Dexec.mainClass="se.kth.dd2480.group15.Decide"
```

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
```
something something
```


## Statement of contributions

#### Rasmus Sjöberg (rassjo@kth.se)
* Created the GitHub organisation and repository, and created the project structure
* Implemented the code corresponding to the “global declarations” (Point, Parameters, Connectors, CompType, doubleCompare)
* Implement functionality, docs and tests for LIC2, 4, and 9
* One of the three “main” test cases for the decide function

#### Emma Tisell (etisell@kth.se)
* Implement getCircleRadius() in Utils.java for determining the smallest enclosing circle + corresponding tests
* Implement LIC1, 8, 13 + corresponding tests
* Corresponding documentation (JavaDoc)
* Code reviews + accepting pull requests

#### Chih-Yun Liu (cyliu4@kth.se):
* Implemented LIC 5, LIC11, evaluateLICs, evaluatePUM, evaluateFUV, and evaluateLAUNCH, and decide features
* Implemented corresponding unit tests
* Corresponding documentation (JavaDoc)
* One of three main test cases
* Code reviews + merging pull requests

#### Filippa Ciuk Olsson (fico@kth.se):
* Implementation of LIC0, LIC6, LIC7, LIC12 and their corresponding tests
* One of three main test cases for Decide function
* Corresponding documentation (JavaDoc)
* Managed merge conflicts
* Code reviews + merging pull requests

#### Jacob (jafr@kth.se):
* Implemented LIC3, LIC10, LIC14
* Implemented getTriangleArea method in Utils.java to handle geometric calculations
* Implemented corresponding unit tests and documentation (JavaDoc)
* Managed merge conflicts
