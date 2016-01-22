package sprint7;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US671_MenuItemMaintenanceDropdownOptions extends AbstractTest {

	// TC1450 Verify drop down selection is available for menu items activity page from inventory drop down
	@Test()
	public void Sprint7_US671_TC1450() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		boolean result = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemActivityPage().MenuItemActivity_Title.isDisplayed();
		if (result) {
			Reporter.reportPassResult(browser, "Sprint7_US671_TC1450",
					"User should be navigated to Menu Item Activity Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint7_US671_TC1450","Sprint7_US671_TC1450",
					"User should be navigated to Menu Item Activity Page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US671_TC1450");
		}
	}

	// TC1451 Verify drop down selection is available for menu items information page from inventory drop down
	@Test()
	public void Sprint7_US671_TC1451() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		boolean result = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemInformationPage().MenuItemInformation_Title.isDisplayed();
		if (result) {
			Reporter.reportPassResult(browser, "Sprint7_US671_TC1451",
					"User should be navigated to Menu Item Information Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint7_US671_TC1451","Sprint7_US671_TC1451",
					"User should be navigated to Menu Item Information Page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US671_TC1451");
		}
	}
}
