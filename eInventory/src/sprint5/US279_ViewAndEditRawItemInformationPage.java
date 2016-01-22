package sprint5;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import eInventoryPageClasses.RawItemInformationPage;
import eInventoryPageClasses.TransferLandingPage;

public class US279_ViewAndEditRawItemInformationPage extends AbstractTest{

	/*******Left Alternate Vendor,Cost/Unit also used close btn instead of 'done with this item'
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws BiffException 
	 * @throws RowsExceededException 
	 * @throws InterruptedException **********/
	//Verify that user is able to search a raw item in raw item information page

	@Test()
	public void Sprint5_US279_TC768() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationSheet = ReadTestData.getTestDataSheet("Sprint5_US279_TC768", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationSheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String ListTypeDefaultValue = ReadTestData.getTestData(rawItemInformationSheet, "ListType");
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//Create instance of homePage & RawItemInformation Page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		 homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//verify user should be able to view all the details respective of particular wRIN id and default value of List Type DD List
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);
		Select selMcDD=new Select(rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_DD_Value);
		Select PrimaryVendorDD=new Select(rawiteminformation.RawItemInformation_Attribute_PrimaryVendor_DD_Value);
		if(selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase(ListTypeDefaultValue)
				&& rawiteminformation.RawItemInformation_Attribute_WRIN_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_WRIN_Value.getText().length()>0 
				&& rawiteminformation.RawItemInformation_Attribute_LongDescription_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_LongDescription_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOM_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOM_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOMCase_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOMCase_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOMSleeve_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOMSleeve_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_InnerPackDescription_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_InnerPackDescription_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_CasePrice_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_CasePrice_Value.getText().length()>=0 
				&& rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_Label.isDisplayed() && selMcDD.getFirstSelectedOption().getText().length()>0 && PrimaryVendorDD.getFirstSelectedOption().getText().length()>0
				&& rawiteminformation.RawItemInformation_Save_BT.isDisplayed() && rawiteminformation.RawItemInformation_Close_BT.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC768", "All the needed Attributes with there respective values must be present", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC768", "Sprint5_US279_TC768", "All the needed Attributes with there respective values must be present", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC768");
		}	
	}

	//Verify that user is able to change the List Type for any searched WRIN ID on raw item information page

	@Test()
	public void Sprint5_US279_TC769() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationSheet = ReadTestData.getTestDataSheet("Sprint5_US279_TC769", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationSheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String ListTypeChangedValue="Monthly";
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//Create instance of homePage & RawItemInformation Page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		 homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//verify user should be able to view all the details respective of particular wRIN id and default value of List Type DD List
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);	
		//change the value of List Type value to 'Monthly'
		selListTypeDD.selectByVisibleText("Monthly");
		//verify whether the list type value has been changed successfully 
		if(selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase(ListTypeChangedValue))
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC769", "List Type Default value must be changed to 'Monthly'", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC769", "Sprint5_US279_TC769", "List Type Default value must be changed to 'Monthly'", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC769");
		}		
	}

	//Validate "Raw Item Information" page and "Physical inventory" page for newly added WRIN ID:X for which no invoice has been posted.

	@Test(enabled=false)
	public void Sprint5_US279_TC1173() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationSheet = ReadTestData.getTestDataSheet("Sprint5_US279_TC1173", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationSheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String ListTypeDefaultValue="Weekly";
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//Create instance of homePage & RawItemInformation Page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		PhysicalInventoryPage physicalinventorypage=PageFactory.initElements(driver, PhysicalInventoryPage.class);
		//Navigate to raw item information page
		 homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//verify user should be able to view all the details respective of particular wRIN id and default value of List Type DD List
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);
		if(selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase(ListTypeDefaultValue)
				&& rawiteminformation.RawItemInformation_Attribute_WRIN_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_WRIN_Value.getText().length()>0 
				&& rawiteminformation.RawItemInformation_Attribute_LongDescription_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_LongDescription_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOM_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOM_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOMCase_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOMCase_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_CasePrice_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_CasePrice_Value.getText().length()>=0 
				&& rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_Label.isDisplayed()
				&& rawiteminformation.RawItemInformation_Save_BT.isDisplayed() && rawiteminformation.RawItemInformation_Close_BT.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1173", "All the needed Attributes with there respective values must be present", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1173_Condition1", "Sprint5_US279_TC1173", "All the needed Attributes with there respective values must be present", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1173_Condition1");
		}	

		// Verify there should not be any value for following attribute: a)Vendor b)case price
		wait.until(ExpectedConditions.visibilityOf(rawiteminformation.RawItemInformation_Attribute_CasePrice_Value));
		if(rawiteminformation.RawItemInformation_Attribute_CasePrice_Value.getText().equalsIgnoreCase("N/A") && driver.findElement(By.xpath("//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Primary')]/following-sibling::td")).getText().equalsIgnoreCase("N/A"))
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1173", "Vendor Attribute and Case Price Attribute should not contain any value", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1173_Condition2", "Sprint5_US279_TC1173", "Vendor Attribute and Case Price Attribute should not contain any value", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1173_Condition2");
		}	
		// Select physical Inventory from Inventory DD and click on 'Start New Inventory' button
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		homePage.Menu_Back_BT.click();
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.StartNewInventory_BT));
		physicalinventorypage.StartNewInventory_BT.click();
		//click on 'start Inventory' Button
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.StartInventory_BT));
		Thread.sleep(3000);
		physicalinventorypage.StartInventory_BT.click();
		//search for the same wRIN id in the search field
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.CreateInventory_Search_TB));
		physicalinventorypage.CreateInventory_Search_TB.sendKeys(wrinId);
		//verify user should not be able to see the selected wRIN id
		if(driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr")).getText().contains("No search results"))
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1173", "user should not be able to view the wRIN id in the search results", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1173_Condition3", "Sprint5_US279_TC1173", "user should not be able to view the wRIN id in the search results", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1173_Condition3");
		}	
	}

	//Validate "Raw Item Information" page and "Physical inventory" page for newly added WRIN ID:X for which invoice has been posted
	@Test()
	public void Sprint5_US279_TC1174() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationSheet = ReadTestData.getTestDataSheet("Sprint5_US279_TC1174", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationSheet, "WRINId");
		String listType = ReadTestData.getTestData(rawItemInformationSheet, "ListType");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		
		//Create instance of homePage & RawItemInformation Page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		PhysicalInventoryPage physicalinventorypage=PageFactory.initElements(driver, PhysicalInventoryPage.class);
		//Navigate to raw item information page
		 homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//verify user should be able to view all the details respective of particular wRIN id and default value of List Type DD List
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);
		if((selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase("Daily") || selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase("Weekly") || selListTypeDD.getFirstSelectedOption().getText().equalsIgnoreCase("Monthly") )
				&& rawiteminformation.RawItemInformation_Attribute_WRIN_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_WRIN_Value.getText().length()>0 
				&& rawiteminformation.RawItemInformation_Attribute_LongDescription_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_LongDescription_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOM_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOM_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_UOMCase_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_UOMCase_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_CasePrice_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_CasePrice_Value.getText().length()>=0 
				&& rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Label.isDisplayed() && rawiteminformation.RawItemInformation_Attribute_TemperatureZone_Value.getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_Label.isDisplayed()
				&& rawiteminformation.RawItemInformation_Save_BT.isDisplayed() && rawiteminformation.RawItemInformation_Close_BT.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1174", "All the needed Attributes with there respective values must be present", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1174_Condition1", "Sprint5_US279_TC1174", "All the needed Attributes with there respective values must be present", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1174_Condition1");
		}	

		// Validate user should find value for following attribute: a)Vendor b)case price
		wait.until(ExpectedConditions.visibilityOf(rawiteminformation.RawItemInformation_Attribute_CasePrice_TB));
		if( driver.findElement(By.xpath("//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Primary')]/following-sibling::td")).getText().length()>0
				&& rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.getAttribute("value").length()>0)
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1174", "Vendor Attribute and Case Price Attribute should be populated", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1174_Condition2", "Sprint5_US279_TC1174", "Vendor Attribute and Case Price Attribute should be populated", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1174_Condition2");
		}	
		// Select physical Inventory from Inventory DD and click on 'Start New Inventory' button
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		homePage.Menu_Back_BT.click();
		Thread.sleep(2000);
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.StartNewInventory_BT));
		physicalinventorypage.StartNewInventory_BT.click();
		//click on 'start Inventory' Button
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.StartInventory_BT));
		Thread.sleep(4000);
		physicalinventorypage.StartInventory_BT.click();
		//search for the same wRIN id in the search field
		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.CreateInventory_Search_TB));
		Select select=new Select(driver.findElement(By.xpath("//select[@id='list_type']")));
		select.selectByVisibleText(listType);
		// accepting java script alert
		physicalinventorypage.acceptTheAlertMessage();
		physicalinventorypage.CreateInventory_Search_TB.sendKeys(wrinId);
		//verify user should not be able to see the selected wRIN id
		if(driver.findElement(By.xpath("//table[@id='inventory_table']/tbody")).getText().contains(wrinId))
		{
			Reporter.reportPassResult(browser, "Sprint5_US279_TC1174", "user should be able to view the wRIN id in the search results", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US279_TC1174_Condition3", "Sprint5_US279_TC1174", "user should be able to view the wRIN id in the search results", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US279_TC1174_Condition3");
		}	
	}
	
	
	//Verify the "transfer" initiation for newly added WRIN ID:X for which the invoice has been posted.
		@Test()
		public void Sprint5_US279_TC1175() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
		{
			/*Start-Variable Deceleration*/
			HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US279_TC1175", "Object1");
			String wrinId = ReadTestData.getTestData(transferLandingPageSheet, "WRINId");
			String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
			String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
			String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
			String storeId=GlobalVariable.StoreId;
			String userId=GlobalVariable.userId;
			/*End-Variable Deceleration*/
			//Create instance of home, Transfer landing Page & ManualInvoiceEditPage
			HomePage homePage=PageFactory.initElements(driver, HomePage.class);
			TransferLandingPage transferlandingpage=PageFactory.initElements(driver, TransferLandingPage.class);
			//Navigate to Transfer Landing Page
			homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
			//click on 'insert new Transfers' button
			transferlandingpage.CreateNewTransfers_BT.click();
			Thread.sleep(3000);
			//find the current date of system
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//Get start date and end as 1 days earlier date from today date
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);
	        String transferDate = dateFormat.format(cal1.getTime());
	        String transferDay = String.valueOf(Base.getDayFromDate(transferDate));
	        String tranferMonth = String.valueOf(Base.getMonthFromDate(transferDate));
			transferlandingpage.selectDateInAddNewTransferPopUp(transferDay, tranferMonth);
	        Thread.sleep(2000);
			//create a transfer-out
			transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
			transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
			Thread.sleep(2000);
			transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
			//Enter a wRI N id in the search field & select item
			transferlandingpage.AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(wrinId);
			action.sendKeys(Keys.SPACE).build().perform(); 
			Thread.sleep(1500); 
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
			wait.until(ExpectedConditions.visibilityOf(transferlandingpage.AddTransferItemsPopup_OuterPack_TB));
			//Enter quantities for outer pack,Inner pack &Looseunits for selected raw item..
			transferlandingpage.AddTransferItemsPopup_OuterPack_TB.sendKeys(outerPackQty);
			transferlandingpage.AddTransferItemsPopup_LooseUnits_TB.sendKeys(looseUnitsQty);
			//click on add button
			transferlandingpage.AddTransferItemsPopup_Add_BT.click();
			//click on submit button
			String time = transferlandingpage.InsertNewTransfersPopup_Time_Value.getText();
			System.out.println("Total amount "+ transferlandingpage.AddTransferPopup_TotalAmount_Value.getText());
			String totalAmount = transferlandingpage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1].trim();
			transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOf(transferlandingpage.ChangesSaved_Confirmation_MSG));
			//verify confirmation message
			if(transferlandingpage.verifyTransferPlaced(transferDate, time, totalAmount))
			{
				Reporter.reportPassResult(browser, "Sprint5_US279_TC1175", "confirmation message should be displayed", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint5_US279_TC1175", "Sprint5_US279_TC1175", "confirmation message should be displayed", "Fail");
				AbstractTest.takeSnapShot("Sprint5_US279_TC1175");

			}	
		}
		//Fail: Defect : transfer for back date is getting saved with current date only
}