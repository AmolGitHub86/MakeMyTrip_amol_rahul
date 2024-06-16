package TestCases;

import org.testng.annotations.Test;

import Page.HomePage_makemytrip;
import Utility.BaseClass_makemytrip;


public class Flight_booking extends BaseClass_makemytrip{
	
	
	@Test
	public void flightbook () {
		HomePage_makemytrip homepage = homepage();
		homepage.closePopUp();
		homepage.fromcityselection();
		homepage.Tocityselection();
		homepage.departDatePick();
		homepage.ArrivalDatePick();
		homepage.TravellerSelection();
		
		
	}

}
