package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import common.Base;

public class RawItemWastePage extends AbstractPage
{

	public RawItemWastePage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//button[contains(.,'Add New Entry')]")
	public WebElement AddNewEntry_BT;
	
	@FindBy(xpath ="//input[@id='insert_new_waste_date']")
	public WebElement SelectDateAndTime_TB;
	
	@FindBy(xpath ="//h2[@id='modal-title' and text()='Raw Waste']")
	public WebElement RawWaste_Title;
	
	@FindBy(xpath ="//h3[contains(.,'	Enter New Raw Waste')]")
	public WebElement EnterNewRawWaste_Title;
	
	@FindBy(xpath ="//div[@id='data_entry_modal' and @aria-hidden='false']")
	public WebElement AddWasteItemPopUpWindow;
	
	@FindBy(xpath ="//input[@type='search']")
	public WebElement AddWasteItemPopUpWindow_Search_TB;
	
	@FindBy(xpath ="//label[text()='Outer Pack:']")
	public WebElement AddWasteItemPopUpWindow_OuterPack_Label;
	
	@FindBy(xpath ="//label[text()='Inner Pack:']")
	public WebElement AddWasteItemPopUpWindow_InnerPack_Label;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='case_count'])[1]")
	public WebElement OuterPack_TB;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='inner_pack_count'])[1]")
	public WebElement InnerPack_TB;
	
	@FindBy(xpath ="//label[text()='Loose Units:']")
	public WebElement AddWasteItemPopUpWindow_LooseUnits_Label;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='loose_count'])[1]")
	public WebElement LooseUnits_TB;
	
	@FindBy(xpath ="//label[text()='Total Units:']")
	public WebElement AddWasteItemPopUpWindow_TotalUnits_Label;
	
	@FindBy(xpath ="//label[text()='Cost:']")
	public WebElement AddWasteItemPopUpWindow_Cost_Label;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Add Item' and text()='Add Item'])[1]")
	public WebElement AddItem_BT;
	
	@FindBy(xpath ="//div[@class='toast toast-success']")
	public WebElement AddWasteItemPopUpWindow_Confirmation_Message;
	
	@FindBy(xpath ="//button[contains(.,'Cancel')]")
	public WebElement AddWasteItemPopUpWindow_Cancel_BT;
	
	@FindBy(xpath ="//input[@id='raw_waste_entry_autocomplete']")
	public WebElement RawItemWasted_TB;

	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Submit'])[1]")
	public WebElement Submit_BT;
	
	@FindBy(xpath ="//input[@id='submit_raw_waste_btn']")
	public WebElement SubmitEntry_BT;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Cancel'])[1]")
	public WebElement Cancel_BT;
	
	@FindBy(xpath ="//input[@id='cancel_raw_waste_btn']")
	public WebElement CancelEntry_BT;
	
	@FindBy(xpath ="//div[@class='modal-content' and contains(.,'All entered information will be lost.  Are you sure you want to cancel?')]")
	public WebElement RawWasteEntryIncomplete_PopUp_Window;
	
	@FindBy(xpath ="//button/span[text()='Yes']")
	public WebElement RawWasteEntryIncomplete_PopUp_YES_BT;
	
	@FindBy(xpath ="//button/span[text()='No']")
	public WebElement RawWasteEntryIncomplete_PopUp_NO_BT;
	
	@FindBy(xpath="//div[@class='toast-message' and contains(.,'Values must be numeric with up to 2 decimals. (Example: 12345.99)')]")
	public WebElement InvalidValue_Error_Message;
	
	@FindBy(id = "back-to-top")
	public WebElement BackToTop_BT;
	
	@FindBy(id = "autosearchAddInputBtn")
	public WebElement AddWrinFromSearchBox_BT;
	
	public boolean isAddWasteItemPopUpWindowLoaded()
	{
		//Verify WRIN ,Description and Add columns are displaying
		//Verify Search Text box is displaying
		return 		driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[1]")).getText().trim().equalsIgnoreCase("WRIN")
				&  	driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[2]")).getText().trim().equalsIgnoreCase("Description")
				&	driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[3]")).getText().trim().equalsIgnoreCase("Add")
				& 	AddWasteItemPopUpWindow_Search_TB.isDisplayed();
	}
	
	//To enter current date and time in the field
	public RawItemWastePage enterCurrentDateAndTimeInRawWasteOccurredAtField() throws InterruptedException
	{
		//Click on Select Date and Time text box
		String todayDate=Base.returnTodayDate();
		int date = Base.getDayFromDate(todayDate);
		int month = Base.getMonthFromDate(todayDate);
		SelectDateAndTime_TB.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar']/table/tbody/tr/td[@data-month='"+month+"']/div[text()='"+date+"'])[3]")).click();
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}
	//To Search and select a raw Item wasted
	public RawItemWastePage searchAndSelectRawItemWasted(String wrin) throws InterruptedException
	{
		RawItemWasted_TB.sendKeys(wrin);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size=driver.findElements(By.xpath("//strong[text()='"+wrin+"']")).size();
		driver.findElement(By.xpath("(//strong[text()='"+wrin+"'])["+size+"]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(AddWrinFromSearchBox_BT));
		AddWrinFromSearchBox_BT.click();
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}
	
	//To Add a Raw Waste Item
	public RawItemWastePage addARawItem(String wrin, String innerPack,String outerPack, String looseUnits) throws InterruptedException
	{
		enterCurrentDateAndTimeInRawWasteOccurredAtField();
		// SelectDateAndTime_TB.click();
		searchAndSelectRawItemWasted(wrin);
		try {
			InnerPack_TB.sendKeys(innerPack);
		} catch (Exception e) {
			// Do nothing
		}
		Thread.sleep(3000);
		OuterPack_TB.sendKeys(outerPack);
		LooseUnits_TB.sendKeys(looseUnits);
		RawWaste_Title.click();
		//AddItem_BT.click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@id='waste_entry_table'])[1]/tbody/tr/td[contains(.,'"+ wrin + "')]")));
		// Submit_BT.click();
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}
	
	public RawItemWastePage addASecondRawItem(String wrin, String innerPack,String outerPack, String looseUnits) throws InterruptedException 
	{
		searchAndSelectRawItemWasted(wrin);
		try {
			InnerPack_TB.sendKeys(innerPack);
		} catch (Exception e) {
			// Do nothing
		}
		Thread.sleep(3000);
		OuterPack_TB.sendKeys(outerPack);
		LooseUnits_TB.sendKeys(looseUnits);
		AddItem_BT.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='waste_entry_table']//td[1][contains(.,'"+ wrin + "')]")));
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}
	
	//Add a Waste raw item with given WRIN ID
	public RawItemWastePage addAWasteRawItem(String wrinID) {
		wait.until(ExpectedConditions.visibilityOf(AddWasteItemPopUpWindow));
		int size = driver.findElements(By.xpath("//table[@id='raw_items_tbl']/tbody/tr")).size();
		for (int i = 1; i <= size; i++) {
			if (driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr["+ i + "]/td[1]")).getText().trim().equalsIgnoreCase(wrinID)) {
				driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr[" + i+ "]/td[3]/button")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='item_wrin' and text()='" + wrinID+ "']")));
				// Verify the OuterPack label and text box
				Assert.assertTrue(AddWasteItemPopUpWindow_OuterPack_Label.isDisplayed()& OuterPack_TB.isDisplayed(),"OuterPack label and text box should display");
				// Verify the InnerPack label and text box
				Assert.assertTrue(AddWasteItemPopUpWindow_InnerPack_Label.isDisplayed() & InnerPack_TB.isDisplayed(),"InnerPack label and text box should display");
				// Verify the Loose Units label and text box
				Assert.assertTrue(AddWasteItemPopUpWindow_LooseUnits_Label.isDisplayed() & LooseUnits_TB.isDisplayed(),"Loose unit label and text box should display");
				// verify the Total Units label
				Assert.assertTrue( AddWasteItemPopUpWindow_TotalUnits_Label.isDisplayed(),"Total unit label should display successfully");
				// Verify the Cost Label
				Assert.assertTrue(AddWasteItemPopUpWindow_Cost_Label.isDisplayed(),"Cost label should display successfully");
			}
		}
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}

	// Search a Raw Item
	public boolean searchARawItem(String itemName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(AddWasteItemPopUpWindow));
		AddWasteItemPopUpWindow_Search_TB.clear();
		AddWasteItemPopUpWindow_Search_TB.sendKeys(itemName);
		Thread.sleep(2000);
		int size = driver.findElements(By.xpath("//table[@id='raw_items_tbl']/tbody/tr")).size();
		if (size == 1) {
			if (driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr[1]/td[2]")).getText().trim().equalsIgnoreCase(itemName)) {
				return true;
			}
		}
		return false;
	}

	/*This method will return total waste amount added*/
	public String getTotalWasteAmunt(){
		List<WebElement> wasteItemSubtotalList = driver.findElements(By.xpath("//table[@id='raw_waste_entry_table']/tbody/tr/td[7]"));
		float subtotal = 0;
		for(WebElement wasteItem : wasteItemSubtotalList){
			String wasteItemsubtotal = wasteItem.getText();
			float itemSubtotal = Float.parseFloat(wasteItemsubtotal);
			subtotal = subtotal+ itemSubtotal;
		}
		return Float.toString(subtotal);
	}
	
	public boolean verifyWasteItemIsAdded(String wrinId){
		return Base.isElementDisplayed(By.xpath("//table[@id='waste_entry_table']/tbody/tr[@id='wasteItem0']/td[contains(text(),'"+wrinId+"')]"));
	}

}
