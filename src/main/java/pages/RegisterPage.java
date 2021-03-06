package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public class RegisterPage {
    private WebDriver driver;
    // Locators to find the sign-up elements
    private final By firstNameField = By.xpath("//input[@name = 'firstname']");
    private final By lastNameField = By.xpath("//input[@name = 'lastname']");
    private final By mobileNumberField = By.xpath("//input[@name = 'phone']");
    private final By emailField = By.xpath("//input[@name = 'email']");
    private final By passwordField = By.xpath("//input[@name = 'password']");
    private final By confirmPasswordField = By.xpath("//input[@name = 'confirmpassword']");
    private final By signUpButton = By.className("signupbtn");
    private final By cookiesButton = By.className("cc-btn");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Hide CookiesBar")
    public void clickCookiesButtonIfExist() {
        WebElement cookiesBar = driver.findElement(cookiesButton);
        if (cookiesBar.isDisplayed()) {
            driver.findElement(cookiesButton).click();
        }
    }
    @Step("Set First Name")
    public boolean setFirstNameField(String firstName) {
        // Check for Empty or Null String
        if (firstName == null || firstName.length() == 0)
            return false;

        driver.findElement(firstNameField).sendKeys(firstName);

        // Check that first letter is capital
        return Character.isUpperCase(firstName.charAt(0));
    }
    @Step("Set Last Name")
    public boolean setLastNameField(String lastName) {
        // Check for Empty or Null String
        if (lastName == null || lastName.length() == 0)
            return false;

        driver.findElement(lastNameField).sendKeys(lastName);

        // Check that first letter is capital
        return Character.isUpperCase(lastName.charAt(0));
    }
    @Step("Set Mobile Number")
    public boolean setMobileNumberField(String mobile) {
        // Check for Empty or Null String
        if (mobile == null || mobile.length() == 0)
            return false;

        driver.findElement(mobileNumberField).sendKeys(mobile);

        // Check that it is a valid mobile number
        String regex = "^[+]*(?:[0-9] ?){6,}[0-9]$";
        return Pattern.matches(regex,mobile);
    }
    @Step("Set Email Address")
    public boolean setEmailField(String emailAddress) {
        // Check for Empty or Null String
        if (emailAddress == null || emailAddress.length() == 0)
            return false;
        driver.findElement(emailField).sendKeys(emailAddress);

        // Check that it is a valid email address using regex
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.matches(regex,emailAddress);
    }
    @Step("Set Password")
    public boolean setPasswordField(String password) {
        // Check for Empty or Null String
        if (password == null || password.length() == 0)
            return false;
        driver.findElement(passwordField).sendKeys(password);
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{1,8}$";
        return Pattern.matches(regex, password);
    }
    @Step("Set Confirmed Password")
    public boolean setConfirmPasswordField(String confirmPassword) {
        // Check for Empty or Null String
        if (confirmPassword == null || confirmPassword.length() == 0)
            return false;

        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);

        return true;
    }
    @Step("Sign Up Now!!")
    public AccountPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new AccountPage(driver);
    }

}
