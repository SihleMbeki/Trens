package testautomation.testing;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import PageObjects.Explore;
import PageObjects.HomePage;

public class Export extends Base {

	
	@Test
	public void export() {
		Explore explore=new Explore(driver);
		explore.selectCountry("South Africa");
	}

}
