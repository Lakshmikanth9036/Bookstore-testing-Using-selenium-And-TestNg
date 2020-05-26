package com.bridgelabz.bookstore.auth;

import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.bridgelabz.bookstore.utility.ExtendsManager;

public class Registration {
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports report;
	private static int count = 0;
	
	public static ExtentReports registrationTest(WebDriver driver) throws Exception{
		
		htmlReporter = ExtendsManager.getReporter("registration_report");
		report = new ExtentReports();
        report.attachReporter(htmlReporter);
		String workingDir = System.getProperty("user.dir");
		BufferedReader csvReader = new BufferedReader(new FileReader(workingDir + "\\datasource\\Registration.csv"));
		String row;
		
		while((row = csvReader.readLine()) != null){
			String[] values = row.split(",");
			count++;
			if(count == 1)
				test = report.createTest("Wrong value");
			else if(count == 2)
				test = report.createTest("Right value");
			System.out.println(values[0]+ " "+ values[1]);
			test.info("Starting the test");
			test.info("Executing the asserstions");
			
			driver.findElement(By.cssSelector("#firstName")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("firstName")).sendKeys(values[0]);
			test.pass("Entering the value of mobile or Email");
			Thread.sleep(1000);
			
			driver.findElement(By.id("lastName")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("lastName")).sendKeys(values[1]);
			test.pass("Entering the value of lastName");
			Thread.sleep(1000);
			
			driver.findElement(By.id("emailAddress")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("emailAddress")).sendKeys(values[2]);
			test.pass("Entering the value of email address");
			Thread.sleep(1000);
			
			driver.findElement(By.id("mobile")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("mobile")).sendKeys(values[3]);
			test.pass("Entering the value of mobile");
			Thread.sleep(1000);
			
			driver.findElement(By.id("password")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("password")).sendKeys(values[4]);
			test.pass("Entering the value of password");
			Thread.sleep(1000);
			
			boolean isEnabled = driver.findElement(By.id("signUp")).isEnabled();
			System.out.println(isEnabled);

			if(isEnabled) 
				test.fail("Wrong Credentials");
			else
				test.pass("Right credentials");
			report.flush();
		}
		csvReader.close();
		return report;
	}
}
