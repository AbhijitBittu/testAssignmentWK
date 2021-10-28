package com.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class ToDoListTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\\\driver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://todomvc.com/examples/angular2");		
	}
	
	
	/*---------------------------------------------
	To validate the title of the web page
	---------------------------------------------*/
	@Test(priority=1,groups="Positive")
	public void checkTitle() {
		String pageTitle= driver.getTitle();
		String expectedTitle = "Angular2 • TodoMVC";
		Assert.assertEquals(pageTitle, expectedTitle);
		System.out.println(pageTitle);		
	}
	
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
	
	/*---------------------------------------------
	To Store mutiple task in one session 
	---------------------------------------------*/
	@Test(priority=3,groups="Positive")
	public void storeMultipleTask() throws InterruptedException {
		appFeature cls = new appFeature ();
		// TASK 1
		int n=1;
		String task1 ="Meeting with Rob";
		cls.enterToDoTask(driver, task1);
		String displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		
		// TASK 2
		n=2;
		String task2 ="Meeting with Alex";
		cls.enterToDoTask(driver, task1);
		displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		
	}
	
	/*---------------------------------------------
	To make task complete 
	---------------------------------------------*/
	@Test(priority=4,groups="Positive")
	public void makeTaskComplete() throws InterruptedException {
		
		appFeature cls = new appFeature ();
		// TASK 1
		int n=1;
		String task1 ="Meeting with Rob";
		cls.enterToDoTask(driver, task1);
		String displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		Thread.sleep(1000);
		WebElement completeTask = driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/input"));
		completeTask.click();
		Thread.sleep(1000);
		Boolean b= completeTask.isSelected();
		Assert.assertTrue(b);	
		
	}
	
	/*---------------------------------------------
	 To validate the input text box is empty
	---------------------------------------------*/
	@Test(priority=5,groups="Negative")
	public void taksRetained() throws InterruptedException {
		boolean b = driver.findElement(By.xpath("/html/body/todo-app/section/header/input")).isDisplayed();
		Assert.assertTrue(b);
		String textValue = driver.findElement(By.xpath("/html/body/todo-app/section/header/input")).getText();
		System.out.println("Text:"+textValue);
		Assert.assertEquals(textValue, "");
		Thread.sleep(1000);
		
	}
	
	/*---------------------------------------------
	 To validate list are retained in case of page refresh 
	---------------------------------------------*/
	@Test(priority=6,groups="Negative")
	public void seacrhBoxEmpty() throws InterruptedException {
		appFeature cls = new appFeature ();
		int n=1;
		String task1 ="Meeting with Rob";
		cls.enterToDoTask(driver, task1);
		String displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		Thread.sleep(1000);		
		List<WebElement> allTask_P = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
		int count_prev = allTask_P.size();
		driver.navigate().refresh();
		List<WebElement> allTask_B = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
		int count_aft = allTask_B.size();
		Assert.assertEquals(count_prev, count_aft);
	}
	
	/*---------------------------------------------
	 To validate if user can edit and save with empty entry. 
	 It should not allow user to save with empty entry.
	 But this application allows and does it fails.
	---------------------------------------------*/
	@Test(priority=7,groups="Negative")
	public void saveEmptyTask() throws InterruptedException {
		appFeature cls = new appFeature ();
		int n=1;
		String task1 ="Meeting with Rob";
		cls.enterToDoTask(driver, task1);
		String displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		Thread.sleep(1000);	
		Actions a = new Actions(driver);
	    a.moveToElement(driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/label"))).
	    doubleClick().
	    build().perform();
	    a.moveToElement(driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/label"))).
	    doubleClick().
	    build().perform();
	    WebElement textBox =driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li[1]/div/label"));
	    Thread.sleep(1000);
	    textBox.click();
		textBox.clear();
		textBox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		displayValue = cls.validateTaskSaved(driver, n);
		Assert.assertEquals(task1, displayValue);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
