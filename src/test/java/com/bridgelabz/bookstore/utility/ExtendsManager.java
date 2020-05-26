package com.bridgelabz.bookstore.utility;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendsManager {
	private static ExtentHtmlReporter extent;
	
    public synchronized static ExtentHtmlReporter getReporter(String reportName) {
        if (extent == null) {
        	//Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                extent = new ExtentHtmlReporter(workingDir + "\\ExtentReports\\"+ reportName+".html");
            }
        }
        return extent;
    }
}
