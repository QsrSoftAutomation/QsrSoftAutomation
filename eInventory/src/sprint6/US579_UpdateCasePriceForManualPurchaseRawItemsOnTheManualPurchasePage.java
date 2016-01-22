package sprint6;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.Reporter;
import sprint2.AbstractTest;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;

public class US579_UpdateCasePriceForManualPurchaseRawItemsOnTheManualPurchasePage extends AbstractTest
{

 	// Verify the user is able to edit case price value for added item in manual purchase screen.
	
	@Test()
	
	public void Sprint6_US579_TC1243() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().goToManualInvoiceNewPage().selectAVendorFromDropdown(GlobalVariable.vendorName);
		//Search and select a WRIN
		manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(GlobalVariable.createPurchaseWrin);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+GlobalVariable.createPurchaseWrin+"]")).click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.DollerCase_TB_List.get(0)));
		//Enter the new $/Case price value
		manualInvoiceNewPage.DollerCase_TB_List.get(0).clear();
		manualInvoiceNewPage.DollerCase_TB_List.get(0).sendKeys("12.33");
		Thread.sleep(3000);
		//Verify that updated $/case price value is showing or not
		if(manualInvoiceNewPage.DollerCase_TB_List.get(0).getAttribute("value").equalsIgnoreCase("12.33"))
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1243", "User should be able to edit $/case value", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1243", "Sprint6_US672_TC1243", "User should be able to edit $/case value", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1243");
		}
	}
	
	// Verify dollar format validations and fields should be consistent with Cash on manual purchase detail page.
	
	@Test()
	
	public void Sprint6_US579_TC1279() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().goToManualInvoiceNewPage().selectAVendorFromDropdown(GlobalVariable.vendorName);
		//Search and select a WRIN
		manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(GlobalVariable.createPurchaseWrin);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+GlobalVariable.createPurchaseWrin+"]")).click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.DollerCase_TB_List.get(0)));
		manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys("2");
		manualInvoiceNewPage.Search_TB_02.click();
		//Verify the following fields on this detail page
		if(manualInvoiceNewPage.Cancel_BT.isDisplayed() && 
				manualInvoiceNewPage.Save_BT.isDisplayed() &&
				manualInvoiceNewPage.Finalize_BT.isDisplayed() &&
				manualInvoiceNewPage.Search_TB_02.isDisplayed() &&
				manualInvoiceNewPage.TotalFood_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalFood_Value.getText().length()>0 &&
				manualInvoiceNewPage.TotalOpsSupplies_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalOpsSupplies_Value.getText().length()>0 &&
				manualInvoiceNewPage.TotalPaper_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalPaper_Value.getText().length()>0 &&
				manualInvoiceNewPage.TotalLines_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalLines_Value.getText().length()>0 &&
				manualInvoiceNewPage.TotalNonProductOther_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalNonProductOther_Value.getText().length()>0 &&
				manualInvoiceNewPage.TotalNonProductHappyMealPremiums_Label.isDisplayed() &&
				manualInvoiceNewPage.TotalNonProductHappyMealPremiums_Value.getText().length()>0
				)
		{
			
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1279", "User should be able to view the 'Cancel','Save',and 'Finalize button and 'TotalFood','TotalOpsSupplies','TotalPaper','TotalLines','TotalNonProductOther' and 'TotalNonProductHappyMealPremiums' fileds shoudl display with their respective value", "Pass");
			
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1279", "Sprint6_US672_TC1279", "User should be able to view the 'Cancel','Save',and 'Finalize button and 'TotalFood','TotalOpsSupplies','TotalPaper','TotalLines','TotalNonProductOther' and 'TotalNonProductHappyMealPremiums' fileds shoudl display with their respective value", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1279");
		}

		
	}
	
	//  	Verify the error message while finalizing an added WRIN from manual purchase detail screen without entering any value for $/case.
		
	@Test()
	
	public void Sprint6_US579_TC1309() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().goToManualInvoiceNewPage().selectAVendorFromDropdown(GlobalVariable.vendorName);
		//Search and select a WRIN
		manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(GlobalVariable.createPurchaseWrin);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("//strong[text()="+GlobalVariable.createPurchaseWrin+"]")).click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.DollerCase_TB_List.get(0)));
		manualInvoiceNewPage.Quantity_TB_List.get(0).clear();
		manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys("2");
		manualInvoiceNewPage.DollerCase_TB_List.get(0).click();
		manualInvoiceNewPage.DollerCase_TB_List.get(0).clear();
		manualInvoiceNewPage.Finalize_BT.click();
		if(manualInvoiceNewPage.FieldValidation_ErrorMessage.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint6_US672_TC1309", "Field Validation error message should display", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US672_TC1309", "Sprint6_US672_TC1309", "Field Validation error message should display", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US672_TC1309");
		}
			
	}
	
	
}
