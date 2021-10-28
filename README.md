# testAssignment

Project Title:
Automation framework for ‘Angular2 ToDoMVC Application testing’.

Motivation:
The application helps user to save the TO DO task and once done the user can mark it as complete.

Code Style:
Code Style: Standard.

Technology/Framework Used:

Technology Used : Java, TestNG, Selenium

Features:
Here you need to add features of your project. For Example: This project will help Automation Testing Companies to test their application using automation to reduce the manual time.

Code Example:
/*---------------------------------------------
To Store a To Do Task
---------------------------------------------*/
@Test(priority=2,groups="Positive")
public void storeToDoTask() throws InterruptedException {
appFeature cls = new appFeature ();
// TASK 1
int n=1;
String task1 ="Meeting with Rob";
cls.enterToDoTask(driver, task1);
String displayValue = cls.validateTaskSaved(driver,n);
Assert.assertEquals(task1, displayValue);
}

Installation:
You need to have a system with Java pre-installed. Additionaly you need to install following parameter,
Eclipse - Editor
Selenium JAR Files
ChromeDriver  

Tests:
First TestNG needs to be configured and then the TestNG class can be executed.
The test cases are grouped in two categories
1) Positive scenario
2) Negative scenario

Once test are executed report can be accessed by opening the index.html expanding test-output file

Page Object Model
By using the Page Object Model you will create a separate class for each page you need to interact with during a test. These objects are then reused in every test case that needs to interact with the specific page. This application just consist of one page, so just a one page class has been created.
