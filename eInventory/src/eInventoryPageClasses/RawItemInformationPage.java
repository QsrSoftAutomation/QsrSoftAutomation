package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RawItemInformationPage extends AbstractPage{

	public RawItemInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h3[text()='Raw Item Information']")
	public WebElement RawItemInformation_Label;
	
	@FindBy(xpath="//input[@id='autosearchInput']")
	public WebElement Search_TB;
	
	@FindBy(xpath="//input[@id='latest_case_price_editable']")
	public WebElement CasePrice_TB;
	
	@FindBy(xpath="//div[@class='toast-message'] and text()='Changes Saved'")
	public WebElement Confirmation_Message;
	
	@FindBy(xpath="//div[@id='toast-container']")
	public WebElement Error_Message;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'List Type')]")
	public WebElement RawItemInformation_Attribute_ListType_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'List Type')]/following-sibling::td/select")
	public WebElement RawItemInformation_Attribute_ListType_DD_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'WRIN')]")
	public WebElement RawItemInformation_Attribute_WRIN_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'WRIN')]/following-sibling::td")
	public WebElement RawItemInformation_Attribute_WRIN_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Long Des')]")
	public WebElement RawItemInformation_Attribute_LongDescription_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Long Des')]/following-sibling::td")
	public WebElement RawItemInformation_Attribute_LongDescription_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Product Cat')]")
	public WebElement RawItemInformation_Attribute_ProductCategory_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Product Cat')]/following-sibling::td")
	public WebElement RawItemInformation_Attribute_ProductCategory_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM']")
	public WebElement RawItemInformation_Attribute_UOM_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM']/following-sibling::td")
	public WebElement RawItemInformation_Attribute_UOM_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM/Case']")
	public WebElement RawItemInformation_Attribute_UOMCase_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM/Case']/following-sibling::td")
	public WebElement RawItemInformation_Attribute_UOMCase_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM/Sleeve']")
	public WebElement RawItemInformation_Attribute_UOMSleeve_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[text()='UOM/Sleeve']/following-sibling::td")
	public WebElement RawItemInformation_Attribute_UOMSleeve_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Inner Pack D')]")
	public WebElement RawItemInformation_Attribute_InnerPackDescription_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Inner Pack D')]/following-sibling::td")
	public WebElement RawItemInformation_Attribute_InnerPackDescription_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Temper')]")
	public WebElement RawItemInformation_Attribute_TemperatureZone_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Temper')]/following-sibling::td")
	public WebElement RawItemInformation_Attribute_TemperatureZone_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'McD')]")
	public WebElement RawItemInformation_Attribute_McDonaldsGLAccount_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'McD')]/following-sibling::td/select")
	public WebElement RawItemInformation_Attribute_McDonaldsGLAccount_DD_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Case Price')]")
	public WebElement RawItemInformation_Attribute_CasePrice_Label;
	
	@FindBy(xpath="//span[@id='latest_case_price']")
	public WebElement RawItemInformation_Attribute_CasePrice_Value;
	
	@FindBy(xpath="//input[@id='latest_case_price_editable']")
	public WebElement RawItemInformation_Attribute_CasePrice_TB;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Primary')]")
	public WebElement RawItemInformation_Attribute_PrimaryVendor_Label;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']/tbody/tr/td[contains(text(),'Primary')]/following-sibling::td/select")
	public WebElement RawItemInformation_Attribute_PrimaryVendor_DD_Value;
	
	@FindBy(xpath="//input[@id='save_button']")
	public WebElement RawItemInformation_Save_BT;
	
	@FindBy(xpath="//input[@id='done_button']")
	public WebElement RawItemInformation_Close_BT;
	
	@FindBy(xpath="//input[@id='man_purch_ckbox']")
	public WebElement ManualPurchase_CB;
	
	@FindBy(xpath="//select[@id='list_type']")
	public WebElement ListType_DD;
	
	@FindBy(xpath="//select[@id='gl_account']")
	public WebElement McDonaldsGLAccount_DD;
	
	@FindBy(xpath="//select[@class='form-control ebos-input editable_data' and @ddm-data='raw_item.primary_vdr']")
	public WebElement PrimaryVendor_DD;
	
	@FindBy(xpath="//select[@class='form-control ebos-input editable_data' and @ddm-data='raw_item.primary_vdr']/option")
	public List <WebElement> PrimaryVendor_VendorName_List;
	
	@FindBy(xpath="//input[@id='save_button']")
	public WebElement Save_BT;
	
	@FindBy(xpath="//div[@id='raw_item_info']/h4")
	public WebElement RawItemInfo_Title_Label;
	
	@FindBy(xpath="//td[text()='Target Yield Range']/following-sibling::td/span")
	public WebElement TargetYieldRange_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']//tr/td[text()='Calculated Yield']/following-sibling::td[1]")
	public WebElement CalculatedYield_Value;
	
	@FindBy(xpath="//table[@id='raw_item_info_table']//tr/td[text()='Calculated Yield']/following-sibling::td[2]/span")
	public WebElement CalculatedYieldAsOf_Label;
	
	// This method will take WRIN Id as argument and search the the WRIN Id in Raw Item Information Page
	public RawItemInformationPage searchAndSelectWRINID(String samplewRINID)
			throws InterruptedException {
		Search_TB.sendKeys(samplewRINID);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size = driver.findElements(By.xpath("//strong[text()='" + samplewRINID + "']")).size();
		driver.findElement(By.xpath("(//strong[text()='" + samplewRINID + "'])[" + size+ "]")).click();
		return PageFactory.initElements(driver, RawItemInformationPage.class);
	}

	//This method will assign a raw item to a vendor
	public RawItemInformationPage associateRawItemToVendor(String wrinId,String vendorName) throws InterruptedException {
		searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(ManualPurchase_CB));
		Thread.sleep(1500);
		try {
			if (ManualPurchase_CB.getAttribute("checked").equals("true")) {
				Select primaryVendor = new Select(PrimaryVendor_DD);
				primaryVendor.selectByVisibleText(vendorName);
			}
		} catch (NullPointerException ex) {
			ManualPurchase_CB.click();
			CasePrice_TB.clear();
			CasePrice_TB.sendKeys("10");
			Select mcdGlAcount = new Select(McDonaldsGLAccount_DD);
			mcdGlAcount.selectByIndex(2);
			Select primaryVendor = new Select(PrimaryVendor_DD);
			primaryVendor.selectByVisibleText(vendorName);
		}
		RawItemInformation_Save_BT.click();
		Thread.sleep(3000);
		return PageFactory.initElements(driver, RawItemInformationPage.class);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
