Feature: Cruise Booking 
  Scenario: About cruise ship
    Given the cruise.booking.com website homepage for cruise booking
    When I give cruise line as input and click VIEW CRUISES
    Then It gives the result of cruises
    When I click the link of the first cruise and click Ship details
    Then There is a detail about the ship

