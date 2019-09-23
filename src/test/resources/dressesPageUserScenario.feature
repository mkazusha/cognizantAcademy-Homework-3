Feature: orange dress test

  Background: We opened homepage
    Given "automationpractice.com" homepage

  Scenario: Check orange dress calculation in the cart
    Given we select "DRESSES" section
    And we select "Orange" color in the filter
    Then on the page appeared dresses with orange color
    When we open any dress
    Then "Orange" color is already selected
    When we add 2 different dresses in the cart
    And we open cart
    Then products are calculated correctly
    Then we close browser

  Scenario: Check price range slider filter
    Given we select "DRESSES" section
    And we select "Orange" color in the filter
    Then on the page appeared dresses with orange color
    When we put price range between 18 and 50
    Then on page appeared dresses with price in selected range
    When we add one dress 2 times
    And we open cart
    Then in cart added 2 same dresses
    And products are calculated correctly
    When we delete 1 dress
    Then products are calculated correctly
    Then we close browser