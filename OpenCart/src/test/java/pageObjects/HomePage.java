package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage extends BasePage {
WebDriver driver;

public HomePage(WebDriver driver) {
	super(driver);
}

@FindBy(xpath= "//span[normalize-space()='My Account']")
WebElement linkMyaccount;


@FindBy(xpath= "//a[normalize-space()='Register']")
WebElement linkregistar;

@FindBy(linkText= "Login")
WebElement linklogin;

public void clickMyaccount() {
	linkMyaccount.click();
}

public void clickRegistar() {
	linkregistar.click();
}

public void clickLogin() {
	linklogin.click();
}


}