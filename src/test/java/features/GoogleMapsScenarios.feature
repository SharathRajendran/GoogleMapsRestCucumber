Feature: TO test google maps APIs

  @AddPlace
  Scenario Outline: TO test add place API
    Given AddPlace Payload is used with "<name>" "<address>" "<website>"
    When User calls "AddPlaceAPI" with "post" HTTP Request
    Then The API call should be successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "name" in "GetPlaceAPI"

    Examples:
    |name|address|website|
    |Chennai|Drmurphy dossstreet|www.chennai.com|
  #  |Madurai|Maudrai dossstreet|www.madurai.com|

  @DeletePlace
  Scenario: To test delete place API

    Given DeletePlace API Payload is used
    When User calls "DeletePlaceAPI" with "post" HTTP Request
    Then The API call should be successful with status code 200
    And "status" in response body is "OK"