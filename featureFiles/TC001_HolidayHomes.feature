Feature: Getting holiday homes from booking.com

  Scenario: Getting Vacation homes
    Given the booking.com website homepage
    When the location is given as Nairobi
    And dates are given five days from tomorrow
    And Setting the adult count to four
    Then the result with search criteria appears
    When the vacation homes is given as property type
    And sort the result with rating in descending order
    And checks and chooses the elevator availability
    Then the result is provided with given criteria