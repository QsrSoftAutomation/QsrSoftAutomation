package sprint2;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US10_PhysicalInventoryRawItemLongDescription extends AbstractTest {
	String TestCaseName = "";
	
	// TC1971: Verify that the user is able to view long description corresponding to the WRIN number for the correct Raw Item.
	@Test()
	public void sprint2_US10_TC1971() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		TestCaseName = "sprint2_US10_TC1971";
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US10_TC1971", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet, "Description");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		System.out.println(TestCaseName + " START");
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinId, description)) {
			Reporter.reportPassResult(
					browser,TestCaseName,
					"User should be able to view long description=x corresponding to the WRIN number=y for the correct Raw Item.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,TestCaseName,TestCaseName,
					"User should be able to view long description=x corresponding to the WRIN number=y for the correct Raw Item.",
					"Fail");
			AbstractTest.takeSnapShot(TestCaseName);
		}
		System.out.println(TestCaseName + " END");
	}
	
	// TC1973: Verify that the user is able to view the description with mixed case.
	@Test()
	public void sprint2_US10_TC1973() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		TestCaseName = "sprint2_US10_TC1973";
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US10_TC1973", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet, "Description");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		System.out.println(TestCaseName + " START");
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinId,description)) {
			Reporter.reportPassResult(
					browser,TestCaseName,
					"User should be able to view the description with mixed case",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,TestCaseName,TestCaseName,
					"User should be able to view the description with mixed case",
					"Fail");
			AbstractTest.takeSnapShot(TestCaseName);

		}
		System.out.println(TestCaseName + " END");
	}

}
