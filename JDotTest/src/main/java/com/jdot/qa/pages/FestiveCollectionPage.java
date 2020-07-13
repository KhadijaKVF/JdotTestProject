package com.jdot.qa.pages;

import java.io.IOException;

import org.testng.annotations.Test;

import com.jdot.qa.base.TestBase;

public class FestiveCollectionPage extends TestBase {

	@Test(priority = 1)
	public void TC_Recovery_15_09() throws InterruptedException, IOException {

		// ---- recover data/content after reconnecting with internet 
		// ---- Recovery Testing

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
