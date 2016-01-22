package sprint10;

import java.io.IOException;
import java.util.regex.Pattern;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.Base;
import common.GlobalVariable;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.StoreControlSettingsPage;

public class US1055_AbilityToSetTheTransferAlertLimitAmount extends AbstractTest{
	
	//TC1807: Verify that the user should be able to view a new area named "Transfer Limits" on the Store Control Settings page.
	@Test()
	public void sprint10_US1055_TC1807() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Verify that user should be able to view a new area named "Transfer Limits" on the Store Control Settings page
		if (Base.isElementDisplayed(storeControlSettingsPage.TransferLimits_Label)) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1807",
					"User should be able to view a new area named 'Transfer Limits' on the Store Control Settings page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1807","sprint10_US1055_TC1807",
					"User should be able to view a new area named 'Transfer Limits' on the Store Control Settings page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1807");
		}
	}
	
	//TC1810: Verify that the user should be able to enable the 'transfer limits' box when restaurant exceeds the transfer limit.
	@Test()
	public void sprint10_US1055_TC1810() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the default state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		boolean result;
		//If checkbox is already checked first uncheck and than check the checkbox 
		if (toggle != null) {
			storeControlSettingsPage.TransferLimits_CB.click();
			Thread.sleep(1500);
			storeControlSettingsPage.TransferLimits_CB.click();
			result = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked").equals("true");
		} else {
			//If checkbox is disabled by default then verify that user is able to check the check box
			storeControlSettingsPage.TransferLimits_CB.click();
			result = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked").equals("true");
		}
		//Verify that the user should be able to enable the 'transfer limits' box when restaurant exceeds the transfer limit.
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1810",
					"User should be bale to view the tick mark in the box provider to enable transfer limits.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1810","sprint10_US1055_TC1810",
					"User should be bale to view the tick mark in the box provider to enable transfer limits.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1810");
		}
	}
	
	//TC1811: Verify that the user should be able to disable the 'transfer limits' box when restaurant exceeds the transfer limit.
	@Test()
	public void sprint10_US1055_TC1811() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		boolean result;
		if (toggle != null) {
			//if check box is enabled than disable the checkbox and verify that user is able to disable the checkbox
			storeControlSettingsPage.TransferLimits_CB.click();
			result = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked") == null;
		} else {
			//if check box is disabled than first enable the checkbox 
			storeControlSettingsPage.TransferLimits_CB.click();
			Thread.sleep(1500);
			//disable the checkbox and verify that user is able to disable the checkbox
			storeControlSettingsPage.TransferLimits_CB.click();
			result = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked") == null ;
		}
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1811",
					"User should be able to disable transfer limits box as previously it was enabled with tick mark.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1811","sprint10_US1055_TC1811",
					"User should be able to disable transfer limits box as previously it was enabled with tick mark.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1811");
		}
	}
	
	//TC1812: Verify that the user should be able to view a field named "Transfer $ Amount limit" on the "Transfer Limits" page.
	@Test()
	public void sprint10_US1055_TC1812() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		boolean result;
		if (toggle != null) {
			//if check box is enabled than Verify that the user should be able to view a field named "Transfer $ Amount limit" label and text box
			result = Base.isElementDisplayed(storeControlSettingsPage.TransferAndAmountLimit_Label)
					& Base.isElementDisplayed(storeControlSettingsPage.TransferAndAmountLimit_TB);
		} else {
			//if check box is disabled than first enable the checkbox 
			storeControlSettingsPage.TransferLimits_CB.click();
			//than Verify that the user should be able to view a field named "Transfer $ Amount limit" label and text box
			result = Base.isElementDisplayed(storeControlSettingsPage.TransferAndAmountLimit_Label)
					& Base.isElementDisplayed(storeControlSettingsPage.TransferAndAmountLimit_TB);
		}
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1812",
					"User should be able to view a field named 'Transfer $ Amount limit' on the 'Transfer Limits' page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1812","sprint10_US1055_TC1812",
					"User should be able to view a field named 'Transfer $ Amount limit' on the 'Transfer Limits' page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1812");
		}
	}
	
	//TC1814: Verify that the user should be able to enter a positive dollar amount into a field named "Transfer $ Amount limit".
	@Test()
	public void sprint10_US1055_TC1814() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String transferAmount = "1234.00";
		String negativeTransferAmount = "-1234.00";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		boolean result;
		if (toggle == null) {
			//if check box is disabled than enable the checkbox
			storeControlSettingsPage.TransferLimits_CB.click();
		}
		//Clear "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.clear();
		//Enter a valid amount value  in  "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.sendKeys(transferAmount);
		//Verify that user should be able to enter a valid amount value  in  "Transfer $ Amount limit" text box
		result = storeControlSettingsPage.TransferAndAmountLimit_TB.getAttribute("value").equals(transferAmount);
		//Clear "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.clear();
		//Enter a negative amount value  in  "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.sendKeys(negativeTransferAmount);
		//Verify that an error message will be displayed and only positive amount value will be entered in "Transfer $ Amount limit" text box
		result = result	&& storeControlSettingsPage.InvalidTransferAmount_Error_MSG	.isDisplayed()
				&& storeControlSettingsPage.TransferAndAmountLimit_TB.getAttribute("value").equals(transferAmount);
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1814",
					"User should be able to enter dollar amount=x into a field named Transfer $ Amount limit and should be able to enter positive dollar amount only",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1814","sprint10_US1055_TC1814",
					"User should be able to enter dollar amount=x into a field named Transfer $ Amount limit and should be able to enter positive dollar amount only",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1814");
		}
	}
	
	// TC1903: Verify that the user is able to view "Transfer $ Amount limit" into "Proper dollar format".
	@Test()
	public void sprint10_US1055_TC1903() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String pattern = "\\d{1,15}(\\.\\d{1,2})?";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		if (toggle == null) {
			//if check box is disabled than enable the checkbox
			storeControlSettingsPage.TransferLimits_CB.click();
		}
		//Get the amount value from the "Transfer $ Amount limit" text box
		String transferAmount = storeControlSettingsPage.TransferAndAmountLimit_TB.getAttribute("value");
		//Verify that transfer amount should be in format x.xx 
		if (Pattern.compile(pattern).matcher(transferAmount).matches()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1903",
					"User should be able to view the Transfer $ Amount limit =$x.xx format.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1903","sprint10_US1055_TC1903",
					"User should be able to view the Transfer $ Amount limit =$x.xx format.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1903");
		}
	}
	
	// TC1905: Verify that the user is able to save the "Transfer $ Amount limit" value.
	@Test()
	public void sprint10_US1055_TC1905() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String transferAmount = "1234.00";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Store Control Setting Page
		StoreControlSettingsPage storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// Click on inventory setting button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettings_Label));
		//Get the state of transfer limits checkbox
		String toggle = storeControlSettingsPage.TransferLimits_CB.getAttribute("checked");
		if (toggle == null) {
			//if check box is disabled than enable the checkbox
			storeControlSettingsPage.TransferLimits_CB.click();
		}
		//Clear "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.clear();
		//Enter a valid amount value  in  "Transfer $ Amount limit" text box
		storeControlSettingsPage.TransferAndAmountLimit_TB.sendKeys(transferAmount);
		//Click on save button
		storeControlSettingsPage.TransferSetting_Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettingPosted_Alert_MSG));
		/*Verfiy that User should be able to view the success message "transfer settings posted". and 
		User should be able to view the message "Attention Changes will take affect at the next start of business day".*/
		boolean result = Base.isElementDisplayed(storeControlSettingsPage.TransferSettingPosted_Alert_MSG)
				&& Base.isElementDisplayed(storeControlSettingsPage.TransferSettingPosted_MSG);
		//Click on the continue button
		storeControlSettingsPage.TransferSettingPostedPopUp_Continue_BT.click();
		if (result){
			Reporter.reportPassResult(
					browser,"sprint10_US1055_TC1905",
					"User is able to save the 'Transfer $ Amount limit' value.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1055_TC1905","sprint10_US1055_TC1905",
					"User is able to save the 'Transfer $ Amount limit' value.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1055_TC1905");
		}
	}
	
}
