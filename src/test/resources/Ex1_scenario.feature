Feature: Ex1 of hw3

  Background:
    When I open SiteURL
    Then Home page is opened
    When I log in as "Roman" - "Jdi1234"
    Then I logged in as "ROMAN IOVLEV"

  Scenario: Assert Browser title
    Then Browser title is "Home Page"

  Scenario: Assert expected items on the header section
    Then There are expected items on the header section

  Scenario: Assert expected images on the Index Page
    Then There are 4 images on the Index Page
    And All images are displayed

  Scenario: Assert texts of the main headers
    Then Assert that there are 4 texts on the Index Page under icons
    And Assert a text of the main headers

  Scenario: Assert the iframe in the center of page
    Then IFrame is displayed
    When I switch to the iframe
    Then There is epam-logo
    And I switch to original window back

  Scenario: Assert the sub header
    Then The text of the sub header is displayed
    And The text of the sub header is "JDI GITHUB"
    And The sub header is a link
    And The URL is "https://github.com/epam/JDI"

  Scenario: Assert the Left Section and Footer
    Then There is a Left Section
    And There is a Footer