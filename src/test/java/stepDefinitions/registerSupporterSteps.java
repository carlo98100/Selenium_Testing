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

    @Given("User is on the registration page")
    public void user_is_on_the_registration_page() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("User provide users date of birth {string} and users firstname {string} and users lastname {string} and users email {string} and users password {string}")
    public void user_Provide_the_users_dateOfBirth_and_the_users_firstname_and_the_users_lastname_and_the_users_email_and_the_users_password(String dateOfBirth, String firstname, String lastname, String email, String password) {
        registerSupporterPage page = new registerSupporterPage(driver);

        firstname = generateRandomString(firstname, 6);
        lastname = generateRandomString(lastname, 6);
        email = generateRandomString(email, 6);
        password = generateRandomString(password, 6);

        page.RegisterValidSupporter(dateOfBirth, firstname, lastname, email, password);

    }

    @And("User clicks on the Confirm and join button")
    public void user_clicks_on_the_Confirm_And_Join_Button() throws InterruptedException {
        page.ClickOnConfirmAndJoinButton();
    }

    @Then("User should be on the confirmation page")
    public void user_should_be_on_the_confirmation_page() {

        Assert.assertTrue(page.IsLockerButtonDisplayed());
        driver.quit();
    }

    @When("User provide users date of birth {string} and users firstname {string} and users email {string} and users password {string} but not the lastname")
    public void user_provide_the_users_date_of_birth_and_the_users_firstname_and_the_users_email_and_the_users_password_but_not_the_lastname(String dateOfBirth, String firstname, String email, String password) {
        page.RegisterSupporterWithoutLastname(dateOfBirth, firstname, email, password);
    }

    @Then("User should get a error message telling that the lastname is required")
    public void user_should_get_an_error_message_telling_that_the_lastname_is_required() {
        Assert.assertTrue(page.IsLastNameIsMissingErrorMessageDisplayed());
        Assert.assertEquals("Last Name is required", page.LastNameIsMissingErrorMessage());
        driver.quit();
    }

    @When("User provide the users date of birth {string} and the users firstname {string} and the users lastname {string} and the users email {string} and the users password {string} and also confirms the password by providing the password {string} again")
    public void user_provides_the_Users_dateOfBirth_and_the_users_firstname_and_the_users_lastname_and_the_users_email_and_the_users_password_and_also_confirms_the_password_by_providing_the_password_again(String dateOfBirth, String firstname, String lastname, String email, String password, String confirmPassword) {
        page.RegisterSupporterWithUnmatchedPasswords(dateOfBirth, firstname, lastname, email, password, confirmPassword);
    }

    @Then("User should get a error message telling that the passwords doesn't match")
    public void user_should_get_an_error_message_telling_that_the_passwords_doesnt_match() {
        Assert.assertTrue(page.IsUnmatchedPasswordsErrorMessageDisplayed());
        Assert.assertEquals("Password did not match", page.UnmatchedPasswordsErrorMessage());
        driver.quit();
    }

    @When("User provide users date of birth {string} and users firstname {string} and users lastname {string} and users email {string} and users password {string} but not the but don't accept the Terms & conditions")
    public void user_provides_the_users_dateOfBirth_and_the_users_firstname_and_the_users_lastname_and_the_users_email_and_theusers_password_but_not_the_but_dont_accept_the_terms_and_conditions(String dateOfBirth, String firstname, String lastname, String email, String password) {
        page.RegisterSupporterWithoutAcceptTermsAndConditions(dateOfBirth, firstname, lastname, email, password);
    }

    @Then("User should get a error message telling that the user must accept the terms & conditions")
    public void user_should_get_an_error_message_telling_that_the_user_must_accept_the_terms_and_conditions() {
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