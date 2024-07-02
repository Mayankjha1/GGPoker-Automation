package RedBus_Challenge;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static Framwork.Allure_Helper_Class.captureScreenshot;
// Here is the Challenge video link
// https://www.youtube.com/watch?v=wVLIdJNGU9c
public class RedBus_Challenge {
    @Test
    @Description("I have to print all the Hoildays + Weekend Dates")
    public void RedBus() throws InterruptedException {

        // Disable Notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // Start
        WebDriver driver = new ChromeDriver(options);
       // driver.addArguments("–disable-notifications");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();

        // wait for the element to load the search bar
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("autoSuggestContainer")));

        // Click on date
        driver.findElement(By.id("onwardCal")).click();


        // Define the target month and year
        String targetMonthYear = "Jan 2025";

        while (true) {
            // Get the current displayed month
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"])[2]")));
            WebElement currentMonthElement = driver.findElement(By.xpath("(//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"])[2]"));
            String currentMonthYear = currentMonthElement.getText();
            System.out.println("Current displayed month: " + currentMonthYear);

            // Check if the current month and year matches the target month and year
            if (currentMonthYear.contains(targetMonthYear)) {
                break;
            }

            // Click on Next Element
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"])[3]")));
            driver.findElement(By.xpath("(//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"])[3]")).click();
            Thread.sleep(2000);  // Wait for the calendar to update
        }



        // quit driver
        Thread.sleep(5000);
        driver.quit();

    }

}
