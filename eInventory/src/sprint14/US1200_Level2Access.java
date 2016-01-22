package sprint14;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.CompletedWastePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemWastePage;
import eInventoryPageClasses.TransferLandingPage;
import sprint2.AbstractTest;

public class US1200_Level2Access extends AbstractTest
{
	
	//TC2511 To verify that the level 2 users are able to use all of the functionality on the Purchases page.
	//Functionality not working will complete once the functionality will be working
	@Test()
	
	public void sprint14_US1200_TC2518() throws InterruptedException
	{
		/** Variable Section : **/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("sprint14_US1200_TC2518", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"WRINId");
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver, RawItemWastePage.class);
		PromotionsAndWastePage promotionAndWastePage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//Go to Raw waste page
		promotionAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		//Create a raw waste entry
		rawItemWastePage.addARawItem(wrinId, "2", "3", "4");
		
		
	}
	
	//TC2519 To verify that the level 2 users are able to use all of the functionality on the Promotions and Waste page.
	//Completed waste page is not working properly so not able to completed it
	@Test()
	
	public void sprint14_US1200_TC2519() throws InterruptedException
	{
		/** Variable Section : **/
		HSSFSheet completedWastePageSheet = ReadTestData.getTestDataSheet("sprint14_US1200_TC2519", "Object1");
		String wrinId = ReadTestData.getTestData(completedWastePageSheet,"WRINId");
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver, CompletedWastePage.class);
		PromotionsAndWastePage promotionAndWastePage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//Go to Completed Waste page
		promotionAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionAndWastePage.CompletedWastePopUp_Title));
		//Search a menu item for waste page
		completedWastePage.searchMenuItemForCompletedWaste(wrinId);
		Thread.sleep(5000);
		
		
	}
	
	//TC2522 To Verify if the level 2 user is restricted to enter raw promo
	
	@Test()
	
	public void sprint14_US1200_TC2522() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PromotionsAndWastePage promotionAndWastePage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		Thread.sleep(5000);
		//Verify that 'Raw Promo' button is available on the page
		if(Base.isElementDisplayed(promotionAndWastePage.RawPromo_BT))
		{
			Reporter.reportTestFailure(browser, "sprint14_US1200_TC2522", "sprint14_US1200_TC2522", "Raw Promo button should display on Promotion and Waste page", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1200_TC2522");
		
			
		}
		else
		{
			Reporter.reportPassResult(browser, "sprint14_US1200_TC2522",
					"Raw Promo button should display on Promotion and Waste page",
					"Pass");
		}
		
	}
	
	
	
	//TC2523 To Verify if the level 2 user is able to transfer raw items in/out to other stores.
	
	@Test()
	
	public void sprint14_US1200_TC2523() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		String samplewRINID = GlobalVariable.addTransferItemWrin;
		String transferType ="in";
		String transferStoreNumber = "716";
		String caseQuantity ="1";
		String innerPackQuantity ="1";
		String looseUnitQuantity ="1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferLandingPage.selectTransferType(transferType)
				.selectLocationToTransfer(transferStoreNumber).insertAndAddDetailsToTransfer(samplewRINID, caseQuantity, innerPackQuantity,looseUnitQuantity);
		Thread.sleep(4000);
		//Verify the 'Cancel' button functionality
		transferLandingPage.AddTransferItemsPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(transferLandingPage.CanceTransferPopup_Yes_BT));
		transferLandingPage.CanceTransferPopup_Yes_BT.click();
		Thread.sleep(3000);
		if(!(Base.isElementDisplayed(transferLandingPage.CanceTransferPopup_Yes_BT)))
		{
			Reporter.reportPassResult(browser, "sprint14_US1200_TC2523",
					"Cancel button functionality should work properly",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "sprint14_US1200_TC2523", "sprint14_US1200_TC2523", "Cancel button functionality should work properly", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1200_TC2523");
		}
		
		//Verify the 'Submit' button functionality
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		String time=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		transferLandingPage.selectTransferType(transferType)
				.selectLocationToTransfer(transferStoreNumber).insertAndAddDetailsToTransfer(samplewRINID, caseQuantity, innerPackQuantity,looseUnitQuantity);
		Thread.sleep(4000);
		String amount = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT));
		transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CreateNewTransfers_BT));
		Thread.sleep(4000);
		if(transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(), time, amount))
		{
			Reporter.reportPassResult(browser, "sprint14_US1200_TC2523",
					"Submitt button functionality should be working properly",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "sprint14_US1200_TC2523", "sprint14_US1200_TC2523", "Submitt button functionality should be working properly", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1200_TC2523");
		}
		
		
	}
	
	
	
	//TC2524 To Verify if the level 2 user is restricted to transfer raw items to office
	@Test()
	
	public void sprint14_US1200_TC2524() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		Select selectTransferType = new Select(transferLandingPage.TransferType_DD);
		System.out.println("first option is"+selectTransferType.getOptions().size());
		for(int i=0;i<selectTransferType.getOptions().size();i++)
		{
			String text=selectTransferType.getOptions().get(i).getText();
			System.out.println(text);
			if(text.equalsIgnoreCase("Office Transfer"))
			{
				Reporter.reportTestFailure(browser, "sprint14_US1200_TC2524", "sprint14_US1200_TC2524", "Office transfer option should not be present", "Fail");
				AbstractTest.takeSnapShot("sprint14_US1200_TC2524");
				break;
			}
			else if(i==(selectTransferType.getOptions().size()-1))
			{
				Reporter.reportPassResult(browser, "sprint14_US1200_TC2524",
						"Office transfer option should not be present",
						"Pass");
				break;
			}
			else
			{
				continue;
			}
		}
		
		
	}
	
	
	//TC2525 To Verify if the level 2 user is able to view and print raw items transfer details.
	@Test()
	
	public void sprint14_US1200_TC2525() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
				
		/** Variable Section : **/
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		//Click on View transfer button for the first record
		driver.findElements(By.xpath("//button[@id='htmlButton' and @value='View']")).get(0).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='htmlButton' and text()='Print'])[2]")));
		if(Base.isElementDisplayed(By.xpath("(//button[@id='htmlButton' and text()='Print'])[2]")))
		{
			Reporter.reportTestFailure(browser, "sprint14_US1200_TC2525", "sprint14_US1200_TC2525", "Print button should display", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1200_TC2525");
		}
		else
		{
			Reporter.reportPassResult(browser, "sprint14_US1200_TC2525",
					"Print button should display",
					"Pass");
		}
		
	}
	
	
	//TC2528 To verify if the level 2 user is able to view the raw item activity details.
	@Test()
	
	public void sprint14_US1200_TC2528() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.level2StoreId;
		String userId = GlobalVariable.level2UserId;
		String wrinID=GlobalVariable.rawItemActivityWrin;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemActivityPage rawItemActivityPage = PageFactory.initElements(driver, RawItemActivityPage.class);
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(wrinID);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.getItemDetails_Button));
		rawItemActivityPage.getItemDetails_Button.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[@id='raw_item_detail_label2']")));
		Thread.sleep(5000);
		int size=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr")).size();
		if(size>2)
		{
			Reporter.reportTestFailure(browser, "sprint14_US1200_TC2528", "sprint14_US1200_TC2528", "User should be able to view the details of Raw Item Activity", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1200_TC2528");
		}
		else
		{
			Reporter.reportPassResult(browser, "sprint14_US1200_TC2528",
					"User should be able to view the details of Raw Item Activity",
					"Pass");
		}
				
	}
	
	//TC2529 To verify that the level 2 users are able to use all of the functionality on the Raw Item Activity page.
	
	@Test()
	
	public void sprint14_US1200_TC2529()
	{
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
