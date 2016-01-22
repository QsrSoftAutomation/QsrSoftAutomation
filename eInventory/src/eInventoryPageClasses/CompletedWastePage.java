package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CompletedWastePage extends AbstractPage {

	public CompletedWastePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath ="//input[@id='insert_new_comp_waste_date']")
	public WebElement SelectDate_TB;
	
	@FindBy(xpath ="//button[@id='insert_new_comp_waste_date_btn']")
	public WebElement SelectDate_BT;
	
	@FindBy(xpath ="//h2[@id='modal-title' and text()='Completed Waste']")
	public WebElement CompletedWaste_Title;
	
	@FindBy(xpath ="//input[@id='comp_waste_autocomplete']")
	public WebElement CompletedWastePopUp_SearchBox_TB ;
	
	@FindBy(xpath ="//input[@id='validatedInput' and @colname='qty_wasted']")
	public WebElement CompletedWastePopUp_QuantityWasted_TB ;
	
	@FindBy(xpath ="(//button[@value='Add Item'])[3]")
	public WebElement AddItem_BT;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Submit'])[3]")
	public WebElement Submit_BT;
	
	@FindBy(xpath ="(//button[@id='htmlButton' and @value='Cancel'])[3]")
	public WebElement Cancel_BT;
	
	@FindBy(xpath ="//button/span[text()='Yes']")
	public WebElement CompletedWasteEntryIncomplete_PopUp_YES_BT;
	
	@FindBy(xpath ="//button/span[text()='No']")
	public WebElement CompletedWasteEntryIncomplete_PopUp_NO_BT;
	
	@FindBy(xpath = "(//button[@id='autosearchAddInputBtn'])[3]")
	public WebElement AddWrinFromSearchBox_BT;
	
	
	public void searchMenuItemForCompletedWaste(String menuItemId) throws InterruptedException{
		CompletedWastePopUp_SearchBox_TB.sendKeys(menuItemId);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size = driver.findElements(By.xpath("//strong[text()='" + menuItemId + "']")).size();
		driver.findElement(By.xpath("(//strong[text()='" + menuItemId + "'])[" + size+ "]")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(AddWrinFromSearchBox_BT));
		AddWrinFromSearchBox_BT.click();
	}
	
	//not completed yet functionality not working
	public void addAMenuItemOnCompletedWastePage(String menuItemId,	String quantity) throws InterruptedException {
		searchMenuItemForCompletedWaste(menuItemId);
		Thread.sleep(2000);
		// Enter quantity in quantity text box
		CompletedWastePopUp_QuantityWasted_TB.sendKeys(quantity);
		CompletedWaste_Title.click();
		//wait.until(ExpectedConditions.visibilityOf(AddItem_BT));
		//AddItem_BT.click();
	}
	
	public String getTotalWasteAmunt(){
		List<WebElement> wasteItemSubtotalList = driver.findElements(By.xpath("//table[@id='comp_waste_entry_table']/tbody/tr/td[4]"));
		float subtotal = 0;
		for(WebElement wasteItem : wasteItemSubtotalList){
			String wasteItemsubtotal = wasteItem.getText();//.substring(1);
			float itemSubtotal = Float.parseFloat(wasteItemsubtotal);
			subtotal = subtotal+ itemSubtotal;
		}
		return Float.toString(subtotal);
	}
	

}
