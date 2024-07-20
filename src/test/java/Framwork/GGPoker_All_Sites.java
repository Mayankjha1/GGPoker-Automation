package Framwork;

import io.qameta.allure.Description;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.util.List;

public class GGPoker_All_Sites {

//    @DataProvider(name = "GGPoker URL's")
//    public Object[][] testDataExcelFile2() throws IOException {
//        String excelFileLocation = "src/test/resources/Searched_input_data.xlsx";
//        return ExcelUtils.getExcelData(excelFileLocation, "URL");
//    }

    //@Test(dataProvider = "GGPoker URL's")
    @Test
    @Description("This is for the L1")
    public void Test_Case() throws InterruptedException {

        // Capture System.out output
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream originalOut = System.out;
//        System.setOut(new PrintStream(baos));

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://help.ggpoker.ca");
        driver.manage().window().maximize();

        // Wait till page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

        // Action Class
        Actions ActionClass = new Actions(driver);

        // This is for L1 Click
        WebElement First_Element = driver.findElement(By.xpath("(//p[@class=\"Level1Class\"])[1]"));
        First_Element.click();

        // This is for level 1
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class=\"level-1\"]/span")));
        List<WebElement> Level_1 = driver.findElements(By.xpath("//li[@class=\"level-1\"]/span"));
        for (int i = 0; i < Level_1.size() ; i++) {
            // System.out.println("Hello");

            Level_1 = driver.findElements(By.xpath("//li[@class=\"level-1\"]/span"));
            // System.out.println("subcategory.get(i) : " + subcategory.get(i));
            WebElement elements = Level_1.get(i);
            System.out.println(i + 1 + " Clicked on lvl 1 " + elements.getText());
            String Expected_Result_1 = elements.getText();
            System.out.println(" Expected => " + Expected_Result_1);
            // Thread.sleep(2000);
            ActionClass.moveToElement(elements).build().perform();
            //captureScreenshot(driver, "Page Screenshot");
            ActionClass.moveToElement(elements).click().build().perform();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class=\"level_1_title\"]/p)[1]")));
            WebElement Actual_Result_1 = driver.findElement(By.xpath("(//div[@class=\"level_1_title\"]/p)[1]"));
            System.out.println(" Actual => " + Actual_Result_1.getText());
            Assert.assertEquals(Expected_Result_1, Actual_Result_1.getText());

        }
            //This is for L2
            // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"level-2\"]/li")));
            List<WebElement> level_2 = driver.findElements(By.xpath("//ul[@class=\"level-2\"]/li"));
            if (!level_2.isEmpty() && level_2.get(0).isDisplayed()) {
                for (int j = 0; j < level_2.size(); j++) {


                    level_2 = driver.findElements(By.xpath("//ul[@class=\"level-2\"]/li"));
                    // System.out.println("subcategory.get(i) : " + subcategory.get(i));
                    WebElement elements_2 = level_2.get(j);
                    System.out.println(j + 1 + " Clicked on lvl 2 " + elements_2.getText());
                    String Expected_Result_2 = elements_2.getText();
                    System.out.println(" Expected => " + Expected_Result_2);
                    // Thread.sleep(2000);
                    ActionClass.moveToElement(elements_2).build().perform();
                    //captureScreenshot(driver, "Page Screenshot");
                    ActionClass.moveToElement(elements_2).click().build().perform();

                    // actual = //div/p[@class="selectedCategoryLevel"]
                   // Thread.sleep(2000);
//                    WebElement L3_1 = driver.findElement(By.xpath("(//div[@class=\"level_1_title\"]/p)[2]"));
//                    WebElement L3_2 = driver.findElement(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]"));

                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]")));
                    WebElement Actual_Result_2 = driver.findElement(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]"));
                    System.out.println(" Actual => " + Actual_Result_2.getText());
                    Assert.assertEquals(Expected_Result_2, Actual_Result_2.getText());

                    // This is for Level 3
                    //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"level-3 display_block\"]/li")));
                    List<WebElement> level_3 = driver.findElements(By.xpath("//ul[@class=\"level-3 display_block\"]/li"));
                    if (!level_3.isEmpty() && level_3.get(0).isDisplayed()) {
                        for (int k = 0; k < level_3.size(); k++) {


                            level_3 = driver.findElements(By.xpath("//ul[@class=\"level-3 display_block\"]/li"));
                            // System.out.println("subcategory.get(i) : " + subcategory.get(i));
                            WebElement elements_3 = level_3.get(k);
                            System.out.println(k + 1 + " Clicked on lvl 3 " + elements_3.getText());
                            String Expected_Result_3 = elements_3.getText();
                            System.out.println(" Expected => " + Expected_Result_3);
                            // Thread.sleep(2000);
                            ActionClass.moveToElement(elements_3).build().perform();
                            //captureScreenshot(driver, "Page Screenshot");
                            ActionClass.moveToElement(elements_3).click().build().perform();

                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]")));
                            WebElement Actual_Result_3 = driver.findElement(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]"));
                            System.out.println(" Actual => " + Actual_Result_3.getText());
                            Assert.assertEquals(Expected_Result_3, Actual_Result_3.getText());
                        }
                    }
                }
            }



        // quit Driver
        Thread.sleep(3000);
        driver.quit();


    }
}
