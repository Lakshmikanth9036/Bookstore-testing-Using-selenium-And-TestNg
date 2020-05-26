package com.bridgelabz.bookstore.auth;

import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.bridgelabz.bookstore.utility.ExtendsManager;


public class Login {
	
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports report;
	private static int count = 0;
	
	public static ExtentReports loginTest(WebDriver driver) throws Exception{
		
		htmlReporter = ExtendsManager.getReporter("login_report");
		report = new ExtentReports();
        report.attachReporter(htmlReporter);
		String workingDir = System.getProperty("user.dir");
		BufferedReader csvReader = new BufferedReader(new FileReader(workingDir + "\\datasource\\LoginCredentials.csv"));
		String row;
		
		while((row = csvReader.readLine()) != null){
			String[] credentials = row.split(",");
			count++;
			if(count == 1)
				test = report.createTest("Wrong Credential");
			else if(count == 2)
				test = report.createTest("Right Credential");
			test.info("Starting the test");
			test.info("Executing the asserstions");
			
			driver.findElement(By.id("emailAddress")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("emailAddress")).sendKeys(credentials[0]);
			test.pass("Entering the value of mobile or Email");
			Thread.sleep(500);
			
			driver.findElement(By.id("password")).clear();
			test.info("Clearing the previous field content");
			driver.findElement(By.id("password")).sendKeys(credentials[1]);
			test.pass("Entering the value of password");
			Thread.sleep(1000);
			boolean isEnabled = driver.findElement(By.cssSelector("#root > div > div > div.MuiPaper-root.MuiCard-root.Login_LoginCard__28114.MuiPaper-outlined.MuiPaper-rounded > div.Login_Form__2PdA9 > div.Login_Buttons__3ctUZ > button")).isEnabled();
			System.out.println(isEnabled);

			if(isEnabled){ 
				test.pass("Right credentials");
//				driver.findElement(By.cssSelector("#root > div > div > div.MuiPaper-root.MuiCard-root.Login_LoginCard__28114.MuiPaper-outlined.MuiPaper-rounded > div.Login_Form__2PdA9 > div.Login_Buttons__3ctUZ > button")).click();
			}
			else{
				test.fail("Wrong Credentials");
			}
			report.flush();
		}
		csvReader.close();
		return report;
	}
}
