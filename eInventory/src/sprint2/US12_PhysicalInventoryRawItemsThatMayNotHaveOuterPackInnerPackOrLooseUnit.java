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

public class US12_PhysicalInventoryRawItemsThatMayNotHaveOuterPackInnerPackOrLooseUnit extends AbstractTest {
	
	// TC1846: Verify that the user should be able to view the inner pack field non-editable when the raw item does not have an inner pack.
	@Test()
	public void sprint2_US12_TC1846() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US12_TC1846", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		//Search for the Wrin ID
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		//Verify that user is not able to edit inner pack quantity  for the wrin ID
		if (physicalInventoryPage.InnerPackQty_TB.getAttribute("type").equals("hidden")) {
			Reporter.reportPassResult(
					browser,"sprint2_US12_TC1846",
					"User should not be able to edit the inner pack column of WRIN=02232-015",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US12_TC1846","sprint2_US12_TC1846",
					"User should not be able to edit the inner pack column of WRIN=02232-015",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US12_TC1846");

		}
	}

}
