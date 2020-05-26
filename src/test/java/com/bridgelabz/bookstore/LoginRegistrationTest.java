package com.bridgelabz.bookstore;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bridgelabz.bookstore.auth.Login;
import com.bridgelabz.bookstore.config.Configuration;

public class LoginRegistrationTest {

	private static WebDriver driver;

	@BeforeTest
	public static WebDriver openBrowser() throws Throwable {
		
		driver = Configuration.webConfig();
		driver.get("http://localhost:3000/login");

		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("Book Store", title);
		return driver;
		 
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
//	@Test(priority = 1)
//	public static void registration() throws Throwable {
//		Registration.registrationTest(driver);
//		Thread.sleep(4000);
//	}
	
	@Test(priority = 1)
	public static void login() throws Throwable {
		Login.loginTest(driver);
		Thread.sleep(4000);
	}
	
}
