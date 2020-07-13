package com.jdot.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jdot.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

// --- parent class

public class TestBase {

	static public WebDriver driver; // driver would be used in child classes
	static public Properties prop = new Properties(); // properties would be used in child classes
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	public TestBase() {

		try {

			// --- Excel File for Input Data

			File src = new File("./src/main/java/com/jdot/qa/base/LoginSignupTestData.xlsx");  //relative path of Excel file
			FileInputStream fis = new FileInputStream(src);

			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --- Initializing WebDriver

	@BeforeSuite (alwaysRun = true)
	public static void initialization() throws InterruptedException {

		// ----  reading the property of browser

		String browserName = "chrome"; // chrome

		if (browserName.equals("chrome")) { 
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();

			// ----Chrome 81 above TimeOut Silent property
			
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); //TimeOut 
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); // testUtil class =20
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS); // testUtil class =10

		// --- Navigating Junaid Jamshed Site
		
		driver.get("https://www.junaidjamshed.com/");

		Thread.sleep(5000);

	}

	@AfterSuite (alwaysRun = true)
	public static void tearDown() throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
	}

	public void failed(String testMethodname) {
		
		// --- Taking ScreenShot of failes test cases

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			
			// --- taking screenshot using FileUtils
			
			FileUtils.copyFile(src,
					new File("D:\\EClipse\\JDotTest\\screenshots\\" + "failshot_" + testMethodname + "_" + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
