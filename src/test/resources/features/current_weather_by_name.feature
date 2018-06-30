@smokeTest
Feature: current weather by city name
  Scenario: user makes call to GET request by city name
    When the user calls current weather by London
    Then the user receives status code of 200
    And the user receives weather in country GB