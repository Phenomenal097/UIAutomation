# UIAutomation

UIAutomation is an end-to-end UI test automation framework built with **Playwright for Java** and **TestNG**. It follows the **Page Object Model (POM)** design pattern to keep tests clean, reusable, and easy to maintain.

## Tech Stack

- Java
- Playwright for Java
- TestNG
- Maven
- Allure Reports
- Docker
- Jenkins

## Features

- Page Object Model based test structure
- Cross-browser UI automation support
- TestNG based test execution
- Maven based dependency management
- Allure report support
- Docker and Jenkins ready setup

## Project Structure

```text
UIAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       ├── config/
│   │       ├── pages/
│   │       └── utils/
│   └── test/
│       └── java/
│           └── tests/
├── pom.xml
├── testng.xml
├── Dockerfile
├── Jenkinsfile
└── README.md
```

## Prerequisites

- Java 11 or higher
- Maven
- Git
- Allure CLI
- Docker

## Setup

Clone the repository:

```
git clone https://github.com/Phenomenal097/UIAutomation.git
cd UIAutomation
```

Install project dependencies:

```
mvn clean install
```

## Run Tests

Run all tests:

```
mvn test
```

Run tests using TestNG suite:

```
mvn test -DsuiteXmlFile=testng.xml
```

## Generate Allure Report

```
allure serve target/allure-results
```

## Docker

Build Docker image:

```
docker build -t ui-automation .
```

Run tests in Docker:

```
docker run --rm ui-automation
```

## Jenkins

This framework can be integrated with Jenkins for automated test execution as part of a CI/CD pipeline.

Typical Jenkins pipeline stages:

```text
Checkout Code
Install Dependencies
Run Tests
Generate Report
Publish Report
```

## Author

[Phenomenal097](https://github.com/Phenomenal097)
