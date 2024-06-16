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


		//code for departure date pick-up
		String Departure_month="Jul";
		String Departure_date ="29";

		while(!driver.findElement(By.cssSelector("div[class='DayPicker-Caption']")).getText().contains(Departure_month)) {
			driver.findElement(By.cssSelector("span[aria-label='Next Month']")).click();
		}


		System.out.println("div[aria-label*='"+Departure_month+ " "+ Departure_date+"']");
		WebElement dateElement = driver.findElement(By.cssSelector("div[aria-label*='" + Departure_month + " " + Departure_date + "']"));
		js.executeScript("arguments[0].scrollIntoView(true);", dateElement);
		wait.until(ExpectedConditions.elementToBeClickable(dateElement));
		js.executeScript("arguments[0].click();", dateElement);		

		//code for date arrival date pick-up
		String return_month = "Dec";
		String return_day = "15";
		driver.findElement(By.cssSelector("div[data-cy='returnArea']")).click();

		while(!driver.findElement(By.cssSelector("div[class='DayPicker-Caption']")).getText().contains(return_month)) {
			driver.findElement(By.cssSelector("span[aria-label='Next Month']")).click();
		}

		System.out.println("div[aria-label*='"+return_month+ " "+ return_day+"']");
		WebElement dateElement_return = driver.findElement(By.cssSelector("div[aria-label*='" + return_month + " " + return_day + "']"));
		//js.executeScript("arguments[0].scrollIntoView(true);", dateElement_return);
		js.executeScript("window.scrollBy(0,150)");
		dateElement_return.click();
		//wait.until(ExpectedConditions.elementToBeClickable(dateElement_return));
		//js.executeScript("arguments[0].click();", dateElement_return);	
		
		//code for traveller selection
		int adult_count= 12;
		int children_count= 9;
		int infants_count=8;
		String travel_class= "Economy";
		String Special_fare_sel= "Doctor";
		
		driver.findElement(By.cssSelector("label[for='travellers']")).click();
		//selection adult
		if (adult_count<10) {
			driver.findElement(By.cssSelector("li[data-cy='adults-"+adult_count+"']")).click();
		}else
		{
			driver.findElement(By.cssSelector("li[data-cy='adults-10']")).click();
		}
		
		//children selection
		if (children_count<7) {
			driver.findElement(By.cssSelector("li[data-cy='children-"+children_count+"']")).click();
		}else {
			driver.findElement(By.cssSelector("li[data-cy='children-7']")).click();
		}
		
		//Infants selection
		if(children_count<7) {
			driver.findElement(By.cssSelector("li[data-cy='infants-"+infants_count+"']")).click();
		}else {
			driver.findElement(By.cssSelector("li[data-cy='infants-7']")).click();
		}
		
		driver.findElement(By.xpath("//li[contains(text(),'"+travel_class+"')]")).click();		
		driver.findElement(By.cssSelector("button[data-cy='travellerApplyBtn']")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'"+Special_fare_sel+"')]")).click();
		driver.findElement(By.cssSelector("a[class='primaryBtn font24 latoBold widgetSearchBtn ']")).click();
		
		






	}


}