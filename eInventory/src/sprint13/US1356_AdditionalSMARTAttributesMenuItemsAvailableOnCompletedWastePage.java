package sprint13;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.CompletedWastePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;

public class US1356_AdditionalSMARTAttributesMenuItemsAvailableOnCompletedWastePage extends AbstractTest{
	
	//TC2426: Verify that Menu Items that are approved for restaurant or  "POS Active" are available to search and add are on the Completed Waste page.
	@Test()
	public void sprint13_US1356_TC2426() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItemId = "5280";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver, CompletedWastePage.class);
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		promotionAndWastePage.CompletedWaste_BT.click();
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId);
		if (completedWastePage.CompletedWastePopUp_SearchBox_TB.getAttribute("value").contains(menuItemId)) {
			Reporter.reportPassResult(browser, "sprint13_US1356_TC2426",
					"User should be able to view menu item on completed waste page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser, "sprint13_US1356_TC2426","sprint13_US1356_TC2426",
					"User should be able to view menu item on completed waste page",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US1356_TC2426");
		}
	}
	
	//TC2427: Verify that Menu Items that have no activity for 60 days are not available on the Completed Waste page.
	@Test()
	public void sprint13_US1356_TC2427() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItemId = "1001";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver, CompletedWastePage.class);
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		promotionAndWastePage.CompletedWaste_BT.click();
		completedWastePage.CompletedWastePopUp_SearchBox_TB.sendKeys(menuItemId);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size = driver.findElements(By.xpath("//strong[text()='" + menuItemId + "']")).size();
		if (size == 0) {
			Reporter.reportPassResult(
					browser,"sprint13_US1356_TC2426",
					"User should not be able to view menu item on completed waste page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint13_US1356_TC2426","sprint13_US1356_TC2426",
					"User should not be able to view menu item on completed waste page",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US1356_TC2426");
		}
	}

}
