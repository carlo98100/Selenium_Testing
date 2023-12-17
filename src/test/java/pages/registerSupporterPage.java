package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class registerSupporterPage {

    //<editor-fold Props>
    WebDriver driver;
    By txt_dateOfBirth = By.xpath("//*[@id=\"dp\"]");
    By txt_firstname = By.xpath("//*[@id=\"member_firstname\"]");
    By txt_lastname = By.xpath("//*[@id=\"member_lastname\"]");
    By txt_email = By.xpath("//*[@id=\"member_emailaddress\"]");
    By txt_confirmEmail = By.xpath("//*[@id=\"member_confirmemailaddress\"]");
    By txt_password = By.xpath("//*[@id=\"signupunlicenced_password\"]");
    By txt_confirmPassword = By.xpath("//*[@id=\"signupunlicenced_confirmpassword\"]");

    By checkbox_fanRole = By.xpath("//*[@id=\"signup_form\"]/div[10]/div/div/div[4]/div/label");
    By checkbox_termsAndConditions = By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/label");
    By checkbox_ageVerifier = By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[2]/label");
    By checkbox_ethicsAndConduct = By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label");

    By span_lastnameErrorMessage = By.xpath("//*[@id=\"signup_form\"]/div[5]/div[2]/div/span/span");
    By span_unmatchedPasswordsErrorMessage = By.xpath("//*[@id=\"signup_form\"]/div[8]/div/div[2]/div[2]/div/span/span");
    By span_acceptTermsAndConditionsErrorMessage = By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/span/span");

    By button_yourLocker = By.xpath("/html/body/div/div[2]/div/div/div[2]/a");
    By button_ConfirmAndJoin = By.xpath("//*[@id=\"signup_form\"]/div[12]/input");
    //</editor-fold>
    public registerSupporterPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //<editor-fold Individual element method>
    public void EnterDateOfBirth(String dateOfBirth)
    {
        driver.findElement(txt_dateOfBirth).sendKeys(dateOfBirth);
    }
    public void EnterFirstname(String firstname)
    {
        driver.findElement(txt_firstname).sendKeys(firstname);
    }
    public void EnterLastname(String lastname)
    {
        driver.findElement(txt_lastname).sendKeys(lastname);
    }

    public void EnterEmail(String email)
    {
        driver.findElement(txt_email).sendKeys(email);
    }

    public void EnterConfirmationEmail(String email)
    {
        driver.findElement(txt_confirmEmail).sendKeys(email);
    }
    public void EnterPassword(String password)
    {
        driver.findElement(txt_password).sendKeys(password);
    }
    public void EnterConfirmationPassword(String password)
    {
        driver.findElement(txt_confirmPassword).sendKeys(password);
    }

    public void MarkTermsAndConditionsCheckbox()
    {
        driver.findElement(checkbox_termsAndConditions).click();
    }

    public void MarkAgeVerifierCheckbox()
    {
        driver.findElement(checkbox_ageVerifier).click();
    }
    public void MarkEticsAndConductCheckbox()
    {
        driver.findElement(checkbox_ethicsAndConduct).click();
    }
    public void MarkFanCheckbox()
    {
        driver.findElement(checkbox_fanRole).click();
    }

    public boolean IsLastNameIsMissingErrorMessageDisplayed()
    {
        return driver.findElement(span_lastnameErrorMessage).isDisplayed();
    }
    public boolean IsUnmatchedPasswordsErrorMessageDisplayed()
    {
        return driver.findElement(span_unmatchedPasswordsErrorMessage).isDisplayed();
    }
    public boolean IsTermsAndConditionsErrorMessageDisplayed()
    {
        return driver.findElement(span_acceptTermsAndConditionsErrorMessage).isDisplayed();
    }

    public String LastNameIsMissingErrorMessage()
    {
       return driver.findElement(span_lastnameErrorMessage).getText();
    }
    public String UnmatchedPasswordsErrorMessage()
    {
        return driver.findElement(span_unmatchedPasswordsErrorMessage).getText();
    }
    public String TermsAndConditionsErrorMessage()
    {
        return driver.findElement(span_acceptTermsAndConditionsErrorMessage).getText();
    }

    public boolean IsLockerButtonDisplayed()
    {
        WaitForElementToBeVisible(button_yourLocker, 10);
        return driver.findElement(button_yourLocker).isDisplayed();
    }

    public void ClickOnConfirmAndJoinButton(){driver.findElement(button_ConfirmAndJoin).click();}
    //</editor-fold>

    public void RegisterValidSupporter(String dateOfBirth, String firstname, String lastname, String email, String password)
    {
        driver.findElement(txt_dateOfBirth).sendKeys(dateOfBirth);
        driver.findElement(txt_firstname).sendKeys(firstname);
        driver.findElement(txt_lastname).sendKeys(lastname);
        driver.findElement(txt_email).sendKeys(email);
        driver.findElement(txt_confirmEmail).sendKeys(email);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(txt_confirmPassword).sendKeys(password);

        driver.findElement(checkbox_termsAndConditions).click();
        driver.findElement(checkbox_ageVerifier).click();
        driver.findElement(checkbox_ethicsAndConduct).click();
        driver.findElement(checkbox_fanRole).click();
    }

    public void RegisterSupporterWithoutLastname(String dateOfBirth, String firstname, String email, String password)
    {
        driver.findElement(txt_dateOfBirth).sendKeys(dateOfBirth);
        driver.findElement(txt_firstname).sendKeys(firstname);
        driver.findElement(txt_email).sendKeys(email);
        driver.findElement(txt_confirmEmail).sendKeys(email);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(txt_confirmPassword).sendKeys(password);

        driver.findElement(checkbox_termsAndConditions).click();
        driver.findElement(checkbox_ageVerifier).click();
        driver.findElement(checkbox_ethicsAndConduct).click();
        driver.findElement(checkbox_fanRole).click();
    }

    public void RegisterSupporterWithUnmatchedPasswords(String dateOfBirth, String firstname, String lastname, String email, String password, String confirmPassword)
    {
        driver.findElement(txt_dateOfBirth).sendKeys(dateOfBirth);
        driver.findElement(txt_firstname).sendKeys(firstname);
        driver.findElement(txt_lastname).sendKeys(lastname);
        driver.findElement(txt_email).sendKeys(email);
        driver.findElement(txt_confirmEmail).sendKeys(email);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(txt_confirmPassword).sendKeys(confirmPassword);
    }

    public void RegisterSupporterWithoutAcceptTermsAndConditions(String dateOfBirth, String firstname, String lastname, String email, String password)
    {
        driver.findElement(txt_dateOfBirth).sendKeys(dateOfBirth);
        driver.findElement(txt_firstname).sendKeys(firstname);
        driver.findElement(txt_email).sendKeys(email);
        driver.findElement(txt_confirmEmail).sendKeys(email);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(txt_confirmPassword).sendKeys(password);

        driver.findElement(checkbox_ageVerifier).click();
        driver.findElement(checkbox_ethicsAndConduct).click();
        driver.findElement(checkbox_fanRole).click();
    }

    private void WaitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
