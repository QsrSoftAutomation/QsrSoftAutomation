package sprint6;

import java.io.IOException;
import java.math.BigDecimal;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;
import sprint2.AbstractTest;

public class US461_ShowProductCategoryDollerAmountsOnAnElectronicInvoiceAndOnManualPurchaseDetailPage extends AbstractTest
{
	
	// Verify that subTotal field is shown for existing posted electronic and manual invoices
	//Invoice Number should be unique
	//WRIN ID food and WRIN Id paper should be associated with the Vendor
	@Test()
	public void Sprint6_US461_TC1271() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/* Start-Fetch the data From Test Data Sheet*/
		HSSFSheet manuaInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint6_US461_TC1271", "Object1");
		String vendor = ReadTestData.getTestData(manuaInvoiceNewPageSheet, "Vendor");
		String userId = GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		/*End*/
		String invoiceNumber=Base.randomNumberFiveDigit();
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage =PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		PurchasesPage purchasesPage =PageFactory.initElements(driver, PurchasesPage.class);
		//Go to purchase landing page and click on Enter Manual purchase button
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().EnterManualPurchase_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		//Select a vendor and search and select the food product category WRIN id and enter the quantity
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor).InvoiceNumber_TB.sendKeys(invoiceNumber);
		Thread.sleep(3000);
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_Food).enterQuantityInQuantityTextBox(GlobalVariable.wrinID_Food,"2");
		//search and select the paper category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_Paper);
		Thread.sleep(3000);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_Paper,"1");
		manualInvoiceNewPage.Search_TB_02.click();
		manualInvoiceNewPage.Search_TB_02.clear();
		manualInvoiceNewPage.Search_TB_02.sendKeys(Keys.BACK_SPACE);
		manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Quantity_TB_List.get(1).click();
		/*//click on somewhere to reflect the sub total value
		manualInvoiceNewPage.Quantity_TB_List.get(1).click();*/
		Thread.sleep(2000);
		//fetch the sub total value of product category Paper type WRIN ID
		String paperSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		System.out.println(" Paper  sub total"+paperSubTotal);
		//fetch the sub total value of product category Food type WRIN ID
		String foodSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(1).getText();
		System.out.println("Food sub total"+foodSubTotal);
		//Post the invoice
		manualInvoiceNewPage.Finalize_BT.click();
		manualInvoiceNewPage.Finalize_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.FinalizePopUp_Continue_BT));
		manualInvoiceNewPage.FinalizePopUp_Continue_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceFinalize_Confirmation_MSG));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='continue_finalize']")));
		Thread.sleep(7000);
		// click on Get Purchase History button
		purchasesPage.GetPurchaseHistory_BT.click();
		//wait for purchase history table to be uploaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='posted_purchases_selection_table']")));
		//fetch the todays date
		String todayDate=Base.returnTodayDate();
		//Go to the last record and click on it
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr/td[1][descendant::span[text()='"+todayDate+"'] and following-sibling::td/span[text()='"+invoiceNumber+"']]/span")).click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.PostedPurchaseDetailPopUp_PostedPurchaseDetail_Label));
		Thread.sleep(5000);
		String totalFood=purchasesPage.PostedPurchaseDetailPopUp_TotalFood_Value.getText().trim().replace("$", "");
		String totalPaper=purchasesPage.PostedPurchaseDetailPopUp_TotalPaper_Value.getText().trim().replace("$", "");
		System.out.println("Total Food"+totalFood);
		System.out.println("Total paper"+totalPaper);
		if(totalFood.equalsIgnoreCase(foodSubTotal) && totalPaper.equalsIgnoreCase(paperSubTotal))
		{
			Reporter.reportPassResult(browser, "Sprint6_US461_TC1271", "Correst value of total food and total sub total should display", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US461_TC1271", "Sprint6_US461_TC1271", "Correst value of total food and total sub total should display", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US461_TC1271");
		}
		
		
		
	}

	//Verify the subtotal in existing Manual Purchase is calculated in dollar for each product category
		@Test()

		public void Sprint6_US461_TC1269() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
		{
			/* Start-Fetch the data From Test Data Sheet*/
			HSSFSheet manuaInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint6_US461_TC1269", "Object1");
			String wrinId = ReadTestData.getTestData(manuaInvoiceNewPageSheet, "EnterQuickSearchWithSuggestionsForManualPurchases");
			String vendor = ReadTestData.getTestData(manuaInvoiceNewPageSheet, "Vendor");
			HSSFSheet manuaInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint6_US461_TC1269", "Object2");
			String wrinId1 = ReadTestData.getTestData(manuaInvoiceNewPageSheet1, "EnterQuickSearchWithSuggestionsForManualPurchases");
			String userId = GlobalVariable.userId;
			String storeId=GlobalVariable.StoreId;
			/*End*/
			//create instances of home page
			HomePage homePage=PageFactory.initElements(driver, HomePage.class);
			ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
			//Navigate to Manual Purchases page
			PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
			//click on Enter Manual Purchase button
			purchaselandingpage.EnterManualPurchase_BT.click();
			//select manual vendor from vendor DDList
			Select sel=new Select(manualInvoiceNewPage.VendorList_DD);
			sel.selectByVisibleText(vendor);
			//Search the sample WRIN ID1: on "Enter Quick Search With Suggestions for Manual Purchases:" and select it
			manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(wrinId);
			action.sendKeys(Keys.SPACE).build().perform();
			Thread.sleep(1500);
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auto complete-suggestion auto complete-selected' and @data-index='0']]")));
			//driver.findElement(By.xpath("//div[@class='auto complete-suggestion auto complete-selected' and @data-index='0']]")).click();
			//Enter Num1 in the Quantity field of samplewRINID1 
			manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys("5");
			manualInvoiceNewPage.Search_TB_02.click();
			//validate the subtotal for wRIN id1
			String CasePriceVal=manualInvoiceNewPage.DollerCase_TB_List.get(0).getAttribute("value");
			BigDecimal CasePriceValue = new BigDecimal(CasePriceVal); 
			//float CasePriceValue= Float.parseFloat(CasePriceVal);
			String QuantityVal=manualInvoiceNewPage.Quantity_TB_List.get(0).getAttribute("value");
			BigDecimal QuantityValue = new BigDecimal(QuantityVal);
			//float QuantityValue=Float.parseFloat(QuantityVal);
			String SubTotalVal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText().trim();
			BigDecimal SubTotalValue = new BigDecimal(SubTotalVal);
			//float SubTotalValue=Float.parseFloat(SubTotalVal);
			System.out.println(SubTotalValue+" SubTotalValue");
			BigDecimal calculatedValue = CasePriceValue.multiply(QuantityValue).setScale(2, BigDecimal.ROUND_DOWN);
			if(String.valueOf(calculatedValue).equals(String.valueOf(SubTotalValue)))
			{
				Reporter.reportPassResult(browser, "Sprint6_US461_TC1269", "SubTotal would be same as $/case*quantity for wRIN id1", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint6_US461_TC1269_condition_1", "Sprint6_US405_TC1269", "SubTotal would be same as $/case*quantity for wRIN id1", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US461_TC1269_condition_1");

			}
			//Search the sample WRIN ID2: on "Enter Quick Search With Suggestions for Manual Purchases:" and select it
			manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.click();
			manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(wrinId1);
			action.sendKeys(Keys.SPACE).build().perform();
			Thread.sleep(1500);
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElement(By.xpath("//strong[text()="+wrinId1+"]")).click();
			Thread.sleep(2000);
			//Enter Num2 in the Quantity field of samplewRINID2 
			manualInvoiceNewPage.Search_TB_02.sendKeys(wrinId1);
			manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys("10");
			manualInvoiceNewPage.Search_TB_02.click();
			//validate the sub total for wRIN id2 
			String CasePriceVal2=manualInvoiceNewPage.DollerCase_TB_List.get(0).getAttribute("value");
			//float CasePriceValue2= Float.parseFloat(CasePriceVal2);
			BigDecimal CasePriceValue2 = new BigDecimal(CasePriceVal2); 
			String QuantityVal2=manualInvoiceNewPage.Quantity_TB_List.get(0).getAttribute("value");
			//float QuantityValue2=Float.parseFloat(QuantityVal2);
			BigDecimal QuantityValue2 = new BigDecimal(QuantityVal2); 
			String SubTotalVal2=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText().trim();
			//float SubTotalValue2=Float.parseFloat(SubTotalVal2);
			BigDecimal SubTotalValue2 = new BigDecimal(SubTotalVal2); 
			System.out.println(SubTotalValue2+" SubTotalValue");
			BigDecimal calculatedValue2 = CasePriceValue2.multiply(QuantityValue2).setScale(2, BigDecimal.ROUND_DOWN);
			System.out.println(calculatedValue2+" Multiply");
			if(String.valueOf(calculatedValue2).equals(String.valueOf(SubTotalValue2)))
			{
				Reporter.reportPassResult(browser, "Sprint6_US461_TC1269", "SubTotal would be same as $/case*quantity for wRIN id2", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint6_US461_TC1269_condition_2", "Sprint6_US461_TC1269", "SubTotal would be same as $/case*quantity for wRIN id2", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US461_TC1269_condition_2");

			}	
			manualInvoiceNewPage.Search_TB_02.clear();
			manualInvoiceNewPage.Search_TB_02.sendKeys(Keys.BACK_SPACE);
			manualInvoiceNewPage.Quantity_TB_List.get(0).click();
			manualInvoiceNewPage.Quantity_TB_List.get(1).click();
			manualInvoiceNewPage.Quantity_TB_List.get(0).click();
			//validate the total purchase sub total
			String SubTotalV1=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText().trim();
			float SubTotalValue1=Float.parseFloat(SubTotalV1);
			String SubTotalV2=manualInvoiceNewPage.Subtotal_Value_List.get(1).getText().trim();
			float SubTotalValue3=Float.parseFloat(SubTotalV2);
			String TotalVal=driver.findElement(By.xpath("//div[@id='total_val']")).getText();
			//TotalVal.replaceFirst("$","");
			float TotalValue=Float.parseFloat(TotalVal.replace('$',' '));
			if(SubTotalValue1+SubTotalValue3==TotalValue)
			{
				Reporter.reportPassResult(browser, "Sprint6_US461_TC1269", "SubTotal1+SubTotal2 should result in Total SubTotal value", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint6_US461_TC1269_condition_3", "Sprint6_US461_TC1269", "SubTotal1+SubTotal2 should result in Total SubTotal value", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US461_TC1269_condition_3");

			}	



		}
	
	
}
