Automated GGPOKER Testing Project


Automated [GGPOKER] project using Selenium with Java, Maven, TestNG, and Excel for data-driven testing, Allure for reporting, and screenshots for test evidence.

Table of Contents
Introduction
Prerequisites
Setup
Usage
Allure Reporting

Contact

Introduction
This project demonstrates automated testing using Selenium WebDriver with Java, integrated with Maven for dependency management and TestNG for test execution and reporting. Data-driven testing is facilitated using Excel files, and Allure is used for detailed test reports that include console logs and screenshots.

Prerequisites
Ensure you have the following installed on your machine:

Java Development Kit (JDK) 8 or higher
Maven
Git
Setup
Clone the repository:

bash
Copy code
git clone https://github.com/Mayankjha1/GGPoker-Automation.git
cd automated-testing-project

Install dependencies [in Pom.xml]:
 - junit
 - testng
 - allure-testng
 - selenium-java
 - poi-ooxml
 - plexus-archiver


Store test data in Excel format under /src/test/resources/data.xlsx.
<img width="430" alt="image" src="https://github.com/Mayankjha1/GGPoker-Automation/assets/81032702/00f54a20-3e7f-4f5e-a065-b320f3c12f4b">


Allure Reporting
Allure generates comprehensive HTML reports automatically after each test run.

To view the report:
code run on your terminal :-
allure serve
Open the generated link in your browser to view detailed test results, including console logs and screenshots.

1. <img width="1440" alt="image" src="https://github.com/Mayankjha1/GGPoker-Automation/assets/81032702/21d0cbb7-d343-46ab-9676-d218793e6dc0">
2. <img width="1440" alt="image" src="https://github.com/Mayankjha1/GGPoker-Automation/assets/81032702/613c9acb-3f9e-4fd2-aaef-789b0a9c70e5">
3. <img width="1438" alt="image" src="https://github.com/Mayankjha1/GGPoker-Automation/assets/81032702/f9441fe2-24b0-4fd9-bc0a-80931310a25c">


Contact
For questions or support, contact me at Mayankjha237@gmail.com or visit my GitHub profile.
