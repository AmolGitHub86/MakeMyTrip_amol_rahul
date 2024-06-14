import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_makemytrip {
	
	@Test
	public void initialization() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("switching in frame");
		driver.switchTo().frame(driver.findElement(By.cssSelector("#webklipper-publisher-widget-container-notification-frame")));
		System.out.println("switch to frame");
		Thread.sleep(10000);
		   //driver.findElement(By.cssSelector("i[class='wewidgeticon we_close']"));                       
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		                          
		System.out.println("finish");
		driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
		driver.findElement(By.id("fromCity")).click();
	    driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys("Mumbai");
	    List<WebElement> citylist = driver.findElements(By.cssSelector("ul[role='listbox']>li"));
	    
	    for (WebElement city : citylist) {
	    	System.out.println("===================");
            System.out.println(city.getText());
            System.out.println("===================");
        }
	    }
	     
	  
		
		
	}
	
	


