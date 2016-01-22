package eInventoryPageClasses;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;










import sprint2.AbstractTest;
import common.Base;
import common.Reporter;

public class RawItemPromoPage extends AbstractPage
{
	public RawItemPromoPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//h2[@id='modal-title' and text()='Raw Promo']")
	public WebElement RawPromo_Title;
	
	@FindBy(xpath ="//button[contains(.,'Add New Entry')]")
	public WebElement AddNewEntry_BT;
	
	@FindBy(xpath ="//input[@id='insert_new_promo_date']")
	public WebElement SelectDateAndTime_TB;
	
	@FindBy(xpath ="//button[@id='insert_new_promo_date_btn']")
	public WebElement SelectDateAndTime_BT;
	
	@FindBy(xpath ="//h3[contains(.,'	Enter New Raw Promo')]")
	public WebElement EnterNewRawPromo_Title;
	
	@FindBy(xpath ="//div[@id='data_entry_modal' and @aria-hidden='false']")
	public WebElement AddPromoItemPopUpWindow;
	
	@FindBy(xpath ="//input[@type='search']")
	public WebElement AddPromoItemPopUpWindow_Search_TB;
	
	@FindBy(xpath ="//label[text()='Outer Pack:']")
	public WebElement AddPromoItemPopUpWindow_OuterPack_Label;
	
	@FindBy(xpath ="//label[text()='Inner Pack:']")
	public WebElement AddPromoItemPopUpWindow_InnerPack_Label;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='case_count'])[1]")
	public WebElement OuterPack_TB;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='inner_pack_count'])[1]")
	public WebElement InnerPack_TB;
	
	@FindBy(xpath ="//label[text()='Loose Units:']")
	public WebElement AddPromoItemPopUpWindow_LooseUnits_Label;
	
	@FindBy(xpath ="(//input[@id='validatedInput' and @colname='loose_count'])[1]")
	public WebElement LooseUnits_TB;
	
	@FindBy(xpath ="//label[text()='Total Units:']")
	public WebElement AddPromoItemPopUpWindow_TotalUnits_Label;
	
	@FindBy(xpath ="//label[text()='Cost:']")
	public WebElement AddPromoItemPopUpWindow_Cost_Label;
	
	@FindBy(xpath ="//button[@id='btn_save']")
	public WebElement AddPromoItemPopUpWindow_Add_BT;
	
	@FindBy(xpath ="//div[@class='toast toast-success']")
	public WebElement AddPromoItemPopUpWindow_Confirmation_Message;
	
	@FindBy(xpath ="//button[contains(.,'Cancel')]")
	public WebElement AddPromoItemPopUpWindow_Cancel_BT;
	
	@FindBy(xpath ="//input[@id='raw_promo_entry_autocomplete']")
	public WebElement RawItemWasted_TB;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Add Item' and text()='Add Item'])[2]")
	public WebElement AddItem_BT;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Submit'])[2]")
	public WebElement Submit_BT;
			
	@FindBy(xpath ="//input[@id='submit_raw_waste_btn']")
	public WebElement SubmitEntry_BT;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Cancel'])[2]")
	public WebElement Cancel_BT;
	
	@FindBy(xpath ="//input[@id='cancel_raw_waste_btn']")
	public WebElement CancelEntry_BT;
	
	@FindBy(xpath ="//div[@class='modal-content' and contains(.,'All entered information will be lost.  Are you sure you want to cancel?')]")
	public WebElement RawPromoEntryIncomplete_PopUp_Window;
	
	@FindBy(xpath ="//button/span[text()='Yes']")
	public WebElement RawPromoEntryIncomplete_PopUp_YES_BT;
	
	@FindBy(xpath ="//button/span[text()='No']")
	public WebElement RawPromoEntryIncomplete_PopUp_NO_BT;
	
	@FindBy(xpath = "(//button[@id='autosearchAddInputBtn'])[2]")
	public WebElement AddWrinFromSearchBox_BT;
	
	// To Search and select a raw Item Promo
	public RawItemPromoPage searchAndSelectRawPromoItem(String wrin)throws InterruptedException {
		RawItemWasted_TB.sendKeys(wrin);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size = driver.findElements(By.xpath("//strong[text()='" + wrin + "']")).size();
		driver.findElement(By.xpath("(//strong[text()='" + wrin + "'])[" + size + "]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(AddWrinFromSearchBox_BT));
		AddWrinFromSearchBox_BT.click();
		return PageFactory.initElements(driver, RawItemPromoPage.class);
	}
	
	// To enter current date and time in the field
	public RawItemPromoPage enterCurrentDateAndTimeInRawWasteOccurredAtField()throws InterruptedException {
		// Click on Select Date and Time text box
		String todayDate = Base.returnTodayDate();
		int date = Base.getDayFromDate(todayDate);
		int month = Base.getMonthFromDate(todayDate);
		SelectDateAndTime_TB.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar']/table/tbody/tr/td[@data-month='"+month+"']/div[text()='"+date+"'])[3]")).click();
		return PageFactory.initElements(driver, RawItemPromoPage.class);
	}
		
	// To Add a Raw Promo Item
	public RawItemWastePage addARawPromoItem(String wrin, String innerPack,String outerPack, String looseUnits) throws InterruptedException
	{
		enterCurrentDateAndTimeInRawWasteOccurredAtField();
		searchAndSelectRawPromoItem(wrin);
		try {
			InnerPack_TB.sendKeys(innerPack);
		} catch (Exception e) {
			// Do Nothing
		}
		Thread.sleep(2000);
		OuterPack_TB.sendKeys(outerPack);
		LooseUnits_TB.sendKeys(looseUnits);
		RawPromo_Title.click();
		//AddItem_BT.click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='waste_entry_table']//td[1][contains(.,'"+ wrin + "')]")));
		return PageFactory.initElements(driver, RawItemWastePage.class);
	}
	
	public RawItemWastePage addASecondPromoItem(String wrin, String innerPack,
			String outerPack, String looseUnits) throws InterruptedException {
		searchAndSelectRawPromoItem(wrin);
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
	
	public boolean isAddPromoItemPopUpWindowLoaded()
	{
		//Verify WRIN ,Description and Add columns are displaying
		//Verify Search Text box is displaying
		return 		driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[1]")).getText().trim().equalsIgnoreCase("WRIN")
				&  	driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[2]")).getText().trim().equalsIgnoreCase("Description")
				&	driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//table[@class='display compact dataTable no-footer']/thead/tr/th[3]")).getText().trim().equalsIgnoreCase("Add")
				& 	AddPromoItemPopUpWindow_Search_TB.isDisplayed();
	}
	
	//Add a Waste raw item with given WRIN ID
	public RawItemPromoPage addAPromoRawItem(String browser,String tcName,String wrinID) throws RowsExceededException, BiffException, WriteException, IOException
	{
		wait.until(ExpectedConditions.visibilityOf(AddPromoItemPopUpWindow));
		int size=driver.findElements(By.xpath("//table[@id='raw_items_tbl']/tbody/tr")).size();
		for(int i=1;i<=size;i++)
		{
			if(driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr["+i+"]/td[1]")).getText().trim().equalsIgnoreCase(wrinID))
					{
						driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr["+i+"]/td[3]/button")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='item_wrin' and text()='"+wrinID+"']")));
						//Verify the OuterPack label and text box
						if(AddPromoItemPopUpWindow_OuterPack_Label.isDisplayed() & OuterPack_TB.isDisplayed())
						{
							Reporter.reportPassResult(browser, tcName, "OuterPack label and text box should display", "Pass");
						}
						else
						{
							Reporter.reportTestFailure(browser,tcName+"_OuterPackLabel", tcName, "OuterPack label and text box should display", "Fail");
							AbstractTest.takeSnapShot(tcName+"_OuterPackLabel");

						}
						//Verify the InnerPack label and text box
						if(AddPromoItemPopUpWindow_InnerPack_Label.isDisplayed() & InnerPack_TB.isDisplayed())
						{
							Reporter.reportPassResult(browser, tcName, "InnerPack label and text box should display", "Pass");
						}
						else
						{
							Reporter.reportTestFailure(browser,tcName+"_InnerPackLabel", tcName, "InnerPack label and text box should display", "Fail");
							AbstractTest.takeSnapShot(tcName+"_InnerPackLabel");

						}
						//Verify the Loose Units label and text box
						if(AddPromoItemPopUpWindow_LooseUnits_Label.isDisplayed() & LooseUnits_TB.isDisplayed())
						{
							Reporter.reportPassResult(browser, tcName, "Loose unit label and text box should display", "Pass");
						}
						else
						{
							Reporter.reportTestFailure(browser,tcName+"_LooseUnitLabel", tcName, "Loose unit label and text box should display", "Fail");
							AbstractTest.takeSnapShot(tcName+"_LooseUnitLabel");

						}
						//verify the Total Units label
						if(AddPromoItemPopUpWindow_TotalUnits_Label.isDisplayed())
						{
							Reporter.reportPassResult(browser, tcName, "Total unit label should display successfully", "Pass");
						}
						else
						{
							Reporter.reportTestFailure(browser,tcName+"_TotalUnitsLabel", tcName, "Total unit label should display successfully", "Fail");
							AbstractTest.takeSnapShot(tcName+"_TotalUnitsLabel");

						}
						//Verify the Cost Label
						if(AddPromoItemPopUpWindow_Cost_Label.isDisplayed())
						{
							Reporter.reportPassResult(browser, tcName, "Cost label should display successfully", "Pass");
						}
						else
						{
							Reporter.reportTestFailure(browser,tcName+"_CostLabel", tcName, "Cost label should display successfully", "Fail");
							AbstractTest.takeSnapShot(tcName+"_CostUnitsLabel");

						}

						break;
					}

		}
		
		
		return PageFactory.initElements(driver, RawItemPromoPage.class);
		
	}
	
	
	//Search a Raw Item
	public boolean searchAPromoItem(String itemName) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(AddPromoItemPopUpWindow));
		AddPromoItemPopUpWindow_Search_TB.sendKeys(itemName);
		Thread.sleep(2000);
		int size=driver.findElements(By.xpath("//table[@id='raw_items_tbl']/tbody/tr")).size();
		if(size==1)
		{
			if(driver.findElement(By.xpath("//table[@id='raw_items_tbl']/tbody/tr[1]/td[2]")).getText().trim().equalsIgnoreCase(itemName))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getTotalPromoAmount(){
		List<WebElement> promoItemSubtotalList = driver.findElements(By.xpath("//table[@id='raw_promo_entry_table']/tbody/tr/td[7]"));
		float subtotal = 0;
		for(WebElement promoItem : promoItemSubtotalList){
			String promoItemsubtotal = promoItem.getText();//.substring(1);
			float itemSubtotal = Float.parseFloat(promoItemsubtotal);
			subtotal = subtotal+ itemSubtotal;
		}
		return Float.toString(subtotal);
	}

}
