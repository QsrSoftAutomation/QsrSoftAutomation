package sprint9;

import java.io.IOException;

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

public class US1044_RefineElectronicInvoiceVarianceSettingsValidations extends AbstractTest{
	
	//TC1710:Verify that user is able to enter value having decimal field up to the hundredths place in range (>0 and <10) for % Total Invoice $ Amount Variance
	@Test()
	public void sprint9_US1044_TC1710() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		StoreControlSettingsPage storeControlSettingsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to StorControSettings Page from Cash DropDown
		storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// click on Inventory settings button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.CasesVarience_TB));
		//Enter the value '3.21' in % Total Invoice $ Amount Variance text box 
		storeControlSettingsPage.TotalInvoiceAmountVariance_TB.clear();
		storeControlSettingsPage.TotalInvoiceAmountVariance_TB.sendKeys("3.21");
		//click on save button
		storeControlSettingsPage.SaveVariance_BT.click();
		//Verify that "Success,Your settings have been saved" message is displayed
		if ( Base.isElementDisplayed(storeControlSettingsPage.VarianceSettingsSaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser,"sprint9_US1044_TC1710",
					"User should be able to view  message as 'Success,Your settings have been saved'",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US1044_TC1710","sprint9_US1044_TC1710",
					"User should be able to view  message as 'Success,Your settings have been saved'",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US1044_TC1710");
		}
	}
	
	//TC1711:Verify that user is able to enter value 0 for % Total Electronic Invoice Cases Variance field.
	@Test()
	public void sprint9_US1044_TC1711() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		StoreControlSettingsPage storeControlSettingsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to StorControSettings Page from Cash DropDown
		storeControlSettingsPage = homePage.selectUser(userId).selectLocation(storeId).goToStoreControlSettingsPage();
		// click on Inventory settings button
		storeControlSettingsPage.InventorySetting_BT.click();
		wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.CasesVarience_TB));
		//Enter the value '3.21' in Total Electronic Invoice Cases Variance field 
		storeControlSettingsPage.CasesVarience_TB.clear();
		storeControlSettingsPage.CasesVarience_TB.sendKeys("0");
		//click on save button
		storeControlSettingsPage.SaveVariance_BT.click();
		//Verify that "Success,Your settings have been saved" message is displayed
		if (Base.isElementDisplayed(storeControlSettingsPage.VarianceSettingsSaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser,"sprint9_US1044_TC1711",
					"User should be able to view  message as 'Success,Your settings have been saved'",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US1044_TC1711","sprint9_US1044_TC1711",
					"User should be able to view  message as 'Success,Your settings have been saved'",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US1044_TC1711");
		}
	}

}
