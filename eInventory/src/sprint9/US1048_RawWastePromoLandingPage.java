package sprint9;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;

public class US1048_RawWastePromoLandingPage extends AbstractTest {
	
	//TC1727:Verify that  all the daily roll ups will be collapsed by default until the user decided to expand the detail under the respective date on raw waste/promo page.
	@Test()
	public void sprint9_US1048_TC1727() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		//Verify that all the daily roll ups will be collapsed by default in raw waste/promo page
		if (promotionsAndWastePage.verifyRecordsAreCollapsed()) {
			Reporter.reportPassResult(
					browser,"sprint9_US1048_TC1727",
					"Roll ups list should be in collapsed mode by default on  Promotions & Waste page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US1048_TC1727","sprint9_US1048_TC1727",
					"Roll ups list should be in collapsed mode by default on  Promotions & Waste page",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US1048_TC1727");
		}
	}
		

}
