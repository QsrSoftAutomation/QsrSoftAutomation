package sprint10;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.StoreControlSettingsPage;
import sprint2.AbstractTest;

public class US1110_AboveStoreUserToChooseTheRestaurantsThatAreValidRestaurantsToTransferToAndFrom
		extends AbstractTest {

	// TC1851: Verify that the user is able to view a new section containing two options as My Org (GER) and My COOP on the Store Control Settings page.
	@Test()
	public void sprint10_US1110_TC1851() throws RowsExceededException,
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
		//Verify that user is able to view a new section containing two options as My Org (GER) and My COOP 
		boolean result = Base.isElementDisplayed(storeControlSettingsPage.TransferGroupType_GER_RB)
				&& Base.isElementDisplayed(storeControlSettingsPage.TransferGroupType_COOP_RB);
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1110_TC1851",
					"User should be able to view a section cotaining two options as My Org (GER) and My COOP.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1110_TC1851","sprint10_US1110_TC1851",
					"User should be able to view a section cotaining two options as My Org (GER) and My COOP.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1110_TC1851");
		}
	}
	
	//TC1865: Verify that the user is able to view "My Org (GER)" as default selection in the new section of the Store Control Settings page.
	@Test()
	public void sprint10_US1110_TC1865() throws RowsExceededException,
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
		// Verify that the user is able to view "My Org (GER)" as default selection
		boolean result = storeControlSettingsPage.TransferGroupType_GER_RB.getAttribute("checked").equals("true");
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1110_TC1865",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1110_TC1865","sprint10_US1110_TC1865",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1110_TC1865");
		}
	}
	
	// TC1874: Verify that the user is able to select My COOP once user changes the settings in the new section of the Store Control Settings page.
	@Test()
	public void sprint10_US1110_TC1874() throws RowsExceededException,
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
		//Click on the COOP Radio Button 
		storeControlSettingsPage.TransferGroupType_COOP_RB.click();
		//Verify that the user is able to select My COOP Radio button in transfer settings
		boolean result = storeControlSettingsPage.TransferGroupType_COOP_RB.getAttribute("checked").equals("true");
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1110_TC1874",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1110_TC1874","sprint10_US1110_TC1874",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1110_TC1874");
		}
	}
	
	// TC1875: Verify that the user has the ability to save the validation preferences (after selecting My COOP or My Org (GER)).
	@Test()
	public void sprint10_US1110_TC1875() throws RowsExceededException,
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
		//Click on the COOP Radio Button 
		storeControlSettingsPage.TransferGroupType_COOP_RB.click();
		//Click on save button
		storeControlSettingsPage.TransferSetting_Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettingPosted_Alert_MSG));
		//Click on the continue button
		storeControlSettingsPage.TransferSettingPostedPopUp_Continue_BT.click();
		boolean result = storeControlSettingsPage.TransferGroupType_COOP_RB.getAttribute("checked").equals("true");
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1110_TC1875",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1110_TC1875","sprint10_US1110_TC1875",
					"User should be able to view 'My Org (GER)' as default selection in the new section of the Store Control Settings page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1110_TC1875");
		}
		//Revert the changes and reset the transfer group as GER 
		//Click on the GER Radio Button 
		storeControlSettingsPage.TransferGroupType_GER_RB.click();
		//Click on save button
		storeControlSettingsPage.TransferSetting_Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.TransferSettingPosted_Alert_MSG));
		//Click on the continue button
		storeControlSettingsPage.TransferSettingPostedPopUp_Continue_BT.click();
	}

}
