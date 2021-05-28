package PageObjects;

import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Utilities.SeleniumDriver;

public class HomePage {
	SeleniumDriver driver;
	PrintWriter writer;
	@FindBys({ @FindBy(css = "md-autocomplete[placeholder='Enter a search term or a topic']"),
		@FindBy(css = "[type='search']")})
	WebElement search;

	@FindBy(how = How.CSS, using = "div.autocomplete-entity.autocomplete-title")
	private List<WebElement> autsuggestions;

	private String suggestions = "div.autocomplete-entity.autocomplete-title";

	/*
	 * 
	 * @FindBys({
	 * 
	 * @FindBy(class="custom-control-check-box"),
	 * 
	 * @FindBy(id="game-chk-box") }) WebElement chkBox;
	 */

	/*
	 * 
	 * @FindAll({
	 * 
	 * @FindBy(id="btn", //doesn't match
	 * 
	 * @FindBy(name="sbmtBtn"), //Matches
	 * 
	 * @FindBy(class="btn-primary") //doesn't match })
	 * 
	 * @FindAll({
	 * 
	 * @FindBy(id="btn", //doesn't match
	 * 
	 * @FindBy(name="sbmtBtn"), //Matches
	 * 
	 * @FindBy(class="btn-primary") //doesn't match })
	 * 
	 * @CacheLookUp
	 * 
	 * @FindBys({
	 * 
	 * @FindBy(class="custom-control-check-box"),
	 * 
	 * @FindBy(id="game-chk-box") })
	 * 
	 * WebElement chkBox;
	 * 
	 * id, name, className, css, tagName, linkText, partialLinkText, xpath
	 * driver.findElement(By.name(“user_password”)).sendKeys(password);
	 * 
	 * @FindBys(@FindBy(css=”div[class=’yt-lockup-tile yt-lockup-video’]”))) private
	 * List<WebElement> videoElements;
	 * 
	 * @FindBy(how = How.LINK_TEXT, using = "APPLY AS A DEVELOPER") private
	 * WebElement developerApplyButton;
	 * 
	 * @FindBy(how=How.CSS,using="div[class=’yt-lockup-tile yt-lockup-video’]")
	 * private List<WebElement> videoElements;
	 */

	public HomePage(SeleniumDriver driver, PrintWriter writer) {
		PageFactory.initElements(driver.getDriver(), this);
		this.driver = driver;
		this.writer = writer;
	}

	public void SubmitSearch(String keyword) {
		search.clear();
		search.sendKeys(keyword);
		driver.waitForElement(suggestions);
		driver.waitForElementVisible(autsuggestions.get(0));
	}

	public void extractSuggestions() {
		for (WebElement element : autsuggestions) {
			writer.write("Suggestion," + element.getText());
		}
	}

}
