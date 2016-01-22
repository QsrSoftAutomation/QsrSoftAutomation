package sprint7;

import java.io.IOException;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.StoreLedgerDetailPage;

public class US759_StoreLedgerPart3 extends AbstractTest {

	@Test()
	public void Sprint7_US759_TC1549() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint7_US759_TC1549", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Select the store 762 and navigate to Purchase landing page
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//Create a new manual purchase
		ManualInvoiceNewPage manualInvoiceNewPage = purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId,vendor,quantity, invoiceId);
		//Post the newly created manual purchase
		manualInvoiceNewPage.clickOnFinalizeButton().postTheManualPurchage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PendingPurchases_Title));
		//Navigate to Store Ledger Detail Page
		StoreLedgerDetailPage storeLedgerDetailPage = purchasePage.clickOnViewStoreLedgerButton();
		// get current date, month and year from the system date
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int Month = (now.get(Calendar.MONTH) + 1);
		int day = now.get(Calendar.DAY_OF_MONTH);
		//Select current month/year from the selectMonthFromStoreLedger DropDown
		storeLedgerDetailPage.selectMonthFromStoreLedgerDrpDwn(Month, year);
		// verify that purchase is posted in store Ledger Page records
		boolean dataPostedInStoreLedgerPage = storeLedgerDetailPage
				.verifyDataIsPostedInStoreLedgerPage(Month, year, day,invoiceId);
		if (dataPostedInStoreLedgerPage) {
			Reporter.reportPassResult(
					browser,"Sprint7_US759_TC1549",
					"Posted Data for today should be displayed in Store Ledger Data Table",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US759_TC1549","Sprint7_US759_TC1549",
					"Posted Data for today should be displayed in Store Ledger Data Table",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US759_TC1549_condition_1");
		}
	}
}
