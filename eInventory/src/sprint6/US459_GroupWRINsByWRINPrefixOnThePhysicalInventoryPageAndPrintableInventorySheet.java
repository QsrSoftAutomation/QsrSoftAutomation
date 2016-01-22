package sprint6;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US459_GroupWRINsByWRINPrefixOnThePhysicalInventoryPageAndPrintableInventorySheet extends AbstractTest
{
	// Verify that Raw Items and WRIN prefix groupings are present when the printed sheet is generated from physical inventory landing page
	@Test(enabled = false)
	public void Sprint6_US459_TC1247() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		//Get the prefix from WRIN Id
		String prefix = GlobalVariable.wrinID1withSamePrefix_1.split("-")[0];
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to Physical Inventory Page
		PhysicalInventoryPage physicalInventoryPage = PageFactory.initElements(driver, PhysicalInventoryPage.class);
		homePage.selectAStore(GlobalVariable.StoreId).goToPhysicalInventoryPage();
		//Select the monthly Type
		Select select = new Select(physicalInventoryPage.SelectABlankInventoryToPrint_DD);
		select.selectByVisibleText("Monthly");
		Thread.sleep(10000);
		// Switch to new window opened
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs2.size());
		driver.switchTo().window(tabs2.get(1));
		//Verify that same prefix WRIN should be grouped together in the Print inventory Page
		boolean samePrefixWRINVerified = physicalInventoryPage.
				verifySamePrefixWRINAreGroupedTogether(prefix, GlobalVariable.wrinID1withSamePrefix_1, GlobalVariable.wrinID1withSamePrefix_2);
		if (samePrefixWRINVerified) {
			Reporter.reportPassResult(browser,
					"Sprint6_US459_TC1247",
					"WRIN ID with similer prefix should be in group","Pass");
		}
		else {
			Reporter.reportTestFailure(browser, 
					"Sprint6_US459_TC1247","Sprint6_US459_TC1247",
					"WRIN ID with similer prefix should be in group","Fail");
			AbstractTest.takeSnapShot("Sprint6_US459_TC1247");
		}
}
	
	//Verify the RAW items are grouped according to WRIN prefix
	@Test
	public void Sprint6_US459_TC1245() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String prefix = GlobalVariable.wrinID1withSamePrefix_1.split("-")[0];
		String userId = GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		String wrinId1 = GlobalVariable.wrinID1withSamePrefix_1;
		String wrinId2 = GlobalVariable.wrinID1withSamePrefix_2;
		//Create instance of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical Inventory Page
		PhysicalInventoryPage physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Start a new inventory
		physicalInventoryPage.saveANewInventory("Monthly",wrinId1, "1","", "1");
		physicalInventoryPage.searchItemAndSaveInInventory(wrinId2, "1","", "1");
		//Get inventory date and time
		String inventoryDate = Base.returnTodayDate();
		String inventoryTime = physicalInventoryPage.InventoryTime_Label.getText().split(": ")[1];
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.PhysicalInventory_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//Save the physical inventory
		physicalInventoryPage.clickOnSavedInventory(inventoryDate, inventoryTime);
		physicalInventoryPage.selectInventoryType("Monthly");
		//Search for the prefix
		physicalInventoryPage.SearchItem_TB.sendKeys(prefix);
		//Verify that WRINId1 should be displayed on searching the prefix
		boolean itemWithSamePrefixIsPresent = physicalInventoryPage.verifyItemWithSimilarPrifixIsSearched(prefix, wrinId1);
		//Verify that WRINId2 should be displayed on searching the prefix
		itemWithSamePrefixIsPresent = itemWithSamePrefixIsPresent && physicalInventoryPage.verifyItemWithSimilarPrifixIsSearched(prefix, wrinId2);
		if (itemWithSamePrefixIsPresent) {
			Reporter.reportPassResult(
					browser,"Sprint6_US459_TC1245",
					"User should be able to view both items in one group with same WRIN prefix",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint6_US459_TC1245","Sprint6_US459_TC1245",
					"User should be able to view both items in one group with same WRIN prefix",
					"Fail");
			AbstractTest.takeSnapShot("Sprint6_US459_TC1245");
		}
	}
	
}
