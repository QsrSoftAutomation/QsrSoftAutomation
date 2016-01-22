package sprint9;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import eInventoryPageClasses.RawItemInformationPage;

public class US777_ViewCalculatedYield  extends AbstractTest {
	
	//TC1734:Verify the calculated yield is shown on the Raw Item information screen.
	@Test()
	public void sprint9_US777_TC1734() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		RawItemInformationPage rawItemInformationPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint9_US777_TC1734", "Object1");
		String wrinID = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Raw item information page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		String calculatedYieldValue = rawItemInformationPage.searchAndSelectWRINID(wrinID).CalculatedYield_Value.getText();
		if (!calculatedYieldValue.isEmpty()) {
			Reporter.reportPassResult(
					browser,"sprint9_US777_TC1734",
					"User should be able to view the countable yield against X.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US777_TC1734","sprint9_US777_TC1734",
					"User should be able to view the countable yield against X.",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US777_TC1734");
		}
	}
	
	//TC1735:Verify the "As-of date" value on raw item information page.
	@Test()
	public void sprint9_US777_TC1735() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		RawItemInformationPage rawItemInformationPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint9_US777_TC1735", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty =ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		physicalInventoryPage =  homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 5 days earlier date from today date
		cal1.add(Calendar.DATE, -1);
        String date = dateFormat.format(cal1.getTime());
        physicalInventoryPage.selectADateForPhysicalInventory(date);
        physicalInventoryPage.StartInventory_BT.click();
        try { 
        	physicalInventoryPage.selectInventoryType(inventoryType); 
        	physicalInventoryPage.searchItemAndSaveInInventory(wrinId, outerPackQty,"", looseCountQty); 
			physicalInventoryPage.postInventory();
		} catch (Exception e) { 
			physicalInventoryPage.EditInventory_BT.click(); 
			if (Base.isElementDisplayed(physicalInventoryPage.Post_BT)) {
				physicalInventoryPage.selectInventoryType(inventoryType); 
				physicalInventoryPage.searchItemAndSaveInInventory(wrinId, outerPackQty,"", looseCountQty); 
				physicalInventoryPage.postInventory(); 
			} 
		}
		//Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		rawItemInformationPage = homePage.selectAStore(storeId).goToRawItemInformationPage();
		String calculatedYieldAsOfValue = rawItemInformationPage.searchAndSelectWRINID(wrinId).CalculatedYieldAsOf_Label.getText();
		//08/24/2015 10:15
		if (calculatedYieldAsOfValue.contains(date+" "+time)) {
			Reporter.reportPassResult(
					browser,"sprint9_US777_TC1735",
					"User should be able to Verify the As-of date value on raw item information page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US777_TC1735","sprint9_US777_TC1735",
					"User should be able to Verify the As-of date value on raw item information page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US777_TC1735");
		}
	}

}
