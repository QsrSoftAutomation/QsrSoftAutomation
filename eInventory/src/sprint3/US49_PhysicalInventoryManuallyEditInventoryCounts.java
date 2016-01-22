package sprint3;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import sprint2.AbstractTest;

public class US49_PhysicalInventoryManuallyEditInventoryCounts extends AbstractTest
{
	
//TC2063 Verify, "the system time stamp of the inventory count will update to the date and time of the most recent save of the inventory count".
	
	@Test()
	
	public void Sprint3_US49_TC2063() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US49_TC2063", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet, "OuterPackQty");
		String outerPackNew="5";
		String userId=GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		PhysicalInventoryPage physicalInventoryPage;
		String time;
		String date=Base.returnTodayDate();
		String beforeLastSaved=null;
		String afterLastSaved=null;
		/*End*/
		
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Click on start New Inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//Fetch the Time of Inventory
		time=physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
		System.out.println("Time is"+time);
		physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		Thread.sleep(2000);
		physicalInventoryPage.BackToTop_BT.click();
		//Fetch the Last saved date and time
		try
		{
			beforeLastSaved=driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[1]")).getText().trim();
			System.out.println("Before"+beforeLastSaved);
			physicalInventoryPage.clickOnInProgressInventory(date, time);
		}
		catch(Exception e)
		{
			physicalInventoryPage.StartNewInventory_BT.click();
			physicalInventoryPage.StartInventory_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.CreateInventory_Search_TB));
		}
		//Select the monthly from drop down
		physicalInventoryPage.selectInventoryType("Monthly");
		try{
			physicalInventoryPage.acceptTheAlertMessage();
			Thread.sleep(20000);
		}catch(Exception e){}
		physicalInventoryPage.CreateInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		// enter the outer pack value
		driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[3]/input")).sendKeys(outerPack);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to Physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//Fetch the before Date
		beforeLastSaved=driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[1]")).getText().trim();
		System.out.println("Before"+beforeLastSaved);
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		Thread.sleep(55000);
		//enter the new outer pack value
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		Thread.sleep(5000);
		//Fetch the Updated last saved value
		afterLastSaved=driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[1]")).getText().trim();
		System.out.println("After"+afterLastSaved);
		if(beforeLastSaved.equalsIgnoreCase(afterLastSaved))
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US49_TC2063","Sprint3_US49_TC2063",
					"The updated last saved date and time should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US49_TC2063");
		}
		else
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US49_TC2063",
					"The updated last saved date and time should display",
					"Pass");
		}
	}
		
	// TC2064 Verify, "any changes made will reflect in the "Total Units" calculated value".
	@Test()
	public void Sprint3_US49_TC2064() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US49_TC2064", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet,"OuterPackQty");
		String outerPackNew = "5";
		String userId = GlobalVariable.userId;
		String storeId = GlobalVariable.StoreId;
		String totalUnitsBeforeChnage;
		String newTotalUnitsBeforeChnage;
		PhysicalInventoryPage physicalInventoryPage;
		String time;
		String date = Base.returnTodayDate();
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory("Monthly", wrinId, outerPack,"", "");
		// Fetch the total units
		totalUnitsBeforeChnage = physicalInventoryPage.TotalUnits_Column_Value.getText().trim();
		time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		homePage.Menu_DD_BT.click();
		physicalInventoryPage.goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		// click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		// enter the new outer pack value
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		// click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		// Go to physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		// click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		// Fetch the total units value
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		newTotalUnitsBeforeChnage = physicalInventoryPage.TotalUnits_Column_Value.getText().trim();
		if (newTotalUnitsBeforeChnage.equalsIgnoreCase(totalUnitsBeforeChnage)) {
			Reporter.reportTestFailure(
					browser, "Sprint3_US49_TC2064","Sprint3_US49_TC2064",
					"User should be able to change the outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US49_TC2064");
		} else {
			Reporter.reportPassResult(
					browser, "Sprint3_US49_TC2064",
					"User should be able to change the outer pack value",
					"Pass");
		}
	}
		
	// TC2065 Verify, "If the field was saved as blank, then the change will not be flagged."
	@Test()
	public void Sprint3_US49_TC2065() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US49_TC2065", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"WRINId");
		String userId = GlobalVariable.userId;
		String storeId = GlobalVariable.StoreId;
		PhysicalInventoryPage physicalInventoryPage;
		String time;
		String date = Base.returnTodayDate();
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory("Monthly", wrinId, "", "", "");
		// Go to Physical Inventory page
		time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		// click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		// enter the new outer pack value
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		physicalInventoryPage.OuterPackQty_TB.clear();
		// click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		if (Base.isElementDisplayed(physicalInventoryPage.InventorySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser,"Sprint3_US49_TC2065",
					"User should be able to saved the inventory without outer pack value",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint3_US49_TC2065","Sprint3_US49_TC2065",
					"User should be able to saved the inventory without outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US49_TC2065");
		}
	}
	
	
	// TC2066 Verify, "Only items that had a digit in the previous save and are changed will be flagged as a change."
	@Test()
	public void Sprint3_US49_TC2066() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US49_TC2066", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet,"OuterPackQty");
		String outerPackNew = "5";
		String userId = GlobalVariable.userId;
		String storeId = GlobalVariable.StoreId;
		PhysicalInventoryPage physicalInventoryPage;
		String time;
		String date = Base.returnTodayDate();
		String expOuterpackvalue = null;
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory("Monthly", wrinId, outerPack,"", "");
		time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Go to Physical Inventory page and click on the created physical inventory
		homePage.Menu_DD_BT.click();
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(date, time);
		// enter the new outer pack value
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		// click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		// Go to physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		// click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		physicalInventoryPage.ViewInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		// Fetch the total units value
		expOuterpackvalue = physicalInventoryPage.OuterPackQty_TB.getAttribute("value");
		System.out.println("outer pack" + expOuterpackvalue);
		if (expOuterpackvalue.equalsIgnoreCase(outerPackNew)) {
			Reporter.reportPassResult(
					browser, "Sprint3_US49_TC2066",
					"Outer pack value should be chnaged successfully", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint3_US49_TC2066",	"Sprint3_US49_TC2066",
					"Outer pack value should be chnaged successfully", "Fail");
			AbstractTest.takeSnapShot("Sprint3_US49_TC2066");
		}
	}

}