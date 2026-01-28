# DECIDE

This assignment is part of the course DD2480 Software Engineering Fundamentals and is an implementation of a Launch Interceptor Program that determines if a series of radar data points constitutes launching an interceptor based on a set of Launch Interceptor Conditions (LIC’s).

## Description

The Decide() function handles the main logic of deciding whether a launch signal should go through or not, based on whether the LICs are true for an input of up to 100 planar data points. The program processes 15 distinct LICs based on geometric conditions of input coordinates, in order to populate a Conditions Met Vector (CMV). The data is then filtered through a Logical Connector Matrix (LCM) and a Preliminary Unlocking Vector (PUV) to generate a Final Unlocking Vector (FUV). This corresponds to a final "YES" or "NO" launch signal. 


## Dependencies

To build and run this software, you need the following versions installed:

    Java: JDK 24 or higher

    Maven: Version 3.9.0 or higher (for dependency management and building).

    JUnit: Version 5.8.1 (used for the automated test suite).

## How to run the program

The primary way to interact with it is through the test suite or the main entry point.

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

#### Jacob Friedrich (jafr@kth.se):
* Implemented LIC3, LIC10, LIC14
* Implemented getTriangleArea method in Utils.java to handle geometric calculations
* Implemented corresponding unit tests and documentation (JavaDoc)
* Managed merge conflicts


## Ways-of-working

We believe that we are most of the way to being "In use", but still figuring out practices, tools and how to evaluate the use of these in order to best support the team. In order to move to the next step, we need to create templates, establish more systems such as establishing an order for doing PR and reviews, and integrate CI practices. We should also establish a procedure for feedback on our way of working. Once we do this we might even be able to reach "In Place".
