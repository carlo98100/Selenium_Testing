package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.registerSupporterPage;

import static Extensions.Extensions.generateRandomString;

public class registerSupporterSteps {

    WebDriver driver;

    registerSupporterPage page;

    @Before
    public void Initialize()
    {
        String browserType = System.getProperty("browserType") != null ? System.getProperty("browserType") : "chrome";
        driver = InitializeWebDriver(browserType);
        page = new registerSupporterPage(driver);
    }

    @Given("I am on the registration page")
    public void IamOnTheRegistrationPage() {
    driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string}")
    public void IProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPassword(String dateOfBirth, String firstname, String lastname, String email, String password) {
        registerSupporterPage page = new registerSupporterPage(driver);

        firstname = generateRandomString(firstname, 6);
        lastname = generateRandomString(lastname, 6);
        email = generateRandomString(email, 6);
        password = generateRandomString(password, 6);

        page.RegisterValidSupporter(dateOfBirth, firstname, lastname, email, password);

    }

    @And("User clicks on the Confirm and join button")
    public void UserClicksOnTheConfirmAndJoinButton() throws InterruptedException {
        page.ClickOnConfirmAndJoinButton();
    }

    @Then("I should be on the confirmation page")
    public void IShouldBeOnTheConfirmationPage() {

        Assert.assertTrue(page.IsLockerButtonDisplayed());
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my email {string} and my password {string} but not the lastname")
    public void IProvideMyDateOfBirthAndMyFirstnameAndMyEmailAndMyPasswordButNotTheLastname(String dateOfBirth, String firstname, String email, String password) {
        page.RegisterSupporterWithoutLastname(dateOfBirth, firstname, email, password);
    }

    @Then("User should get a error message telling that the lastname is required")
    public void UserShouldGetAErrorMessageTellingThatTheLastnameIsRequired() {
        Assert.assertTrue(page.IsLastNameIsMissingErrorMessageDisplayed());
        Assert.assertEquals("Last Name is required", page.LastNameIsMissingErrorMessage());
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string} and also confirms the password by providing the password {string} again")
    public void IProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPasswordAndAlsoConfirmsThePasswordByProvidingThePasswordAgain(String dateOfBirth, String firstname, String lastname, String email, String password, String confirmPassword) {
        page.RegisterSupporterWithUnmatchedPasswords(dateOfBirth, firstname, lastname, email, password, confirmPassword);
    }

    @Then("User should get a error message telling that the passwords doesn't match")
    public void UserShouldGetAErrorMessageTellingThatThePasswordsDoesntMatch() {
        Assert.assertTrue(page.IsUnmatchedPasswordsErrorMessageDisplayed());
        Assert.assertEquals("Password did not match", page.UnmatchedPasswordsErrorMessage());
        driver.quit();
    }

    @When("I provide my date of birth {string} and my firstname {string} and my lastname {string} and my email {string} and my password {string} but not the but don't accept the Terms & conditions")
    public void IProvideMyDateOfBirthAndMyFirstnameAndMyLastnameAndMyEmailAndMyPasswordButNotTheButDontAcceptTheTermsConditions(String dateOfBirth, String firstname, String lastname, String email, String password) {
        page.RegisterSupporterWithoutAcceptTermsAndConditions(dateOfBirth, firstname, lastname, email, password);
    }

    @Then("User should get a error message telling that the user must accept the terms & conditions")
    public void UserShouldGetAErrorMessageTellingThatTheUserMustAcceptTheTermsConditions() {
        Assert.assertTrue(page.IsTermsAndConditionsErrorMessageDisplayed());
        Assert.assertEquals("You must confirm that you have read and accepted our Terms and Conditions", page.TermsAndConditionsErrorMessage());
        driver.quit();
    }

    public WebDriver InitializeWebDriver(String browserType) {
        WebDriver driver = null;
        switch (browserType)
        {
            case "chrome":
                WebDriverManager.chromedriver().config();
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless");

                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().config();

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                edgeOptions.addArguments("--headless");

                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().config();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        return driver;
    }
}