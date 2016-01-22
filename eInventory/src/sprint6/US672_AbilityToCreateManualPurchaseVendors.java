package sprint6;

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

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemInformationPage;
import sprint2.AbstractTest;

public class US672_AbilityToCreateManualPurchaseVendors extends AbstractTest
{
	// Verify the user is able to select  "Manual vendors " option from inventory drop down and move to "Manual Vendors" landing page.
	@Test()
	public void Sprint6_US672_TC1212() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Go to purchase landing page and verify that it is redirected
		boolean result=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage().ManualVendors_Label.isDisplayed();
		if(result)
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1212", "User should be redirected to Manual Vendor page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1212", "Sprint6_US672_TC1212", "User should be redirected to Manual Vendor page", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1212");
		}		
		
	}

	// Verify the user is able to enter vendor name while creating a manual vendor from manual vendors landing page.
	@Test()

	public void Sprint6_US672_TC1213() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		HSSFSheet manualVendorPageSheet = ReadTestData.getTestDataSheet("Sprint6_US672_TC1213", "Object1");
		String vendorName = ReadTestData.getTestData(manualVendorPageSheet, "VendorName");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualVendorsPage manualVendorPage=PageFactory.initElements(driver, ManualVendorsPage.class);
		//Go to Manual vendors page and click on Add vendor button
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage().AddVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.AddvendorDetailsPopUp_VendorName_TB));
		//enter the vendor name in vendor name text box
		manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.clear();
		manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		//verify the entered text
		if(manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.getAttribute("value").equalsIgnoreCase(vendorName))
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1213", "User should be able to enter the vendor name", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1213", "Sprint6_US672_TC1213", "User should be able to enter the vendor name", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1213");
		}
		
	}


	// Verify the impact on manual purchase detail screens once new manual purchase vendor is created.
	@Test()
	
	public void Sprint6_US672_TC1220() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		
		String vendorName=null;
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage=PageFactory.initElements(driver, PurchasesPage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//go to Manual Vendor page and click on Add Vendor button
		vendorName=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage().vendorName_List.get(0).getText();
		//Go to purchase landing page and verify that vendor is present or not
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_OtherInventoryFunction_Back_BT));
		Thread.sleep(2000);
		homePage.Menu_OtherInventoryFunction_Back_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Purchases_BT));
		homePage.Purchases_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.Purchases_Label));
		int vendorNumber=purchasesPage.goToManualInvoiceNewPage().VendorName_List.size();
		for(int i=0;i<=vendorNumber;i++)
		{
			if(manualInvoiceNewPage.VendorName_List.get(i).getText().equalsIgnoreCase(vendorName))
			{
				Reporter.reportPassResult(browser, "Sprint6_US672_TC1220", "Vendor name should display in vendor name drop down", "Pass");
				break;
			}
			else if(i==vendorNumber)
			{
				Reporter.reportTestFailure(browser, "Sprint6_US672_TC1220", "Sprint6_US672_TC1220", "Vendor name should display in vendor name drop down", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US672_TC1220");
			}
			else
			{
				continue;
			}
		}
		
			
	}
	
	// Verify the association of  WRINS to created manual vendor from raw item information page
	@Test()
	
	public void Sprint6_US672_TC1282() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String casePrice="144.4444";
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemInformationPage rawiteminformation=PageFactory.initElements(driver, RawItemInformationPage.class);
		//Navigate to raw item information page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Enter sample wRIN ID in search box and select one raw item
		rawiteminformation.Search_TB.sendKeys(GlobalVariable.rawItemInformationWrin);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()='"+GlobalVariable.rawItemInformationWrin+"']")).click();
		//Enter case price in case price field
		rawiteminformation.CasePrice_TB.clear();
		rawiteminformation.CasePrice_TB.sendKeys(casePrice);
		//Select monthly from list type drop down
		Select select = new Select(rawiteminformation.ListType_DD);
		select.selectByVisibleText("Monthly");
		//Select a option from the McDonalds GL Account drop down
		Select select1=new Select(rawiteminformation.McDonaldsGLAccount_DD);
		select1.selectByIndex(2);
		//Select the vendor from the primary vendor drop down
		Select select2=new Select(rawiteminformation.PrimaryVendor_DD);
		select2.selectByVisibleText(GlobalVariable.vendorName);	
		//click on save button
		rawiteminformation.Save_BT.click();
		Thread.sleep(4000);
		if(driver.getPageSource().contains("Changes Saved"))
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1282", "Confirmation message should be displayed", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1282", "Sprint6_US672_TC1282", "Confirmation message should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1282");
		}
		
	}
	//Verify the user is able to enter Manual number while creating a manual vendor from manual vendors landing page.
	@Test()
	
	public void Sprint6_US672_TC1292() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String manualNumber=Base.randomNumberFiveDigit();
		String vendorName="Test"+Base.randomNumberFiveDigit();
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualVendorsPage manualVendorPage=PageFactory.initElements(driver, ManualVendorsPage.class);
		//Go to Manual vendors page and click on Add vendor button
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage().AddVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.AddvendorDetailsPopUp_ManualNumber_TB));
		manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		manualVendorPage.AddvendorDetailsPopUp_ManualNumber_TB.sendKeys(manualNumber);
		manualVendorPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.Confirmation_Message));
		//fetch the size of vendor list
		Thread.sleep(5000);
		int size=driver.findElements(By.xpath("//table[@id='vendor_info']/tbody/tr")).size();
		String text=driver.findElement(By.xpath("//table[@id='vendor_info']/tbody/tr["+size+"]/td[2]")).getText();
		if(text.equalsIgnoreCase(manualNumber))
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1292", "User should be able to enter the manual number", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1292", "Sprint6_US672_TC1292", "User should be able to enter the manual number", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1292");
		}
	}
	// Verify the completion of new vendor from "Manual Vendors" Landing page.
	@Test()
	public void Sprint6_US672_TC1295() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = "Test" + Base.randomNumberFiveDigit();
		String manualNumber = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualVendorsPage manualVendorPage = PageFactory.initElements(driver,ManualVendorsPage.class);
		// Go to Manual Vendor page and click on Add Vendor button
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToManualVendorsPage().AddVendor_BT.click();
		// Enter the vendor name in vendor name text box
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.AddvendorDetailsPopUp_VendorName_TB));
		Thread.sleep(1500);
		manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		// Enter the value in the manual number text box
		manualVendorPage.AddvendorDetailsPopUp_ManualNumber_TB.sendKeys(manualNumber);
		// click on save button
		manualVendorPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.Confirmation_Message));
		Thread.sleep(2000);
		if (manualVendorPage.VendorName_Row(vendorName).isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint6_US672_TC1295",
					"Added Vendor should display in Manage vendor page", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint6_US672_TC1295","Sprint6_US672_TC1295",
					"Added Vendor should display in Manage vendor page", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1295");
		}
	}
	
	// Verify the impact on raw item information page once new manual purchase vendor is created.
	@Test()
	public void Sprint6_US672_TC1297() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = "Test" + Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualVendorsPage manualVendorPage = PageFactory.initElements(driver,ManualVendorsPage.class);
		RawItemInformationPage rawItemInformationPage = PageFactory.initElements(driver, RawItemInformationPage.class);
		// Go to Manual Vendor page and click on Add Vendor button
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToManualVendorsPage().AddVendor_BT.click();
		// Enter the vendor name in vendor name text box
		manualVendorPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(vendorName);
		// click on save button
		manualVendorPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorPage.Confirmation_Message));
		Thread.sleep(3000);
		// Go to raw item information page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		homePage.RawItemInformation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInformation_Label));
		// Enter sample wRIN ID in search box and select one raw item
		rawItemInformationPage.Search_TB.sendKeys(GlobalVariable.rawItemInformationWrin);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()='"+ GlobalVariable.rawItemInformationWrin + "']")).click();
		// Verify that added vendor is showing in the Primary Vendor drop down
		for (int i = 0; i <= rawItemInformationPage.PrimaryVendor_VendorName_List.size(); i++) {
			if (rawItemInformationPage.PrimaryVendor_VendorName_List.get(i).getText().equalsIgnoreCase(vendorName)) {
				Reporter.reportPassResult(
						browser,"Sprint6_US672_TC1297",
						"Added Vendor should display in Primary Vendor dropdown",
						"Pass");
				break;
			} else if (i == rawItemInformationPage.PrimaryVendor_VendorName_List.size()) {
				Reporter.reportTestFailure(
						browser,"Sprint6_US672_TC1297","Sprint6_US672_TC1297",
						"Added Vendor should display in Primary Vendor dropdown",
						"Fail");
				AbstractTest.takeSnapShot("Sprint6_US672_TC1297");
			} else {
				continue;
			}
		}
	}
}




