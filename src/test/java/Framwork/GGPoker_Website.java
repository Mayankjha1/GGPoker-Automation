package Framwork;


import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Framwork.Allure_Helper_Class.captureScreenshot;


public class GGPoker_Website {

    // Excel Reader
    @DataProvider(name = "GGPoker URL's")
    public Object[][] testDataExcelFile2() throws IOException {
        String excelFileLocation = "src/test/resources/Searched_input_data.xlsx";
        return ExcelUtils.getExcelData(excelFileLocation, "Article");
    }

    @Test(dataProvider = "GGPoker URL's")
    @Description("This is for the L1")
    public void Test_Case_For_L1(String URL) throws InterruptedException {

        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        driver.manage().window().maximize();

        // Wait till page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));
        try {

            //   List<WebElement> lists = driver.findElements(By.xpath("//button[@type=\"button\"]"));
            // for (WebElement elements : lists) {
            //   if (elements.getText().equals("Portuguese")) {

            List<WebElement> List_Of_L1 = driver.findElements(By.xpath("//div/div/p[@class=\"Level1Class\"]"));


            for (int i = 0; i < List_Of_L1.size(); i++) {
                // Re-fetch the elements to avoid StaleElementReferenceException
                List_Of_L1 = driver.findElements(By.xpath("//div/div/p[@class='Level1Class']"));
                WebElement elements_Of_L1 = List_Of_L1.get(i);
                System.out.println(elements_Of_L1.getText());
                // Taking Screenshot
                captureScreenshot(driver, elements_Of_L1.getText());
                elements_Of_L1.click();
                //System.out.println("Clicked");

                // Wait for the page to load after click
                //Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"level_1_title\"]")));
                Thread.sleep(3000);
                // Going Back to the Main Page !!!
                captureScreenshot(driver, "Page Screenshot");
                driver.navigate().back();

                // Wait for the original elements to be visible again after navigating back
                //Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));
                // System.out.println("Navigated back");
            }

//                    } else {
//                        System.out.println("Some Other Language");
//                    }
            //   }
            // This is for Logs in allure reports
            //attachConsoleLogs(driver, "ConsoleLogs");
            Allure_Helper_Class.attachConsoleLogs(driver, "ConsoleLogs");
            // This is for Console output in allure reports
            System.setOut(originalOut);
            String consoleOutput = baos.toString(StandardCharsets.UTF_8);
            Allure_Helper_Class.attachConsoleOutput(consoleOutput, "ConsoleOutput");

        } catch (Exception e) {
            System.out.println("\nException caught");
            StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(ste -> ste.getClassName().equals(this.getClass().getName())).collect(Collectors.toList()).get(0);

            System.out.println("Method Name : " + stackTraceElement.getMethodName());
            System.out.println("Line Number : " + stackTraceElement.getLineNumber());
            System.out.println("Class Name : " + stackTraceElement.getClassName());
            System.out.println("File Name : " + stackTraceElement.getFileName());

            System.out.println("Error Message : " + e.getMessage());

        }
        Thread.sleep(5000);
        driver.quit();
    }

    ////div/div/p/span[@class="secondElement"]
    @Test(dataProvider = "GGPoker URL's")
    @Description("This is for L2")
    public void Test_Case_For_L2(String URL) throws InterruptedException {
        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        // Hovering Part
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        driver.manage().window().maximize();

        // Wait till page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

        try {
            // Locate all L1 elements
            List<WebElement> List_Of_L1 = driver.findElements(By.xpath("//div/div/p[@class='Level1Class']"));

            // Instantiate Actions class
            Actions actions = new Actions(driver);

            // Loop through each L1 element
            for (int i = 0; i < List_Of_L1.size(); i++) {
                // Re-fetch the elements to avoid StaleElementReferenceException
                List_Of_L1 = driver.findElements(By.xpath("//div/div/p[@class='Level1Class']"));
                WebElement mainMenu = List_Of_L1.get(i);
                // System.out.println("Inside loop of L1");

                // Hover over the L1 element
                actions.moveToElement(mainMenu).perform();
                captureScreenshot(driver, "Found");

                // Thread.sleep(2000);  // Wait for submenu to appear
                // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"level_1_title\"]")));

                // Locate all L2 elements under the current L1
                List<WebElement> List_Of_L2 = driver.findElements(By.xpath("//div/div/p/span[@class='secondElement']"));

                // Loop through each L2 element
                for (int j = 0; j < List_Of_L2.size(); j++) {
                    //System.out.println("Inside loop of L2");
                    // Re-fetch the L2 elements to avoid StaleElementReferenceException
                    List_Of_L2 = driver.findElements(By.xpath("//div/div/p/span[@class='secondElement']"));
                    WebElement subMenu = List_Of_L2.get(j);
                    System.out.println("subMenu " + List_Of_L2.get(j).getText());

                    // subMenu.click();

                    // Hover over the L2 element
                    captureScreenshot(driver, "Page Screenshot");
                    actions.moveToElement(subMenu).click().perform();
                    //System.out.println("Clicked on L2 element: " + subMenu.getText());

                    // Wait for the page to load after click
                    Thread.sleep(3000);
                    //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]")));


                    // Navigate back to the main page
                    captureScreenshot(driver, "Page Screenshot");
                    driver.navigate().back();
                    //  Thread.sleep(3000);

                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

                    //Again Hover and Click on the L2 Categories
                    List_Of_L1 = driver.findElements(By.xpath("//div/div/p[@class='Level1Class']"));
                    mainMenu = List_Of_L1.get(i);
                    actions.moveToElement(mainMenu).perform();

                }
            }
            // This is for Logs in allure reports
//attachConsoleLogs(driver, "ConsoleLogs");
            Allure_Helper_Class.attachConsoleLogs(driver, "ConsoleLogs");
// This is for Console output in allure reports
            System.setOut(originalOut);
            String consoleOutput = baos.toString(StandardCharsets.UTF_8);
            Allure_Helper_Class.attachConsoleOutput(consoleOutput, "ConsoleOutput");
        } catch (Exception e) {
            System.out.println("\nException caught");
            StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(ste -> ste.getClassName().equals(this.getClass().getName())).collect(Collectors.toList()).get(0);

            System.out.println("Method Name : " + stackTraceElement.getMethodName());
            System.out.println("Line Number : " + stackTraceElement.getLineNumber());
            System.out.println("Class Name : " + stackTraceElement.getClassName());
            System.out.println("File Name : " + stackTraceElement.getFileName());

            System.out.println("Error Message : " + e.getMessage());
        }

        // Quit Driver
        Thread.sleep(10000);
        driver.quit();

    }

    @Test(dataProvider = "GGPoker URL's")
    @Description("This is for Contact us")
    public void Test_Case_Contact_US(String URL) throws InterruptedException {

        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        driver.manage().window().maximize();

        // Wait till page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));
        try {

            captureScreenshot(driver, "Page Screenshot");

            driver.findElement(By.xpath("//button[@c-chatcustominterface_chatcustominterface]")).click();

            driver.findElement(By.xpath("//div[@class=\"footer\"]/div[@c-chatcustominterface_chatcustominterface]")).click();
            captureScreenshot(driver, "Page Screenshot");
            Thread.sleep(1000);
            captureScreenshot(driver, "Page Screenshot");
//        String ActualTitle = driver.findElement(By.xpath("//div[@data-key=\"warning\"]")).getText();
//        Toast Warning
            // //span[@class='toastMessage forceActionsText']
            String ActualTitle = driver.findElement(By.xpath("//span[@class='toastMessage forceActionsText']")).getText();
            System.out.println("ActualTitle ->> " + ActualTitle);
            String ExpectedTitle = "Por favor, preencha todos os campos obrigatórios";
            //Assert.assertEquals(ActualTitle, ExpectedTitle);
            org.testng.Assert.assertEquals(ActualTitle, ExpectedTitle);
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
        // Quit Driver
        Thread.sleep(10000);
        driver.quit();


    }

    @Test(dataProvider = "GGPoker URL's")
    @Description("This is for Contact us List of L1 and L2 and L3")
    public void List_of_options(String URL) throws InterruptedException {

        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        driver.manage().window().maximize();

        // Wait till page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

        // This is for L1
        WebElement Contact_us_Box = driver.findElement(By.xpath("//button[@c-chatcustominterface_chatcustominterface]"));
        Contact_us_Box.click();

        // WebElement ddown = driver.findElement(By.xpath("//*[text()=\"Select a case type\"]"));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));
        WebElement ddown = driver.findElement(By.xpath("(//button[@role=\"combobox\"])[2]"));
        ddown.click();
        captureScreenshot(driver, "Page Screenshot");
        // Create an Actions object to perform the click
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role=\"listbox\"]/lightning-base-combobox-item")));
        List<WebElement> lists = driver.findElements(By.xpath("//div[@role=\"listbox\"]/lightning-base-combobox-item"));

        System.out.println("Size " + lists.size());
        try {
            for (int i = 0; i < lists.size(); i++) {
                //driver.findElement(By.xpath("//*[text()=\"Select a case type\"]")).click();
                // Re-fetch the list elements inside the loop
                lists = driver.findElements(By.xpath("//div[@role=\"listbox\"]/lightning-base-combobox-item"));
                WebElement mainMenu = lists.get(i);

                // Print the text of the current element
                captureScreenshot(driver, "Page Screenshot");
                System.out.println("Clicking on Level 1 : " + mainMenu.getText());


                actions.moveToElement(mainMenu).click().perform();
                captureScreenshot(driver, "Page Screenshot");
                //Thread.sleep(3000);


                // This is for L2

                //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@name=\"Cat2\"]")));
                List<WebElement> level2Elements = driver.findElements(By.xpath("//button[@name=\"Cat2\"]"));
                if (!level2Elements.isEmpty() && level2Elements.get(0).isDisplayed()) {
                    // Create an Actions object to perform the click
                    Actions actions_L2_on_Contact_us = new Actions(driver);
                    // WebElement level2Element = driver.findElement(By.xpath("//*[text()=\"Select level 2\"]"));
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@name=\"Cat2\"]")));
                    WebElement level2Element = driver.findElement(By.xpath("//button[@name=\"Cat2\"]"));
                    //WebElement level2Element = driver.findElement(By.xpath("//div[@id=\"dropdown-element-135\"]"));
                    captureScreenshot(driver, "Page Screenshot");
                    System.out.println("Found Level 2");
                    //level2Element.click();

                    actions_L2_on_Contact_us.moveToElement(level2Element).build().perform();
                    captureScreenshot(driver, "Page Screenshot");
                    actions_L2_on_Contact_us.moveToElement(level2Element).click().build().perform();
                    captureScreenshot(driver, "Page Screenshot");
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div/div/div[@role=\"listbox\"])[3]/lightning-base-combobox-item")));
                    List<WebElement> List_Of_L2_Items = driver.findElements(By.xpath("(//div/div/div[@role=\"listbox\"])[3]/lightning-base-combobox-item"));
                    System.out.println("Size of L2 : " + List_Of_L2_Items.size());
                    for (int j = 0; j < List_Of_L2_Items.size(); j++) {
                        // System.out.println("List_Of_L2_Items.size() " + List_Of_L2_Items.size());
                        //System.out.println("J value : " + j);
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div/div/div[@role=\"listbox\"])[3]/lightning-base-combobox-item")));
                        List_Of_L2_Items = driver.findElements(By.xpath("(//div/div/div[@role=\"listbox\"])[3]/lightning-base-combobox-item"));
                        // System.out.println("One");
                        WebElement mainMenu_L2 = List_Of_L2_Items.get(j);
                        // System.out.println("Two");
                        System.out.println("List_Of_L2_Items.get(j); " + List_Of_L2_Items.get(j).getText());

                        // Print the text of the current element
                        actions_L2_on_Contact_us.moveToElement(mainMenu_L2).build().perform();
                        captureScreenshot(driver, "Page Screenshot");
                        actions_L2_on_Contact_us.moveToElement(mainMenu_L2).click().build().perform();
                        captureScreenshot(driver, "Page Screenshot");

                        //Open L2 Box
                        actions_L2_on_Contact_us.moveToElement(level2Element).build().perform();
                        captureScreenshot(driver, "Page Screenshot");
                        actions_L2_on_Contact_us.moveToElement(level2Element).click().build().perform();
                        captureScreenshot(driver, "Page Screenshot");


                        //  This is for L3
                        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@name=\"Cat3\"]")));
                        List<WebElement> level3Elements = driver.findElements(By.xpath("//button[@name=\"Cat3\"]"));
                        // System.out.println("level3Elements : " + level3Elements.size());
                        //           if (driver.findElement(By.xpath("//button[@name=\"Cat2\"]")).isDisplayed()){
                        if (!level3Elements.isEmpty() && level3Elements.get(0).isDisplayed()) {
                            // Create an Actions object to perform the click
                            Actions actions_L3_on_Contact_us = new Actions(driver);
                            // System.out.println("Found Level 3");
                            captureScreenshot(driver, "Page Screenshot");
                            actions_L2_on_Contact_us.moveToElement(level2Element).click().perform();
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@name=\"Cat3\"]")));
                            WebElement level3Element = driver.findElement(By.xpath("//button[@name=\"Cat3\"]"));
                            // level3Element.click();
                            actions_L3_on_Contact_us.moveToElement(level3Element).build().perform();
                            captureScreenshot(driver, "Page Screenshot");
                            actions_L3_on_Contact_us.moveToElement(level3Element).click().build().perform();
                            captureScreenshot(driver, "Page Screenshot");


                            System.out.println("level3Element Clicked");
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div/div/div[@role=\"listbox\"])[4]/lightning-base-combobox-item")));
                            List<WebElement> List_Of_L3_Items = driver.findElements(By.xpath("(//div/div/div[@role=\"listbox\"])[4]/lightning-base-combobox-item"));
                            //System.out.println("Size of L3 : " + List_Of_L3_Items.size());

                            for (int k = 0; k < List_Of_L3_Items.size(); k++) {
                                System.out.println("Inside for Loop Of L3");
                                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div/div/div[@role=\"listbox\"])[4]/lightning-base-combobox-item")));
                                captureScreenshot(driver, "Page Screenshot");
                                List_Of_L3_Items = driver.findElements(By.xpath("(//div/div/div[@role=\"listbox\"])[4]/lightning-base-combobox-item"));

                                WebElement mainMenu_L3 = List_Of_L3_Items.get(k);
                                //  System.out.println("Five");
                                System.out.println("List_Of_L3_Items.get(k); " + List_Of_L3_Items.get(k).getText());
                                captureScreenshot(driver, "Page Screenshot");

                                // Print the text of the current element
                                // System.out.println("Clicking on level 3: " + mainMenu_L3.getText());
                                actions_L3_on_Contact_us.moveToElement(mainMenu_L3).build().perform();
                                captureScreenshot(driver, "Page Screenshot");
                                actions_L3_on_Contact_us.moveToElement(mainMenu_L3).click().build().perform();
                                captureScreenshot(driver, "Page Screenshot");

                                //Click on L3 Box
                                actions_L3_on_Contact_us.moveToElement(level3Element).build().perform();
                                captureScreenshot(driver, "Page Screenshot");
                                actions_L3_on_Contact_us.moveToElement(level3Element).click().build().perform();
                                captureScreenshot(driver, "Page Screenshot");
                            }
                            actions_L2_on_Contact_us.moveToElement(level2Element).build().perform();
                            captureScreenshot(driver, "Page Screenshot");
                            actions_L2_on_Contact_us.moveToElement(level2Element).click().build().perform();
                            captureScreenshot(driver, "Page Screenshot");
                        } else {
                            System.out.println("Element 'Select level 3' is not found or not displayed.");
                        }

                    }
                } else {
                    System.out.println("Element 'Select level 2' is not found or not displayed.");
                }

                // System.out.println("inside Last One  ");
                actions.moveToElement(ddown).build().perform();
                captureScreenshot(driver, "Page Screenshot");
                actions.moveToElement(ddown).click().build().perform();
                captureScreenshot(driver, "Page Screenshot");


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
        //  Quit Driver
        Thread.sleep(4000);
        driver.quit();
    }


    // Searched box input values
//    @DataProvider(name = "Search Data")
//    public Object[][] Search_Data() {
//        return new Object[][]{
//                {"Help"},
//                {">"},
//                {" "},
//                {"GGPoker"}
//        };
//    }

    // Take the Input from Excel file
    @DataProvider(name = "Data")
    public Object[][] testDataExcelFile() throws IOException {
        String excelFileLocation = "src/test/resources/Searched_input_data.xlsx";
        return ExcelUtils.getExcelData(excelFileLocation, "Sheet1");
    }


    @Test(dataProvider = "Data")
    @Description("This is for Search box")
    public void Search_Box(String input_Box) throws InterruptedException {

        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://nsus.my.site.com/ggpokerbr");
        driver.manage().window().maximize();
        // Wait to page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));

        try {

            Actions Search_Action = new Actions(driver);

            WebElement input_box = driver.findElement(By.xpath("(//div[@type=\"search\"])[2]"));

            // Click and enter texts on search bar
            Search_Action.moveToElement(input_box).build().perform();
            Search_Action.moveToElement(input_box).click().sendKeys(input_Box).build().perform();
            // input_box.sendKeys("");

            //click on search button
            WebElement Search_icon = driver.findElement(By.xpath("//div[@class='topsection']//div[@class='SearchIconHome']"));

            Search_Action.moveToElement(Search_icon).build().perform();
            Search_Action.moveToElement(Search_icon).click().build().perform();

            // If No article is found then print no article is found else print result
            //*[text()=" No related article found"]
            //p[@class='searchresult']


            // Wait till element shows up
            //Thread.sleep(6000);

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/p[@class=\"selectedCategoryLevel\"]")));

            // Code for Printing titles
            List<WebElement> result_found = driver.findElements(By.xpath("//div[@class=\"title_border\"]"));
            if (!result_found.isEmpty() && result_found.get(0).isDisplayed()) {
                WebElement List_of_Results = driver.findElement(By.xpath("//p[@class='searchresult']"));
                System.out.println("List_of_Results  : " + List_of_Results.getText());
                //captureScreenshot(driver, "SearchResultScreenshot");
                captureScreenshot(driver, "SearchResultScreenshot");
                for (WebElement element : result_found) {

                    //If any tag contains then it is bug else print all title of search results

                    String[] arrays = {"<a>", "<A>", "</a>", "</A>",
                            "<b>", "<B>", "</b>", "</B>",
                            "<div>", "<DIV>", "</div>", "</DIV>",
                            "<h1>", "<H1>", "</h1>", "</H1>",
                            "<h2>", "<H2>", "</h2>", "</H2>",
                            "<h3>", "<H3>", "</h3>", "</H3>",
                            "<h4>", "<H4>", "</h4>", "</H4>",
                            "<h5>", "<H5>", "</h5>", "</H5>",
                            "<h6>", "<H6>", "</h6>", "</H6>",
                            "<img>", "<IMG>", "</img>", "</IMG>",
                            "<li>", "<LI>", "</li>", "</LI>",
                            "<ol>", "<OL>", "</ol>", "</OL>",
                            "<p>", "<P>", "</p>", "</P>",
                            "<span>", "<SPAN>", "</span>", "</SPAN>",
                            "<strong>", "<STRONG>", "</strong>", "</STRONG>",
                            "<table>", "<TABLE>", "</table>", "</TABLE>",
                            "<tr>", "<TR>", "</tr>", "</TR>",
                            "<td>", "<TD>", "</td>", "</TD>",
                            "<ul>", "<UL>", "</ul>", "</UL>"};

                    String elementText = element.getText();
                    for (String substring : arrays) {
                        if (elementText.contains(substring)) {
                            System.out.println("Found Some HTML Tags [Bug]");
                            Thread.sleep(2000);
                            driver.quit();

                        }
                    }
                    System.out.println("\n" + "____________________________________ARTICLE____________________________________" + "\n");
                    System.out.println("Article : " + element.getText());


                }

            } else {
                System.out.println("No Article is there ");
                captureScreenshot(driver, "NoArticleFoundScreenshot");
            }

            // This is for Logs in allure reports
            //attachConsoleLogs(driver, "ConsoleLogs");
            Allure_Helper_Class.attachConsoleLogs(driver, "ConsoleLogs");
            // This is for Console output in allure reports
            System.setOut(originalOut);
            String consoleOutput = baos.toString(StandardCharsets.UTF_8);
            Allure_Helper_Class.attachConsoleOutput(consoleOutput, "ConsoleOutput");


        } catch (Exception e) {
            System.out.println("\nException caught");
            StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(ste -> ste.getClassName().equals(this.getClass().getName())).collect(Collectors.toList()).get(0);

            System.out.println("Method Name : " + stackTraceElement.getMethodName());
            System.out.println("Line Number : " + stackTraceElement.getLineNumber());
            System.out.println("Class Name : " + stackTraceElement.getClassName());
            System.out.println("File Name : " + stackTraceElement.getFileName());

            System.out.println("Error Message : " + e.getMessage());

        }


        // Quit Driver
        Thread.sleep(5000);
        driver.quit();

    }


    // This excel sheet for Subject,name,email and descriptions
    @DataProvider(name = "Data Sheet 2")
    public Object[][] testDataExcelFiles() throws IOException {
        String excelFileLocation = "src/test/resources/Searched_input_data.xlsx";
        return ExcelUtils.getExcelData(excelFileLocation, "Sheet2");
    }

    @Test(dataProvider = "Data Sheet 2")
    @Description("This is for input box for Contact us")
    public void input_box_for_Contact_us(String Subjects, String names, String emails, String descriptions) throws
            InterruptedException {

        // Capture System.out output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://nsus.my.site.com/ggpokerbr");
        driver.manage().window().maximize();
        // Wait to page load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"Level1Element\"]")));
        try {
            // Action Class
            Actions Action_Class = new Actions(driver);

            // This is contact us modal open
            WebElement Contact_us_Box = driver.findElement(By.xpath("//button[@c-chatcustominterface_chatcustominterface]"));
            Contact_us_Box.click();
            captureScreenshot(driver, "Page Screenshot");

            // WebElement ddown = driver.findElement(By.xpath("//*[text()=\"Select a case type\"]"));
            //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));
            WebElement ddown = driver.findElement(By.xpath("(//button[@role=\"combobox\"])[2]"));
            ddown.click();
            captureScreenshot(driver, "Page Screenshot");
            // wait for the element to load
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role=\"listbox\"]//lightning-base-combobox-item[8]")));
            WebElement Last_Element_of_L1 = driver.findElement(By.xpath("//div[@role=\"listbox\"]//lightning-base-combobox-item[8]"));

            // Hover and click on to that last element
            Action_Class.moveToElement(Last_Element_of_L1).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(Last_Element_of_L1).click().build().perform();
            captureScreenshot(driver, "Page Screenshot");


            // This is for Subject

            WebElement Subject = driver.findElement(By.xpath("(//div/input[@class=\"slds-input\"])[3]"));
            // Hover and click on to that last element
            Action_Class.moveToElement(Subject).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(Subject).click().sendKeys(Subjects).build().perform();
            captureScreenshot(driver, "Page Screenshot");

            // This is for Name

            WebElement Name = driver.findElement(By.xpath("(//div/input[@class=\"slds-input\"])[4]"));
            // Hover and click on to that last element
            Action_Class.moveToElement(Name).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(Name).click().sendKeys(names).build().perform();
            captureScreenshot(driver, "Page Screenshot");

            // This is for email

            WebElement email = driver.findElement(By.xpath("(//div/input[@class=\"slds-input\"])[5]"));
            // Hover and click on to that last element
            Action_Class.moveToElement(email).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(email).click().sendKeys(emails).build().perform();
            captureScreenshot(driver, "Page Screenshot");

            // This is for Description

            WebElement Description = driver.findElement(By.xpath("//textarea[@class=\"slds-textarea\"]"));
            // Hover and click on to that last element
            Action_Class.moveToElement(Description).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(Description).click().sendKeys(descriptions).build().perform();
            captureScreenshot(driver, "Page Screenshot");

            // This is for File Upload
//        WebElement File_upload = driver.findElement(By.xpath("//div/label[@class=\"slds-file-selector__body\"]"));
//        System.out.println(File_upload.getText());
//        // Hover and click on to that last element
//        Action_Class.moveToElement(File_upload).build().perform();
//        // to get the path of the current directory
//        String Project_Path = System.getProperty("user.dir");
//        Action_Class.sendKeys(Project_Path + "\\resources\\Image.jpg").build().perform();

            //Click on submit button
            WebElement Submit_btn = driver.findElement(By.xpath(" //div[@class=\"footer\"]/div"));
            // Hover and click on to that last element
            Action_Class.moveToElement(Submit_btn).build().perform();
            captureScreenshot(driver, "Page Screenshot");
            Action_Class.moveToElement(Submit_btn).click().build().perform();
            captureScreenshot(driver, "Page Screenshot");


            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//lightning-icon[@title=\"approval\"]")));
            if (driver.findElement(By.xpath("//lightning-icon[@title=\"approval\"]")).isDisplayed()) {
                captureScreenshot(driver, "Page Screenshot");
                System.out.println("Case has been Created Successfully");
            } else {
                System.out.println("inside else part");
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

        //div[@class="footer"]/div

        // This is for Logs in allure reports
//attachConsoleLogs(driver, "ConsoleLogs");
        Allure_Helper_Class.attachConsoleLogs(driver, "ConsoleLogs");
// This is for Console output in allure reports
        System.setOut(originalOut);
        String consoleOutput = baos.toString(StandardCharsets.UTF_8);
        Allure_Helper_Class.attachConsoleOutput(consoleOutput, "ConsoleOutput");

        // Driver quit
        Thread.sleep(9000);
        driver.quit();

    }


}




