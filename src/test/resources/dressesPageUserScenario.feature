Feature: orange dress test

  Scenario: Check orange dress calculation in the cart
    Given "automationpractice.com" home page
    When user selected "Dress" section
    And selected "orange" color in the filter
    Then on the page appeared dresses with "orange" color
    When user opens any dress
    Then "orange" color is already selected
    When user adds 2 different dresses in the cart
    And opens cart
    Then "Total products" is calculated correctly
