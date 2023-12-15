Feature: Registration for Basketball Supporter
  #Scenario Outline: Successfully register as a basketball supporter
    #Given I am on the registration page
    #When I provide my date of birth "<dateOfBirth>" and my firstname "<firstname>" and my lastname "<lastname>" and my email "<email>" and my password "<password>"
    #And User clicks on the Confirm and join button
    #Then I should be on the confirmation page
    #Examples:
      #| dateOfBirth | firstname | lastname | email | password |
      #| 03/12/1988 | alex | johnson | alex.johnson@email.com | Password123! | (Used)
      #| 11/20/1992 | emily | miller | emily.miller@email.com | Secret456! | (Used)
      #| 07/08/1985 | daniel | andersen | daniel.andersen@email.com | Secure789! |
      #| 09/15/1990 | sophie | thompson | sophie.thompson@email.com | Passphrase987! |
      #| 02/28/1983 | chris | parker | chris.parker@email.com | Hidden321! |


  Scenario: Register as a basketball supporter without lastname
    Given I am on the registration page
    When I provide my date of birth "02/28/1983" and my firstname "chris" and my email "chris.parker@email.com" and my password "Hidden321!" but not the lastname
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the lastname is required

  Scenario: Register as a basketball supporter with unmatched passwords
    Given I am on the registration page
    When I provide my date of birth "02/28/1983" and my firstname "chris" and my lastname "parker" and my email "chris.parker@email.com" and my password "Hidden321!" and also confirms the password by providing the password "Hidden321!!!" again
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the passwords doesn't match

  Scenario: Register as a basketball supporter without accepting terms & conditions
    Given I am on the registration page
    When I provide my date of birth "02/28/1983" and my firstname "chris" and my lastname "parker" and my email "chris.parker@email.com" and my password "Hidden321!" but not the but don't accept the Terms & conditions
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the user must accept the terms & conditions