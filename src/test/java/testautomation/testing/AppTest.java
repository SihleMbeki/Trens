package testautomation.testing;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import PageObjects.Explore;
import PageObjects.HomePage;

public class AppTest extends Base {

	@Test(priority=1)
	@Parameters("Keyword")
	public void test(String keyword) throws IOException {
		String[] files={"relatedEntities.csv","relatedQueries.csv"};
		driver.getDriver().get("https://trends.google.com/trends/?geo=US");
		HomePage login=new HomePage(driver,writer);
		login.SubmitSearch(keyword);
		//login.extractSuggestions();
		driver.pressEnter();
		//getTest().log(Status.PASS, keyword);
		//Explore explore=new Explore(driver);
		//explore.clearFiles(files);
		//explore.selectCountry("South Africa");
		//explore.selectDays();
		//explore.export();
		//explore.copyFiles(files);
	}
	
}
