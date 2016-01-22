package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import common.Base;

public class TransferLandingPage extends AbstractPage{

	public TransferLandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h1[text()='Transfers']")
	public WebElement TransferLandingPage_Label;
	
	
	@FindBy(xpath ="//div[@class='toast toast-error']/div[text()='Error']")
	public WebElement InsertNewTransfersPopup_Error_MSG;
	
	@FindBy(xpath ="//div[@class='toast-message' and text()='Changes Saved']")
	public WebElement ChangesSaved_Confirmation_MSG;
	
	@FindBy(xpath ="//input[@id='history_start_date']")
	public WebElement TransferLandingPage_StartDate_TB;
	
	@FindBy(xpath ="//input[@id='history_end_date']")
	public WebElement TransferLandingPage_EndDate_TB;

	@FindBy(xpath ="//button[@id='start_date_btn']")
	public WebElement TransferLandingPage_StartDateCalendar_BT;
	
	@FindBy(xpath ="//button[@id='end_date_btn']")
	public WebElement TransferLandingPage_EndDateCalendar_BT;
	
	@FindBy(xpath ="//button[@value='Refresh']")
	public WebElement TransferLandingPage_Refresh_BT;
	
	@FindBy(xpath ="//div[@class='eb-container-header-left']/eb-button/button[@value='Create New Transfers']")
	public WebElement CreateNewTransfers_BT;

	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[1]//strong[contains(.,'Type')]")
	public WebElement TransferDetailsPopup_Type_Label;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[2]//strong[contains(.,'National Store Number')]")
	public WebElement TransferDetailsPopup_NationalStoreNO_Label;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[3]//strong[contains(.,'Date & Time')]")
	public WebElement TransferDetailsPopup_DateTime_Label;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[5]//strong[contains(.,'Total Amount')]")
	public WebElement TransferDetailsPopup_TotalAmount_Label;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[6]//strong[contains(.,'Created By')]")
	public WebElement TransferDetailsPopup_CreatedBy_Label;
	
	@FindBy(xpath ="//button[@value='Print']")
	public WebElement TransferDetailsPopup_Print_BT;
	
	@FindBy(xpath ="//input[@id='autosearchInput']")
	public WebElement InsertNewTransfersPopup_InputNationalStoreNo_TB;
	
	@FindBy(xpath ="//input[@value='out']")
	public WebElement InsertNewTransfersPopup_TransferOut_RB;
	
	@FindBy(xpath ="//input[@value='in']")
	public WebElement InsertNewTransfersPopup_TransferIn_RB;
	
	@FindBy(xpath ="//input[@id='continue_transfer']")
	public WebElement InsertNewTransfersPopup_ContinueTransfer_BT;
	
	@FindBy(xpath ="//button[@value='Cancel']")
	public WebElement InsertNewTransfersPopup_Cancel_BT;
	
	@FindBy(xpath ="//input[@id='autosearchInput']")
	public WebElement AddTransferItemsPopup_RawItemsSearchBox_TB;
	
	@FindBy(xpath ="//input[@label='Outer Pack']")
	public WebElement AddTransferItemsPopup_OuterPack_TB;
	
	@FindBy(xpath ="//input[@label='Inner Pack']")
	public WebElement AddTransferItemsPopup_InnerPack_TB;
	
	@FindBy(xpath ="//input[@label='Loose Count']")
	public WebElement AddTransferItemsPopup_LooseUnits_TB;
	
	@FindBy(xpath ="//button[@value='Add']")
	public WebElement AddTransferItemsPopup_Add_BT;
	
	@FindBy(xpath ="(//table[@id='transfer_modal_tbl']/tbody[@id='transfer_modal_body'])[1]/tr")
	public List <WebElement> TransferDetailsPopup_Records_List;
	
	@FindBy(xpath ="//table[@id='transfercounts']/tbody/tr/td//button[@value='View']")
	public List <WebElement> TransferLandingPage_Records_List;
	
	@FindBy(xpath ="//button[@value='Cancel']")
	public WebElement AddTransferItemsPopup_Cancel_BT;
	
	@FindBy(xpath ="//div[@id='notTitle' and text()='Warning: Transfer Incomplete']")
	public WebElement AddTransferItemsPopup_Warning_Message;
	
	@FindBy(xpath ="//button[@id='htmlButton']/span[text()='Yes']")
	public WebElement AddTransferItemsPopup_Warning_Message_Yes_BT;
	
	@FindBy(xpath ="//button[@id='htmlButton']/span[text()='No']")
	public WebElement AddTransferItemsPopup_Warning_Message_No_BT;
	
	@FindBy(xpath ="//button[@value='Submit']")
	public WebElement AddTransferItemsPopup_Submit_BT;
	
	@FindBy(xpath ="//div[@id='eb_tp_input']/span")
	public WebElement InsertNewTransfersPopup_Time_Value;
	
	@FindBy(xpath = "//input[@id='office_transfer']")
	public WebElement transferToOffice_chkBox;

	@FindBy(xpath = "//input[@id='disp_date']")
	public WebElement dateForTransfer_Label;

	@FindBy(xpath = "//input[@id='disp_time']")
	public WebElement timeForTransfer_Label;

	@FindBy(xpath = "//tbody[@id='transfer_add_body']/tr/td[6]")
	public WebElement unitCountForTransfer_Value;

	@FindBy(xpath = "//div[@id='transfer_detail_modal_count_div']//b[contains(.,'Date & Time')]/parent::span/following-sibling::span/span[1]")
	public WebElement insertNewTransfersDate_label;

	@FindBy(xpath = "//div[@id='transfer_detail_modal_count_div']//b[contains(.,'Date & Time')]/parent::span/following-sibling::span/span[2]")
	public WebElement InsertNewTransfersTime_label;
	
	@FindBy(xpath="//div[@class='toast-message' and text()='Store Number is not valid']")
	public WebElement InvalidStoreNumber_Messag;
	
	@FindBy(xpath="//tbody[@id='transfer_modal_body']/tr/td[7]/span")
	public WebElement SubTotalForTransfer_Label;

	@FindBy(xpath="//input[@value='Close']")
	public WebElement TransferDetailPopUp_Close_BTN;
	
	@FindBy(xpath="//label[text()='Select a date and time for your new transfer:']")
	public WebElement InsertNewTransfersPopup_SelectDateTime_Label;
	
	@FindBy(id = "back-to-top")
	public WebElement BackToTop_BT;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[1]/span[2]/span")
	public WebElement SubmittedTransferPopup_Type_Value;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[2]/span[2]")
	public WebElement SubmittedTransferPopup_NationalStoreNO_Value;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[3]/span[2]/span[1]")
	public WebElement SubmittedTransferPopup_Date_Value;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[3]/span[2]/span[2]")
	public WebElement SubmittedTransferPopup_Time_Value;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[5]/span[2]")
	public WebElement SubmittedTransferPopup_TotalAmount_Value;
	
	@FindBy(xpath ="//div[@id='transfer_detail_modal_count_div']/div/div[6]/span[2]")
	public WebElement SubmittedTransferPopup_CreatedBy_Value;
	
	@FindBy(xpath ="//h5[@id='total_amount_div']/strong")
	public WebElement AddTransferPopup_TotalAmount_Value;
	
	@FindBy(xpath ="//input[@id='insert_new_transfer_date']")
	public WebElement AddTransferPopup_Date_TB;
	
	@FindBy(xpath ="//button/span[text()='Yes']")
	public WebElement CanceTransferPopup_Yes_BT;
	
	@FindBy(xpath ="//button[@id='htmlButton']/span[text()='No']")
	public WebElement CanceTransferPopup_No_BT;
	
	@FindBy(xpath ="//input[@id='transfer_input_total_pack']")
	public WebElement AddTransferItemsPopup_TotalUnits_TB;
	
	@FindBy(id="transfer_type_menu")
	public WebElement TransferType_DD;
	
	@FindBy(id="location_menu")
	public WebElement Location_DD;
	
	@FindBy(id="autosearchAddInputBtn")
	public WebElement AddTransferItemsPopup_AddRawItem_BT;;
	
	@FindBy(xpath="//input[@colname='inner_pack_count']")
	public WebElement AddTransferItemsPopup_InnerPackCount_TB;
	
	@FindBy(xpath="//input[@colname='case_count']")
	public WebElement AddTransferItemsPopup_CaseCount_TB;
	
	@FindBy(xpath="//input[@colname='loose_count']")
	public WebElement AddTransferItemsPopup_LooseCount_TB;	
	
	@FindBy(xpath="//button/span[text()='Yes']")
	public WebElement SubmitTransferConfirmationPopUp_Yes_BT;
	
	@FindBy(xpath="//button/span[text()='No']")
	public WebElement SubmitTransferConfirmationPopUp_No_BT;
	
	@FindBy(xpath ="(//button[@value='Print'])[1]")
	public WebElement AddTransferItemsPopup_Print_BT;
	
	@FindBy(xpath ="//div[@id='grand_amount']/span[2]/span")
	public WebElement ViewTransferItemsPopup_GrandTotal_Value;
	
	@FindBy(xpath ="//eb-button[@id='btn_print_2']/button")
	public WebElement ViewTransferItemsPopup_Print_BT;
	
	@FindBy(xpath ="//h2[@id='modal-title' and text()='Transfer']")
	public WebElement Transfer_Title;

	
	/*
	 * After selecting the store this method will click on the continue button
	 * and allow the user to search for a WRIN id. it will insert the
	 * outerPackQty, innerPackQty(if applicable) , looseUnitsQty and than add the transfer
	 */
	/*public TransferLandingPage insertAndAddDetailsToTransfer(String samplewRINID, String outerPackQty, String innerPackQty,String looseUnitsQty) throws InterruptedException {
		// Click on the continue transfer button in Insert New Transfers Popup
		//InsertNewTransfersPopup_ContinueTransfer_BT.click();
		// Enter the WRIN Id
		AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(samplewRINID);
		driver.findElement(By.xpath("//strong[text()='" + samplewRINID + "']")).click();
		AddTransferItemsPopup_AddRawItem_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_OuterPack_TB));
		// Enter the outerPackQty for the food item
		AddTransferItemsPopup_OuterPack_TB.sendKeys(outerPackQty);
		try {
			// Enter the innerPackQty for the food item
			AddTransferItemsPopup_InnerPack_TB.sendKeys(innerPackQty);
		} catch (Exception e) {
			// innerPackQty is not applicable for the food item
		}
		// Enter the looseUnitsQty for the food item
		AddTransferItemsPopup_LooseUnits_TB.sendKeys(looseUnitsQty);
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_Add_BT));
		// Add the transfer
		AddTransferItemsPopup_Add_BT.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_Submit_BT));
		return PageFactory.initElements(driver, TransferLandingPage.class);

	}*/
	
	public TransferLandingPage insertAndAddDetailsToTransfer(String samplewRINID, String outerPackQty, String innerPackQty,String looseUnitsQty) throws InterruptedException {
		seacrhAndSelectRawItem(samplewRINID);
		Thread.sleep(3000);
		// Enter the outerPackQty for the food item
		Actions actions = new Actions(driver);
		actions.moveToElement(AddTransferItemsPopup_CaseCount_TB);
		// actions.click();
		actions.perform();
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_CaseCount_TB));
		AddTransferItemsPopup_CaseCount_TB.sendKeys(outerPackQty);
		try {
			// Enter the innerPackQty for the food item
			AddTransferItemsPopup_InnerPackCount_TB.sendKeys(innerPackQty);
		} catch (Exception e) {
			// innerPackQty is not applicable for the food item
		}
		// Enter the looseUnitsQty for the food item
		AddTransferItemsPopup_LooseCount_TB.sendKeys(looseUnitsQty);
		Transfer_Title.click();
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_Submit_BT));
		return PageFactory.initElements(driver, TransferLandingPage.class);
	}
	
	
	//Create a transfer IN or Out type of transaction
	public TransferLandingPage createATransferTransaction(String samplewRINID,String nationalStoreNumber,String type ,String outerPackQty, String innerPackQty,String looseUnitsQty) throws InterruptedException
	{
		//click on insert new transfer button
		CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(InsertNewTransfersPopup_InputNationalStoreNo_TB));
		InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(nationalStoreNumber);
		//click on transfer out or in
		if(type.equalsIgnoreCase("OUT"))
		{
			InsertNewTransfersPopup_TransferOut_RB.click();
		}
		else
		{
			InsertNewTransfersPopup_TransferIn_RB.click();
		}
		insertAndAddDetailsToTransfer(samplewRINID, outerPackQty, innerPackQty, looseUnitsQty);
		Thread.sleep(3000);
		//click on submit button
		AddTransferItemsPopup_Submit_BT.click();
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOf(ChangesSaved_Confirmation_MSG));
		return PageFactory.initElements(driver, TransferLandingPage.class);
		
	}
	
	public boolean verifyTransferPlaced(String date, String time, String amount) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(CreateNewTransfers_BT));
		System.out.println("//table[@id='transfercounts']//tr/td/span[text()='"+date+"']/../following-sibling::td/span[text()='"+amount+"']");
		return Base.isElementDisplayed(By.xpath("//table[@id='transfercounts']//tr/td/span[text()='"+date+"']/../following-sibling::td/span[text()='"+amount+"']"));
	}
	
	public void selectDateInAddNewTransferPopUp(String day, String month) throws InterruptedException{
		AddTransferPopup_Date_TB.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[3]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
	}
	
	public TransferLandingPage selectTransferType(String transferTypeValue){
		Select selectTransferType = new Select(TransferType_DD);
		TransferType_DD.click();
		selectTransferType.selectByVisibleText(transferTypeValue);
		return PageFactory.initElements(driver, TransferLandingPage.class);
	}
	
	public TransferLandingPage selectLocationToTransfer(String storeId){
		Select selectLocation = new Select(Location_DD);
		selectLocation.selectByVisibleText(storeId);
		return PageFactory.initElements(driver, TransferLandingPage.class);
	}
	
	public TransferLandingPage seacrhAndSelectRawItem(String samplewRINID) throws InterruptedException{
		AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(samplewRINID);
		driver.findElement(By.xpath("//strong[text()='" + samplewRINID + "']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(AddTransferItemsPopup_AddRawItem_BT));
		AddTransferItemsPopup_AddRawItem_BT.click();
		return PageFactory.initElements(driver, TransferLandingPage.class);
	}
	
	public void viewTransfer(String date, String time, String amount) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(CreateNewTransfers_BT));
		WebElement viewTransfer_BT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@id='transfercounts']//tr/td/span[text()='"+date+"']/../following-sibling::td/span[text()='"+amount+"'])[1]/../following-sibling::td//button[@value='View']")));
		Thread.sleep(5000);
		viewTransfer_BT.click();
		//System.out.println("//table[@id='transfercounts']//tr/td/span[text()='"+date+"']/../following-sibling::td/span[text()='"+amount+"']");
		//driver.findElement(By.xpath("(//table[@id='transfercounts']//tr/td/span[text()='"+date+"']/../following-sibling::td/span[text()='"+amount+"'])[1]/../following-sibling::td//button[@value='View']")).click();
	}
	
}
