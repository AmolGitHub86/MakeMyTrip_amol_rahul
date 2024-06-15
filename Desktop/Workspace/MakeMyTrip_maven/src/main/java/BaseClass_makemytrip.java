import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

		//code for date pick-up

		List<WebElement> months = driver.findElements(By.cssSelector("div[class='DayPicker-Months'] >div")); 

		int i=0;
		String selectdate = "14";
		for (WebElement month : months) {
			i= i+1;
			if (month.getText().contains("July")) 
			{				 
				System.out.println("======================");
				System.out.println(month.findElement(By.cssSelector("div[class='DayPicker-Body']  > div:nth-child("+i+")")).getText());
				System.out.println("======================");
				List<WebElement> weekdates = month.findElements(By.cssSelector("div[class='DayPicker-Body']  > div"));
				
				for (WebElement week:weekdates) {
					System.out.println("------------");
					System.out.println(week.getText());
					System.out.println("------------");
					System.out.println(week.findElement(By.cssSelector("div[class='DayPicker-Day']  > div >p")).getText());
					if (week.findElement(By.cssSelector("div[class='DayPicker-Day']  > div >p")).getText().contains(selectdate)) {
						System.out.println("--enter into 2nd loop-------");
						System.out.println(week.getText());
						System.out.println(week);
						week.click();
						System.out.println("---exist from 2nd loop------");
						break;
					}
				}
				
				//=====
//				for (WebElement date:weekdates) {
//					System.out.println("enter into 2 for loop");
//
//					if (date.findElement(By.cssSelector("div[class='dateInnerCell']>p")).getText().contains(selectdate)) {
//						System.out.println("---------");
//						System.out.println(date.getText());
//						date.click();
//						System.out.println("---------");
//						break;
//					}
//				}
			}


		}


	}
}