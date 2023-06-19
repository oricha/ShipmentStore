Feature: Testing a Store REST API
  Users should be able to submit GET and POST requests to a web service, represented by WireMock

  Scenario: Filter Delivery by Date
    When users call the API shipment with a Date path parameter 2022-11-01
    Then the API returns a valid Delivery

  Scenario: Data retrieval from a web service
    When users want to filter Deliveries between a initial date 2022-11-01 and end Date 2022-12-01
    Then the API returns a valid Delivery