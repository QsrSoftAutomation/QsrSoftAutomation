package sprint7;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemPromoPage;
import eInventoryPageClasses.RawItemWastePage;

public class US425_WastePromoLandingPage extends AbstractTest {

	// TC1737 : Verify that the user should be able to initiate a Raw Waste entry.
	@Test()
	public void Sprint7_US425_TC1737() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String innerPack = "3";
		String outerPack = "3";
		String looseUnits = "8";
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Promotions And WastePage

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		int recordCountBeforeAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		// Click on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// add a raw waste item
		rawItemWastePage.addARawItem(GlobalVariable.rawItemWatsewrin1, innerPack, outerPack,looseUnits);
		//get the total waste amount
		String wasteSubtotal = rawItemWastePage.getTotalWasteAmunt();
		System.out.println("Waste Totral"+wasteSubtotal);
		//click on bck to top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		// Submit the raw waste entry
		rawItemWastePage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		int recordCountAfterAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		promotionsAndWastePage.Record_Expend_Button_List.get(0).click();
		Thread.sleep(3000);
		// verify that Raw Waste entry should be added in Raw Waste and Promo History table
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String todayDate=dateFormat.format(date);
		boolean wasteEntryAdded = promotionsAndWastePage.isRawWasteEntryPresent(todayDate, wasteSubtotal);
		if (wasteEntryAdded && (recordCountAfterAdd==recordCountBeforeAdd+1)) {
			Reporter.reportPassResult(
					browser,"Sprint7_US425_TC1737",
					"User should be able to view Raw Waste entry in Raw Waste and Promo History table",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US425_TC1737","Sprint7_US425_TC1737",
					"User should be able to view Raw Waste entry in Raw Waste and Promo History table",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US425_TC1737");

		}
	}

	// TC1738 : Verify that the user should be able to able to initiate a Raw Promo entry.
	@Test()
	public void Sprint7_US425_TC1738() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String outerPack="3";
		String innerPack="2";
		String looseUnits="8";
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String todayDate=dateFormat.format(date);
		//Go to the Promotion and Waste page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		Thread.sleep(3000);
		int recordCountBeforeAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemPromoPage rawItemPromoPage=PageFactory.initElements(driver, RawItemPromoPage.class);
		//click on Promo tab
		promotionsAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		rawItemPromoPage.addARawPromoItem(GlobalVariable.rawItemPromowrin1, innerPack, outerPack, looseUnits);
		rawItemPromoPage.addASecondPromoItem(GlobalVariable.rawItemPromowrin2, "", outerPack, looseUnits);
		Thread.sleep(3000);
		String amount1=driver.findElement(By.xpath("//tr[@id='wasteItem0']/td[7]")).getText().replace("$", "");
		String amount2=driver.findElement(By.xpath("//tr[@id='wasteItem1']/td[7]")).getText().replace("$","");
		BigDecimal amountOfFirstItem = new BigDecimal(amount1);
		BigDecimal amountOfSecondItem = new BigDecimal(amount2);
		BigDecimal totalAmount1=amountOfFirstItem.add(amountOfSecondItem);
		String totalAmount=String.valueOf(totalAmount1);
		//Click on Back to Top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemPromoPage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		int recordCountAfterAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		boolean result=promotionsAndWastePage.isRawPromoEntryPresent(todayDate, totalAmount);
		if (result && (recordCountAfterAdd==recordCountBeforeAdd+1)) {
			Reporter.reportPassResult(
					browser,"Sprint7_US425_TC1738",
					"User should be able to view Promo entry in Raw Waste and Promo History table",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US425_TC1738","Sprint7_US425_TC1738",
					"User should be able to view Promo entry in Raw Waste and Promo History table",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US425_TC1738");

		}
	}
	
	// TC1739 : Verify that the user should be able to select a start and end date to filter the list of raw waste/promo entries.
	@Test()
	public void Sprint7_US425_TC1739() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Promotions And WastePage

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 5 days earlier date from today date
		cal1.add(Calendar.DATE, -5);
        String fromDate = dateFormat.format(cal1.getTime());
        //Get end date as 1 day earlier date from today date
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -1);
        String toDate = dateFormat.format(cal2.getTime());
        //Select start date and end date to filter the list of raw waste/promo entries.
		promotionsAndWastePage.selectStartAndEndDateForWasteHistory(fromDate,toDate);
		//click on update table button
		promotionsAndWastePage.updateTable_BT.click();
		Thread.sleep(2000);
		//get fromDateHeader text and toDateHeader text above the Raw Waste and Promo History table 
		String startDate = promotionsAndWastePage.fromDateHeader_Label.getText().replace("-", "");
		String endDate = promotionsAndWastePage.toDateHeader_Label.getText().replace("-", "");
		fromDate = fromDate.replace("/", "");
		toDate = toDate.replace("/", "");
		/*Verify that fromDateHeader text and toDateHeader text above the Raw Waste and Promo History table 
		is same as start date (5 days earlier date) and end date( 1 day earlier date) we have passed*/
		if(fromDate.equals(startDate) && toDate.equals(endDate)){
			Reporter.reportPassResult(
					browser,"Sprint7_US425_TC1739",
					"User should be able to select a start and end date for Raw Waste and Promo History records",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US425_TC1739","Sprint7_US425_TC1739",
					"User should be able to select a start and end date for Raw Waste and Promo History records",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US425_TC1739");

		}
			
		}
	
	// TC1741 : Verify that the user should be able to view raw promo and waste history for the selected date range.
	@Test()
	public void Sprint7_US425_TC1741() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Promotions And WastePage

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 4 days earlier date from today date
		cal1.add(Calendar.DATE, -4);
		String fromDate = dateFormat.format(cal1.getTime());
		// Get end date as 1 day earlier date from today date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String toDate = dateFormat.format(cal2.getTime());
		// Select start date and end date to filter the list of raw waste/promo entries.
		promotionsAndWastePage.selectStartAndEndDateForWasteHistory(fromDate,toDate);
		// click on update table button
		promotionsAndWastePage.updateTable_BT.click();
		Thread.sleep(4000);
		//Change the date format as yyyymmdd
		String reformattedDate = fromDate.split("/")[2]+fromDate.split("/")[0]+fromDate.split("/")[1];
		System.out.println("reformattedDate"+reformattedDate);
		/*verify that on clicking the date group header in raw waste/promo history table it will be expanded 
		or collapsed depends nn the default view*/
		boolean toggleRecordsView = promotionsAndWastePage.verifyExpandAndCollapseFunctionalityForDateGroup(reformattedDate);
		toggleRecordsView = toggleRecordsView && promotionsAndWastePage.verifyExpandAndCollapseFunctionalityForDateGroup(reformattedDate);
		if(toggleRecordsView){
			Reporter.reportPassResult(
					browser,"Sprint7_US425_TC1741",
					"User should be able to expand and collapse Raw Waste and Promo History records for a date",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US425_TC1741","Sprint7_US425_TC1741",
					"User should be able to expand and collapse Raw Waste and Promo History records for a date",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US425_TC1741");

		}
			
		}
	
	  // TC1743 : Verify that the user should be able to view the raw waste/promo details after double clicking on an entry.
		@Test()
		public void Sprint7_US425_TC1743() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			String storeId=GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Select the store 762 and navigate to Promotions And WastePage

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// Get start date as 4 days earlier date from today date
			cal1.add(Calendar.DATE, -4);
			String fromDate = dateFormat.format(cal1.getTime());
			// Get end date as today date
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, 0);
			String toDate = dateFormat.format(cal2.getTime());
			// Select start date and end date to filter the list of raw waste/promo entries.
			promotionsAndWastePage.selectStartAndEndDateForWasteHistory(fromDate,toDate);
			// click on update table button
			promotionsAndWastePage.updateTable_BT.click();
			Thread.sleep(7000);
			//Change the date format as yyyymmdd
			String reformattedDate = toDate.split("/")[2]+toDate.split("/")[0]+toDate.split("/")[1];
			//click on the first record for the end date
			promotionsAndWastePage.clickOnFirstRecordOfADateGroup(reformattedDate);
			wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.wasteDetail_WRINColumn_Label));
			// verify that details for that records will be displayed in a raw history pop up
			boolean wasteDetailsDisplayed = promotionsAndWastePage.wasteDetail_WRINColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_DescriptionColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_OutePackColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_InnerPackColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_LooseUnitColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_TotalUnitsColumn_Label.isDisplayed()
					&& promotionsAndWastePage.wasteDetail_SubtotalColumn_Label.isDisplayed();
			if(wasteDetailsDisplayed){
				Reporter.reportPassResult(
						browser,"Sprint7_US425_TC1743",
						"User should be able to view waste/Promo details for a selected record",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"Sprint7_US425_TC1743","Sprint7_US425_TC1743",
						"User should be able to view waste/Promo details for a selected record",
						"Fail");
				AbstractTest.takeSnapShot("Sprint7_US425_TC1743");

			}
			
			}
}
