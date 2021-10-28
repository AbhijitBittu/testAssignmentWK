package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class appFeature {

	public void enterToDoTask(WebDriver driver, String task) throws InterruptedException {
		// TODO Auto-generated method stub
		WebElement textBox = driver.findElement(By.xpath("/html/body/todo-app/section/header/input"));
		Thread.sleep(1000);
		textBox.sendKeys(task);
		textBox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}


	public String validateTaskSaved(WebDriver driver, Integer num) {
		// TODO Auto-generated method stub
		WebElement labelTxt = driver.findElement(By.xpath("/html/body/todo-app/section/section/ul/li["+num+"]/div/label"));
		String displayedText = labelTxt.getText();
		return displayedText;
	}
	
}
