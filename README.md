# Test For BTN

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Intermezzo

**TestNG** is a testing framework inspired from JUnit and NUnit but introducing some new functionalities that make it more powerful and easier to use, such as:

* Annotations.
  ( Run your tests in arbitrarily big thread pools with various policies available (all methods in their own thread, one thread per test class, etc...).
  ( Test that your code is multithreading safe.
* Flexible test configuration.
* Support for data-driven testing (with @DataProvider).
* Support for parameters.
* Powerful execution model (no more TestSuite).
* Supported by a variety of tools and plug-ins (Eclipse, IDEA, Maven, etc...).
* Embeds BeanShell for further flexibility.
* Default JDK functions for runtime and logging (no dependencies).
* Dependent methods for application server testing.

TestNG is designed to cover all categories of tests:  unit, functional, end-to-end, integration, etc...

### Prerequisites

What things you need to install:

- [Java Development Kit 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html)

### Git Repository

The repository that is currently under development only exists in the development branch and to enter the master branch, you must first pass a code review

## How to Run the Project

* ### Running All Test Cases on Local Machine

  1. Open the project using any IDE (Eclipse, IDEA, etc)
  2. Download sources Maven and update the project
  3. Running the project using command `mvn clean test`
  4. If you want to run in headless mode, you can use this command `mvn clean test -Dheadless=true`
  

* ### Run Specific Test Case
  1. Choose any test case on folder `/src/test/java/tests`

