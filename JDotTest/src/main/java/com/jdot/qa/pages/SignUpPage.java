package com.jdot.qa.pages;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.jdot.qa.base.TestBase;

@Listeners(CustomListener.class) // -- to check failed method from listener class

public class SignUpPage extends TestBase {
	
   
	public SignUpPage() {
		
		super(); // -- calling super class
	
	}  
	
	//--- Initializing drivers
	
		@BeforeClass (alwaysRun = true)
		public void SetUp() throws Exception {
			
			
			//opening properties file to read locators path and testdata
			try 
			 {
				FileInputStream file = new FileInputStream("./src/main/resources/signup.properties");
				
				 prop.load(file);
				 System.out.println("File Found");
				 
				
			 } 
			catch (Exception e) 
			 {
				System.out.println("File not Found");
			 }
			
			initialization();
			
		}
		
	@Test(groups="smoke" , priority = 1)
	public void TC_Signup_02_01() {

		driver.get(prop.getProperty("url")); // opening J. site

		boolean isenabled = driver.findElement(By.xpath(prop.getProperty("Signup_Btn"))).isEnabled(); // checking Signup

		if (isenabled) {
			System.out.println("Button is enabled ");
			driver.findElement(By.xpath(prop.getProperty("Signup_Btn"))).click();
		} else {
			System.out.println("Sorry Button is not clickable");
		}
	}

	@Test(groups="sanity" , priority = 2)
	public void TC_Signup_02_02() throws InterruptedException {
		Thread.sleep(3000);

		// driver.findElement(By.xpath(prop.getProperty("signup_btn"))).click();
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("signup_url");
		ActualResult = driver.getCurrentUrl();
		if (ActualResult.equals(ExpectedResult)) {
			System.out.println("TestCase Passed : Landing on Create Account Page");
		} else {
			System.out.println("TestCase Failed : Page Not Found");

		}

	}

	 @Test(groups="sanity" , priority= 3)
	public void TC_Signup_02_03() throws InterruptedException {

		Thread.sleep(2000);

		String Firstname = sheet.getRow(9).getCell(0).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("first_name"))).sendKeys(Firstname); // firstName
		System.out.println(Firstname);
		Thread.sleep(2000);

		String Lastname = sheet.getRow(9).getCell(1).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("last_name"))).sendKeys(Lastname); // LastName
		System.out.println(Lastname);
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("checkbox"))).click(); // checkBox
		Thread.sleep(2000);

		String Email = sheet.getRow(9).getCell(4).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(Email); // Email
		System.out.println(Email);
		Thread.sleep(2000);

		String Password = sheet.getRow(9).getCell(5).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("password_acc"))).sendKeys(Password); // Password
		System.out.println(Password);
		Thread.sleep(2000);

		String ConfirmPassword = sheet.getRow(9).getCell(6).getStringCellValue();
		driver.findElement(By.xpath(prop.getProperty("confirm_pass"))).sendKeys(ConfirmPassword); // ConfirmPassword
		System.out.println(ConfirmPassword);

		Thread.sleep(3000);
		driver.navigate().refresh(); // refresh
		Thread.sleep(5000);

	}

	@Test(groups="sanity" , priority= 4)
	public void TC_Signup_02_04() throws InterruptedException {

		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("first_name"))).sendKeys(""); // firstName
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("last_name"))).sendKeys(""); // LastName
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("checkbox"))).click(); // checkBox
		System.out.println("CheckBox is clicked");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("alternate_email"))).sendKeys(""); // alternateEmail
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("create_acc"))).click(); // btnClick
		Thread.sleep(2000);

		// driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(); // Email
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		// driver.findElement(By.xpath(prop.getProperty("password_acc"))).sendKeys(); //
		// Password
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		// driver.findElement(By.xpath(prop.getProperty("confirm_pass"))).sendKeys("");
		// // ConfirmPassword
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		// <---validation checks
		
		String actualResult_firstname, actualResult_lastname, actual_email, actualResult_password, actual_confirm,
				actual_captcha;
		String expectedResult;
		expectedResult = prop.getProperty("error_msg"); // expected message

		actualResult_firstname = driver.findElement(By.xpath("lbl_firstname")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actualResult_firstname, expectedResult);
		Thread.sleep(3000);

		actualResult_lastname = driver.findElement(By.xpath("lbl_lastname")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actualResult_lastname, expectedResult);
		Thread.sleep(3000);

		actual_email = driver.findElement(By.xpath("lbl_email")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actual_email, expectedResult);
		Thread.sleep(3000);

		actualResult_password = driver.findElement(By.xpath("password")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actualResult_password, expectedResult);
		Thread.sleep(3000);

		actual_confirm = driver.findElement(By.xpath("lbl_confirmation")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actual_confirm, expectedResult);
		Thread.sleep(3000);

		actual_captcha = driver.findElement(By.xpath("captcha")).getText();
		
		// <-- Assert Condition
		
		Assert.assertEquals(actual_captcha, expectedResult);
		Thread.sleep(3000);

		driver.navigate().refresh(); // refresh
		Thread.sleep(5000);

	}

	// --->> Data provider Implemetation
	
	@DataProvider(name = "TestData")
	public Object[][] getData() {
		// --- 2-Dinmensional Array for storing input data in row-column format

		Object[][] data = new Object[3][6]; // [row][col]

		// --1st Row
		data[0][0] = "Khadija"; // firstname
		data[0][1] = "Asif"; // lastname
		data[0][2] = "khadija.asif@kualitatem.com"; // alternate_email
		data[0][3] = "khadija.asif@kualitatem.com"; // email
		data[0][4] = "qwerty"; // pass
		data[0][5] = "qwerty"; // confirm

		// --2nd Row
		data[1][0] = "Amna"; // firstname
		data[1][1] = "Ali"; // lastname
		data[1][2] = "abc.com@gmail"; // alternate_email
		data[1][3] = "khadija.asif@kualitatem.com"; // email
		data[1][4] = "qwerty"; // pass
		data[1][5] = "qwerty"; // confirm

		// --3rd Row
		data[2][0] = "Momina"; // firstname
		data[2][1] = "Aslam"; // lastname
		data[2][2] = "moas@gmail.com"; // alternate_email
		data[2][3] = "khadija.asif@kualitatem.com"; // email
		data[2][4] = "qwerty"; // pass
		data[2][5] = "qwerty"; // confirm

		return data; // -- returns object type data
	}

	@Test(dataProvider = "TestData", groups = "sanity" , priority = 5)
	public void TC_Signup_02_05(String firstname, String lastname, String alternate_email, String email, String pass,
			String confirm) throws InterruptedException { // --- Taking data from DataProvider in TestData
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("first_name"))).clear();
		driver.findElement(By.xpath(prop.getProperty("first_name"))).sendKeys(firstname); // variable firstName
		System.out.println("FirstName is :" + firstname);
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("last_name"))).clear();
		driver.findElement(By.xpath(prop.getProperty("last_name"))).sendKeys(lastname); // variable lastName
		System.out.println("LastName is :" + lastname);
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("checkbox"))).click(); // checkBox
		System.out.println("CheckBox is clicked");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("alternate_email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("alternate_email"))).sendKeys(alternate_email); // alternate_email
																										
		System.out.println("Email is :" + alternate_email);
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email); // Email
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("password_acc"))).clear();
		driver.findElement(By.xpath(prop.getProperty("password_acc"))).sendKeys(pass); // Password
		System.out.println("This is a required Field");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("confirm_pass"))).clear();
		driver.findElement(By.xpath(prop.getProperty("confirm_pass"))).sendKeys(confirm); // ConfirmPassword
		System.out.println("This is a required Field");
		Thread.sleep(2000);

	}
}
