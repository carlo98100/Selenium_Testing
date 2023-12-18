
Feature: Registration for Basketball Supporter
  Scenario Outline: Successfully register as a basketball supporter
    Given User is on the registration page
    When User provide users date of birth "<dateOfBirth>" and users firstname "<firstname>" and users lastname "<lastname>" and users email "<email>" and users password "<password>"
    And User clicks on the Confirm and join button
    Then User should be on the confirmation page
    Examples:
      | dateOfBirth | firstname | lastname | email | password |
      | 03/12/1988 | userFirstname | userLastname | user@email.com | Password123! |
      | 11/20/1992 | emily | miller | emily.miller@email.com | Secret456! |
      | 07/08/1985 | daniel | andersen | daniel.andersen@email.com | Secure789! |
      | 09/15/1990 | sophie | thompson | sophie.thompson@email.com | Passphrase987! |
      | 02/28/1983 | chris | parker | chris.parker@email.com | Hidden321! |

  Scenario: Register as a basketball supporter without lastname
    Given User is on the registration page
    When User provide users date of birth "02/28/1983" and users firstname "chris" and users email "chris.parker@email.com" and users password "Hidden321!" but not the lastname
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the lastname is required

  Scenario: Register as a basketball supporter with unmatched passwords
    Given User is on the registration page
    When User provide the users date of birth "02/28/1983" and the users firstname "chris" and the users lastname "parker" and the users email "chris.parker@email.com" and the users password "Hidden321!" and also confirms the password by providing the password "Hidden321!!!" again
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the passwords doesn't match

  Scenario: Register as a basketball supporter without accepting terms & conditions
    Given User is on the registration page
    When User provide users date of birth "02/28/1983" and users firstname "chris" and users lastname "parker" and users email "chris.parker@email.com" and users password "Hidden321!" but not the but don't accept the Terms & conditions
    And User clicks on the Confirm and join button
    Then User should get a error message telling that the user must accept the terms & conditions