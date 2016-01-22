package sprint8;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualVendorsPage;

public class US825_ManualPurchaseVendorsRestrictAbilityToAddOrEditDuplicateVendor extends AbstractTest{
	
	//TC1566:Manual Purchase Vendors: Restrict the ability to add or edit the same vendor name and/or vendor number as an existing vendor.
	@Test()
	public void sprint8_US825_TC1566() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = GlobalVariable.vendorName;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//CLick on Add New vendor button
		manualVendorsPage.AddVendor_BT.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Enter an existing vendor name
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor Name already exists error should be displayed 
		boolean duplicateVendorNotAdded =  Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		//Change the case of the vendor name
		String vendorNameInMixedCase = vendorName.substring(0,1).toUpperCase()+vendorName.substring(1,vendorName.length());
		//Enter the vendor name
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.clear();
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorNameInMixedCase);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor Name already exists error should be displayed 
		duplicateVendorNotAdded =  duplicateVendorNotAdded && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		if(duplicateVendorNotAdded){
			Reporter.reportPassResult(
					browser,"sprint8_US825_TC1566",
					"User should not be able to enter and save a vendor name that is already utilized by an existing restaurant vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US825_TC1566","sprint8_US825_TC1566",
					"User should not be able to enter and save a vendor name that is already utilized by an existing restaurant vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US825_TC1566");

		}
	}
	

	// TC1567: Verify that when a user creates a manual purchase vendor the system will not allow the user to enter and save a vendor number that is already utilized by an existing restaurant vendor.
	@Test()
	public void sprint8_US825_TC1567() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorNumber = GlobalVariable.vendorNumber;
		/***********************************/
		String newVendorName = "TestAuto"+Base.generateNdigitRandomNumber(4);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//CLick on Add New vendor button
		manualVendorsPage.AddVendor_BT.click();
		//Enter a new vendor name
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB));
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(newVendorName);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor should be saved
		boolean newVendorAdded =  Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorSaved_Message);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		Thread.sleep(3000);
		//CLick on the added vendor name
		manualVendorsPage.editVendor_BT(newVendorName).click();;
		//Enter an existing manual number for that vendor
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB));
		manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB.sendKeys(vendorNumber);
		//Click on Save vendor button
		manualVendorsPage.EditvendorDetailsPopUp_SaveVendor_BT.click();
		Thread.sleep(3000);
		//verify that vendor number already exists error should be displayed 
		boolean duplicateVendorNumberNotAdded =  newVendorAdded && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorNumberAlreadyExists_Message);
		if(duplicateVendorNumberNotAdded){
			Reporter.reportPassResult(
					browser,"sprint8_US825_TC1567",
					"User should not be able to enter and save a vendor number that is already utilized by an existing restaurant vendor.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US825_TC1567","sprint8_US825_TC1567",
					"User should not be able to enter and save a vendor number that is already utilized by an existing restaurant vendor.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US825_TC1567");

		}
	}
	
	/*TC1568: Verify that when a user enters a manual purchase vendor name or number that is already being utilized by an 
	existing vendor, it should display error messages in both the cases asking to enter new vendor name  and number.*/
	@Test()
	public void sprint8_US825_TC1568() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = GlobalVariable.vendorName;
		String vendorNumber = GlobalVariable.vendorNumber;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual vendor Page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//CLick on Add New vendor button
		manualVendorsPage.AddVendor_BT.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Enter an existing vendor name
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		//Enter an existing vendor manual number
		manualVendorsPage.AddvendorDetailsPopUp_ManualNumber_TB.sendKeys(vendorNumber);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor name already exists error should be displayed and vendor manual number already exists error should be displayed
		boolean duplicateVendorNotAdded = Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		duplicateVendorNotAdded = duplicateVendorNotAdded && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		if (duplicateVendorNotAdded) {
			Reporter.reportPassResult(
					browser,"sprint8_US825_TC1568",
					"User should not be able to enter and save a manual purchase vendor name or number that is already being utilized by an existing vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US825_TC1568","sprint8_US825_TC1568",
					"User should not be able to enter and save a manual purchase vendor name or number that is already being utilized by an existing vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US825_TC1568");

		}
	}
	
	/*TC1569: Verify that when an user enters a manual purchase vendor name and number that is already
	  being utilized by an existing vendor, none of the invalid data will be saved.*/
	@Test()
	public void sprint8_US825_TC1569() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = GlobalVariable.vendorName;
		String vendorNumber = GlobalVariable.vendorNumber;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor Page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Get the number of vendors
		int vendorListSize = manualVendorsPage.VendorInfo_List.size();
		//CLick on Add vendor button
		manualVendorsPage.AddVendor_BT.click();
		//Enter an existing vendor name
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		//Enter an existing vendor manual number
		manualVendorsPage.AddvendorDetailsPopUp_ManualNumber_TB.sendKeys(vendorNumber);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor name already exists error should be displayed and vendor manual number already exists error should be displayed
		boolean duplicateVendorErrorDisplayed = Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		duplicateVendorErrorDisplayed = duplicateVendorErrorDisplayed && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		//click on Cancel button
		manualVendorsPage.AddvendorDetailsPopUp_Cancel_BT.click();
		Thread.sleep(1000);
		//Verify that duplicated vendor should not be added
		boolean duplicateVendorNotAdded = (vendorListSize == manualVendorsPage.VendorInfo_List.size());
		if (duplicateVendorErrorDisplayed && duplicateVendorNotAdded) {
			Reporter.reportPassResult(
					browser,"sprint8_US825_TC1569",
					"User should not be able to save a manual purchase vendor name or number that is already being utilized by an existing vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US825_TC1569","sprint8_US825_TC1569",
					"User should not be able to save a manual purchase vendor name or number that is already being utilized by an existing vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US825_TC1569");

		}
	}
	
	/*TC1577 : Verify that when an user edits an existing vendor name/number or both, it should display error message/messages and none of the invalid data will be saved.*/
	@Test()
	public void sprint8_US825_TC1577() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = GlobalVariable.vendorName;
		HSSFSheet manualVendorPageSheet = ReadTestData.getTestDataSheet("sprint8_US825_TC1577", "Object1");
		String vendor2Name= ReadTestData.getTestData(manualVendorPageSheet, "VendorName");
		String vendor2Number = ReadTestData.getTestData(manualVendorPageSheet, "VendorNumber");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor Page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Get number of vendors
		int vendorListSize = manualVendorsPage.VendorInfo_List.size();
		//Click on a vendor Name
		manualVendorsPage.editVendor_BT(vendorName).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB));
		//Enter an another existing vendor name
		manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB.clear();
		manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB.sendKeys(vendor2Name);
		//CLick on save vendor changes button
		manualVendorsPage.EditvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor name already exists error should be displayed
		boolean duplicateVendorErrorDisplayed = Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		//click on cancel button
		manualVendorsPage.AddvendorDetailsPopUp_Cancel_BT.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Click on a vendor Name
		manualVendorsPage.editVendor_BT(vendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB));
		//Enter an another existing vendor manual number
		manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB.clear();
		manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB.sendKeys(vendor2Number);
		//CLick on save vendor changes button
		manualVendorsPage.EditvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor manual number already exists error should be displayed
		duplicateVendorErrorDisplayed = duplicateVendorErrorDisplayed && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorNumberAlreadyExists_Message);
		//click on cancel button
		manualVendorsPage.AddvendorDetailsPopUp_Cancel_BT.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Click on a vendor Name
		manualVendorsPage.editVendor_BT(vendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB));
		//Enter an another existing vendor name
		manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB.clear();
		manualVendorsPage.EditvendorDetailsPopUp_VendorName_TB.sendKeys(vendor2Name);
		//Enter an another existing vendor manual number
		manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB.clear();
		manualVendorsPage.EditvendorDetailsPopUp_VendorNumber_TB.sendKeys(vendor2Number);
		//CLick on save vendor changes button
		manualVendorsPage.EditvendorDetailsPopUp_SaveVendor_BT.click();
		//verify that vendor name already exists error should be displayed and vendor manual number already exists error should be displayed
		duplicateVendorErrorDisplayed = duplicateVendorErrorDisplayed && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		duplicateVendorErrorDisplayed = duplicateVendorErrorDisplayed && Base.isElementDisplayed(manualVendorsPage.AddvendorDetailsPopUp_vendorAlreadyExists_Message);
		//click on cancel button
		manualVendorsPage.AddvendorDetailsPopUp_Cancel_BT.click();
		//Verify that duplicated vendor should not be added
		boolean duplicateVendorNotAdded = (vendorListSize == manualVendorsPage.VendorInfo_List.size());
		if (duplicateVendorErrorDisplayed && duplicateVendorNotAdded) {
			Reporter.reportPassResult(
					browser,"sprint8_US825_TC1577",
					"when an user edits an existing vendor name/number or both, it should display error message/messages and none of the invalid data will be saved",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US825_TC1577","sprint8_US825_TC1577",
					"when an user edits an existing vendor name/number or both, it should display error message/messages and none of the invalid data will be saved",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US825_TC1577");

		}
	}

}
