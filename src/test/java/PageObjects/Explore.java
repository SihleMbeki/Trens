package PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.Duration;
import Utilities.ExtentReport;
import Utilities.SeleniumDriver;

public class Explore {
	SeleniumDriver driver;
	@FindBy(xpath = "(//div[@class='hierarchy-select ng-pristine ng-valid'])[1]/..")
	WebElement country;

	@FindBy(xpath = "(//md-autocomplete[@md-search-text]//md-autocomplete-wrap[@role='listbox']//input)[2]")
	WebElement input;

	@FindBy(css = "span.highlight")
	WebElement highlight;

	@FindBy(css = "div.hierarchy-picker-node md-icon")
	WebElement picker;

	@FindBy(xpath = "(//div[contains(@class,'explore-header-wrapper')]//md-select-value)[1]/..")
	private WebElement past;

	@FindBy(xpath = "(//div[@class='fe-related-queries fe-atoms-generic-container']//button[@class='widget-actions-item export'])[1]")
	private WebElement exportTopics;

	@FindBy(xpath = "(//div[@class='fe-related-queries fe-atoms-generic-container']//button[@class='widget-actions-item export'])[2]")
	private WebElement exportQueries;

	private String suggestions = "div.autocomplete-entity.autocomplete-title";

	private String title = "//title[contains(text(),'- Explore - Google Trends')]";

	public Explore(SeleniumDriver driver) {
		PageFactory.initElements(driver.getDriver(), this);
		this.driver = driver;
		this.driver.waitForElementXpath(title);
	}

	public void selectCountry(String searchCountry) {
		driver.pause(300);
		country.click();
		country.click();
		Actions actions = new Actions(driver.getDriver());
		actions.doubleClick(country).perform();
		driver.pause(100);
		input.clear();
		input.sendKeys(searchCountry);
		driver.pause(1000);
		driver.waitForElementVisible(highlight);
		actions.click(highlight).perform();
		// driver.waitForElementVisible(picker);
		// actions.click(picker).perform();
		// picker.click();
	}

	public void selectDays() {
		WebElement pastDuration = driver.getDriver()
				.findElement(By.cssSelector(Duration.twelveMonths.getPastDuration()));

		Actions actions = new Actions(driver.getDriver());
		// actions.click(past).perform();
		driver.pause(3000);
		past.click();

		driver.waitForElementVisible(Duration.twelveMonths.getPastDuration());
		pastDuration.click();
	}

	public void export() {
//		Robot robot;
//		try {
//			robot = new Robot();
//			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView();", exportQueries);

		// Actions action =new Actions(driver.getDriver());
		// action.moveToElement(exportQueries).perform();;
		driver.pause(3000);
		exportTopics.click();
		driver.switchToDefaultContent();
		exportQueries.click();
		driver.pause(50000);
	}

	public void copyFiles(String[] files) {
		for (String fileName : files) {
			try {
				File file = new File(ExtentReport.getDownloadDirectory() + fileName);
				File destination = new File(ExtentReport.getDirectory() + "\\" + fileName);
				FileUtils.copyFile(file, destination);
			} catch (Exception e) {
				Assert.fail();
			}
		}

	}

	public void clearFiles(String[] files) {
		for (String fileName : files) {
			try {

				File file = new File(ExtentReport.getDownloadDirectory() + fileName);
				if (file.exists())
					file.delete();
			} catch (Exception e) {
				Assert.fail();
			}
		}

	}

}
