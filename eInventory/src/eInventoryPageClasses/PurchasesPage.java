package eInventoryPageClasses;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;

public class PurchasesPage extends AbstractPage
{
	public PurchasesPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[text()='purchases']")
	public WebElement Purchases_Label;	
	
	@FindBy(id="title_id")
	public WebElement PurchaseForStoreNumber_Title;
	
	@FindBy(id="pend_purchase_id")
	public WebElement PendingPurchases_Title;
	
	@FindBy(xpath="//strong[text()='Pending Adjustments']")
	public WebElement PendingAdjustments_Title;
	
	@FindBy(xpath="//input[@id='enter_manual_purchase']")
	public WebElement EnterManualPurchase_BT;
	
	@FindBy(xpath="//button[@value='View Ledger']")
	public WebElement ViewStoreLedger_BT;
	
	@FindBy(xpath="//input[@value='Get Purchase History']")
	public WebElement GetPurchaseHistory_BT;
	
	@FindBy(xpath="//tbody/tr[@class='even' or @class='odd']")
	public List<WebElement> Invoices_List;
	
	@FindBy(xpath="//button[@id='start_calendar_btn']")
	public WebElement StartDate_BT; 
	
	@FindBy(xpath="//button[@id='end_calendar_btn']")
	public WebElement EndDate_BT; 
	
	@FindBy(xpath="//h4[text()='Posted Purchase Detail']")
	public WebElement PostedPurchaseDetailPopUp_PostedPurchaseDetail_Label; 
	
	@FindBy(xpath="//div[@id='total_food']")
	public WebElement PostedPurchaseDetailPopUp_TotalFood_Value; 
	
	@FindBy(xpath="//div[@id='total_paper']")
	public WebElement PostedPurchaseDetailPopUp_TotalPaper_Value; 
	
	@FindBy(xpath="//div[@id='total_row']/b/span")
	public WebElement PostedPurchaseDetailPopUp_TotalPurchases_Value; 
	
	@FindBy(xpath="//div[@id='outside_table']//span[4]/span")
	public WebElement PostedPurchaseDetailPopUp_InvoiceDate_Value; 
	
	@FindBy(xpath="//div[@id='outside_table']//span[5]/span")
	public WebElement PostedPurchaseDetailPopUp_DeliveryDate_Value; 
	
	@FindBy(xpath="//th[text()='Audit']")
	public WebElement Audit_Label; 
	
	@FindBy(xpath="//b[text()='Audit For Manual Purchase Invoice on ']")
	public WebElement AuditPopUp_AuditForManualPurchase_Title; 
	
	@FindBy(xpath="//table[@id='purchase_audit_modal_tbl']//th[text()='Time Stamp']")
	public WebElement AuditPopUp_TimeStamp_Column_Label; 
	
	@FindBy(xpath="//table[@id='purchase_audit_modal_tbl']//th[text()='Field Name']")
	public WebElement AuditPopUp_FieldName_Column_Label; 
	
	@FindBy(xpath="//table[@id='purchase_audit_modal_tbl']//th[text()='Before Value']")
	public WebElement AuditPopUp_BeforeValue_Column_Label; 
	
	@FindBy(xpath="//table[@id='purchase_audit_modal_tbl']//th[text()='After Value']")
	public WebElement AuditPopUp_AfterValue_Column_Label; 
	
	@FindBy(xpath=".//*[@id='posted_purchases_selection_table']/tbody/tr[@class='even' or @class='odd']")
	public List<WebElement> postedPurchase_List;

	@FindBy(xpath=".//th[text()='WRIN']")
	public WebElement PostedPurchaseDetailPopUp_WRINColumn_Label;

	@FindBy(xpath=".//th[text()='Description']")
	public WebElement PostedPurchaseDetailPopUp_descriptionColumn_Label;

	@FindBy(xpath=".//th[text()='UOM']")
	public WebElement PostedPurchaseDetailPopUp_UOMColumn_Label;

	@FindBy(xpath=".//th[text()='UOM/Case']")
	public WebElement PostedPurchaseDetailPopUp_UOMCaseColumn_Label;

	@FindBy(xpath=".//th[text()='Case Price']")
	public WebElement PostedPurchaseDetailPopUp_CasePriceColumn_Label;

	@FindBy(xpath=".//th[text()='Cases Purchased']")
	public WebElement PostedPurchaseDetailPopUp_CasesPurchasedColumn_Label;

	@FindBy(xpath=".//th[text()='Cost']")
	public WebElement PostedPurchaseDetailPopUp_costColumn_Label;
	
	@FindBy(xpath="//div[@id='outside_table']/div/div[1]/span[2]/span")
	public WebElement PostedPurchaseDetailPopUp_VendorName_Value;
	
	@FindBy(id="purchase_modal")
	public WebElement PostedPurchasePopUp_Title;
	
	@FindBy(xpath="//label[contains(.,'Invoice ID')]")
	public WebElement PostedPurchaseDetailPopUp_InvoiceId_Label;
	
	@FindBy(xpath="//label[contains(.,'Invoice Date')]")
	public WebElement PostedPurchaseDetailPopUp_InvoiceDate_Label;
	
	@FindBy(xpath="//label[contains(.,'Delivery Date')]")
	public WebElement PostedPurchaseDetailPopUp_DeliveryDate_Label;
	
	@FindBy(xpath="//div[(text()='Total Purchases:')]")
	public WebElement PostedPurchaseDetailPopUp_TotalPurchases_Label;
	
	@FindBy(xpath="//eb-button[@id='restore_purchases']/button")
	public WebElement RestorePurchases_BT;
	
	@FindBy(xpath="//h2[text()='Restore Manual Invoice']")
	public WebElement RestoreManualInvoice_Title;
	
	@FindBy(xpath="//eb-button[@id='restore_purchase_modal_restore_btn']/button")
	public WebElement RestoreManualInvoice_Restore_BT;
	
	@FindBy(xpath="//input[@id='restore_purchases_confirmation']")
	public WebElement RestorePurchasesConfirmationPopUp_RestorePurchase_BT;
	
	@FindBy(xpath="//button[@value='Create Manual Invoice']")
	public WebElement CreateManualInvoice_BT; 
	
	@FindBy(xpath = "//eb-button[@id='purchase_modal_approve_btn']/button")
	public WebElement ManualInvoiceApprove_BT;
	
	
	//To verify purchase landing page loaded successfully	
	public boolean isPurchaseLandingPageLoaded()
	{

		wait.until(ExpectedConditions.visibilityOf(PurchaseForStoreNumber_Title));
		/*	
		 *  Verify that "Purchase For Store" title is displaying
		 *  Pending Purchases title is displaying
		 *  Date,Type and Vendor columns are displaying
		 *  A Label 'Purchase History List' is displaying
		 *  Start date and End date are displaying under purchase History List Section
		 *  "View Store Ledger" button is displaying
		 *  "Get Purchase History" button is displaying.
		 */
		return 		PurchaseForStoreNumber_Title.getText().contains("Purchases for Store Number") 
					& PendingPurchases_Title.getText().equalsIgnoreCase("Pending Purchases:") 
					& driver.getPageSource().contains("Date")
					& driver.getPageSource().contains("Type") & driver.getPageSource().contains("Vendor") & driver.getPageSource().contains("Purchase History List")
					& driver.getPageSource().contains("Start Date:") & driver.getPageSource().contains("End Date:") & EnterManualPurchase_BT.isDisplayed() & ViewStoreLedger_BT.isDisplayed()
					& GetPurchaseHistory_BT.isDisplayed();

	
	}
	
//To verify Any Invoice Present or not on the page	
	public boolean isAnyInvoicePresent() {
		int size = Invoices_List.size();

		if (size == 0) {
			return false;
		} else {
			return true;
		}

	}
	
//To Verify manual invoice present or not
	public boolean isManualInvoicePresent() {
		wait.until(ExpectedConditions.visibilityOf(PurchaseForStoreNumber_Title));
		int size = driver.findElements(By.xpath("//tbody/tr[@class='even' or @class='odd']/td[2]")).size();
		for (int i = 1; i <= size; i++) {
			String text = driver.findElement(By.xpath("(//tbody/tr[@class='even' or @class='odd']/td[2])["+ i + "]")).getText();
			if (text.equalsIgnoreCase("Manual")) {
				return true;
			}
		}
		return false;
	}
	
// Go to Create manual invoice page	
	public ManualInvoiceNewPage goToManualInvoiceNewPage()
	{
		wait.until(ExpectedConditions.elementToBeClickable(CreateManualInvoice_BT));
		CreateManualInvoice_BT.click();
		return PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		
	}
//Click on the invoice with the given invoice number
	
	public ManualInvoiceEditPage clickOntheInvoice(String invoiceNumber)
	{
		String todayDate=Base.returnTodayDate();
		driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr/td[1][descendant::span[text()='"+todayDate+"'] and following-sibling::td/span[text()='"+invoiceNumber+"']]/span")).click();
		ManualInvoiceEditPage manualInvoiceEditPage=PageFactory.initElements(driver, ManualInvoiceEditPage.class);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.Search_Label_02));
		return PageFactory.initElements(driver, ManualInvoiceEditPage.class);

	}
	
//Go to Manual Invoice-Edit Page for any manual invoice by double clicking on it	
	
	
	public ManualInvoiceEditPage doubleClickOnFirstAvailableManualInvoice() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(PurchaseForStoreNumber_Title));
		int size=driver.findElements(By.xpath("//table[@id='purchases_selection_table']/tbody/tr")).size();
		for(int i=1;i<=size;i++)
		{
			String text=driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]")).getText();
			if(text.equalsIgnoreCase("Manual"))
			{
				action.moveToElement(driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+(i)+"]/td[1]"))).doubleClick().build().perform();			
				break;

			}
		}
		//wait for primary table to be loaded
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='wrin_list_tbl']//tr")));
		//Wait for the secondary table to be loaded
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@id='invoice_tbl_body']/tr")));
		//Manual Invoice title
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='manual_invoice_title']")));
		
		return PageFactory.initElements(driver, ManualInvoiceEditPage.class);
		
	}
	
// click on View Store Ledger Button
	
	public StoreLedgerDetailPage clickOnViewStoreLedgerButton()
	{
//		wait.until(ExpectedConditions.visibilityOf(Purchases_Label));
		ViewStoreLedger_BT.click();
		//Wait for the Store Ledger title
		StoreLedgerDetailPage storeLedgerDetailPage=PageFactory.initElements(driver, StoreLedgerDetailPage.class);
		//wait.until(ExpectedConditions.visibilityOf(storeLedgerDetailPage.StoreLedgerForStoreNumber_Title));
		return PageFactory.initElements(driver, StoreLedgerDetailPage.class);
		
	}
	
	
	//Verify vendor,type and date is visible for for all manual and electronic invoice in pending purchase list	
	
		public boolean isDateTypeVendorPresentForAllPendingManualAndElectronicInvoice() throws InterruptedException
		
		{
			boolean result = true;
			wait.until(ExpectedConditions.visibilityOf(PurchaseForStoreNumber_Title));
			int size=driver.findElements(By.xpath("//table[@id='purchases_selection_table']/tbody/tr")).size();
			for(int i=1;i<=size;i++)
			{
			String text=driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/span")).getText();
			if(text.equalsIgnoreCase("Manual"))
				{
				
		    result=	 driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/span")).getText().isEmpty() &			
			driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/preceding-sibling::td")).getText().isEmpty() &
	        driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/following-sibling::td")).getText().isEmpty();
		

				}
			if(text.equalsIgnoreCase("Electronic"))
			{
			
	        result=	 driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]")).getText().isEmpty() &			
		    driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/preceding-sibling::td")).getText().isEmpty() &
	        driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/following-sibling::td")).getText().isEmpty();


			}
			
			}
			
			return !result;
			
		}	
	

		/*@Author :Hemlata
		This method will take date and invoice id as argument and click on the related posted purchase 
		from the posted purchase list in Purchase Page*/
		public void clickOnPostedPurchaseRecord(String date,String invoiceId){
			wait.until(ExpectedConditions.visibilityOfAllElements(postedPurchase_List));
			action.moveToElement(driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr/td[1][descendant::span[text()='"+date+"'] and following-sibling::td/span[text()='"+invoiceId+"']]/span"))).doubleClick().build().perform();

		}
		
		//@Author : Hemlata
		//This method will take WRIN Id as argument and return subtotal value for that food item from Posted Purchase Detail Pop up
		public String getSubtotalValueForFoodItemPostedPurchase(String WRINId){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='purchase_detail_table']")));
			return driver.findElement(By.xpath("(//table[@id='purchase_detail_table']/tbody/tr/td/span[contains(text(),'"+WRINId+"')]/parent::td/following-sibling::td)[6]/span")).getText();
		}

	// This method will take invoiceID as a argument and will click on the 'Audit' button for this invoice
	public PurchasesPage clickOnAuditButtonForInvoice(String invoiceID) {
		driver.findElement(By.xpath("//table[@id='purchases_selection_table']//tr/td/span[text()='"+ invoiceID + "']/../following-sibling::td[6]/span")).click();
		wait.until(ExpectedConditions.visibilityOf(AuditPopUp_AuditForManualPurchase_Title));
		return PageFactory.initElements(driver, PurchasesPage.class);
	}
		
	public int getNumberOfRecords() {
		return driver.findElements(By.xpath("//table[@id='purchases_selection_table']/tbody/tr")).size();
	}

	//This method will verify that pending transaction is displayed in purchase page
	public boolean verifyPendindInvoiceIsPresent(String invoiceNumber) {
		String todayDate = Base.returnTodayDate();
		return Base.isElementDisplayed(By.xpath("//table[@id='eb_purchase_table']/tbody/tr/td/span[text()='"+invoiceNumber+"']"));
		/*return Base.isElementDisplayed(By
						.xpath("//table[@id='purchases_selection_table']//tr/td[1][descendant::span[text()='"+ todayDate
								+ "'] and following-sibling::td/span[text()='"+ invoiceNumber + "']]/span"));*/
	}
	
	//This method will verify that purchase history records will display for selected date range
	public boolean verifyPurchaseHistoryDisplayedForSelectedDateRange(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = new Date();
		// Convert the start date into the date format
		startDate = sdf.parse(date1);
		Date endDate = new Date();
		// Convert the end date into the date format
		endDate = sdf.parse(date2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='posted_purchases_selection_table']")));
		// Verify that only the records between the start and end date is displaying
		int size = driver.findElements(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr")).size();
		for (int i = 1; i <= size; i++) {
			String date3 = driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr["+ i + "]/td[1]")).getText();
			Date recordDate = new Date();
			recordDate = sdf.parse(date3);
			if ((recordDate.before(endDate) & recordDate.after(startDate))|| date3.equalsIgnoreCase(date1) | date3.equalsIgnoreCase(date2)) {
				if (i == size) {
					return true;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	// This method will verify that pending transaction is displayed in purchase page
	public boolean verifyPurchaseHistoryRecordsAreInAscendingOrder() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		int size = driver.findElements(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr")).size();
		for (int j = 1; j <= size; j++) {
			if (j >= 2) {
				String date3 = driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr["+ j + "]/td[1]")).getText();
				Date recordDate = new Date();
				recordDate = sdf.parse(date3);
				String date4 = driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr["+ (j - 1) + "]/td[1]")).getText();
				Date previousRecordDate = new Date();
				previousRecordDate = sdf.parse(date4);
				if (previousRecordDate.after(recordDate)|| date4.equalsIgnoreCase(date3)) {
					if (j == size) {
						return true;
					}
				}else{return false;}
			}
		}
		return false;
	}
	
	//This method will take row number, wrinId, Description,Uom, and Uom case values and verify these values for a posted purchase
	public boolean verifyRawItemValuesInPostesPurchase(int rowNum, String wrinId,String description,String uom,String uomCase){
		boolean result = driver.findElement(By.xpath("//table[@id='purchase_detail_table']/tbody/tr["+rowNum+"]/td[1]/span")).getText().trim().equalsIgnoreCase(wrinId)
		& driver.findElement(By.xpath("//table[@id='purchase_detail_table']/tbody/tr["+rowNum+"]/td[2]/span")).getText().trim().equalsIgnoreCase(description)
		& driver.findElement(By.xpath("//table[@id='purchase_detail_table']/tbody/tr["+rowNum+"]/td[3]/span")).getText().trim().equalsIgnoreCase(uom)
		& driver.findElement(By.xpath("//table[@id='purchase_detail_table']/tbody/tr["+rowNum+"]/td[4]/span")).getText().trim().equalsIgnoreCase(uomCase);
		return result;
	}
	
	public String getTotalPurchaseCostForPostedPurchase(){
		List<WebElement>rawItemList = driver.findElements(By.xpath("//table[@id='purchase_detail_table']/tbody/tr[@role='row']"));
		BigDecimal rawItemCost = new BigDecimal(0.00);
		for(int i= 1;i<=rawItemList.size();i++){
		rawItemCost = rawItemCost.add(new BigDecimal(driver.findElement(By.xpath("(//table[@id='purchase_detail_table']/tbody/tr[@role='row'])["+i+"]/td[7]/span")).getText()));
		}
		return String.valueOf(rawItemCost);
	}
	
	public void restoreDeletedPurchases(String invoiceId){
		wait.until(ExpectedConditions.visibilityOf(RestoreManualInvoice_Title));
		driver.findElement(By.xpath("//tr[contains(@class,'deleted_purchases_history')]/td[text()='"+invoiceId+"']/preceding-sibling::td[@class='restore_purchase select-checkbox']")).click();
		wait.until(ExpectedConditions.visibilityOf(RestoreManualInvoice_Restore_BT)).click();
	}
}
