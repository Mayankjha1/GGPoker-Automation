package Framwork;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;
import java.util.Date;

public class Allure_Helper_Class {


    @Step("Take screenshot and attach to Allure report")
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    // This is for Logs
    @Step("Capture and attach console logs to Allure report")
    public static void attachConsoleLogs(WebDriver driver, String logName) {
        try {
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            StringBuilder logs = new StringBuilder();

            for (LogEntry entry : logEntries) {
                logs.append(new Date(entry.getTimestamp())).append(" ")
                        .append(entry.getLevel()).append(" ")
                        .append(entry.getMessage()).append("\n");
            }

            String logContent = logs.toString();
            Allure.addAttachment(logName, "text/plain", logContent);
        } catch (Exception e) {
            System.out.println("Exception while capturing console logs: " + e.getMessage());
        }
    }

    // This is for Console outputs
    @Step("Attach console output to Allure report")
    public static void attachConsoleOutput(String consoleOutput, String logName) {
        try {
            Allure.addAttachment(logName, "text/plain", consoleOutput);
        } catch (Exception e) {
            System.out.println("Exception while attaching console output: " + e.getMessage());
        }
    }
}
