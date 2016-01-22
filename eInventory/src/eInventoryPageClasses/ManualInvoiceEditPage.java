package eInventoryPageClasses;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManualInvoiceEditPage extends AbstractPage
{

	public ManualInvoiceEditPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//*[@id='manual_invoice_title']")
	 public WebElement ManualInvoice_Edit_Label;
	
	@FindBy(xpath="//input[@id='complete_button']")
	 public WebElement Finalize_BT;
	
	@FindBy(xpath="//input[@id='continue_finalize']")
	public WebElement FinalizePopUp_Continue_BT;
	
	@FindBy(xpath="//input[@id='close_button']")
	 public WebElement Cancel_BT;
	
	@FindBy(xpath="//input[@id='save_button']")
	 public WebElement Save_BT;
	
	@FindBy(xpath="//div[@class='toast-message' and contains(.,'Your invoice has been saved')]")
	public WebElement InvoiceSave_Confirmation_Message;
	
	@FindBy(xpath="//div[@class='toast-message' and contains(.,'Your invoice has been posted')]")
	public WebElement InvoiceFinalize_Confirmation_Message;
	
	@FindBy(xpath="//a[contains(.,'Inventory')]//b[@class='caret']")
	public WebElement Inventory_DD_Button;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']/li[contains(.,'Purchases')]")
	public WebElement Inventory_DD_Purchases;
	
	@FindBy(id="vendor_list")
	public WebElement VendorList_DD;
	
	@FindBy(xpath="//input[@id='invoice_identifier']")
	public WebElement InvoiceNumber_TB;
	
	@FindBy(xpath = "//label[contains(text(),'Invoice Number:')]")
	public WebElement InvoiceNumber_Label;
	
	@FindBy(xpath = "//label[contains(text(),'Invoice Date:')]")
	public WebElement InvoiceDate_Label;
	
	@FindBy(xpath = "//label[contains(text(),'Vendor:')]")
	public WebElement Vendor_Label;
	
	@FindBy(xpath = "//div[div[@class='col-sm-4']]/div[2]/div/div/div/label/input")
	public WebElement SubSection_Search_TB;
	
	@FindBy(xpath = "//div[@class='col-xs-10 text-right']")
	public WebElement Total_Label;
	
	@FindBy(xpath = "//input[@id='autocomplete']")
	public WebElement EnterQuickSearchWithSuggestionsForManualPurchases_TB;
	
	@FindBy(xpath = "//tbody[@id='invoice_tbl_body']/tr/td[7]/input")
	public List<WebElement> Quantity_TB;
	
	@FindBy(xpath = "//div[contains(text(),'Your invoice has been saved')]")
	public WebElement InvoiceSaved_Confirmation_MSG;
	
	@FindBy(xpath="//table[@id='wrin_list_tbl']")
	public WebElement RowItemTable;
	
	@FindBy(xpath="//div[@id='wrin_list_tbl_wrapper']//label[contains(. ,'Search')]")
	public WebElement Search_Label_01;

	@FindBy(xpath="//div[@id='invoice_tbl_filter']/label[contains(.,'Search')]")
	public WebElement Search_Label_02;
	
	@FindBy(xpath="//table[@id='wrin_list_tbl']//tr[2]/td[3]/button")
	public WebElement RowItemTable_First_Add_BT;
	
	@FindBy(id="delete_button")
	public WebElement Delete_BT;
	
	@FindBy(xpath="//button[@class='btn btn-default' and text()='Cancel']")
	public WebElement DeletePopup_Cancel_BT; 
	
	@FindBy(xpath="//button[@class='btn btn-danger']")
	public WebElement DeletePopup_OK_BT;
	
	@FindBy(xpath="//input[@class='btn btn-danger']")
	public WebElement CancelPopup_OK_BT;
	
	@FindBy(xpath="//input[@class='btn btn-default']")
	public WebElement CancelPopup_Cancel_BT; 
	
	@FindBy(xpath="//th[text()='WRIN']")
	public WebElement WRIN_Column_Label;
	
	@FindBy(xpath="//th[text()='Description']")
	public WebElement Description_Column_Label;
	
	@FindBy(xpath="//th[text()='UOM']")
	public WebElement UOM_Column_Label;
	
	@FindBy(xpath="//th[text()='UOM/Case']")
	public WebElement UOMCase_Column_Label;
	
	@FindBy(xpath="//th[text()='$/Case']")
	public WebElement DollerCase_Column_Label;
	
	@FindBy(xpath="//th[text()='Quantity']")
	public WebElement Quantity_Column_Label;
	
	@FindBy(xpath="//th[text()='Sub-total']")
	public WebElement SubTotal_Column_Label;
	
	@FindBy(xpath="//th[text()='Remove']")
	public WebElement Remove_Column_Label;
	
	@FindBy(xpath="//button[@class='btn btn-link btn-link']")
	public List <WebElement> Remove_Link_List;
	
	//@Author : Hemlata
	@FindBy(xpath="(//div[@class='xdsoft_time_variant'])[1]/div[contains(@class,'xdsoft_current')]/preceding-sibling::div[1]")
	public WebElement previousToCurrentTime_label;

	//@Author : Hemlata
	@FindBy(xpath="(//div[@class='xdsoft_time_variant'])[1]/div[contains(@class,'xdsoft_current')]")
	public WebElement finalizeWindowSelectdTime_label;

	//@Author : Hemlata
	@FindBy(xpath=".//*[@id='start_inv_div']/div/div[1]/div/div/div[contains(@class,'xdsoft_timepicker')]/button[@class='xdsoft_prev']")
	public WebElement datePickerPrevious_Button;

	//@Author : Hemlata
	@FindBy(xpath=".//*[@id='disp_date2']")
	public WebElement finalizeWindowDateField_label;

	//@Author : Hemlata
	@FindBy(xpath=".//*[@id='disp_time2']")
	public WebElement finalizeWindowTimeField_label;

	//@Author : Hemlata
	@FindBy(xpath=".//label[contains(text(),'When did your truck pull off the parking lot?:')]")
	public WebElement finalizeWindowForm_label;
	
	@FindBy(xpath="//input[@id='disp_date']")
	public WebElement invoiceDate_TB;
	
	@FindBy(xpath="//tbody[@id='invoice_tbl_body']/tr[contains(.,'No search results found')]")
	public WebElement NoSearchResultsFound_Message_Label;
	
	@FindBy(xpath="//div[@class='toast-message' and contains(.,'Invoice deleted.')]")
	public WebElement InvoiceDeleted_Confirmation_MSG;
	
	public boolean isManualInvoiceEditPageIsLoaded() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(ManualInvoice_Edit_Label));
		Thread.sleep(4000);
		String text=ManualInvoice_Edit_Label.getText();
		text=text.trim();
		System.out.println("Text "+text);
		/* Verify the following fields
		 * Cancel button
		 * Finalize button
		 * Manual Invoice Edit header
		 * Save button
		 * Invoice number label
		 * Invoice date label
		 * Subsection Search text box
		 * Total label
		 * Main section Enter Quick Search With Suggestions for Manual Purchases:  text box
		 */
		return    Cancel_BT.getAttribute("value").contentEquals("Cancel")
				& Finalize_BT.getAttribute("value").contentEquals("Finalize")
				& text.equalsIgnoreCase("Manual Invoice - Edit")
				& Save_BT.getAttribute("value").contentEquals("Save")
				& InvoiceNumber_Label.isDisplayed()
				& InvoiceDate_Label.isDisplayed() & Vendor_Label.isDisplayed()
				& SubSection_Search_TB.isDisplayed()
				& Total_Label.getText().contains("Total")
				& EnterQuickSearchWithSuggestionsForManualPurchases_TB.isDisplayed();
	}
	
	
	public void enterQuantityAndSave(String val) 
	{
		/*entering quantity in quantity box of last record
		 * clicking save button
		 * verify the confirmation message
		 * verify the quantity has been updated
		 */
		Quantity_TB.get(Quantity_TB.size()-1).clear();
        Quantity_TB.get(Quantity_TB.size()-1).sendKeys(val);
		//enter the 
		Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(InvoiceSaved_Confirmation_MSG));
		
		
	}
	
	
	
	//click on plus sign for first row item in the list
	public ManualInvoiceEditPage clickOnPlusSignForFirstRowItem()
		{
			wait.until(ExpectedConditions.visibilityOf(RowItemTable));
			RowItemTable_First_Add_BT.click();
			return PageFactory.initElements(driver, ManualInvoiceEditPage.class);

	}
	
	
	public ManualInvoiceEditPage removeAllItemsOfSubSection() throws InterruptedException
	{
			
		int size=driver.findElements(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td[9]/button")).size();
		System.out.println("total element"+size);
		for(int i=1;i<=size;i++)
		{
			
			driver.findElement(By.xpath("//tbody[@id='invoice_tbl_body']/tr[1]/td[9]/button")).click();
			Thread.sleep(2000);
		}
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td[9]/button")));
		return PageFactory.initElements(driver, ManualInvoiceEditPage.class);

	}
	
	
	//Search and select a Raw item

	public ManualInvoiceEditPage searchAndSelectARawItem(String wrinId) throws InterruptedException
	{
		
		 EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(wrinId);
		 action.sendKeys(Keys.SPACE).build().perform(); 
	     Thread.sleep(1500); 
	     action.sendKeys(Keys.BACK_SPACE).build().perform();
		 driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		 return PageFactory.initElements(driver, ManualInvoiceEditPage.class);
	}
	
	

	//This method will take WRIN Id as argument and return subtotal value for that food item from Manual Invoice Edit Page
	public String getSubtotalValueForFoodItemPurchase(String WRINId){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='invoice_tbl_body']")));
		return driver.findElement(By.xpath(".//*[@id='invoice_tbl_body']/tr/td[contains(text(),'"+WRINId+"')]/following-sibling::td[contains(@class,'sub_total')]")).getText();
	}
	
	public int getNumberOfItemInPurchase(){
		return  driver.findElements(By.xpath("//tbody[@id='invoice_tbl_body']/tr")).size();
	}
	

}
