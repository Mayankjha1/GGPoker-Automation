package Framwork;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Framwork.Allure_Helper_Class.captureScreenshot;

public class Article_Checker {

    // List of GGPoker URL's in excel

    @DataProvider(name = "GGPoker URL's")
    public Object[][] testDataExcelFile() throws IOException {
        String excelFileLocation = "src/test/resources/Searched_input_data.xlsx";
        return ExcelUtils.getExcelData(excelFileLocation, "Article");
    }

    @Test(dataProvider = "GGPoker URL's")
    public void Article_checker(String URL) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);

        driver.manage().window().maximize();

        // This is for Console Log Print Statement report on Allure
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            // Wait for the Element to load the Elements
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

            // This for Language Filters
            driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();
            List<WebElement> language_Button = driver.findElements(By.xpath("//div[@role=\"listbox\"]/lightning-base-combobox-item"));

            for (int Language_loop = 0; Language_loop < language_Button.size(); Language_loop++) {
                System.out.println("Language Size : " + language_Button.size());

                language_Button = driver.findElements(By.xpath("//div[@role=\"listbox\"]/lightning-base-combobox-item"));
                WebElement elements_Languages = language_Button.get(Language_loop);

                System.out.println("--------------------" + driver.getCurrentUrl() + "--------------------");
                System.out.println("--------------------" + elements_Languages.getText() + "--------------------");

                System.out.println("Language Name : " + elements_Languages.getText());

                language_Button.get(Language_loop).click();
                // Wait for 7 Second
                Thread.sleep(12000);
                captureScreenshot(driver, "Main Page Screenshot ");

                // Action Class
                Actions ActionClass = new Actions(driver);
                // Click on the First Link
                driver.findElement(By.xpath("(//div/p[@class=\"Level1Class\"])[1]")).click();
                //  System.out.println("Item Clicked");

                // Click on every item
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"level-2\"]/li")));
                captureScreenshot(driver, "Categorized Page Screenshot ");
                List<WebElement> subcategory = driver.findElements(By.xpath("//ul[@class=\"level-2\"]/li"));
                for (int i = 0; i < subcategory.size(); i++) {
                    // System.out.println("Inside for loop");
                    subcategory = driver.findElements(By.xpath("//ul[@class=\"level-2\"]/li"));
                    // System.out.println("subcategory.get(i) : " + subcategory.get(i));
                    WebElement elements = subcategory.get(i);
                    System.out.println("Level 2 : " + elements.getText());

                    ActionClass.moveToElement(elements).build().perform();
                    //captureScreenshot(driver, "Page Screenshot");
                    ActionClass.moveToElement(elements).click().build().perform();

                    // Wait for [1.9 Sec] the Articles to show up
                    Thread.sleep(3000);
                    //For Articles Found
                    List<WebElement> Articles_Level2 = driver.findElements(By.xpath("//div[@class=\"datatableforcategory\"]/div/div"));
                    if (!Articles_Level2.isEmpty() && Articles_Level2.get(0).isDisplayed()) {
                        // for (int article = 0; article < Articles_Level2.size(); article++) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"datatableforcategory\"]/div/div")));
                        //  WebElement Article_elements = Articles_Level2.get(i);
                        // System.out.println("Articles Name " + Article_elements.getText());
                        captureScreenshot(driver, elements.getText() + " --> Articles");
                        System.out.println("Articles Size " + Articles_Level2.size());
                        // Thread.sleep(1000);
                        // System.out.println("Articles Size " + Article_elements.getText());

                    } else {
                        captureScreenshot(driver, elements.getText() + " --> No Articles");
                        System.out.println("No Articles : " + elements.getText());
                    }


                    //  Thread.sleep(2000);


                    List<WebElement> subcategory_Level3 = driver.findElements(By.xpath("//ul[@class=\"level-3 display_block\"]/li"));
                    if (!subcategory_Level3.isEmpty() && subcategory_Level3.get(0).isDisplayed()) {

                        for (int j = 0; j < subcategory_Level3.size(); j++) {
                            // System.out.println("Inside for loop of J");
                            subcategory_Level3 = driver.findElements(By.xpath("//ul[@class=\"level-3 display_block\"]/li"));
                            // System.out.println("subcategory.get(i) : " + subcategory.get(i));
                            WebElement elements_Level3 = subcategory_Level3.get(j);
                            System.out.println("Level 3 : " + elements_Level3.getText());
                            // Taking Screenshot
                            //  captureScreenshot(driver, elements_Of_L1.getText());
                            // elements_Level3.click();

                            ActionClass.moveToElement(elements_Level3).build().perform();
                            //captureScreenshot(driver, "Page Screenshot");
                            ActionClass.moveToElement(elements_Level3).click().build().perform();

                            // Wait for [1.9 Sec] the Articles to show up
                            Thread.sleep(3000);
                            // For Articles Found
                            List<WebElement> Articles_Level3 = driver.findElements(By.xpath("//div[@class=\"datatableforcategory\"]/div/div"));
                            if (!Articles_Level3.isEmpty() && Articles_Level3.get(0).isDisplayed()) {
                                // for (int article = 0; article < Articles_Level2.size(); article++) {
                                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"datatableforcategory\"]/div/div")));
                                captureScreenshot(driver, elements_Level3.getText() + " -->  Articles");
                                //  WebElement Article_elements = Articles_Level2.get(i);
                                // System.out.println("Articles Name " + Article_elements.getText());

                                System.out.println("Articles Size " + Articles_Level3.size());
                                // Thread.sleep(1000);
                                // System.out.println("Articles Size " + Article_elements.getText());

                            } else {
                                captureScreenshot(driver, elements_Level3.getText() + " --> No Articles");
                                System.out.println("No Articles : " + Articles_Level3.size());
                            }
                        }

                    } else {
                        //System.out.println("Else Part");
                    }


                }
                //   break;
                driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();
            }




        } catch (Exception e) {
            System.out.println("\nException caught");
            StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(ste -> ste.getClassName().equals(this.getClass().getName())).collect(Collectors.toList()).get(0);

            System.out.println("Method Name : " + stackTraceElement.getMethodName());
            System.out.println("Line Number : " + stackTraceElement.getLineNumber());
            System.out.println("Class Name : " + stackTraceElement.getClassName());
            System.out.println("File Name : " + stackTraceElement.getFileName());

            System.out.println("Error Message : " + e.getMessage());
        }

        // This is for Logs in allure reports
        //attachConsoleLogs(driver, "ConsoleLogs");
        Allure_Helper_Class.attachConsoleLogs(driver, "ConsoleLogs");
        // This is for Console output in allure reports
        System.setOut(originalOut);
        String consoleOutput = baos.toString(StandardCharsets.UTF_8);
        Allure_Helper_Class.attachConsoleOutput(consoleOutput, "ConsoleOutput");

        // Driver Quit

        Thread.sleep(3000);
        driver.quit();
    }
}
