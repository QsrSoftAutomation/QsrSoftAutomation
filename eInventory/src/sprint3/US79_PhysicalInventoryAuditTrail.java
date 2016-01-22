package sprint3;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US79_PhysicalInventoryAuditTrail extends AbstractTest {

	// TC1985: Verify that the user is able to view the header "Audit" on physical inventory page.
	@Test()
	public void sprint3_US79_TC1985() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Verify that Audit column is displayed in Physical Inventory page table
		if (Base.isElementDisplayed(physicalInventoryPage.InventoryTable_AuditColumn_Label)) {
			Reporter.reportPassResult(
					browser,"sprint3_US79_TC1985",
					"User should be able to view the header Audit on physical inventory page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US79_TC1985","sprint3_US79_TC1985",
					"User should be able to view the header Audit on physical inventory page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US79_TC1985");
		}
	}

	// TC1987: Verify that the user is able to view "Time Stamp, User, Field Name, Before Value, After Value" on audit pop-up screen.
	@Test()
	public void sprint3_US79_TC1987() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US79_TC1987", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Update the outerpack quantity for the raw item
		outerPackQty = String.valueOf(Integer.parseInt(outerPackQty) + 1);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1000);
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress">> update the outer pack quantity for the raw item >> save the inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time).searchItemAndSaveInInventory(wrinId, outerPackQty,"",looseCountQty);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical inventory Page >> Click on the audit mark for the saved inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnAuditMarkForInventory(Base.returnTodayDate(), time);
		//Verify that audit details displayed for the saved inventory
		if (physicalInventoryPage.verifyAuditPopUpTableHeaderDisplayed()) {
			Reporter.reportPassResult(
					browser,"sprint3_US79_TC1987",
					"User should be able to view view 'Time Stamp, User, Field Name, Before Value, After Value' on audit pop-up screen",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US79_TC1987","sprint3_US79_TC1987",
					"User should be able to view view 'Time Stamp, User, Field Name, Before Value, After Value' on audit pop-up screen",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US79_TC1987");
		}
	}

}
