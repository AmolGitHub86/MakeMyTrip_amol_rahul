package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage_makemytrip {
	WebDriver  driver;	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	String fromcity_input="New Delhi";
	String Sel_fromcity="New Delhi";
	String tocity_input="bangkok";
	String Sel_tocity="Bangkok";
	String Departure_month="Jun";
	String Departure_date ="18";
	String return_month = "Jun";
	String return_day = "22";
	int adult_count= 5;
	int children_count= 3;
	int infants_count=1;
	String travel_class= "First Class";
	String Special_fare_sel= "Regul";

	public HomePage_makemytrip(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id = "webklipper-publisher-widget-container-notification-close-div")
	WebElement popup_window_ad_close;

	@FindBy(css = "span[class='commonModal__close']")
	WebElement popup_login_close;

	@FindBy(id = "fromCity")
	WebElement fromcity;

	@FindBy(css = "input[placeholder='From']")
	WebElement enter_fromcity;

	@FindBy(css = "ul[role='listbox']>li")
	List<WebElement> fromcity_list;

	@FindBy(id="toCity")
	WebElement tocity;

	@FindBy(css ="input[placeholder='To']")
	WebElement enter_tocity;

	@FindBy(css = "ul[role='listbox']>li")
	List<WebElement> tocity_list;

	@FindBy(css = "div[class='DayPicker-Caption']")
	WebElement Depart_datepickup;

	@FindBy(css = "span[aria-label='Next Month']")
	WebElement depart_nextmonth;

	@FindBy(css = "div[data-cy='returnArea']")
	WebElement returnarea;	

	@FindBy(css = "div[class='DayPicker-Caption']")
	WebElement Arrival_datepickup;

	@FindBy(css = "span[aria-label='Next Month']")
	WebElement Arrival_nextmonth;

	@FindBy(css = "label[for='travellers']")
	WebElement travellerArea;

	@FindBy(css  = "button[data-cy='travellerApplyBtn']")
	WebElement Apply_button;

	@FindBy(css = "a[class='primaryBtn font24 latoBold widgetSearchBtn ']")
	WebElement flight_search;

	public void closePopUp() {
		try {System.out.println("switching in frame");
		driver.switchTo().frame(driver.findElement(By.cssSelector("#webklipper-publisher-widget-container-notification-frame")));
		System.out.println("switched to frame");
		wait.until(ExpectedConditions.elementToBeClickable(popup_window_ad_close)).click();
		System.out.println("finish");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.switchTo().parentFrame();
		System.out.println("switch to parent window");
		wait.until(ExpectedConditions.elementToBeClickable(popup_login_close)).click();
		System.out.println("home page opened");
	}


	public void fromcityselection() {

		fromcity.click();
		enter_fromcity.sendKeys(fromcity_input);
		List<WebElement> fromCitylist=fromcity_list;     

		for (WebElement fromCity : fromCitylist) {
			if(fromCity.getText().contains("New Delhi"))
			{ System.out.println(fromCity.getText());
			fromCity.click();
			break; 
			}}}

	public void Tocityselection() {
		//code for to city selection
		Actions actions = new Actions(driver);
		tocity.click();
		enter_tocity.sendKeys(tocity_input);
		List<WebElement> toCitylist=tocity_list;

		for (WebElement toCity : toCitylist) {			
			if(toCity.getText().contains(Sel_tocity))		    
			{
				System.out.println(toCity.getText());
				actions.click(toCity).perform();
				//toCity.click();
				break;	}}}	    	

	public void departDatePick() {
		while(!Depart_datepickup.getText().contains(Departure_month)) {
			depart_nextmonth.click();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("div[aria-label*='"+Departure_month+ " "+ Departure_date+"']");
		WebElement dateElement = driver.findElement(By.cssSelector("div[aria-label*='" + Departure_month + " " + Departure_date + "']"));
		js.executeScript("window.scrollBy(0,150)");
		dateElement.click();}

	public void ArrivalDatePick() {
		//code for date arrival date pick-up
		returnarea.click();
		while(!Arrival_datepickup.getText().contains(return_month)) {
			Arrival_nextmonth.click();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("div[aria-label*='"+return_month+ " "+ return_day+"']");
		WebElement dateElement_return = driver.findElement(By.cssSelector("div[aria-label*='" + return_month + " " + return_day + "']"));
		//js.executeScript("arguments[0].scrollIntoView(true);", dateElement_return);
		js.executeScript("window.scrollBy(0,150)");
		dateElement_return.click();}

	public void TravellerSelection() {	
		//code for traveller selection
		travellerArea.click();
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
		Apply_button.click();
		driver.findElement(By.xpath("//div[contains(text(),'"+Special_fare_sel+"')]")).click();	
		flight_search.click();}
}


