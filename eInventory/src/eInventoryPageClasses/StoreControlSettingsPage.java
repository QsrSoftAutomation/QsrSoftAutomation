package eInventoryPageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreControlSettingsPage extends AbstractPage {

	public StoreControlSettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@value='Inventory Settings']")
	public WebElement InventorySetting_BT;
	
	@FindBy(xpath = "//input[@id='dollar_variance']")
	public WebElement TotalInvoiceAmountVariance_TB;
	
	@FindBy(xpath = "//input[@id='case_variance']")
	public WebElement CasesVarience_TB;
	
	@FindBy(xpath = "//input[@id='submit_variance']")
	public WebElement SaveVariance_BT;
	
	@FindBy(xpath ="//div[@class='toast-message' and text()='Your Settings have been saved.']")
	public WebElement VarianceSettingsSaved_Confirmation_MSG;
	
	@FindBy(xpath = "//h4[text()='Transfer Settings']")
	public WebElement TransferSettings_Label;
	
	@FindBy(xpath = "//b[text()='Transfer Limits']")
	public WebElement TransferLimits_Label;
	
	@FindBy(xpath = "//input[@id='transfer_limit_checkbox']")
	public WebElement TransferLimits_CB;
	
	@FindBy(xpath = "//label[@class='toggle_transfer_label']")
	public WebElement TransferAndAmountLimit_Label;
	
	@FindBy(xpath = "//input[@id='transfer_limit_textbox']")
	public WebElement TransferAndAmountLimit_TB;
	
	@FindBy(xpath ="//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")
	public WebElement InvalidTransferAmount_Error_MSG;
	
	@FindBy(xpath = "//input[@id='submit_transfer_settings']")
	public WebElement TransferSetting_Save_BT;
	
	@FindBy(xpath ="//div[@class='toast-message' and text()='Transfer Settings Posted']")
	public WebElement TransferSettingPosted_MSG;
	
	@FindBy(xpath ="//div[@id='save_modal_body']/p[text()='Changes will take affect at the next start of business day']")
	public WebElement TransferSettingPosted_Alert_MSG;
	
	@FindBy(xpath = "//button[text()='Continue']")
	public WebElement TransferSettingPostedPopUp_Continue_BT;
	
	@FindBy(xpath = "//input[@id='transfer_type_radio_ger']")
	public WebElement TransferGroupType_GER_RB;
	
	@FindBy(xpath = "//input[@id='transfer_type_radio_coop']")
	public WebElement TransferGroupType_COOP_RB;
	
}
