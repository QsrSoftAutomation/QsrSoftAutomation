package eInventoryPageClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import common.Base;
import common.Reporter;
import sprint2.AbstractTest;

public class StoreLedgerDetailPage extends AbstractPage {
	public StoreLedgerDetailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[@id='store_ledger_title']")
	public WebElement StoreLedgerForStoreNumber_Title;

	@FindBy(xpath = "//select[@class='form-control ebos-input']/option[1]")
	public WebElement month_DD_FirstElement;

	@FindBy(xpath = "//select[@class='form-control ebos-input']")
	public WebElement month_DD;

	@FindBy(xpath = "//label[contains(.,'Search')]")
	private WebElement Search_Lable;

	@FindBy(xpath = "//label[contains(.,'Search')]/input")
	private WebElement Search_TB;

	@FindBy(xpath = "//table[@id='ledger_table']/tbody/tr/td[1]")
	public List<WebElement> InvoiceId_List;

	//@Author :Hemlata
	@FindBy(xpath = "//table[@id='ledger_table']/tbody/tr[@class='even' or @class='odd']")
	public List<WebElement> storeledger_List;
	
	@FindBy(xpath = "//th[text()='Invoice ID']")
	public WebElement StoreLedgerTable_InvoiceId_Label;
	
	@FindBy(xpath = "//th[text()='Delivery Date']")
	public WebElement StoreLedgerTable_DeliveryDate_Label;
	
	@FindBy(xpath = "//th[text()='Invoice Type']")
	public WebElement StoreLedgerTable_InvoiceType_Label;
	
	@FindBy(xpath = "//th[text()='Transcation Amount']")
	public WebElement StoreLedgerTable_TransactionAmount_Label;
	
	@FindBy(xpath = "//th[text()='Food']")
	public WebElement StoreLedgerTable_Food_Label;
	
	@FindBy(xpath = "//th[text()='Paper']")
	public WebElement StoreLedgerTable_Paper_Label;
	
	@FindBy(xpath = "//th[text()='Ops Supplies']")
	public WebElement StoreLedgerTable_OpsSupplies_Label;
	
	@FindBy(xpath = "//th[text()='Linens']")
	public WebElement StoreLedgerTable_Linens_Label;
	
	@FindBy(xpath = "//th[text()='Non-product: Happy Meal']")
	public WebElement StoreLedgerTable_NonProductHappyMeal_Label;
	
	@FindBy(xpath = "//th[text()='Non-product: Other']")
	public WebElement StoreLedgerTable_NonProductOther_Label;
	
	@FindBy(xpath = "//th[text()='Tax 1']")
	public WebElement StoreLedgerTable_Tax1_Label;
	
	@FindBy(xpath = "//th[text()='Tax 2']")
	public WebElement StoreLedgerTable_Tax2_Label;
	
	@FindBy(xpath = "//th[text()='Tax 3']")
	public WebElement StoreLedgerTable_Tax3_Label;
	
	@FindBy(xpath = "//th[text()='State Tax']")
	public WebElement StoreLedgerTable_StateTax_Label;
	
	public void verifyStoreLedgerDetailPage(String browser, String tcName)
			throws RowsExceededException, BiffException, WriteException,IOException {
		wait.until(ExpectedConditions.visibilityOf(StoreLedgerForStoreNumber_Title));
		String month = null;
		boolean result;
		// Verify that all the columns are present on the page
		for (int i = 1; i <= 15; i++) {
			switch (i) {
			case 1:
				result = driver.getPageSource().contains("Invoice ID");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Invoice ID column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_InvoiceIDColumn",tcName,
							"Invoice ID column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_InvoiceIDColumn");
				}
				break;
			case 2:
				result = driver.getPageSource().contains("Delivery Date");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Delivery Date column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Delivery Date",tcName,
							"Delivery Date column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Delivery Date");
				}
				break;
			case 3:
				result = driver.getPageSource().contains("Invoice Type");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Invoice Type column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Invoice Type",tcName,
							"Invoice Type column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Invoice Type");
				}
				break;
			case 4:
				result = driver.getPageSource().contains("Food");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Food column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Food",tcName,
							"Food column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Food");
				}
				break;
			case 5:
				result = driver.getPageSource().contains("Paper");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Paper column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Paper",tcName,
							"Paper column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Paper");

				}
				break;
			case 6:
				result = driver.getPageSource().contains("Ops Supplies");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Ops Supplies column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Ops Supplies",tcName,
							"Ops Supplies column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Ops Supplies");
				}
				break;
			case 7:
				result = driver.getPageSource().contains("Linens");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Linens  column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Linens",tcName,
							"Linens  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Linens");
				}
				break;
			case 8:
				result = driver.getPageSource().contains(
						"Non-product: Happy Meal");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Non-product: Happy Meal  column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Non-product: Happy Meal",tcName,
							"Non-product: Happy Meal  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName
							+ "_Non-product: Happy Meal");
				}
				break;
			case 9:
				result = driver.getPageSource().contains("Non-product: Other");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Non-product: Other  column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Non-product: Other",tcName,
							"Non-product: Other  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Non-product: Other");
				}
				break;
			case 10:
				result = driver.getPageSource().contains("Tax 1");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Tax 1  column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Tax 1",tcName,
							"Tax 1  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Tax 1");
				}
				break;
			case 11:
				result = driver.getPageSource().contains("Tax 2");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Tax 2 column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Tax 2",tcName,
							"Tax 2  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Tax 2");
				}
				break;
			case 12:
				result = driver.getPageSource().contains("Tax 3");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"Tax 3  column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_Tax 3",tcName,
							"Tax 3  column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_Tax 3");
				}
				break;
			case 13:
				result = driver.getPageSource().contains("State Tax");
				if (result) {
					Reporter.reportPassResult(
							browser,tcName,
							"State Tax column should be Present on Invoice detail page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,tcName + "_State Tax",tcName,
							"State Tax column should be Present on Invoice detail page",
							"Fail");
					AbstractTest.takeSnapShot(tcName + "_State Tax");
				}
				break;
			}
		}
		// Fetch the current month and Year from the system
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String Year = Integer.toString(year);
		int Month = (now.get(Calendar.MONTH) + 1);
		// Change the number of month into the String
		month = Base.getMonthName(Month);
		// Convert the month and year into the expected format
		String month_dd_value = "" + month + " " + Year + "";
		// Verifying that "Store Ledger for Store Number" title is displaying
		if (StoreLedgerForStoreNumber_Title.getText().contains(
				"Store Ledger for Store Number")) {
			Reporter.reportPassResult(
					browser,tcName,
					"A title 'Store Ledger for store number' should display on the page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,tcName + "_Store Ledger for Store Number",tcName,
					"A title 'Store Ledger for store number' should display on the page",
					"Fail");
			AbstractTest.takeSnapShot(tcName + "_Store Ledger for Store Number");
		}
		// Verifying current month view is displaying
		if (month_DD_FirstElement.getText().trim()
				.equalsIgnoreCase(month_dd_value)) {
			Reporter.reportPassResult(
					browser,tcName,
					"Default current month view should display on store ledger detail page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,tcName + "_Current Month View",tcName,
					"Default current month view should display on store ledger detail page",
					"Pass");
			AbstractTest.takeSnapShot(tcName + "_Current Month View");
		}
	}

	/*
	 * @Author :Hemlata
	 * This method will verify the date sequence in Select Store Ledger BY Month
	 * DropDown which should be current Month as the first option preceding with the past months up to one year
	 */	
	public boolean VerifyDateSequenceInSelectStoreLedgerDropDown() {
		// Get current month and year from the system date
		ArrayList<String> dateSequenceUpToOneYear = getDateSequence();
		// Get list of month and year from Select Store Ledger BY Month Dropdown
		ArrayList<String> dateSequenceFromDropDown = getDateOptionsFromSelectStoreLedgerDropDown();
		boolean result = true;
		// Verify month and year should be in descending order from the current mm/yyyy up to one year back
		for (int i = 0; i < dateSequenceUpToOneYear.size(); i++) {
			result = result && dateSequenceUpToOneYear.get(i).equals(dateSequenceFromDropDown.get(i));
			}
		return result;

	}

	//@Author :Hemlata
	// This method will return one year mm/yyyy based on current month and year
	public ArrayList<String> getDateSequence() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String Year = Integer.toString(year);
		int Month = (now.get(Calendar.MONTH) + 1);
		ArrayList<String> dateSequenceUpToOneYear = new ArrayList<>();
		dateSequenceUpToOneYear.add(Base.getMonthName(Month) + " " + Year);
		int monthCount = 12;
		for (int i = 0; i < 11; i++) {
			int lastMonth = now.get(Calendar.MONTH) - i;
			int lastYear;
			int getMonth;
			if (lastMonth <= 0) {
				getMonth = monthCount + lastMonth;
				lastYear = now.get(Calendar.YEAR) - 1;
				dateSequenceUpToOneYear.add(Base.getMonthName(getMonth) + " "+ lastYear);

			} else {
				getMonth = lastMonth;
				lastYear = now.get(Calendar.YEAR);
				dateSequenceUpToOneYear.add(Base.getMonthName(getMonth) + " "+ lastYear);
			}

		}
		return dateSequenceUpToOneYear;

	}


	/*@Author :Hemlata
	This method will return the Select Store Ledger BY Month option list*/
	public ArrayList<String> getDateOptionsFromSelectStoreLedgerDropDown() {
		ArrayList<String> dateValues = new ArrayList<String>();
		Select DateDropDown = new Select(month_DD);
		for (WebElement month : DateDropDown.getOptions()) {
			dateValues.add(month.getText());
		}
		return dateValues;
	}

	/*@Author :Hemlata
	This method will take month and year as input and select that from the
	Select Store Ledger dropdown*/
	public void selectMonthFromStoreLedgerDrpDwn(int month, int year) {
		Select DateDropDown = new Select(month_DD);
		String monthValue = Integer.toString(month);
		String yearValue = Integer.toString(year);
		String date;
		if (monthValue.length() == 1) {
			date = yearValue + "-0" + monthValue + "-" + "01";
		} else {
			date = yearValue + "-" + monthValue + "-" + "01";

		}
		System.out.println("Date is"+date);
		DateDropDown.selectByValue(date);

	}

	/*@Author :Hemlata
	This method will verify that the data in store ledger table should be for selected mm/yyyy*/
	public boolean verifyDataForSelectedMonth(int month, int year) {
		boolean result = true;
		for (int i = 1; i < storeledger_List.size(); i++) {
			String date = driver
					.findElement(By.xpath("(//table[@id='ledger_table']/tbody/tr[@class='even' or @class='odd'])["+ i + "]/td[2]/span")).getText();
			System.out.println("date "+date);
			result = result && (Integer.parseInt(date.split("/")[0]) == month);
			result = result && (Integer.parseInt(date.split("/")[2]) == year);
		}
		return result;

	}

	/*@Author :Hemlata
	This method will verify that posted data for today date is displayed in store ledger detail page*/
	public boolean verifyDataIsPostedInStoreLedgerPage(int month, int year,
			int day, String invoiceId) {
		//return driver.findElement(By.xpath("//table[@id='ledger_table']//tr/td/span[text()='"+invoiceId+"']/../following-sibling::td[1]/span[text()='"+day+"']")).isDisplayed();
		for (int i = 1; i < storeledger_List.size(); i++) {
			String date = driver.findElement(By.xpath("(//table[@id='ledger_table']/tbody/tr[@class='even' or @class='odd'])["+ i + "]/td[2]/span")).getText();
			boolean result = (Integer.parseInt(date.split("/")[0]) == month);
			result = result && (Integer.parseInt(date.split("/")[1]) == day);
			result = result && (Integer.parseInt(date.split("/")[2]) == year);
			if (result) {
				String invoicIdInTable = driver.findElement(By.xpath("(//table[@id='ledger_table']/tbody/tr[@class='even' or @class='odd'])["+ i + "]/td[1]/span")).getText();
				if (invoicIdInTable.equals(invoiceId)) {
					return true;
				}
			}
		}
		return false;

	}
	
	/*This overloaded method will take date invoice id, total transaction amount and total food amount argument and verify
	that posted transaction is displayed in store ledger detail page*/
	public boolean verifyDataIsPostedInStoreLedgerPage(String date,String invoiceId, String totalValue, String totalFooddValue) 
	{
		return driver.findElement(
				By.xpath("//table[@id='ledger_table']//tr/td/span[text()='"+ invoiceId
						+ "']/../following-sibling::td[1]/span[text()='" + date
						+ "']/../following-sibling::td[2]/span[text()='"+ totalValue
						+ "']/../following-sibling::td[1]/span[text()='"+ totalFooddValue + "']")).isDisplayed();
	}
	
	//@Author :Hemlata
	public boolean verifyPosetedPurchaseDisplayedForNewVendor(String vendorName,String date,String invoiceID){
		return Base.isElementDisplayed(driver.findElement(By.xpath("//table[@id='ledger_table']/tbody/tr/td[1]/span[text()='"+vendorName+"']/../../following-sibling::tr[1]/td[1]/span[text()='"+invoiceID+"']/../following-sibling::td[1]/span[(text()='"+date+"')]")));
	}	
	
	//verify that transfer details are displayed in Store Ledger Page
	public boolean verifyTransferIsDisplayedInStoreLedgerPage(String nationalStoreNumber, String Date,String amount){
		return driver.findElement(By.xpath("//table[@id='ledger_table']//tr/td/span[text()='"+Date+"']/../following-sibling::td/span[text()='"+nationalStoreNumber+"']/../following-sibling::td/span[contains(text(),'"+amount+"')]")).isDisplayed();
	}
	
	
	
	
	
	
	
	
	
	

}
