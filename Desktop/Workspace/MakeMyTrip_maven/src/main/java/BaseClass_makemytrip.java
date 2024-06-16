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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_makemytrip {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void initialization() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {System.out.println("switching in frame");
		driver.switchTo().frame(driver.findElement(By.cssSelector("#webklipper-publisher-widget-container-notification-frame")));
		System.out.println("switched to frame");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("webklipper-publisher-widget-container-notification-close-div"))).click();                      
		System.out.println("finish");
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		driver.switchTo().parentFrame();
		System.out.println("switch to parent window");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='commonModal__close']"))).click();
		System.out.println("home page opened");
		driver.findElement(By.id("fromCity")).click();
		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys("Mumbai");
		List<WebElement> fromCitylist = driver.findElements(By.cssSelector("ul[role='listbox']>li"));


		for (WebElement fromCity : fromCitylist) {

			if(fromCity.getText().contains("New Delhi"))
			{
				System.out.println(fromCity.getText());
				fromCity.click();
				break;   

			}
		}

		//code for to city selection
		Actions actions = new Actions(driver);
		driver.findElement(By.id("toCity")).click();
		driver.findElement(By.cssSelector("input[placeholder='To']")).sendKeys("bangkok");

		List<WebElement> toCitylist = driver.findElements(By.cssSelector("ul[role='listbox']>li"));

		for (WebElement toCity : toCitylist) {			
			if(toCity.getText().contains("Bangkok"))		    
			{
				System.out.println(toCity.getText());
				actions.click(toCity).perform();
				//toCity.click();
				break;		    	
			}
		} 


		//code for date departure date pick-up
		String Departure_month="Jul";
		String Departure_date ="05";

		System.out.println("div[aria-label*='"+Departure_month+ " "+ Departure_date+"']");
		WebElement dateElement = driver.findElement(By.cssSelector("div[aria-label*='" + Departure_month + " " + Departure_date + "']"));
		js.executeScript("arguments[0].scrollIntoView(true);", dateElement);
		wait.until(ExpectedConditions.elementToBeClickable(dateElement));
		js.executeScript("arguments[0].click();", dateElement);

		//code for date arrival date pick-up
		
		




	}


}