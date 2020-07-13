package com.jdot.qa.pages;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jdot.qa.base.TestBase;

@Listeners(CustomListener.class) // -- to check failed method from listener class

public class LoginPage extends TestBase {

	public LoginPage() {

		super();

	}

	// --- Initializing drivers

	@BeforeClass(alwaysRun = true)
	public void SetUp() throws Exception {

		// opening properties file to read locators path and testdata
		try {
			FileInputStream file = new FileInputStream("./src/main/resources/login.properties");

			prop.load(file);
			System.out.println("File Found");

		} catch (Exception e) {
			System.out.println("File not Found");
		}

		initialization();

	}

	@Test(groups = "smoke", priority = 1)
	public void TC_Login_01_01() {

		// ----Getting Url and checking signin option is clickable

		driver.get(prop.getProperty("url")); // opening J. site
		boolean isenabled = driver.findElement(By.xpath(prop.getProperty("signin"))).isEnabled(); // checking Signin btn
																									// clickable or not
		if (isenabled) {
			System.out.println("button is enabled ");
			driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		} else {
			System.out.println("Sorry Button is not clickable");
		}
	}

	@Test(groups = "sanity", priority = 2)

	public void TC_Login_01_02() {

		// --- Navigating signin Page

		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("login_url");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));
	}

	@Test(groups = "sanity", priority = 3)
	public void TC_Login_01_03() throws InterruptedException {

		// --- invalid email & password

		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
		String Username = sheet.getRow(1).getCell(0).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys(Username);
		System.out.println(Username);

		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
		double Password = sheet.getRow(1).getCell(1).getNumericCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys("Password");
		System.out.println(Password);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		Thread.sleep(2000);
		driver.navigate().refresh();

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("error_message");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("Errormessage_path"))).getText();

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);
		// Assert.assertTrue(ActualResult.contains(prop.getProperty("Errormessage_path")));
		Thread.sleep(3000);

		// --clear fields

		// --Refresh Page
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	@Test(groups = "sanity", priority = 4)
	public void TC_Login_01_04() throws InterruptedException {

		// --- Entered Username and empty password

		Thread.sleep(3000);

		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
		String Username = sheet.getRow(2).getCell(0).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys(Username);
		System.out.println(Username);

		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys("");
		System.out.println();
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		Thread.sleep(2000);

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("error_msg");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("message_pass"))).getText();

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);
		// Assert.assertTrue(ActualResult.contains(prop.getProperty("message_pass")));
		Thread.sleep(3000);

		// --clear fields
		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();

		// --Refresh Page
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	@Test(groups = "sanity", priority = 5)
	public void TC_Login_01_05() throws InterruptedException {

		// --- empty username and entered password

		Thread.sleep(3000);

		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys("");

		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
		double Password = sheet.getRow(3).getCell(1).getNumericCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys("Password");
		System.out.println(Password);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		Thread.sleep(2000);

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("error_msg");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("message_username"))).getText();

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);
		// Assert.assertTrue(ActualResult.contains(prop.getProperty("message_username")));
		Thread.sleep(3000);

		// --clear fields
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();

		// --Refresh Page
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	@Test(groups = "sanity", priority = 6)
	public void TC_Login_01_06() throws InterruptedException {

		// --- Entered valid Username and invalid password

		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
		String Username = sheet.getRow(4).getCell(0).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys(Username);
		System.out.println(Username);

		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
		double Password = sheet.getRow(4).getCell(1).getNumericCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys("Password");
		System.out.println(Password);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("error_message");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("Errormessage_path"))).getAttribute("InnerHTML");

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);
		// Assert.assertTrue(ActualResult.contains(prop.getProperty("Errormessage_path")));
		Thread.sleep(3000);

		// --clear fields

		// --Refresh Page
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	@Test(groups = "sanity", priority = 7)
	public void TC_Login_01_09() throws InterruptedException {

		// --- checking Forgot Password in new Tab Opened

		Thread.sleep(5000);

		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		driver.get("https://www.junaidjamshed.com/customer/account/forgotpassword/");
		// driver.get(prop.getProperty("forgot_pass"));
		driver.switchTo().window(tabs.get(0)); // switches to new tab
		driver.get("login_url");
		Thread.sleep(2000);

	}

	@Test(groups = "sanity", priority = 8)
	public void TC_Login_01_07() throws InterruptedException {

		// --- Valid Username and Valid password

		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
		String Username = sheet.getRow(5).getCell(0).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys(Username);
		System.out.println(Username);

		driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
		String Password = sheet.getRow(5).getCell(1).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys(Password);
		System.out.println(Password);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		Thread.sleep(3000);

	}

	@Test(groups = "sanity", priority = 9)
	public void TC_Login_01_10() throws InterruptedException {

		// <--Signout

		Thread.sleep(5000);
		// Type dropdown selection
		driver.findElement(By.xpath(prop.getProperty("drop_down"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("signout"))).click();

	}
	
@DataProvider 
public void getLoginData()
{
	



}	
	
	
	
	
	
	
	
	@Test
	public void LoginTest(String username , String password) throws InterruptedException 
	{
		// --- Valid Username and Valid password

				Thread.sleep(3000);
				driver.findElement(By.xpath(prop.getProperty("login_email"))).clear();
				driver.findElement(By.xpath(prop.getProperty("login_email"))).sendKeys(username);
				System.out.println(username);

				driver.findElement(By.xpath(prop.getProperty("login_pass"))).clear();
				driver.findElement(By.xpath(prop.getProperty("login_pass"))).sendKeys(password);
				System.out.println(password);
				
				
				driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
				Thread.sleep(3000);
		
	}
	}


