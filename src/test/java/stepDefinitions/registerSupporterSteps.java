package stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.registerSupporterPage;

import static java.lang.Thread.*;

public class registerSupporterSteps {

    WebDriver driver = null;

    registerSupporterPage page;

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        //System.getProperty("webdriver.chrome.driver", "");
        driver = new ChromeDriver();
        page = new registerSupporterPage(driver);

        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string}")
    public void iProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPassword(String dateOfBirth, String firstname, String lastname, String email, String password) {
        registerSupporterPage page = new registerSupporterPage(driver);
        page.registerValidSupporter(dateOfBirth, firstname, lastname, email, password);

    }

    @And("User clicks on the Confirm and join button")
    public void userClicksOnTheConfirmAndJoinButton() throws InterruptedException {
        page.ClickOnConfirmAndJoinButton();
    }

    @Then("I should be on the confirmation page")
    public void iShouldBeOnTheConfirmationPage() {
        boolean isLockerButtonDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/a")).isDisplayed();

        Assert.assertTrue(isLockerButtonDisplayed);
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my email {string} and my password {string} but not the lastname")
    public void iProvideMyDateOfBirthAndMyFirstnameAndMyEmailAndMyPasswordButNotTheLastname(String dateOfBirth, String firstname, String email, String password) {
        page.registerSupporterWithoutLastname(dateOfBirth, firstname, email, password);
    }

    @Then("User should get a error message telling that the lastname is required")
    public void userShouldGetAErrorMessageTellingThatTheLastnameIsRequired() {
        Assert.assertTrue(page.IsLastNameIsMissingErrorMessageDisplayed());
        Assert.assertEquals("Last Name is required", page.LastNameIsMissingErrorMessage());
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string} and also confirms the password by providing the password {string} again")
    public void iProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPasswordAndAlsoConfirmsThePasswordByProvidingThePasswordAgain(String dateOfBirth, String firstname, String lastname, String email, String password, String confirmPassword) {
        page.registerSupporterWithUnmatchedPasswords(dateOfBirth, firstname, lastname, email, password, confirmPassword);
    }

    @Then("User should get a error message telling that the passwords doesn't match")
    public void userShouldGetAErrorMessageTellingThatThePasswordsDoesntMatch() {
        Assert.assertTrue(page.IsUnmatchedPasswordsErrorMessageDisplayed());
        Assert.assertEquals("Password did not match", page.UnmatchedPasswordsErrorMessage());
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string} but not the but don't accept the Terms & conditions")
    public void iProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPasswordButNotTheButDontAcceptTheTermsConditions(String dateOfBirth, String firstname, String lastname, String email, String password) {
        page.registerSupporterWithoutAcceptTermsAndConditions(dateOfBirth, firstname, lastname, email, password);
    }

    @Then("User should get a error message telling that the user must accept the terms & conditions")
    public void userShouldGetAErrorMessageTellingThatTheUserMustAcceptTheTermsConditions() {
        Assert.assertTrue(page.IsTermsAndConditionsErrorMessageDisplayed());
        Assert.assertEquals("You must confirm that you have read and accepted our Terms and Conditions", page.TermsAndConditionsErrorMessage());
        driver.quit();
    }
}
