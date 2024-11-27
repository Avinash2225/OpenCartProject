package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage  extends BasePage{

	public  MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")  // added in step no 6
	WebElement lnkLogout;
	public boolean isMyAccountPageExists() {
		try {
			return (msgHeading.isDisplayed());
		}
	catch (Exception e1) {
		return false;
	}
		
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	
	
	
	
}
