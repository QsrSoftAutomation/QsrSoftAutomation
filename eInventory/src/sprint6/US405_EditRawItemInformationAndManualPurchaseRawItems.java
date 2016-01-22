package sprint6;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

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

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemInformationPage;
import sprint2.AbstractTest;

public class US405_EditRawItemInformationAndManualPurchaseRawItems extends AbstractTest{

	//Verify the designation of raw item present in "Raw Item Information" page as a manual purchase

	@Test()

	public void Sprint6_US405_TC1210() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("Sprint6_US405_TC1210", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		String primaryVendor = ReadTestData.getTestData(rawItemInformationPageSheet, "PrimaryVendor");
		String CasePriceVal = ReadTestData.getTestData(rawItemInformationPageSheet, "CasePriceValue");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//create instances of home,transfer landing and manual invoice edit pages
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//check whether wRIN ID should not be added to manual purchase before
		if(!rawiteminformation.ManualPurchase_CB.isSelected())
		{
			rawiteminformation.ManualPurchase_CB.click();
		}
		//select the list type as monthly
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);
		selListTypeDD.selectByVisibleText("Monthly");
		//Enter the case Price in the CP Edit Box
		wait.until(ExpectedConditions.visibilityOf(rawiteminformation.RawItemInformation_Attribute_CasePrice_TB));
		rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.clear();
		rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.sendKeys(CasePriceVal);
		//select a McDonalds GL Account from "McDonalds GL Account drop down
		Select selMcDD=new Select(rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_DD_Value);
		selMcDD.selectByIndex(2);
		//select 'xyz' vendor from the DD List
		Select PrimaryVendorDD=new Select(rawiteminformation.RawItemInformation_Attribute_PrimaryVendor_DD_Value);
		PrimaryVendorDD.selectByVisibleText(primaryVendor);
		//click save btn and verify the confirmation message
		rawiteminformation.RawItemInformation_Save_BT.click();
		Thread.sleep(3000);
		if(driver.getPageSource().contains("Changes Saved"))
		{
			Reporter.reportPassResult(browser, "Sprint6_US405_TC1210", "confirmation message should be displayed", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US405_TC1210", "Sprint6_US405_TC1210", "confirmation message should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US405_TC1210");

		}	

	}

	//verify that option "UOM/Sleeve" and "Inner Pack Description" on "Raw Item Information" page is hidden for those items which does not have inner pack

	@Test()

	public void Sprint6_US405_TC1250() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("Sprint6_US405_TC1250", "Object1");
		String wrin = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/*End-Variable Deceleration*/
		
		//create instances of home,transfer landing and manual invoice edit pages
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrin);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+wrin+"]")).click();
		//verify "UOM/Sleeve" and "Inner Pack Description" are hidden for the user for the wRIN which doesn't have a Inner Pack
		if(driver.findElements(By.xpath("//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM/Sleeve']")).size()==0 && driver.findElements(By.xpath("//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Inner Pack D')]")).size()==0)
		{
			Reporter.reportPassResult(browser, "Sprint6_US405_TC1250", "'UOM/Sleeve' and 'Inner Pack Description' must be hidden for the user for particular wRIN ID", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US405_TC1250", "Sprint6_US405_TC1250", "'UOM/Sleeve' and 'Inner Pack Description' must be hidden for the user for particular wRIN ID", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US405_TC1250");

		}	
	}
	//Validate the error while entering values for editable fields, once the user has designated a raw item as manual purchase on raw item information page.	

	@Test()

	public void Sprint6_US405_TC1249() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("Sprint6_US405_TC1249", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		String primaryVendor = ReadTestData.getTestData(rawItemInformationPageSheet, "PrimaryVendor");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String InValidCasePrice="144.44444";
		/*End-Variable Deceleration*/

		//create instances of home,transfer landing and manual invoice edit pages
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(wrinId);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		//Associate wRIN id to the manual purchase if not associated
		if(!rawiteminformation.ManualPurchase_CB.isSelected())
		{
			rawiteminformation.ManualPurchase_CB.click();
		}
		//Enter Invalid casePrice
		rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.clear();
    	/***********user should not be allowed to enter invalid case price***************/
		//select the list type as monthly
		Select selListTypeDD=new Select(rawiteminformation.RawItemInformation_Attribute_ListType_DD_Value);
		selListTypeDD.selectByVisibleText("Monthly");
		//select a McDonalds GL Account from "McDonalds GL Account drop down
		Select selMcDD=new Select(rawiteminformation.RawItemInformation_Attribute_McDonaldsGLAccount_DD_Value);
		selMcDD.selectByIndex(2);
		//select 'xyz' vendor from the DD List
		Select PrimaryVendorDD=new Select(rawiteminformation.RawItemInformation_Attribute_PrimaryVendor_DD_Value);
		PrimaryVendorDD.selectByVisibleText(primaryVendor);
		rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.sendKeys(InValidCasePrice);
		if(rawiteminformation.Error_Message.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint6_US405_TC1249", "Error confirmation message should be displayed", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US405_TC1249", "Sprint6_US405_TC1249", "Error confirmation message should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US405_TC1249");

		}	
	}
	//Verify the impact on manual purchase page once the raw item is designated as a manual purchase from "Raw Item Information" page against a particular manual vendor

	@Test ()

	public void Sprint6_US405_TC1251() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException 
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint6_US405_TC1251", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet, "Vendor");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/*End-Variable Deceleration*/
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//Navigate to Manual Purchases page
		PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//click on Enter Manual Purchase button
		purchaselandingpage.EnterManualPurchase_BT.click();
		//select manual vendor 'xyz' from vendor DDList
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor);
		//Search the sample WRIN ID: on "Enter Quick Search With Suggestions for Manual Purchases:" and select it
		manualInvoiceNewPage.searchAndSelectARawItem(wrinId);
		//validate wrin id is added to manual purchase
		if(driver.findElement(By.xpath("//table[@id='invoice_tbl']/tbody/tr/td[2]")).getText().contains(wrinId))
		{
			Reporter.reportPassResult(browser, "Sprint6_US405_TC1251", "wRIN id should be added to manual purchase", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US405_TC1251", "Sprint6_US405_TC1251", "wRIN id should be added to manual purchase", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US405_TC1251");

		}	


	}
	//Verify the case price of Raw Item is changed, once it is updated from manual purchase detail screen.

	@Test ()

	public void Sprint6_US405_TC1314() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException, AWTException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint6_US405_TC1314", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet, "Vendor");
		String CasePriceVal = ReadTestData.getTestData(manualInvoiceNewPageSheet, "DollerCase");
		String QuantityVal = ReadTestData.getTestData(manualInvoiceNewPageSheet, "Quantity");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String ExpectedCPVal="12.3300";
		/*End-Variable Deceleration*/
		//create instances of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//Navigate to Manual Purchases page
		PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//click on Enter Manual Purchase button
		purchaselandingpage.EnterManualPurchase_BT.click();
		//select manual vendor from vendor DDList
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor);
		//Search the sample WRIN ID: on "Enter Quick Search With Suggestions for Manual Purchases:" and select it
		manualInvoiceNewPage.searchAndSelectARawItem(wrinId);
		//Enter 'case-price' value as 12.33 and Quantity as '1'.
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.DollerCase_TB_List.get(0)));
		manualInvoiceNewPage.DollerCase_TB_List.get(0).clear();
		Thread.sleep(1000);
		manualInvoiceNewPage.DollerCase_TB_List.get(0).sendKeys(CasePriceVal);
		manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys(QuantityVal);
//		String ActualCPVal=purchaselandingpage.CasePrice_Search_TB.getText();
		//click 'finaliza' button
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		action.moveToElement(manualInvoiceNewPage.Finalize_BT).click().build().perform();
		action.moveToElement(manualInvoiceNewPage.Finalize_BT).click().build().perform();
		Thread.sleep(2000);
		/*//select delivery time
		while(!Invoicetime.equalsIgnoreCase(driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]")).getText()))
		{	
			driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
			if(driver.findElement(By.xpath("//div[text()='00:30']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[text()='00:30']")).click();
				break;
			}
		}*/
		//click continue button
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.FinalizePopUp_Continue_BT));
		manualInvoiceNewPage.FinalizePopUp_Continue_BT.click();
		Thread.sleep(2500);
		//Navigate to the 'Raw Item Information' Page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		homePage.RawItemInformation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawiteminformation.RawItemInformation_Label));
		//Search the same wRIN id & Select it.
		rawiteminformation.searchAndSelectWRINID(wrinId);
		//validate the 'case-price' against it.
		String ActualCPVal=rawiteminformation.RawItemInformation_Attribute_CasePrice_TB.getAttribute("value");
		if(ActualCPVal.equalsIgnoreCase(ExpectedCPVal))
		{
			Reporter.reportPassResult(browser, "Sprint6_US405_TC1314", "user should be able to view the updated case-price value", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US405_TC1314", "Sprint6_US405_TC1314", "user should be able to view the updated case-price value", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US405_TC1314");

		}	

	}

}

