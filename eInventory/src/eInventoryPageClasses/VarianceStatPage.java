package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import common.Base;

public class VarianceStatPage  extends AbstractPage{

	public VarianceStatPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h3[text()='Daily Inventory Stat']")
	public WebElement DailyInventory_Label;
	
	@FindBy(xpath = "//button[@id='start_date_btn']")
	public WebElement StartDate_BT;
	
	@FindBy(xpath = "(//div[@class='xdsoft_label xdsoft_month'])[1]/div[contains(@class,'xdsoft_monthselect')]")
	public WebElement MonthPicker_DD;
	
	@FindBy(xpath = "//select[@id='print_list']")
	public WebElement ListType_DD;
	
	@FindBy(xpath = "//select[@id='landing_date_value']")
	public WebElement VarianceStatType_DD;
	
	@FindBy(xpath = "//select[@id='landing_month_value']")
	public WebElement SelectMonth_DD;
	
	@FindBy(xpath = "//th[text()='Wrin']")
	public WebElement VarianceStatTable_WrinHeader_Label;
	
	@FindBy(xpath = "//th[text()='Desc']")
	public WebElement VarianceStatTable_DescHeader_Label;
	
	@FindBy(xpath = "//th[text()='Inv Time']")
	public WebElement VarianceStatTable_InvTimeHeader_Label;
	
	@FindBy(xpath = "//th/h4[text()='Cal Usage Total: ']")
	public WebElement VarianceStatTable_CalUsageHeader_Label;
	
	@FindBy(xpath = "//th/h4[text()='Actual Usage Total: ']")
	public WebElement VarianceStatTable_ActualUsageHeader_Label;
	
	@FindBy(xpath = "//th/h4[text()='Used Difference Total: ']")
	public WebElement VarianceStatTable_UsedDifferenceHeader_Label;
	
	@FindBy(xpath = "//th[text()='Calc yields']")
	public WebElement VarianceStatTable_CalcYieldsHeader_Label;
	
	@FindBy(xpath = "//th/h4[text()='$Difference Total: ']")
	public WebElement VarianceStatTable_DifferenceTotalHeader_Label;
	
	@FindBy(xpath = "//tbody[@id='daily_inv_table_body']/tr/td[@class='dataTables_empty']")
	public WebElement VarianceStatTable_EmptyTable_Label;
	
	@FindBy(xpath = "//tbody[@id='daily_inv_table_body']/tr")
	public List<WebElement> VarianceStatTable_Data_List;
	
	@FindBy(xpath = "//h5[text()='Dollar Gain: ']")
	public WebElement DollerGain_Label;
	
	@FindBy(xpath = "//h5[text()='Dollar Gain: ']/span")
	public WebElement DollerGain_Value;
	
	@FindBy(xpath = "//h5[text()='Dollar Loss: ']")
	public WebElement DollerLoss_Label;
	
	@FindBy(xpath = "//h5[text()='Dollar Loss: ']/span")
	public WebElement DollerLoss_Value;
	
	@FindBy(xpath = "//h5[text()='Dollar Variance: ']")
	public WebElement DollerVariance_Label;
	
	@FindBy(xpath = "//h5[text()='Dollar Variance: ']/span")
	public WebElement DollerVariance_Value;
	
	@FindBy(xpath = "//input[@id='done_button']")
	public WebElement DoneWithThisItem_BT;
	
	public void selectMonth(String date){
		Select monthDropdown = new Select(SelectMonth_DD);
		monthDropdown.selectByValue(date);
	}
	
	public void selectDateFromCalender(String date) throws InterruptedException{
		Thread.sleep(1000);
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])//tbody/tr//td[@data-month='"+month+"'and @data-date='"+day+"']")).click();
	}
	
	public VarianceStatPage selectVarianceStatType(String varianceType) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(VarianceStatType_DD));
		Thread.sleep(2000);
		Select varianceStatTypeDD = new Select(VarianceStatType_DD);
		varianceStatTypeDD.selectByVisibleText(varianceType);
		return PageFactory.initElements(driver, VarianceStatPage.class);
	}
	
	public boolean verifyVarianceStatLoaded() {
		System.out.println("VarianceStatTableHeaderDisplayed "+ VarianceStatTableHeaderDisplayed());
		System.out.println("VarianceStatTable_Data_List.size() "+ VarianceStatTable_Data_List.size());
		return VarianceStatTableHeaderDisplayed()
				& (Base.isElementDisplayed(VarianceStatTable_EmptyTable_Label) || VarianceStatTable_Data_List.size() > 0);
	}
	
	public boolean VarianceStatTableHeaderDisplayed() {
		return Base.isElementDisplayed(VarianceStatTable_WrinHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_DescHeader_Label)
				//& Base.isElementDisplayed(VarianceStatTable_InvTimeHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_CalUsageHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_ActualUsageHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_UsedDifferenceHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_CalcYieldsHeader_Label)
				& Base.isElementDisplayed(VarianceStatTable_DifferenceTotalHeader_Label);
	}

}
