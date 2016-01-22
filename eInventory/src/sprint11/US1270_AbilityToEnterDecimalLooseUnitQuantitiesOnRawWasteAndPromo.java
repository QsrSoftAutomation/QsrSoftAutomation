package sprint11;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemPromoPage;
import eInventoryPageClasses.RawItemWastePage;

public class US1270_AbilityToEnterDecimalLooseUnitQuantitiesOnRawWasteAndPromo extends AbstractTest{
	
	//TC2044:Verify that the user should be able to enter 2 digits past the decimal in  loose unit fields on the raw waste entry page.
	@Test()
	public void sprint11_US1270_TC2044() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01=GlobalVariable.rawItemWatsewrin1;
		String outerPack="1";
		String innerPack="1";
		String looseUnits="11.22";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to promo and waste landing page

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		//click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver, RawItemWastePage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		//Add a raw waste item with loose unit quantity as 2 digits past the decimal
		rawItemWastePage.addARawItem(wrinId01, innerPack, outerPack, looseUnits);
		Thread.sleep(3000);
		//Verify that user is able to enter raw waste for a wrin with loose unit quantity as 2 digits past the decimal
		if (driver.findElement(By.xpath("//*[@id='wasteItem0']/td[5]")).getText().equals(looseUnits)) {
			Reporter.reportPassResult(
					browser,"sprint11_US1270_TC2044",
					"user should be able to enter 2 digits past the decimal in  loose unit fields on the raw waste entry page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1270_TC2044","sprint11_US1270_TC2044",
					"user should be able to enter 2 digits past the decimal in  loose unit fields on the raw waste entry page",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1270_TC2044");
		}
	}
	
	//TC2045:Verify that the user should be able to enter 2 digits past the decimal in  loose unit fields on the raw promo entry page.
	@Test()
	public void sprint11_US1270_TC2045() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01 = GlobalVariable.rawItemWatsewrin1;
		String outerPack = "1";
		String innerPack = "1";
		String looseUnits = "11.22";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to promo and waste landing page
		PromotionsAndWastePage promotionsAndWastePage = homePage
				.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		// click on Promo tab
		promotionsAndWastePage.RawPromo_BT.click();
		RawItemPromoPage rawItemPromoPage = PageFactory.initElements(driver,RawItemPromoPage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		//Add a raw promo item with loose unit quantity as 2 digits past the decimal
		rawItemPromoPage.addARawPromoItem(wrinId01, innerPack, outerPack, looseUnits);
		Thread.sleep(3000);
		//Verify that user is able to enter raw promo for a wrin with loose unit quantity as 2 digits past the decimal
		if (driver.findElement(By.xpath("//*[@id='wasteItem0']/td[5]")).getText().equals(looseUnits)) {
			Reporter.reportPassResult(
					browser,"sprint11_US1270_TC2044",
					"user should be able to enter 2 digits past the decimal in  loose unit fields on the raw waste entry page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1270_TC2044","sprint11_US1270_TC2044",
					"user should be able to enter 2 digits past the decimal in  loose unit fields on the raw waste entry page",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1270_TC2044");
		}
	}
	
	//TC2084:Verify that the user should not be able to enter more than 2 digits past the decimal as well as negative values in  loose unit fields on the raw promo entry page.
	@Test()
	public void sprint11_US1270_TC2084() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01 = GlobalVariable.rawItemWatsewrin1;
		String outerPack = "1";
		String innerPack = "1";
		String invalidLooseUnitsQty1 = "-11.22";
		String invalidLooseUnitsQty2 = "11.222";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to promo and waste landing page

		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver, RawItemWastePage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		//Select current date and time
		rawItemWastePage.enterCurrentDateAndTimeInRawWasteOccurredAtField();
		rawItemWastePage.SelectDateAndTime_TB.click();
		//Search and select raw item
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId01);
		//Enter inner pack qty if applicable for the raw item
		try {
			rawItemWastePage.InnerPack_TB.sendKeys(innerPack);
		} catch (Exception e) {
			// Do Nothing
		}
		Thread.sleep(2000);
		//Enter outer pack qty
		rawItemWastePage.OuterPack_TB.sendKeys(outerPack);
		//Enter loose unit qty as negative value
		rawItemWastePage.LooseUnits_TB.sendKeys(invalidLooseUnitsQty1);
		Thread.sleep(1000);
		//Verify that error message should displayed
		boolean errorMsgDisplayed = Base.isElementDisplayed(rawItemWastePage.InvalidValue_Error_Message);
		//Clear the loose units qty text box
		rawItemWastePage.LooseUnits_TB.clear();
		//Enter loose unit qty as value as more than 2 digits past the decimal
		rawItemWastePage.LooseUnits_TB.sendKeys(invalidLooseUnitsQty2);
		Thread.sleep(1000);
		//Verify that error message should displayed
		errorMsgDisplayed = errorMsgDisplayed & Base.isElementDisplayed(rawItemWastePage.InvalidValue_Error_Message);
		if (errorMsgDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint11_US1270_TC2084",
					"user should not be able to enter more than 2 digits past the decimal as well as negative values in  loose unit fields on the raw promo entry page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1270_TC2084","sprint11_US1270_TC2084",
					"user should not be able to enter more than 2 digits past the decimal as well as negative values in  loose unit fields on the raw promo entry page",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1270_TC2084");
		}
	}

}
