package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import common.Base;

public class ManualInvoiceNewPage extends AbstractPage {

	public ManualInvoiceNewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

@FindBy(xpath="//h2[text()='Manual Invoice']")
public WebElement ManualInvoiceNew_Label;
	
@FindBy(id="vendor_list")
public WebElement VendorList_DD;	


@FindBy(xpath="//div[@id='wrin_list_tbl_wrapper']//label[contains(. ,'Search')]")
public WebElement Search_Label_01;

@FindBy(xpath="//div[@id='invoice_tbl_filter']/label[contains(.,'Search')]")
public WebElement Search_Label_02;

@FindBy(xpath="//div[@class='col-xs-10 text-right']")
public WebElement Total_Label;

@FindBy(xpath="//table[@id='wrin_list_tbl']")
public WebElement RowItemTable;

@FindBy(xpath="//input[@id='continue_finalize']")
public WebElement FinalizePopUp_Continue_BT;

@FindBy(xpath="//table[@id='wrin_list_tbl']//tr[2]/td[3]/button")
public WebElement RowItemTable_First_Add_BT;

@FindBy(xpath="//eb-button[@id='manual_purchase_modal_cancel_btn']/button")
public WebElement Cancel_BT;

@FindBy(xpath="//input[@id='invoice_identifier']")
public WebElement InvoiceNumber_TB;

@FindBy(id="save_button")
public WebElement Save_BT;

@FindBy(xpath="//input[@id='complete_button']")
public WebElement Finalize_BT;

@FindBy(id="delete_button")
public WebElement Delete_BT;

@FindBy(xpath="//input[@id='validatedInput']")
public List <WebElement> Quantity_TB_List;

@FindBy(xpath="//tbody[@id='invoice_tbl_body']/tr/td[8]")
public List <WebElement> Subtotal_Value_List;

@FindBy(xpath="//input[@id='input_price']")
public List <WebElement> DollerCase_TB_List;

@FindBy(xpath="//input[@id='disp_date']")
public WebElement InvoiceDate_TB;

@FindBy(xpath="//a[contains(.,'Inventory')]//b[@class='caret']")
public WebElement Inventory_DD_Button;

@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']/li[contains(.,'Purchases')]")
public WebElement Inventory_DD_Purchases;

@FindBy(xpath="//div[@class='toast-message' and contains(.,'Your invoice has been saved')]")
public WebElement InvoiceSaved_Confirmation_MSG;

@FindBy(xpath="//div[@class='toast-message' and text()='The manual invoice number is a duplicate.  Please enter a new manual invoice number.']")
public WebElement DuplicateInvoiceNumber_Error_MSG;

@FindBy(xpath="//div[@class='toast-message' and text()='Your invoice has been posted.']")
public WebElement InvoiceFinalize_Confirmation_MSG;

@FindBy(xpath = "//input[@id='autocomplete']")
public WebElement EnterQuickSearchWithSuggestionsForManualPurchases_TB;

@FindBy(xpath="//div[@id='invoice_tbl_filter']//input[@class='form-control table-search']")
public WebElement Search_TB_02;

@FindBy(xpath = "//td[@class='compact sorting_1']/following-sibling::td[2]/button")
public List <WebElement> SearchedRawItem_Add_BT;

@FindBy(xpath = "//select[@id='vendor_list']/option")
public List <WebElement> VendorName_List;

@FindBy(xpath = "//div[text()='Total Food:']")
public WebElement TotalFood_Label;

@FindBy(xpath = "//div[@id='total_food']")
public WebElement TotalFood_Value;

@FindBy(xpath = "//div[text()='Total Ops Supplies:']")
public WebElement TotalOpsSupplies_Label;

@FindBy(xpath = "//div[@id='total_ops']")
public WebElement TotalOpsSupplies_Value;

@FindBy(xpath = "//div[text()='Total Paper:']")
public WebElement TotalPaper_Label;

@FindBy(xpath = "//div[@id='total_paper']")
public WebElement TotalPaper_Value;

@FindBy(xpath = "//div[text()='Total Linens:']")
public WebElement TotalLines_Label;

@FindBy(xpath = "//div[@id='total_linens']")
public WebElement TotalLines_Value;

@FindBy(xpath = "//div[text()='Total Non-product (Other):']")
public WebElement TotalNonProductOther_Label;

@FindBy(xpath = "//div[@id='total_other']")
public WebElement TotalNonProductOther_Value;

@FindBy(xpath = "//div[text()='Total Non-product (Happy Meal Premiums):']")
public WebElement TotalNonProductHappyMealPremiums_Label;

@FindBy(xpath = "//div[@id='total_happy_meal']")
public WebElement TotalNonProductHappyMealPremiums_Value;

@FindBy(xpath = "//div[@id='total_val']")
public WebElement TotalPurchases_Value;

@FindBy(xpath = "//tbody[@id='invoice_tbl_body']/tr/td[2]")
public WebElement InvoiceTable_WrinId_Value;

@FindBy(xpath = "//tbody[@id='invoice_tbl_body']/tr/td[3]")
public WebElement InvoiceTable_Description_Value;

@FindBy(xpath = "//tbody[@id='invoice_tbl_body']/tr/td[4]")
public WebElement InvoiceTable_Uom_Value;

@FindBy(xpath = "//tbody[@id='invoice_tbl_body']/tr/td[5]")
public WebElement InvoiceTable_UomCase_Value;

@FindBy(xpath = "//div[@class='toast-message' and text()='Please enter a valid value for the highlighted field(s). Fields must have a value and be greater than 0, otherwise the row should be removed from the invoice.']")
public WebElement FieldValidation_ErrorMessage;

/****/

@FindBy(id = "autocomplete_add_item_btn")
public WebElement AddWrinFromSearchBox_BT;

@FindBy(xpath = "//eb-button[@id='manual_purchase_modal_submit_btn']/button")
public WebElement Submit_BT;

@FindBy(xpath = "//button[@id='datetimepicker_purchases']")
public WebElement ManualInvoiceDatePicker_BT;

@FindBy(xpath = "//eb-button[@id='purchase_modal_delete_btn']/button")
public WebElement ManualInvoiceDelete_BT;

@FindBy(xpath = "//eb-button[@id='purchase_modal_cancel_btn']/button")
public WebElement ManualInvoiceCancel_BT;

@FindBy(xpath = "//button/span[text()='Yes']")
public WebElement ManualInvoiceDelete_ConfirmationPopUp_Yes_BT;

@FindBy(xpath = "//button/span[text()='No']")
public WebElement ManualInvoiceDelete_ConfirmationPopUp_No_BT;

@FindBy(xpath="//div[@class='toast-message' and contains(.,'Invoice deleted.')]")
public WebElement InvoiceDeleted_Confirmation_MSG;

	
public boolean isManualInvoiceNewPageIsLoaded()
{
	wait.until(ExpectedConditions.visibilityOf(ManualInvoiceNew_Label));
	return 	ManualInvoiceNew_Label.getText().trim().equalsIgnoreCase("Manual Invoice - New") & driver.getPageSource().contains("Vendor")
			& driver.getPageSource().contains("Invoice Date") & driver.getPageSource().contains("Invoice Number");
}

// To Select a vendor from the Vendor drop down list	

public ManualInvoiceNewPage selectAVendorFromDropdown(String vendorName)
	{
		wait.until(ExpectedConditions.visibilityOf(ManualInvoiceNew_Label));
		Select select = new Select(VendorList_DD);
		select.selectByVisibleText(vendorName);
		/*wait.until(ExpectedConditions.visibilityOf(Search_Label_01));
		boolean condition1=Search_Label_01.isDisplayed();
		Assert.assertEquals(condition1,true,"Search Text box is not Present on Manual Invoice new Page");
		boolean condition2=RowItemTable.isDisplayed();
		Assert.assertEquals(condition2,true,"table of raw items are not present on Manual Invoice New Page");*/
		return PageFactory.initElements(driver, ManualInvoiceNewPage.class);

	}

/*Select the Invoice date from the calendar
 * Invoice Date should be in MM/DD/YYYY format
 * */
public ManualInvoiceNewPage selectInvoiceDate(String invoiceDate)
{
	
	wait.until(ExpectedConditions.visibilityOf(ManualInvoiceNew_Label));
	int mon=Base.getMonthFromDate(invoiceDate);
	int day = Base.getDayFromDate(invoiceDate);
	//Select the date from the calendar
	//driver.findElement(By.xpath("//div[@class='xdsoft_calendar']/table/tbody/tr/td[@data-month="+(mon)+"]/div[text()="+day+"]")).click();
	driver.findElement(By.id("datetimepicker_purchases")).click();
//
	driver.findElement(By.xpath("//div[@class='xdsoft_calendar']/table/tbody/tr/td[@data-month="+(mon)+"]/div[text()="+day+"]")).click();
//
	return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
	
}

//click on plus sign for first row item in the list
public ManualInvoiceNewPage clickOnPlusSignForFirstRowItem()
	{
		wait.until(ExpectedConditions.visibilityOf(RowItemTable));
		RowItemTable_First_Add_BT.click();
		wait.until(ExpectedConditions.visibilityOf(Search_Label_02));
		boolean condition= 	Search_Label_02.isDisplayed() & Cancel_BT.isDisplayed() & Save_BT.isDisplayed() & Finalize_BT.isDisplayed()
						& driver.getPageSource().contains("UOM") & driver.getPageSource().contains("$/Case") & driver.getPageSource().contains("Quantity") & driver.getPageSource().contains("Sub-total") & driver.getPageSource().contains("Remove");
		Assert.assertEquals(condition, true);
//	Total_Label.getText().trim().equalsIgnoreCase("Total") & 
		return PageFactory.initElements(driver, ManualInvoiceNewPage.class);

}
	
// Enter the quantity in the quantity text box	

public 	ManualInvoiceNewPage enterQuantityInQuantityTextBox(String wrinId,String quantity) throws InterruptedException
{
		
		wait.until(ExpectedConditions.visibilityOf(Quantity_TB_List.get(0)));
		//Search_TB_02.clear();
		//.sendKeys(wrinId);
		//Thread.sleep(2000);
		Quantity_TB_List.get(0).clear();
		Quantity_TB_List.get(0).sendKeys(quantity);
		return PageFactory.initElements(driver, ManualInvoiceNewPage.class);

}
	
 //click on Finalize button

public ManualInvoiceNewPage clickOnFinalizeButton()
{
	//wait until finalize button is displaying
	 wait.until(ExpectedConditions.visibilityOf(Finalize_BT));
	 Finalize_BT.click();
	 //wait for the confirmation message
	 wait.until(ExpectedConditions.visibilityOf(FinalizePopUp_Continue_BT));
	return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
	
}

	// Click on Save button
	public ManualInvoiceNewPage clickOnSaveButton() {
		wait.until(ExpectedConditions.visibilityOf(Save_BT));
		Save_BT.click();
		return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
	}

/*Search the Raw Items
 * Return:"No Items Available"/"Items are Available"
 * Parameter:Wrin Id of row Item
 * */
public String searchRawItemWithWrinID(String Wrin) throws InterruptedException
{
	 wait.until(ExpectedConditions.visibilityOf(EnterQuickSearchWithSuggestionsForManualPurchases_TB));
	//enter the WRIN id in search box
	 EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(Wrin);
	Thread.sleep(2000);
	if(SearchedRawItem_Add_BT.isEmpty())
	{
		return "No Items Available";
	}
	else
	{
		return "Items are Available";
	}
	
}

/* Search and Add a Raw Item
 * Return:"No Items Available"/"Item is Added"/"Item is not Added"
 * Parameter:Wrin Id of row Item
 * */

public String searchAndAddFirstRowItem(String Wrin) throws InterruptedException
{
	
	String result=searchRawItemWithWrinID(Wrin);
	if(result.equalsIgnoreCase("No Item Available"))
	{
		return "No Items Available";
	}
	else
	{
		//click on plus button for First Searched item
		SearchedRawItem_Add_BT.get(0).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(Search_Label_02));
		 boolean condition=Search_Label_02.isDisplayed() 
				    & Cancel_BT.isDisplayed() 
				    & Save_BT.isDisplayed() 
				    & Finalize_BT.isDisplayed()
					& driver.getPageSource().contains("UOM") 
					& driver.getPageSource().contains("$/Case") 
					& driver.getPageSource().contains("Quantity") 
					& driver.getPageSource().contains("Sub-total") 
					& driver.getPageSource().contains("Remove");
		 if(condition)
		 {
			 return "Item is Added";
		 }
		 else
		 {
			 return "Item is not Added";
		 }
			 
		
	}

}

//Search and select a Raw item

public ManualInvoiceNewPage searchAndSelectARawItem(String wrinId) throws InterruptedException
{
	
	 EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(wrinId);
	 action.sendKeys(Keys.SPACE).build().perform(); 
     Thread.sleep(1500); 
     action.sendKeys(Keys.BACK_SPACE).build().perform();
	 int size=driver.findElements(By.xpath("//strong[text()='"+wrinId+"']")).size();
	 driver.findElement(By.xpath("(//strong[text()='"+wrinId+"'])["+size+"]")).click();
	 wait.until(ExpectedConditions.visibilityOf(AddWrinFromSearchBox_BT));
	 AddWrinFromSearchBox_BT.click();
	 return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
}
/* Create a Manual Purchase with given WRIN Id
 * 
 * 
 * 
 * */

public ManualInvoiceNewPage  createAManualPurchaseForWrinID(String Wrin,String vendorName,String quantity,String invoiceNumber) throws InterruptedException
{
	
	selectAVendorFromDropdown(vendorName);
	searchAndSelectARawItem(Wrin);
	enterQuantityInQuantityTextBox(Wrin,quantity);
	String invoiceDate = "01/20/2016";
	selectInvoiceDate(invoiceDate);
	InvoiceNumber_TB.sendKeys(invoiceNumber);	
	wait.until(ExpectedConditions.visibilityOf(Submit_BT)).click();
	//clickOnSaveButton();
	wait.until(ExpectedConditions.visibilityOf(InvoiceSaved_Confirmation_MSG));
	return PageFactory.initElements(driver, ManualInvoiceNewPage.class);

	
	
}
// Create a Manual Purchase with multiple WRIN Ids
public ManualInvoiceNewPage  createAManualPurchaseForTwoWrinID(String Wrin1,String Wrin2,String vendorName,String quantity,String invoiceNumber) throws InterruptedException
{
	selectAVendorFromDropdown(vendorName);
	searchAndSelectARawItem(Wrin1);
	searchAndSelectARawItem(Wrin2);
	enterQuantityInQuantityTextBox(Wrin1,quantity);
	enterQuantityInQuantityTextBox(Wrin2,quantity);
	InvoiceNumber_TB.sendKeys(invoiceNumber);
	clickOnSaveButton();
	wait.until(ExpectedConditions.visibilityOf(InvoiceSaved_Confirmation_MSG));
	return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
}

//Click on Continue button in Finalize PopUp and post the manual purchase*/
public ManualInvoiceNewPage postTheManualPurchage() throws InterruptedException{
	FinalizePopUp_Continue_BT.click();
	wait.until(ExpectedConditions.visibilityOf(InvoiceFinalize_Confirmation_MSG));
	Thread.sleep(5000);
	return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
	
}

//This method will verify that a vendor is listed in the vendor drop down in manual invoice new page*/
	public boolean verifyVendorDisplayed(String vendorName) {
		wait.until(ExpectedConditions.visibilityOf(VendorList_DD));
		Select vendorDD = new Select(VendorList_DD);
		List<WebElement> vendorList = vendorDD.getOptions();
		for (WebElement vendor : vendorList) {
			if (vendor.getText().equals(vendorName))
				return true;
		}
		return false;

	}
	
	public void clickOnApproveButtonForManualPurchase(String invoiceId){
		driver.findElement(By.xpath("//table[@id='eb_purchase_table']/tbody/tr/td/span[text()='"+invoiceId+"']/../following-sibling::td/eb-button[@id='eb_approve_button']/button")).click();
	}
	
	public boolean verifyManualInvoiceIsDisplayed(String invoiceId){
		return Base.isElementDisplayed(By.xpath("//table[@id='eb_purchase_table']/tbody/tr/td/span[text()='"+invoiceId+"']"));
	}

}
