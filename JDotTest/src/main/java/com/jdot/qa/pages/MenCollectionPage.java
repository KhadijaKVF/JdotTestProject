package com.jdot.qa.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jdot.qa.base.TestBase;



@Listeners(CustomListener.class) // -- to check failed method from listener class

public class MenCollectionPage extends TestBase {
	
 
	public  MenCollectionPage() {
		
		super(); // -- calling super class
	
	}  
	
	//--- Initializing drivers
	
		@BeforeClass (alwaysRun = true)
		public void SetUp() throws Exception {
			
			
			//opening properties file to read locators path and testdata
			try 
			 {
				FileInputStream file = new FileInputStream("./src/main/resources/MenFestive.properties");
				
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
	
	public void TC_Site_01_01() throws InterruptedException {

		// ---- Getting Url and checking new arrivals option is clickable

		driver.get(prop.getProperty("url")); // opening J. site
		
		System.out.println("Landing to Junaid Jamshed Official Site Page");

		Thread.sleep(4000);

	}

	@SuppressWarnings("unused")
	@Test(groups="sanity" , priority = 2)
	public void TC_Festive_02_01() throws InterruptedException {
		
		Thread.sleep(4000);
		
		//--> Action event for MouseHover
		
		Actions action = new Actions(driver);
		
		action.moveToElement(driver.findElement(By.xpath(prop.getProperty("new_arrivals")))).build().perform();

		// --- Explicit Wait to get the visibility on mouseOver

		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("men_col"))));
		
		boolean isenabled = driver.findElement(By.xpath(prop.getProperty("men_col"))).isEnabled();

		// --- Men Collection is clickable or not
		
		if (isenabled) {
			System.out.println("Men Collection'2020  is enabled ");
		} else {
			System.out.println("Sorry Button is not clickable");
		}

		Thread.sleep(4000);

	}

	@SuppressWarnings("unused")
	@Test(groups="sanity" , priority = 3)
	public void TC_Festive_02_02() throws InterruptedException {

		// --- Navigating Men-Collection 20'20 Page

		// --- Explicit Wait to get the visibility of Men Collection
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement element2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("men_col"))));

		driver.findElement(By.xpath(prop.getProperty("men_col"))).click();
		
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("men_collection-url");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));
		System.out.println("Landing to Men Collection'2020 Page");

		Thread.sleep(4000);

	}
	
	@Test(groups="sanity" , priority = 4)
	public void TC_Festive_02_03() throws InterruptedException {

		// --- Getting All 1-15 Items visibility on Men Collection Page
		
		Thread.sleep(4000);

		driver.get(prop.getProperty("men_collection-url"));
		Thread.sleep(4000);

		List<WebElement> elements = driver.findElements(By.className("product-item-link"));

		for (int a = 1; a < elements.size(); a++) {

			Boolean result = driver
					.findElement(By.xpath("//div[@id=\"layer-product-list\"]/div[2]/ol/li[" + a + "]/div/div[2]/h2/a"))
					.isDisplayed();
			Thread.sleep(2000);

			// will check whether the product id is displayed or not
			if (result == true) {
				System.out.println("Test Case Passed:  Available Item no".concat(" ") + a + " is visible on the page");
			} else {
				System.out
						.println("Test Case Failed:  Available Item no".concat(" ") + a + "is not visible on the page");
			}

		}

	}

	@Test(groups="sanity" , priority = 5)
	public void TC_Festive_02_04() throws InterruptedException {

		Thread.sleep(4000);

		// --- Checking Stock ID & Stock price is displayed or not

		boolean isdisplayed1 = driver.findElement(By.xpath(prop.getProperty("stock_id"))).isDisplayed();
		boolean isdisplayed2 = driver.findElement(By.xpath(prop.getProperty("price"))).isDisplayed();

		if (isdisplayed1) {
			System.out.println("Stock ID is displayed ");
		} else {
			System.out.println("Stock ID is not displayed");
		}

		if (isdisplayed2) {
			System.out.println("Stock price is displayed ");
		} else {
			System.out.println("Stock price is not displayed");
		}
		Thread.sleep(5000);

	}

	@Test(groups="sanity" , priority = 6)
	public void TC_AddToBag_03_01() throws InterruptedException {
		Thread.sleep(4000);

		// --- Checking Add to Bag is available or not
		
		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("add_bag"))).isDisplayed();

		if (isavailable) {
			System.out.println("Add to Bag is displayed ");
		} else {
			System.out.println("Add to Bag  is not displayed");
		}

	}

	@Test(groups="sanity" , priority = 7)
	public void TC_AddToBag_03_02() throws InterruptedException {
		Thread.sleep(4000);

		// --- Checking Add to Bag is available or not
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("add_bag"))).isEnabled();

		if (isclickable) {
			System.out.println("Add to Bag is clickable ");
			driver.findElement(By.xpath(prop.getProperty("add_bag"))).click();
		} else {
			System.out.println("Add to Bag is not clickable");
		}

	}

	@Test(groups="sanity" , priority = 9)
	public void TC_AddToBag_03_03() throws InterruptedException {

		Thread.sleep(4000);

		// --- Navigating Information Page

		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("info_page");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));
		System.out.println("Landing to Information Page");
		Thread.sleep(5000);

	}

	@Test(groups="sanity" , priority = 10)
	public void TC_AddToBag_03_04_A() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Size is selected or not
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("medium"))).isEnabled();

		if (isclickable) {
			System.out.println("Medium size is clickable");
		} else {
			System.out.println("Medium size is not clickable");
		}

		Thread.sleep(2000);
	}

	@Test(groups="sanity" , priority = 11)
	public void TC_AddToBag_03_04_B() throws InterruptedException {

		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("medium"))).click();
		System.out.println("Medium size is Selected");

		// --- Checking Size is matched or not
		boolean ismatched = driver.findElement(By.xpath(prop.getProperty("medium-txt"))).isDisplayed();

		if (ismatched) {
			System.out.println("Medium size Text is viewed");
		} else {
			System.out.println("Medium size Text is not viewed");
		}

	}

	@Test(groups="sanity" , priority = 12)
	public void TC_AddToBag_03_05() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Add to Bag Button is clickable
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("add_to_bag"))).isEnabled();

		if (isclickable) {
			System.out.println("Add to bag Button is clickable");
		} else {
			System.out.println("Add to bag Button is not clickable");
		}

	}

	@Test(groups="sanity" , priority = 13)
	public void TC_AddToBag_03_06() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Add to Bag Button is clicked and item is added in the bag

		driver.findElement(By.xpath(prop.getProperty("add_to_bag"))).click();
		System.out.println("Add to Bag Button is clicked");

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("added_msg");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("added"))).getText();

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(2000);

	}

	@Test(groups="sanity" , priority = 14)
	public void TC_Cart_04_01() throws InterruptedException {

		Thread.sleep(2000);
		
		// --- Checking Add to Bag Button is clicked and item is added in the Cart popup

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("poup_text");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("cart_popup_id"))).getText();

		// <-- Assert Condition
		Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(groups="sanity" , priority = 15)
	public void TC_Cart_04_02() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Go To Checkout is available

		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("check_out"))).isDisplayed();

		if (isavailable) {
			System.out.println("Go To Checkout Button is available");
		} else {
			System.out.println("Go To Checkout Button is not available");
		}

	}

	@Test(groups="sanity" , priority = 16)
	public void TC_Cart_04_03() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking View and Edit option is available at the bottom of the pop up cart screen

		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("view_edit_cart"))).isDisplayed();

		if (isavailable) {
			System.out.println("View and Edit option is available");
		} else {
			System.out.println("View and Edit option is  not available");
		}

	}

	@Test(groups="sanity" , priority = 17)
	public void TC_Cart_04_04() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Go To Checkout option is clickable
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("check_out"))).isEnabled();

		if (isclickable) {
			System.out.println("Go To Checkout option is clickable");
		} else {
			System.out.println("Go To Checkout option is not clickable");
		}

	}

	@Test(groups="sanity" , priority = 18)
	public void TC_Cart_04_05_A() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Navigating Secure checkout page

		driver.findElement(By.xpath(prop.getProperty("check_out"))).click();
		System.out.println("Go To CheckOut");

		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("url_checkout");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));

	}

	@Test(groups="sanity" , priority = 19)
	public void TC_Cart_04_05_B() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Navigating Information Page and again adding product to bag to access view and edit cart Page

		driver.get(prop.getProperty("info_page"));
		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("Large"))).click();

		// --- Checking Size is matched or not
		
		boolean ismatched = driver.findElement(By.xpath(prop.getProperty("large_txt"))).isDisplayed();

		if (ismatched) {
			System.out.println("Large size is viewed");
		} else {
			System.out.println("Large size is not viewed");
		}

		// --- Adding to Bag
		
		driver.findElement(By.xpath(prop.getProperty("add_to_bag"))).click();

		Thread.sleep(3000);
	}

	@Test(groups="sanity" , priority = 20)
	public void TC_Cart_04_06() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking View and Edit option is clickable
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("view_edit_cart"))).isEnabled();

		if (isclickable) {
			System.out.println("View and Edit option is clickable");
		} else {
			System.out.println("View and Edit option is not clickable");
		}

	}

	@Test(groups="sanity" , priority = 21)
	public void TC_Cart_04_07() throws InterruptedException {

		Thread.sleep(4000);

		// --- Navigating Shopping Cart page
		
		driver.findElement(By.xpath(prop.getProperty("view_edit_cart"))).click();
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("url_viewEdit");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));

	}

	@Test(groups="sanity" , priority = 22)
	public void TC_MainCart_05_01() throws InterruptedException {

		Thread.sleep(4000);

		// --- Verify that after clicking View and Edit option, Shopping cart page open
		// successfully showing items, price, quantity and summary

		String actual_item, actualResult_price, actual_qty, actualResult_summary;
		String expected_item, expected_price, expected_qty, expected_summary;

		expected_item = prop.getProperty("item_name"); // expected item
		actual_item = driver.findElement(By.xpath("name_path")).getText();
		
		// <-- Assert Condition
		Assert.assertEquals(actual_item, expected_item);
		Thread.sleep(3000);

		expected_price = prop.getProperty("item_price"); // expected item
		actualResult_price = driver.findElement(By.xpath("price_path")).getText();
		
		// <-- Assert Condition
		Assert.assertEquals(actualResult_price, expected_price);
		Thread.sleep(3000);

		expected_qty = prop.getProperty("item_qty"); // expected item
		actual_qty = driver.findElement(By.xpath("qty_path")).getText();
		
		// <-- Assert Condition
		Assert.assertEquals(actual_qty, expected_qty);
		Thread.sleep(3000);

		expected_summary = prop.getProperty("item_summary"); // expected item
		actualResult_summary = driver.findElement(By.xpath("summary_path")).getText();
		
		// <-- Assert Condition
		Assert.assertEquals(actualResult_summary, expected_summary);
		Thread.sleep(3000);
	}

	@Test(groups="sanity" , priority = 23)
	public void TC_MainCart_05_02() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Edit Icon is available

		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("edit_icon"))).isDisplayed();

		if (isavailable) {
			System.out.println("Edit Icon is available");
		} else {
			System.out.println("Edit Icon is  not available");
		}

	}

	@Test(groups="sanity" , priority = 25)
	public void TC_MainCart_05_03() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Edit icon is clickable
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("edit_icon"))).isEnabled();

		if (isclickable) {
			System.out.println("Edit Icon is clickable");
		} else {
			System.out.println("Edit Icon is not clickable");
		}
		Thread.sleep(2000);

	}

	@Test(groups="sanity" , priority = 26)
	public void TC_MainCart_05_04() throws InterruptedException {

		Thread.sleep(4000);

		// --- Navigating Item update page
		
		driver.findElement(By.xpath(prop.getProperty("edit_icon"))).click();
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("url_item_update");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));

	}

	@Test(groups="sanity" , priority = 27)
	public void TC_MainCart_05_05() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Update cart is available or not
		
		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("update_cart"))).isDisplayed();

		if (isavailable) {
			System.out.println("Update cart is available");
		} else {
			System.out.println("Update cart is  not available");
		}

	}

	@Test(groups="sanity" , priority = 28)
	public void TC_MainCart_05_06() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Update cart is clickable
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("update_cart"))).isEnabled();

		if (isclickable) {
			System.out.println("Update cart is clickable");
		} else {
			System.out.println("Update cart is not clickable");
		}

		driver.findElement(By.xpath(prop.getProperty("update_cart"))).click();
		System.out.println("Update cart is clicked");
		
		// --- Checking Update cart is clicked and pop message appears

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("text");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("field_required"))).getText();

		// <-- Assert Condition
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		Thread.sleep(2000);

		// --- Reselecting Size and Quantity
		
		driver.findElement(By.xpath(prop.getProperty("X_Large_size"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("drop_down"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("qty_select"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("update_cart"))).click();
		System.out.println("Update cart is clicked");
		Thread.sleep(4000);

	}

	@Test(groups="sanity" , priority = 29)
	public void TC_MainCart_05_07() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Navigating to shopping cart page

		String ActualResult;
		String ExpectedResult;
		ExpectedResult = prop.getProperty("msg");
		ActualResult = driver.findElement(By.xpath(prop.getProperty("update_msg"))).getText();

		// <-- Assert Condition
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		Thread.sleep(2000);
	}

	@Test(groups="sanity" , priority = 30)
	public void TC_MainCart_05_08() throws InterruptedException {

		Thread.sleep(4000);

		// --- Checking Delete Icon is available or not
		
		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("Delete_icon"))).isDisplayed();

		if (isavailable) {
			System.out.println("Delete Icon is available");
		} else {
			System.out.println("Delete Icon is  not available");
		}

	}

	@Test(groups="sanity" , priority = 31)
	public void TC_MainCart_05_09() throws InterruptedException {

		Thread.sleep(4000);

		// --- Checking Delete Icon is clickable
		
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("Delete_icon"))).isEnabled();

		if (isclickable) {
			System.out.println("Delete Icon is clickable");
		} else {
			System.out.println("Delete Icon is not clickable");
		}
	}

	@Test(groups="sanity" , priority = 32)
	public void TC_MainCart_05_10() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Delete Icon is clicked

		driver.findElement(By.xpath(prop.getProperty("Delete_icon"))).click();
		System.out.println("Deleting Item from the Cart");

	}

	@Test(groups="sanity" , priority = 33)
	public void TC_MainCart_05_11() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Proceed To Checkout is available or not
		
		boolean isavailable = driver.findElement(By.xpath(prop.getProperty("proceed_checkout"))).isDisplayed();

		if (isavailable) {
			System.out.println("Proceed To Checkout is available");
		} else {
			System.out.println("Proceed To Checkout is not available");
		}
	}

	@Test(groups="sanity" , priority = 34)
	public void TC_MainCart_05_12() throws InterruptedException {

		Thread.sleep(4000);
		
		// --- Checking Proceed To Checkout is clickable
		boolean isclickable = driver.findElement(By.xpath(prop.getProperty("proceed_checkout"))).isEnabled();

		if (isclickable) {
			System.out.println("Proceed To Checkout is clickable");
		} else {
			System.out.println("Proceed To Checkout is not clickable");
		}

	}

	@Test(groups="sanity" , priority = 35)
	public void TC_MainCart_05_13() throws InterruptedException {

		// --- Proceed To Checkout is clicked
		
		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("proceed_checkout"))).click();
		System.out.println("Proceed To Checkout is clicked");
		Thread.sleep(4000);

		// --- Navigating Secure Checkout page
		String ExpectedResult, ActualResult;
		ExpectedResult = prop.getProperty("url_proceed");
		ActualResult = driver.getCurrentUrl();

		// <--Assert Conition
		Assert.assertEquals(ActualResult, ExpectedResult);
		Assert.assertTrue(ActualResult.contains(driver.getCurrentUrl()));
		Thread.sleep(4000);

	}
	
	@Test(groups="sanity" , priority = 36)
	public void TC_Recovery_15_09() throws InterruptedException, IOException {

		// ---- Disconnecting Internet & recover data/content after reconnecting with internet 
		// ---- Recovery Testing
		
		Thread.sleep(4000);
		driver.get(prop.getProperty("men_collection-url")); // navigating url
		Thread.sleep(5000);
		Runtime.getRuntime().exec("netsh wlan disconnect"); // disconnecting Internet
		Thread.sleep(3000);
		Runtime.getRuntime().exec("netsh wlan connect name = 'MAD_Max'");// Reconnecting Internet
		driver.navigate().refresh();
        driver.get(prop.getProperty("url")); // naviagting url
		driver.navigate().refresh();

	}
	
	
	
}
