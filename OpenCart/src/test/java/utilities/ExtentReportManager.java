package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentSparkReporter sparkReporter;
    private ExtentTest test;
    private String repName;

    public void onStart(ITestContext testContext) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String timeStamp = df.format(new Date());
        
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        String os = testContext.getCurrentXmlTest().getParameter("os");
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.FAIL, result.getThrowable());

        String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();

        try {
            File reportFile = new File("./reports/" + repName);
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

    //    sendEmailWithReport();
    }

  /*  private void sendEmailWithReport() {
        try {
            @SuppressWarnings("deprecation")
			URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + repName);

            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googleemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("avinashyadav3278@gmail.com", "password"));
            email.setSSLOnConnect(true);
            email.setFrom("avinashyadav3278@gmail.com");  // sender
            email.setSubject("Test Results");
            email.setHtmlMsg("Please find attached the test report.");
            email.addTo("avinashyadav3278@gmail.com");   // Receiver
            email.send(); // send the email
        } catch (Exception e) {
            e.printStackTrace();
        } */
    }

