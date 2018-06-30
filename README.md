# OpenWeatherMapTests

This repository consists test automation framework for REST API and UI tests

As a object of testing used service: https://openweathermap.org

### Prerequisites

- Installed Java 8
- Installed Git
- Installed Lombok plugin in Intellij IDEA for building project in IDEA

### Technologies

- Java 8
- Gradle 4.4.1
- TestNG 6.10
- Selenide 4.7
- Rest-Assured 3.0.3
- Jackson 2.8.8
- Lombok 1.16.14
- Spring Boot 1.5.9.RELEASE
- Spring Web 4.3.8.RELEASE
- Mockito 1.6.4
- Yandex Allure 2.5
- Cucumber-jvm, cucumber-spring, cucumber-testng: 1.2.5

### Getting Started
Clone this repository via git command
```
$ git clone https://github.com/yurydiahiliev/OpenWeatherMapTests.git
```

### Running the tests
For running test suite use following commands with parameters:

1. Next command runs smoke tests with default HTTP client parameter (restassured)
```
$./gradlew clean smokeTest
```
or
```
$./gradlew clean smokeTest -PhttpClient=restassured
```
2. Also it is possible to indicate type of HTTP client as a system property
```
$./gradlew clean smokeTest -PhttpClient=resttemplate
```
3. If you want to run test with cucumber integration, execute Gradle task:

```
$./gradlew clean cucumber 
```
There are main entities with cucumber integration: 
```
/api/stepDefinitions/CucumberSteps.java
/tests/CucumberTest.java
/src/test/resources/features/current_weather_by_name.feature
```
### Test execution monitoring

During running tests may possible to monitoring test execution process. All tests have been executed on cluster with following  services on remote Linux machine http://35.229.91.161:8082/#/
* Docker
* Selenoid
* GGR
* GGR-UI

![alt text](https://i.imgur.com/Y4MzVYq.png)


### Code coverage

There is a simple Intellij IDEA code coverage report

![alt text](https://i.imgur.com/cthU2n3.png)

### Static analysis tools

As a static analysis tool used SonarLint. SonarLint is an IntelliJ IDEA plugin that provides on the fly feedback to developers on new bugs and quality issues injected into Java, JavaScript, PHP and Python code. SonarLint supports SonarSource code analyzers (SonarJava, SonarJS, SonarPHP and SonarPython) as well as custom rules that extend these code analyzers.

![alt text](https://i.imgur.com/BVGhTPz.png)

### Test Reporting
In project used Yandex Allure reporting. All reports are available in project directory:
```
$ cd /OpenWeatherMapTests/build/reports/allure-report
```
For generating report use Gradle task.
```
$./gradlew allureReport
```

![alt text](https://i.imgur.com/vxWoDEo.png)

Folder /target/cucumber presents small Cucumber feature report

![alt text](https://i.imgur.com/6Q9yeH6.png)

### Authors

* **Yurii Diahiliev** - *Initial work* -(https://github.com/yurydiahiliev)

See also the list of [contributors](https://github.com/yurydiahiliev/OpenWeatherMapTests/contributors) who participated in this project.
