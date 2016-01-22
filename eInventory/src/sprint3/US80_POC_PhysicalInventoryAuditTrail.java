package sprint3;

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
import eInventoryPageClasses.PhysicalInventoryPage;

public class US80_POC_PhysicalInventoryAuditTrail extends AbstractTest{

	//TC1997: Verify that the user is able to view the header "Audit" on an inventory page.
	@Test()
	public void sprint3_US80_TC1997() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US80_TC1997", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType, wrinId, outerPackQty,"",looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		//Verify that Audit column is displayed for saved inventory
		if (Base.isElementDisplayed(physicalInventoryPage.InventoryTable_AuditColumn_Label)) {
			Reporter.reportPassResult(
					browser,"sprint3_US80_TC1997",
					"User should be able to view the header Audit on inventory page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US80_TC1997","sprint3_US80_TC1997",
					"User should be able to view the header Audit on inventory page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US79_TC1985");
		}
	}
	
	//TC1998: Verify that the user is able to view "Audit for raw-item description=x and WRIN=y" on audit pop-up screen of an inventory for raw-item=x.
	@Test()
	public void sprint3_US80_TC1998() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US80_TC1998", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet,"Description");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType, wrinId, outerPackQty,"",looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Update the outerpack quantity for the raw item
		outerPackQty = String.valueOf(Integer.parseInt(outerPackQty) + 1);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress">> update the outer pack quantity for the raw item >> save the inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time).searchItemAndSaveInInventory(wrinId, outerPackQty,"",looseCountQty);;
		//Click on the audit mark created for the updated raw item
		physicalInventoryPage.clickOnAuditMarkForARawItem(wrinId);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.AuditDetailForRawItemPopUp_Headet_Label));
		Thread.sleep(2000);
		//Verify that audit details for the raw item are displayed in the pop up
		boolean auditDetailDisplayed = physicalInventoryPage.AuditDetailForRawItemPopUp_Headet_Label.getText().equals("Audit For "+wrinId+" "+description);
		if (auditDetailDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint3_US80_TC1998",
					"User should be able to view 'Audit for raw-item description=x and WRIN=y' on audit pop-up screen of an inventory for raw-item=x.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US80_TC1998","sprint3_US80_TC1998",
					"User should be able to view 'Audit for raw-item description=x and WRIN=y' on audit pop-up screen of an inventory for raw-item=x.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US80_TC1998");
		}
	}
	
	//TC1999: Verify that the user is able to view "Time Stamp, User, Field Name, Before Value, After Value" on audit pop-up screen for an inventory's raw-item=x.
	@Test()
	public void sprint3_US80_TC1999() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US80_TC1999", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType, wrinId, outerPackQty,"",looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Update the outer pack quantity for the raw item
		outerPackQty = String.valueOf(Integer.parseInt(outerPackQty) + 1);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress" >> update the outer pack quantity for the raw item >> save the inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time).searchItemAndSaveInInventory(wrinId, outerPackQty,"",looseCountQty);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical inventory Page >> Click on the audit mark for the saved inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnAuditMarkForInventory(Base.returnTodayDate(), time);
		//Verify that audit details displayed for the saved inventory
		if (physicalInventoryPage.verifyAuditPopUpTableHeaderDisplayed()) {
			Reporter.reportPassResult(
					browser,"sprint3_US80_TC1999",
					"User should be able to view view 'Time Stamp, User, Field Name, Before Value, After Value' on audit pop-up screen for an inventory's raw-item=x.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US80_TC1999","sprint3_US80_TC1999",
					"User should be able to view view 'Time Stamp, User, Field Name, Before Value, After Value' on audit pop-up screen for an inventory's raw-item=x.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US80_TC1999");
		}
	}

}
