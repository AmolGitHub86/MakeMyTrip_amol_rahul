package Utility;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page.HomePage_makemytrip;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_makemytrip {
	
	WebDriver driver;
	HomePage_makemytrip homepage_makemytrip;
	

	@BeforeTest
	public WebDriver initialization() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    return driver;
	}


	public HomePage_makemytrip homepage() {
		homepage_makemytrip = new HomePage_makemytrip(driver);	
		return homepage_makemytrip;
	}


}