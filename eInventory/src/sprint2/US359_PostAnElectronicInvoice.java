package sprint2;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.GlobalVariable;

import eInventoryPageClasses.HomePage;

public class US359_PostAnElectronicInvoice extends AbstractTest {

	// TC593 Verify that User is able to access the purchase Landing Page in e*Inventory application

	@Test()
	public void Sprint2_US359_TC539() throws InterruptedException {
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to purchase landing page and verify that purchase landing page is
		// loaded successfully.
		boolean result = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().isPurchaseLandingPageLoaded();
		Assert.assertEquals(result, true,"purchage landing page is loaded successfully");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
