package sprint6;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import eInventoryPageClasses.ManualInvoiceEditPage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;
import sprint2.AbstractTest;

public class US477_Part2ManualPurchase extends AbstractTest
{
	
	// Verify each product category sub total is calculated in real-time on the Manual Purchase detail page
	@Test()
	public void Sprint6_US477_TC1214() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/* Start-Fetch the data From Test Data Sheet*/
		HSSFSheet manuaInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint6_US477_TC1214", "Object1");
		String vendor = ReadTestData.getTestData(manuaInvoiceNewPageSheet, "Vendor");
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/*End*/	
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage =PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//Go to purchase landing page and click on Enter Manual purchase button
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().EnterManualPurchase_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		//Select a vendor and search and select the food product category WRIN id and enter the quantity
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor);
		Thread.sleep(3000);
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_Food).enterQuantityInQuantityTextBox(GlobalVariable.wrinID_Food,"2");
		//fetch the sub total value of product category Food type WRIN ID
		manualInvoiceNewPage.Search_TB_02.click();
		String FoodSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		//search and select the paper category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_Paper);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_Paper,"1");
		//fetch the sub total value of product category paper type WRIN ID
		manualInvoiceNewPage.Search_TB_02.click();
		String PaperSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		//search and select the OPPS Supplier category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_opsSupplier);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_opsSupplier,"2");
		//fetch the sub total value of product category Oops Supplier type WRIN ID
		manualInvoiceNewPage.Search_TB_02.click();
		String opsSupplierSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		//search and select the Linens category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_Linens);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_Linens,"3");
		//fetch the sub total value of product category Linens type WRIN ID
		manualInvoiceNewPage.Search_TB_02.click();
		String LinensSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		//search and select the Non Product Happy meal premiums category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_NonProductHappyMealPremiums);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_NonProductHappyMealPremiums,"4");
		manualInvoiceNewPage.Search_TB_02.click();
		String NonProductHappyMealPremiumsSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		//search and select the non product others category WRIN id
		manualInvoiceNewPage.searchAndSelectARawItem(GlobalVariable.wrinID_NonProductother);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(GlobalVariable.wrinID_NonProductother,"5");
		//fetch the sub total value of non product others category  type WRIN ID
		manualInvoiceNewPage.Search_TB_02.click();
		String NonProductotherSubTotal=manualInvoiceNewPage.Subtotal_Value_List.get(0).getText();
		manualInvoiceNewPage.Search_TB_02.clear();
		/*manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Search_TB_02.click();
		manualInvoiceNewPage.Search_TB_02.clear();*/
		manualInvoiceNewPage.Search_TB_02.sendKeys(Keys.BACK_SPACE);
		//We are clicking on each element because value is not reflecting in total value
		manualInvoiceNewPage.Quantity_TB_List.get(0).click();
		manualInvoiceNewPage.Quantity_TB_List.get(1).click();
		manualInvoiceNewPage.Quantity_TB_List.get(2).click();
		manualInvoiceNewPage.Quantity_TB_List.get(3).click();
		manualInvoiceNewPage.Quantity_TB_List.get(4).click();
		manualInvoiceNewPage.Quantity_TB_List.get(5).click();
		Thread.sleep(5000);
		//save the invoice
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.Save_BT));
		manualInvoiceNewPage.Save_BT.click();
		manualInvoiceNewPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceSaved_Confirmation_MSG));
		String totalFood=manualInvoiceNewPage.TotalFood_Value.getText().replace("$","");
		String totalLines=manualInvoiceNewPage.TotalLines_Value.getText().replace("$","");
		String totalNonProductHappyMealPremiums=manualInvoiceNewPage.TotalNonProductHappyMealPremiums_Value.getText().replace("$","");
		String totalNonProductOther=manualInvoiceNewPage.TotalNonProductOther_Value.getText().replace("$","");
		String totalPaper=manualInvoiceNewPage.TotalPaper_Value.getText().replace("$","");
		String totalOpsSupplies=manualInvoiceNewPage.TotalOpsSupplies_Value.getText().replace("$","");
		if(totalFood.equalsIgnoreCase(FoodSubTotal) && totalLines.equalsIgnoreCase(LinensSubTotal) && totalNonProductHappyMealPremiums.equalsIgnoreCase(NonProductHappyMealPremiumsSubTotal)
				&& totalNonProductOther.equalsIgnoreCase(NonProductotherSubTotal) && totalPaper.equalsIgnoreCase(PaperSubTotal) && totalOpsSupplies.equalsIgnoreCase(opsSupplierSubTotal))
		{
			Reporter.reportPassResult(browser, "Sprint6_US477_TC1214", "Correst total value should  display for all product Category type", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US477_TC1214", "Sprint6_US477_TC1214", "Correst total value should  display for all product Category type", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US477_TC1214");
		}
	}
	
	
	// Verify the delivery date and invoice date from purchase history, for posted manual purchase.
	
	@Test()
	
	public void Sprint6_US477_TC1331() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceNumber=Base.randomNumberFiveDigit();
		String ExpinvoiceDate;
		String todayDate=Base.returnTodayDate();
		ExpinvoiceDate=todayDate;
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage =PageFactory.initElements(driver, PurchasesPage.class);
		//Go to Manual Invoice new page and select the vendor and also select the invoice date from the calendar
		ManualInvoiceNewPage manualInvoiceNewPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage().goToManualInvoiceNewPage().selectAVendorFromDropdown(GlobalVariable.vendorName).selectInvoiceDate(todayDate);
		//search and select the Linens category WRIN id
		manualInvoiceNewPage.createAManualPurchaseForWrinID(GlobalVariable.wrinID_Food, GlobalVariable.vendorName, "2", invoiceNumber);
		manualInvoiceNewPage.clickOnFinalizeButton();
		wait .until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.FinalizePopUp_Continue_BT));
		//click on continue button on pop up message
		manualInvoiceNewPage.FinalizePopUp_Continue_BT.click();
		wait .until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceFinalize_Confirmation_MSG));
		Thread.sleep(10000);
		// click on Get Purchase History button
		purchasesPage.GetPurchaseHistory_BT.click();
		//wait for purchase history table to be uploaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='posted_purchases_selection_table']")));
		Thread.sleep(5000);
		//click on the newly added records
		driver.findElement(By.xpath("//table[@id='posted_purchases_selection_table']/tbody/tr/td[1][descendant::span[text()='"+todayDate+"'] and following-sibling::td/span[text()='"+invoiceNumber+"']]/span")).click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.PostedPurchaseDetailPopUp_PostedPurchaseDetail_Label));
		String actInvoiceDate=purchasesPage.PostedPurchaseDetailPopUp_InvoiceDate_Value.getText().trim();
		String actDeliveryDate=purchasesPage.PostedPurchaseDetailPopUp_DeliveryDate_Value.getText().trim();
		String expDeliveryDate = Base.returnTodayDate();
		if(ExpinvoiceDate.equalsIgnoreCase(actInvoiceDate) && expDeliveryDate.equalsIgnoreCase(actDeliveryDate))
		{
			Reporter.reportPassResult(browser, "Sprint6_US477_TC1331", "Correst total value should  display for all product Category type", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US477_TC1331", "Sprint6_US477_TC1331", "Correst total value should  display for all product Category type", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US477_TC1331");
		}
	}
	

	//Verify Manual Vendors are selectable from manual purchase page

	@Test ()
	public void Sprint6_US477_TC1262() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		//create instances of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//Navigate to Manual Purchases page
		PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//click on Enter Manual Purchase button
		purchaselandingpage.EnterManualPurchase_BT.click();
		//select manual vendor 'xyz' from vendor DDList
		Select sel=new Select(manualInvoiceNewPage.VendorList_DD);
		sel.selectByVisibleText(GlobalVariable.vendorName);
		//validate manual vendor is selected from the DDL
		if(sel.getFirstSelectedOption().getText().equalsIgnoreCase(GlobalVariable.vendorName))
		{
			Reporter.reportPassResult(browser, "Sprint6_US477_TC1262", "Manual Vendor must be selected from the dropdownlist", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US477_TC1262", "Sprint6_US477_TC1262", "Manual Vendor must be selected from the dropdownlist", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US477_TC1262");
		}	
	}

	//Verify the "Warning message" while deleting a manual purchase from manual purchase detail page.

	@Test ()

	public void Sprint6_US477_TC1266() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		//create instances of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceEditPage manualInvoiceEditPage=PageFactory.initElements(driver, ManualInvoiceEditPage.class);
		//Navigate to Manual Purchases page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//select 1st Manual pending manual purchase
		Thread.sleep(1500);
		//find total no of rows
		int size=driver.findElements(By.xpath("//table[@id='purchases_selection_table']/tbody//tr")).size();
		for(int i=1;i<=size;i++)
		{
			driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/span")).getText();
			if(driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[3]/span")).getText().equalsIgnoreCase("Manual"))
			{
				action.moveToElement(driver.findElement(By.xpath("//table[@id='purchases_selection_table']/tbody/tr["+i+"]/td[1]/span"))).doubleClick().build().perform();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.Delete_BT));
		//click on 'delete' button on manual purchase detail screen
		manualInvoiceEditPage.Delete_BT.click();
		//click 'cancel' button on the warning message
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.DeletePopup_Cancel_BT));
		Thread.sleep(1500);
		manualInvoiceEditPage.DeletePopup_Cancel_BT.click();
		//Again click on 'delete' button on manual purchase detail screen
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.Delete_BT));
		manualInvoiceEditPage.Delete_BT.click();
		//click 'ok' button on the warning message
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.DeletePopup_OK_BT));
		Thread.sleep(1500);
		manualInvoiceEditPage.DeletePopup_OK_BT.click();
		//verify the 'Manual Purchase' is deleted
		int sizeafterdelete=driver.findElements(By.xpath("//table[@id='purchases_selection_table']/tbody//tr")).size();
		if(sizeafterdelete==size-1)
		{
			Reporter.reportPassResult(browser, "Sprint6_US477_TC1266", "Manual Purchase Item should be deleted", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US477_TC1266", "Sprint6_US477_TC1266", "Manual Purchase Item should be deleted", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US477_TC1266");

		}	
	}

	//Verify the user has the ability to select invoice date and time, when creating a new manual purchase
	@Test ()
	public void Sprint6_US477_TC1264() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException 
	{
		/*String Invoicetime="00:30";*/
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		//create instances of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
		//Navigate to Manual Purchases page
		PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//click on Enter Manual Purchase button
		purchaselandingpage.EnterManualPurchase_BT.click();
		//select manual vendor 'xyz' from vendor DDList
		Select sel=new Select(manualInvoiceNewPage.VendorList_DD);
		sel.selectByVisibleText(GlobalVariable.vendorName);
		//Select Invoice date,time from calendar widget
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		//split and find the current dateFormat
		String[] ddc=dateFormat.format(date).split("/");
		System.out.println("current date is"+ddc[1]);
		if(ddc[1].equals("01")||ddc[1].equals("02")||ddc[1].equals("03")||ddc[1].equals("04")||ddc[1].equals("05")||ddc[1].equals("06")||ddc[1].equals("07")||ddc[1].equals("08")||ddc[1].equals("09"))
		{
			ddc[1]=ddc[1].replaceFirst("0","");
		}
		//Date-1
		int currentdate=Integer.parseInt(ddc[1]);
		int previousdate=currentdate-01;
		String previousdatestr=Integer.toString(previousdate);
		//Month-1
		int currentmonth=Integer.parseInt(ddc[0]);
		int previousmonth=currentmonth-01;
		String previousmonthstr=Integer.toString(previousmonth);
		//click on current date-1 in the calendar widget
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month="+previousmonthstr+"]/div[text()="+previousdatestr+"]")).click();
	/*	while(!Invoicetime.equalsIgnoreCase(driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]")).getText()))
		{	
			driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
			if(driver.findElement(By.xpath("//div[text()='00:30']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[text()='00:30']")).click();
				break;
			}
		}*/
		//Validate correct date and time is selected
		System.out.println("dd"+manualInvoiceNewPage.InvoiceDate_TB.getAttribute("value"));
		if(manualInvoiceNewPage.InvoiceDate_TB.getAttribute("value").equalsIgnoreCase(ddc[0]+"/"+previousdatestr+"/"+ddc[2]))
		{
			Reporter.reportPassResult(browser, "Sprint6_US477_TC1262", "correct date should be selected", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US477_TC1262", "Sprint6_US477_TC1262", "correct date should be selected", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US477_TC1262");

		}	

	}
	
	//Verify the user has the ability to select a delivery date and time when the user  post manual purchase.
		@Test()
		public void Sprint6_US477_TC1312() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException 
		{
			String Invoicetime=Base.getCurrentTimeStamp();
			System.out.println("time1 is"+Invoicetime);
			String[]  array=Invoicetime.split(":");
			String newInvoiceTime=Base.get15MinuteTimeSlice(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
			int invoiceTimeMin =Integer.parseInt(newInvoiceTime.split(":")[1]);
			System.out.println("time is"+newInvoiceTime);
			String QuantityValue="1";
			String storeId=GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			//create instances of home page
			HomePage homePage=PageFactory.initElements(driver, HomePage.class);
			ManualInvoiceNewPage manualInvoiceNewPage=PageFactory.initElements(driver, ManualInvoiceNewPage.class);
			//Navigate to Manual Purchases page
			PurchasesPage purchaselandingpage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
			//click on Enter Manual Purchase button
			purchaselandingpage.EnterManualPurchase_BT.click();
			//select manual vendor 'xyz' from vendor DDList
			Select sel=new Select(manualInvoiceNewPage.VendorList_DD);
			sel.selectByVisibleText(GlobalVariable.vendorName);
			//Select Invoice date,time from calendar widget
			//find the current date of system
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			//split and find the current dateFormat
			String[] ddc=dateFormat.format(date).split("/");
			System.out.println("current date is"+ddc[1]);
			if(ddc[1].equals("01")||ddc[1].equals("02")||ddc[1].equals("03")||ddc[1].equals("04")||ddc[1].equals("05")||ddc[1].equals("06")||ddc[1].equals("07")||ddc[1].equals("08")||ddc[1].equals("09"))
			{
				ddc[1]=ddc[1].replaceFirst("0","");
			}
			//Date-1
			int currentdate=Integer.parseInt(ddc[1]);
			int previousdate=currentdate-01;
			String previousdatestr=Integer.toString(previousdate);
			//Month-1
			int currentmonth=Integer.parseInt(ddc[0]);
			int previousmonth=currentmonth-01;
			String previousmonthstr=Integer.toString(previousmonth);
			//click on current date-1 in the calendar widget
			driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month="+previousmonthstr+"]/div[text()="+previousdatestr+"]")).click();
			//Search the sample WRIN ID: on "Enter Quick Search With Suggestions for Manual Purchases:" and select it
			manualInvoiceNewPage.EnterQuickSearchWithSuggestionsForManualPurchases_TB.sendKeys(GlobalVariable.createPurchaseWrin);
			action.sendKeys(Keys.SPACE).build().perform();
			Thread.sleep(1500);
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()="+GlobalVariable.createPurchaseWrin+"]")));
			driver.findElement(By.xpath("//strong[text()="+GlobalVariable.createPurchaseWrin+"]")).click();
			//Enter 'case-price' & Quantity.
			manualInvoiceNewPage.Quantity_TB_List.get(0).sendKeys(QuantityValue);
			//click finalize button
			manualInvoiceNewPage.Finalize_BT.click();
			wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.FinalizePopUp_Continue_BT));
			//select delivery time
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='start_inv_div']//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
			driver.findElement(By.xpath("//div[@id='start_inv_div']//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
			driver.findElement(By.xpath("//div[@id='start_inv_div']//div[@class='xdsoft_timepicker active']//div[text()='"+newInvoiceTime+"']")).click();

			//Validate correct time is selected
			if(driver.findElement(By.xpath("//div[@class='xdsoft_time xdsoft_current' and @data-minute='"+invoiceTimeMin+"']")).getText().equalsIgnoreCase(newInvoiceTime))
			{
				Reporter.reportPassResult(browser, "Sprint6_US477_TC1312", "correct time is selected", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint6_US477_TC1312", "Sprint6_US477_TC1312", "correct time is selected", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US477_TC1312");

			}	
		}
	
		

}
