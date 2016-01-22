package eInventoryPageClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;


public class RawItemActivityPage extends AbstractPage {

	public RawItemActivityPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h3[@id='title']")
	public WebElement RawItemActivity_Label;

	@FindBy(xpath = "//input[@id='autosearchInput']")
	public WebElement Search_TB;

	@FindBy(xpath = "//h4[@id='raw_item_detail_label']")
	public WebElement RawItemActivity_Header;

	@FindBy(xpath = "//input[@id='history_start_date']")
	public WebElement RawItemActivityPage_StartDate_TB;

	@FindBy(xpath = "//input[@id='history_end_date']")
	public WebElement RawItemActivityPage_EndDate_TB;

	@FindBy(xpath = ".//*[@id='get_item_details_btn']")
	public WebElement getItemDetails_Button;

	@FindBy(xpath = ".//*[@id='raw_item_detail_table']/tbody/tr")
	public List<WebElement> rawItemDetailList;
	
	@FindBy(xpath="//span[contains(text(),'Time: ')]")
	public WebElement InventoryPopUp_Time_Label;
	
	@FindBy(xpath="//span[contains(text(),'Date: ')]")
	public WebElement InventoryPopUp_Date_Label;
	
	@FindBy(xpath="//input[@id='done_button']")
	public WebElement DoneWithThisItem_BT;
	
	@FindBy(xpath="//button[@id='start_calendar_btn']")
	public WebElement StartDate_BT;
	
	@FindBy(xpath="//button[@id='end_calendar_btn']")
	public WebElement EndDate_BT;
	
	@FindBy(xpath="//div[@id='waste_hist_detail_data']/table/tbody[@id='waste_hist_detail_table_body']/tr/td")
	public WebElement WasteDetailPopUp_WasteDetailList;
	
	@FindBy(xpath="//input[@value='Close']")
	public WebElement ActivityDetailPopUp_Close_BT;
	
	

	// This method will take WRIN Id as argument and search the the WRIN Id in Raw Item Activity Page
	public RawItemActivityPage searchAndSelectWRINID(String samplewRINID)
			throws InterruptedException {
		Search_TB.sendKeys(samplewRINID);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size=driver.findElements(By.xpath("//strong[text()='" + samplewRINID + "']")).size();
		driver.findElement(By.xpath("(//strong[text()='" + samplewRINID + "'])["+size+"]")).click();
		return PageFactory.initElements(driver, RawItemActivityPage.class);

	}

	// This method will navigate the user to Raw Item Activity Detail page
	public RawItemActivityPage clickOngetItemDetailButton() {
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		getItemDetails_Button.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//u[text()='System Date and Time']")));
		return PageFactory.initElements(driver, RawItemActivityPage.class);
	}

	// This method will take date and time as argument and return the deducted count
	public boolean verifyDeductedCountForSelectedDateTime(String date, String time,String deductedCount) 
	{
		String dateTime = date +" "+time;
		deductedCount = "-"+deductedCount+".0000";
		/*String deductedCount = "";
		for (int i = 1; i <= rawItemDetailList.size(); i++) {
			String dateTime = driver.findElement(By.xpath(".//*[@id='raw_item_detail_table']/tbody/tr[" + i+ "]/td[1]/span")).getText();
			boolean result = dateTime.split(" ")[0].equals(date);
			result = result && dateTime.split(" ")[1].equals(time);
			if (result) {
				deductedCount = driver.findElement(
						By.xpath(".//*[@id='raw_item_detail_table']/tbody/tr["
								+ i + "]/td[4]/span")).getText();
				return deductedCount;
			}
		}*/
		return driver.findElement(By.xpath("//table[@id='raw_item_detail_table']//tr/td/span[text()='"+dateTime+"']/../following-sibling::td/span[text()='Transfer Out']/../following-sibling::td[1]/span[text()='"+deductedCount+"']")).isDisplayed();
		
	}
	
	// This method will return count for a row item for physical inventory event from Raw Item Activity Page
	public String getCountForInventoryEvent(String date, String time) {
		String dateTime = date + " " + time;
		return driver.findElement(By.xpath("//table[@id='raw_item_detail_table']//tr/td[1]/span[text()='"+ dateTime+ "']/../following-sibling::td/span[text()='Inventory']/../following-sibling::td[1]/span")).getText();
	}

	// This method will return style of the record for a row item for physical inventory event from Raw Item Activity Page
	public String getStyleForPostedInventoryEvent(String date, String time) {
		String dateTime = date + " " + time;
		return driver.findElement(By.xpath("//table[@id='raw_item_detail_table']//tr/td[1]/span[text()='"+ dateTime+ "']/../following-sibling::td/span[text()='Inventory']/../..")).getAttribute("style");
	}

	// This method will click on View details button for a row item for physical inventory event from Raw Item Activity Page
	public RawItemActivityPage clickOnPostedInventoryViewDetailsButton(String date, String time) {
		String dateTime = date + " " + time;
		driver.findElement(By.xpath("//table[@id='raw_item_detail_table']//tr/td[1]/span[text()='"+ dateTime+ "']/../following-sibling::td/span[text()='Inventory']/../following-sibling::td/button[@id='view_details_btn']")).click();
		return PageFactory.initElements(driver, RawItemActivityPage.class);
	}

	// This method will select start date from calendar
	public void selectStartDate(String date) {
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]/table/tbody/tr/td[@data-month='"+ month + "' and @data-date='" + day + "']/div")).click();
	}

	// This method will select end date from calendar
	public void selectEndDate(String date) {
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[2]/table/tbody/tr/td[@data-month='"+ month + "' and @data-date='" + day + "']/div")).click();
	}
	
	//click on waste details in raw item activity
	public void clickOnWasteDetails(){
		driver.findElement(By.xpath("(.//*[@id='raw_item_detail_table']/tbody/tr/td/span[text()='Waste'])[1]/../following-sibling::td/button[@id='view_details_btn']")).click();
	}
}
