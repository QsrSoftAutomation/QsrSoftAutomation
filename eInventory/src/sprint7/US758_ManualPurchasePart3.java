package sprint7;

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
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceEditPage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;

public class US758_ManualPurchasePart3 extends AbstractTest {

	//TC1356 : Verify the subtotal $ amounts shown for pending manual purchase need to be calculated the same as the subtotals shown for electronic invoice.
	@Test()
	public void Sprint7_US758_TC1356() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1356", "Object1");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Quantity");
		HSSFSheet manualInvoiceNewPageSheet2 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1356", "Object2");
		String wrinId2 = ReadTestData.getTestData(manualInvoiceNewPageSheet2,"EnterQuickSearchWithSuggestionsForManualPurchases");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase with WRIN id 1
		ManualInvoiceNewPage manualInvoiceNewPage = purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId1, vendor, quantity, invoiceId);
		// Add another food item in purchase, save the purchase and navigate to purchase landing page
		Thread.sleep(2000);
		manualInvoiceNewPage.searchAndSelectARawItem(wrinId2).enterQuantityInQuantityTextBox(wrinId2, quantity).clickOnSaveButton();
		Thread.sleep(2000);
		// Click on the pending invoice id in Purchase landing Page
		homePage.Menu_DD_BT.click();
		ManualInvoiceEditPage manualInvoiceEditPage = purchasePage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.WRIN_Column_Label));
		/*
		 * Verify that User should be able to view the following details:
		 * 1)Column headers: (WRIN,DESCRIPTION,UOM,UOM/CASE, $/CASE, Quantity,Sub Total)
		 */
		boolean purchaseColumnHeadersDisplayed = manualInvoiceEditPage.WRIN_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.Description_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.UOM_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.UOMCase_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.DollerCase_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.Quantity_Column_Label.isDisplayed()
				&& manualInvoiceEditPage.SubTotal_Column_Label.isDisplayed();
		if (purchaseColumnHeadersDisplayed) {
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1356",
					"User should be able to view pending purchase column headers in manual invoice edit page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1356","Sprint7_US758_TC1356",
					"User should be able to view pending purchase column headers in manual invoice edit page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1356_condition_1");
		}
		//Get the subtotal values for food items in manual Invoice Edit Page
		String subtotalValueForWRINId1 = manualInvoiceEditPage.getSubtotalValueForFoodItemPurchase(wrinId1);
		String subtotalValueForWRINId2 = manualInvoiceEditPage.getSubtotalValueForFoodItemPurchase(wrinId2);
		//Verify that subtotal values for food items should be present in manual Invoice Edit Page
		boolean subtotalValueDisplayed = (!subtotalValueForWRINId1.isEmpty()) && !(subtotalValueForWRINId2.isEmpty());
		if(subtotalValueDisplayed){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1356",
					"User should be able to view Cost value corresponding to Food subtotal in manual invoice edit page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1356","Sprint7_US758_TC1356",
					"User should be able to view Cost value corresponding to Food subtotal in manual invoice edit page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1356_condition_2");
			}
		}
	
	//TC1367 : Verify the date and time selection post clicking "Finalize" button for pending manual purchase.
	@Test()
	public void Sprint7_US758_TC1367() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint7_US758_TC1356", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase with WRIN id 1
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		homePage.Menu_DD_BT.click();
		// Click on the pending invoice id in Purchase landing Page
		ManualInvoiceEditPage manualInvoiceEditPage = purchasePage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		//Click on finalize button in Manual Invoice Edit Page		
		manualInvoiceEditPage.Finalize_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.finalizeWindowDateField_label));
		//Verify that date and time fields should be displayed in finalize window
		boolean dateAndTimeFieldDisplayed = manualInvoiceEditPage.finalizeWindowDateField_label.isDisplayed()
				&& manualInvoiceEditPage.finalizeWindowTimeField_label.isDisplayed();
		if(dateAndTimeFieldDisplayed){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1367",
					"User should be able to view date and time field in finalize window for pending purchase",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1367","Sprint7_US758_TC1367",
					"User should be able to view date and time field in finalize window for pending purchase",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1367_condition_1");
		}
		//Click on the datePicker previous button 
		manualInvoiceEditPage.datePickerPrevious_Button.click();
		//click on the time label which is displayed previous to current time
		manualInvoiceEditPage.previousToCurrentTime_label.click();
		Thread.sleep(1500);
		/*Verify that user is not able to select date in Finalize pop up as it should be read only and
		user is able to select time from dateTime picker and previous time values are not disabled*/
		boolean timeSelectdFromDateTimePicker = (!(manualInvoiceEditPage.finalizeWindowSelectdTime_label.getAttribute("class").contains("xdsoft_disabled"))
				&& manualInvoiceEditPage.finalizeWindowDateField_label.getAttribute("readonly").equals("true"));
		if(timeSelectdFromDateTimePicker){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1367",
					"User should be able to select time in finalize window and previous time options should not be disabled",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1367","Sprint7_US758_TC1367",
					"User should be able to select time in finalize window and previous time options should not be disabled",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1367_condition_2");
			}
		}
	
	//TC1370 : Verify manual purchase details screen from purchase history.
	@Test()
	public void Sprint7_US758_TC1370() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1356", "Object1");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Quantity");
		HSSFSheet manualInvoiceNewPageSheet2 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1356", "Object2");
		String wrinId2 = ReadTestData.getTestData(manualInvoiceNewPageSheet2,"EnterQuickSearchWithSuggestionsForManualPurchases");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase with WRIN id 1
		ManualInvoiceNewPage manualInvoiceNewPage = purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId1, vendor, quantity, invoiceId);
		// Add another food item in purchase, save the purchase, finalize the purchase,post the purchase and navigate to purchase landing page
		manualInvoiceNewPage.searchAndSelectARawItem(wrinId2).enterQuantityInQuantityTextBox(wrinId2, quantity)
				.clickOnSaveButton().clickOnFinalizeButton().postTheManualPurchage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PendingPurchases_Title));
		//Get the current Date from system
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String todayDate=Base.returnTodayDate();
        //Get Month Start date
        cal.set(Calendar.DAY_OF_MONTH, 1);	
        String monthStartDate = dateFormat.format(cal.getTime());
        /*Verify that Month Start date should be displayed in start date field
        and today date should be displayed in end date field in Purchase Page*/
        wait.until(ExpectedConditions.visibilityOf(purchasePage.StartDate_BT));
        Thread.sleep(2000);
        boolean startDateEndDateDisplayed = monthStartDate.equals(purchasePage.StartDate_BT.getAttribute("value"))
        		&& todayDate.equals(purchasePage.EndDate_BT.getAttribute("value"));
        if(startDateEndDateDisplayed){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1370",
					"Month start date and today date should be displayed in purchase history start date,end date fields in purchase page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1370","Sprint7_US758_TC1370",
					"Month start date and today date should be displayed in purchase history start date,end date fields in purchase page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1370_condition_1");
			}
        //Click on purchase history button in Purchase Page
        wait.until(ExpectedConditions.elementToBeClickable(purchasePage.GetPurchaseHistory_BT));
        purchasePage.GetPurchaseHistory_BT.click();
        Thread.sleep(10000);
        //Get the posted purchase from the Posted Purchase history Table and click that record
        purchasePage.clickOnPostedPurchaseRecord(todayDate, invoiceId);
        wait.until(ExpectedConditions.visibilityOf(purchasePage.PostedPurchaseDetailPopUp_WRINColumn_Label));
       /* Verify that User should be able to view the column headers in Posted Purchase Pop up :-
        WRIN,DESCRIPTION,UOM,UOM/CASE, $/CASE, Quantity,Sub Total*/
		boolean columnHeadersDisplayedForPostedPurchase = purchasePage.PostedPurchaseDetailPopUp_WRINColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_descriptionColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_UOMColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_UOMCaseColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_CasesPurchasedColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_CasePriceColumn_Label.isDisplayed()
				&& purchasePage.PostedPurchaseDetailPopUp_costColumn_Label.isDisplayed();
		if(columnHeadersDisplayedForPostedPurchase){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1370",
					"User should be able to view column headers for posted purchase in posted Purchase pop up ",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1370","Sprint7_US758_TC1370",
					"User should be able to view column headers for posted purchase in posted Purchase pop up ",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1370_condition_2");
			}
		//Get the subtotal values for food items in Posted Purchase Pop up
        String subtotalValueForWRINId1 = purchasePage.getSubtotalValueForFoodItemPostedPurchase(wrinId1);
        String subtotalValueForWRINId2 =  purchasePage.getSubtotalValueForFoodItemPostedPurchase(wrinId2);
       //Verify that subtotal values for food items should be present Posted Purchase Pop up
        boolean subtotalValueDisplayed = (!subtotalValueForWRINId1.isEmpty()) && !(subtotalValueForWRINId2.isEmpty());
		if(subtotalValueDisplayed){
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1370",
					"User should be able to view Cost value corresponding to Food subtotal in posted Purchase pop up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1370","Sprint7_US758_TC1370",
					"User should be able to view Cost value corresponding to Food subtotal in posted Purchase pop up",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1356_condition_3");
			}
	}
	
	//TC1369 : Verify no future dates or dates and time past the current date is selected on the calendar and time selector, once the manual purchase is finalized.
	@Test()
	public void Sprint7_US758_TC1369() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint7_US758_TC1369", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase with WRIN id and navigate back to purchase page
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		homePage.Menu_DD_BT.click();
		ManualInvoiceEditPage manualInvoiceEditPage = purchasePage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.WRIN_Column_Label));
		// Click on finalize button in Manual Invoice Edit Page
		manualInvoiceEditPage.Finalize_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.finalizeWindowDateField_label));
		// Verify that user is not able to select date in Finalize purchase pop up and date field is read only
		if (manualInvoiceEditPage.finalizeWindowDateField_label.getAttribute("readonly").equals("true")) {
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1369",
					"User should not be able to select date from finalize purchase pop up and date field is read only",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1369","Sprint7_US758_TC1369",
					"User should not be able to select date from finalize purchase pop up and date field is read only",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1369_condition_1");
		}
	}
	
	//TC1483 Verify the information contained for posted invoice  is sent to the store ledger.
	@Test()
	public void Sprint7_US758_TC1483() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1483", "Object1");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Quantity");
		HSSFSheet manualInvoiceNewPageSheet2 = ReadTestData.getTestDataSheet("Sprint7_US758_TC1483", "Object2");
		String wrinId2 = ReadTestData.getTestData(manualInvoiceNewPageSheet2,"EnterQuickSearchWithSuggestionsForManualPurchases");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage = PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase with WRIN id and navigate back to purchase page
		purchasePage.goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor).searchAndSelectARawItem(wrinId1).searchAndSelectARawItem(wrinId2)
				.enterQuantityInQuantityTextBox(wrinId1, quantity).enterQuantityInQuantityTextBox(wrinId2, quantity).Search_TB_02.clear();
		manualInvoiceNewPage.InvoiceNumber_TB.sendKeys(invoiceId);
		manualInvoiceNewPage.Search_TB_02.sendKeys(Keys.BACK_SPACE);
		manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Quantity_TB_List.get(1).click();
		Thread.sleep(3000);
		String expFoodValue = manualInvoiceNewPage.TotalPurchases_Value.getText().trim().replace("$", "");
		System.out.println("expFoodValue" + expFoodValue);
		// Click on finalize button in Manual Invoice Edit Page
		manualInvoiceNewPage.Finalize_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.FinalizePopUp_Continue_BT));
		manualInvoiceNewPage.FinalizePopUp_Continue_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PendingPurchases_Title));
		// Click on Get Purchase History button
		purchasePage.GetPurchaseHistory_BT.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='posted_purchases_selection_table']")));
		String date = Base.returnTodayDate();
		purchasePage.clickOnPostedPurchaseRecord(date, invoiceId);
		// wait until posted purchase detail page is opened
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PostedPurchaseDetailPopUp_PostedPurchaseDetail_Label));
		String actFoodValue = purchasePage.PostedPurchaseDetailPopUp_TotalPurchases_Value.getText().trim();
		System.out.println("actFoodValue" + actFoodValue);
		if (actFoodValue.equalsIgnoreCase(expFoodValue)) {
			Reporter.reportPassResult(
					browser,"Sprint7_US758_TC1483",
					"User should be able to view the subtotal against food items on posted manual purchase",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US758_TC1483","Sprint7_US758_TC1483",
					"User should be able to view the subtotal against food items on posted manual purchase.",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US758_TC1483");
		}
	}
}
