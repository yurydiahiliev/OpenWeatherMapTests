# OpenWeatherMapTests

This repository consists test automation framework for REST API and UI tests
As a object of testing used service: https://openweathermap.org

## Getting Started

```
$ git clone https://github.com/yurydiahiliev/OpenWeatherMapTests.git
```

### Prerequisites

- Installed Java 8
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

## Running the tests
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

### Test execution monitoring

During running tests may possible to monitoring test execution process. All tests have been executed on cluster with following  services on remote Linux machine http://35.229.91.161:8082/#/
* Docker
* Selenoid
* GGR
* GGR-UI

![alt text](https://i.imgur.com/Y4MzVYq.png)


## Code coverage

There is a simple Intellij IDEA code coverage report

![alt text](https://i.imgur.com/cthU2n3.png)

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
