# Log Parsing Project 

Simple log parsing project in Java 17 
that reads and processes some fixed example log data. 

Returns the top IP addresses visted, top URLs visited and number of unique IP addresses

### Pre-requisite
* JDK 17 or later 
* Gradle

### Setup
Clone the repository and navigate to the project directory: 

git clone https://github.com/dev-william-tan/logReader17. 

git cd logReader17

### Installation
`./gradlew clean build`

### Run Project
in Terminal run
`./gradlew run`

The appplication will prompt user to enter the size of the result needed

eg. Entering 3 will return 

* Top 3 IP address visted 
* Top 3 URLs visited 

### Run Tests
`./gradlew test`

Assumptions:
* Example logs are in Apache Log format and fixed
* This current iteration does not handle ties.

### Dependencies
* Java 17 
* Gradle 
* Log4j2 