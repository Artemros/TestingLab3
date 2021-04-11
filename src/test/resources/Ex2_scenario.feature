Feature: Ex2 of hw3

  Background:
    When I open SiteURL
    Then Home page is opened
    When I log in as "Roman" - "Jdi1234"
    Then I logged in as "ROMAN IOVLEV"

  Scenario: Assert Browser title
    Then Browser title is "Home Page"

  Scenario: Assert "Service" subcategory in the header
    When I click on Service
    Then There are 9 elements with proper texts in the header

  Scenario: Assert "Service" subcategory in the left section
    When I click on Service in the left section
    Then There are 9 elements with proper texts in the left section

  Scenario: Assert interface of Different Elements page
    When I open Different Elements page
    Then There are 4 checkboxes and 4 radios and 2 buttons and 1 dropdown
    And There is left section
    And There is right section

    And I open select checkboxes
    And Checkboxes are selected
    And Log rows of checkboxes are displayed and correct

    And I select Selen
    And Log rows of radios are displayed and correct

    And I select Yellow
    And Log rows of dropdown are displayed and correct

    And I unselect checkboxes
    And Checkboxes not selected