package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;

public class FoodOverBasePage extends AbstractPage {

	public FoodOverBasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[text()='Food Over Base']")
	public WebElement FoodOverBase_Label;
	
	@FindBy(xpath = "//div[@class='container-other-months']/div/div/h4")
	public List<WebElement> PreviousMonths_Header_Label;
	
	@FindBy(xpath = "//div[@class='container-current-month']/div/div/h4")
	public WebElement CurrentMonth_Header_Label;
	
	@FindBy(xpath = "//td[contains(@id,'pct_display')]")
	public List<WebElement> CurrentMonth_TargetPercent_List;
	
	@FindBy(xpath = "//a[@title='click to post']//span[@class='glyphicon glyphicon-info-sign']")
	public WebElement CurrentMonth_TargetPercent_ColumnHeader_Image_LK;
	
	@FindBy(xpath = "//td[contains(@id,'dlr_display')]")
	public List<WebElement> CurrentMonth_TargetDoller_List;
	
	@FindBy(xpath = "//td[contains(@id,'dlr_display')]/following-sibling::td[1]")
	public List<WebElement> CurrentMonth_MonthToDateActualPercent_List;
	
	@FindBy(xpath = "//td[contains(@id,'dlr_display')]/following-sibling::td[2]")
	public List<WebElement> CurrentMonth_MonthToDateActualDoller_List;
	
	@FindBy(xpath = "//div[@class='container-current-month']/h4")
	public WebElement ProductNetSales_Label;
	
	@FindBy(xpath = "//input[@id='base_food_target_percent']")
	public WebElement PostTargetValuesPopUp_BaseFood_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Menu Item Waste']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_MenuItemWaste_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Raw Waste']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_RawWaste_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Condiment']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_Condiment_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Employee/Manager Food']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_EmployeeManagerFood_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Discount Coupon']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_DiscountCoupon_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Stat Variance']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_StatVariance_TB;
	
	@FindBy(xpath = "//tr[@class='form group']/td[text()='Unexplained Difference']/following-sibling::td/input")
	public WebElement PostTargetValuesPopUp_UnexplainedDifference_TB;
	
	@FindBy(xpath = "//input[@id='continue_transfer20']")
	public WebElement PostTargetValuesPopUp_Save_BT;
	
	@FindBy(xpath = "//div[@class='toast-message' and text()='Value must be numeric from 0 to 49.99']")
	public WebElement PostTargetValuesPopUp_WrongValue_Error_MSG;
	
	@FindBy(xpath = "//div[@class='toast-message' and text()='Changes Saved']")
	public WebElement PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG;
	
	@FindBy(xpath = "//span[@class='glyphicon glyphicon-ok']")
	public WebElement AuditTrail_Image_LK;
	
	@FindBy(xpath = "//b[contains(.,'Audit For Food Over Base for')]")
	public WebElement AuditForFoodOverBasePopUp_Title;
	
	@FindBy(xpath = "//td[@id='base_food_pct_display']")
	public WebElement CurrentMonth_BaseFood_TargetPercent_Value;
	
	@FindBy(xpath = "//th[text()='Difference %']")
	public WebElement DifferencePercentage_Column_Label;
	
	@FindBy(xpath = "//td[@id='completed_waste_pct_display']")
	public WebElement CurrentMonth_MenuItemWaste_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='raw_waste_pct_display']")
	public WebElement CurrentMonth_RawWaste_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='condiment_pct_display']")
	public WebElement CurrentMonth_Condiment_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='emp_mang_food_pct_display']")
	public WebElement CurrentMonth_EmployeeManagerFood_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='discount_coupon_pct_display']")
	public WebElement CurrentMonth_DiscountCoupon_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='stat_variance_pct_display']")
	public WebElement CurrentMonth_StatVariance_TargetPercent_Value;
	
	@FindBy(xpath = "//td[@id='unexplained_diff_pct_display']")
	public WebElement CurrentMonth_UnexplainedDifference_TargetPercent_Value;
	
	@FindBy(xpath = "//th[text()='Difference $']")
	public WebElement DifferenceDoller_Column_Label;
	
	@FindBy(xpath = "//table[contains(@id,'raw_item_info_table+undefined+')]/tbody/tr[9]/td")
	public WebElement PostCommentForCurrentMonth_BT;
	
	@FindBy(xpath = "//textarea[@id='text_save']")
	public WebElement CommentBox_TB;
	
	@FindBy(xpath = "//input[@id='btn_save_comment']")
	public WebElement SaveComment_BT;
	
	public boolean verifyControllableCompHeaderDisplayedForEachMonth(){
		List<WebElement>rawInfoTable = driver.findElements(By.xpath("//table[contains(@id,'raw_item_info_table')]"));
		boolean result = true;
		for(int i=1;i<rawInfoTable.size();i++){
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]//tr/th[contains(text(),'Controllable Comp')]")).isDisplayed();
		}
		return result;
	}
	
	public boolean verifyRawHeaderDisplayedForEachMonth(){
		List<WebElement>rawInfoTable = driver.findElements(By.xpath("//table[contains(@id,'raw_item_info_table')]"));
		boolean result = true;
		for(int i=1;i<rawInfoTable.size();i++){
			//Verify Base Food row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Base Food']")).isDisplayed();
			//Verify Menu Item Waste row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Menu Item Waste']")).isDisplayed();
			//Verify Raw Waste row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Raw Waste']")).isDisplayed();
			//Verify Condiment row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Condiment']")).isDisplayed();
			//Verify Employee/Manager Food row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Employee/Manager Food']")).isDisplayed();
			//Verify Discount Coupon row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Discount Coupon']")).isDisplayed();
			//Verify Stat Variance row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Stat Variance']")).isDisplayed();
			//Verify Unexplained Difference row header is displayed 
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/tbody/tr/td[text()='Unexplained Difference']")).isDisplayed();
		}
		return result;
	}
	
	public boolean verifyMonthAndYearIsDisplayedForEachHistoricalMonth(){
		int currentMonth = Base.getMonthFromDate(Base.returnTodayDate())+1;
		int currentYear = Base.getYearFromDate(Base.returnTodayDate());
		String monthName = Base.getMonthName(currentMonth);
		boolean result = true;
		//Verify month  name and year displayed for current month
		String monthYear = driver.findElement(By.xpath("//div[@class='container-current-month']/div/div/h4")).getText();
		result = result && monthYear.equals(monthName+" "+currentYear);
		List<WebElement>rawInfoTableHeader = driver.findElements(By.xpath("//div[@class='container-other-months']/div/div"));
		for(int i=1;i<=rawInfoTableHeader.size();i++){
			currentMonth--;
			monthName = Base.getMonthName(currentMonth);
			monthYear = driver.findElement(By.xpath("(//div[@class='container-other-months']/div/div)["+i+"]/h4")).getText();
			result = result && monthYear.equals(monthName+" "+currentYear);
		}
		return result;
	}
	
	public boolean verifyActualValuesDisplayedForEachHistoricalMonth(){
		List<WebElement>rawInfoTable = driver.findElements(By.xpath("//table[contains(@id,'raw_item_info_table')]"));
		boolean result = true;
		for(int i=2;i<rawInfoTable.size();i++){
			//Verify Actual % Column header is displayed for each historical month
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/thead/tr/th[text()='Actual %']")).isDisplayed();
			//Verify Actual $ Column header is displayed for each historical month
			result = result && driver.findElement(By.xpath("(//table[contains(@id,'raw_item_info_table')])["+i+"]/thead/tr/th[text()='Actual $']")).isDisplayed();
		}
		return result;
	}
	
	public boolean verifyColumnHeaderDisplayedForCurrentMonth(){
		boolean result = true;
		// Verify Controllable Comp. Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[text()='Controllable Comp.']")).isDisplayed();
		// Verify Targets % Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Targets %')]")).isDisplayed();
		// Verify Targets $ Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Targets $')]")).isDisplayed();
		// Verify Month to Date (Actual) % Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Month to Date (Actual) %')]")).isDisplayed();
		// Verify Month to Date (Actual) $ Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Month to Date (Actual) $')]")).isDisplayed();
		// Verify Difference % Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Difference %')]")).isDisplayed();
		// Verify Difference $ Column header is displayed for Current month
		result = result && driver.findElement(By.xpath("//table[contains(@id,'raw_item_info_table+undefined+')]/thead/tr/th[contains(text(),'Difference $')]")).isDisplayed();
		return result;
	}
	

	public void editTargetPercentValues(String validValue) throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(PostTargetValuesPopUp_BaseFood_TB));
		Thread.sleep(3000);
		PostTargetValuesPopUp_BaseFood_TB.clear();
		PostTargetValuesPopUp_BaseFood_TB.sendKeys(validValue);
		PostTargetValuesPopUp_MenuItemWaste_TB.clear();
		PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(validValue);
		PostTargetValuesPopUp_RawWaste_TB.clear();
		PostTargetValuesPopUp_RawWaste_TB.sendKeys(validValue);
		PostTargetValuesPopUp_Condiment_TB.clear();
		PostTargetValuesPopUp_Condiment_TB.sendKeys(validValue);
		PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
		PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(validValue);
		PostTargetValuesPopUp_DiscountCoupon_TB.clear();
		PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(validValue);
		PostTargetValuesPopUp_StatVariance_TB.clear();
		PostTargetValuesPopUp_StatVariance_TB.sendKeys(validValue);
		PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
		PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(validValue);
		Base.executeJavaScript("document.getElementById('continue_transfer20').click();");
		//PostTargetValuesPopUp_Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG));
	}
	
	public void postCommentForCurrentMonth(String comment) throws InterruptedException{
		System.out.println("comment "+comment);
		PostCommentForCurrentMonth_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(CommentBox_TB)).clear();
		Thread.sleep(1000);
		CommentBox_TB.sendKeys(comment);
		SaveComment_BT.click();
		wait.until(ExpectedConditions.visibilityOf(PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG));
		Thread.sleep(2000);
	}
}
