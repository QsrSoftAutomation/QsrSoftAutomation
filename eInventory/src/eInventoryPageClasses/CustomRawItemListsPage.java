package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;

public class CustomRawItemListsPage extends AbstractPage
{

	public CustomRawItemListsPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h1[text()='Custom Raw Items Lists']")
	public WebElement CustomeRawItemLists_Title; 
	
	@FindBy(xpath="//input[@id='add_custom_raw_item_list']")
	public WebElement AddList_BT; 
	
	@FindBy(xpath="//input[@id='add_raw_item_list']")
	public WebElement CreateNewRawItemListPopup_Name_TB; 
	
	@FindBy(xpath="//input[@id='add_raw_item_list_button']")
	public WebElement CreateNewRawItemListPopup_Save_BT; 
	
	@FindBy(xpath="//input[@class='btn btn-default btn-sm cancel_raw_item_list']")
	public WebElement CreateNewRawItemListPopup_Cancel_BT; 
	
	@FindBy(xpath="//h3[@id='add_raw_item']")
	public WebElement CreateNewRawItemListPopup_Title; 
	
	@FindBy(xpath="//table[@id='custom_raw_items_lists']/tbody/tr/td[1]")
	public List <WebElement> CustomRawItemsLists_Table_Name_List;
	
	@FindBy(xpath="//input[@id='add_item']")
	public WebElement CustomRawItemDetail_AddItem_BT; 
	
	@FindBy(xpath="//h4[contains(.,'Add an Item')]")
	public WebElement AddAnItemPopUp_Title; 
	
	@FindBy(xpath="//input[@id='autocomplete']")
	public WebElement AddAnItemPopUp_EnterRawItemWrinOrDescription_TB; 
	
	@FindBy(xpath="//input[@id='add_item_modal_button']")
	public WebElement AddAnItemPopUp_Add_BT; 
	
	@FindBy(xpath="//input[@id='save_item_modal_button']")
	public WebElement AddAnItemPopUp_Save_BT;
	
	@FindBy(xpath="//button[@class='close delete_raw_item']")
	public List <WebElement> CustomRawItemDetail_RawDelete_Button_List;
	
	@FindBy(xpath="//table[@id='raw_list_table']/tbody/tr/th[1]")
	public List <WebElement> CustomRawItemDetail_Table_WRIN_List;
	
	@FindBy(xpath="//table[@id='custom_raw_items_lists']/tbody/tr/td[2]/a")
	public List <WebElement> CustomRawItemsLists_Table_Delete_Button_List;
	
	@FindBy(xpath="//input[@id='save_custom_raw_item_list']")
	public WebElement CustomRawItemDetail_SaveList_BT;
	
	@FindBy(xpath="//h4[text()='Remove Raw Item']")
	public WebElement RemoveRawItemPopUp_Title;
	
	@FindBy(xpath="//input[@id='delete_raw_item_confirmation']")
	public WebElement RemoveRawItemPopUp_Delete_BT;
	
	@FindBy(xpath="//div[@class='toast-message' and text()='Changes Saved']")
	public WebElement ChangesSaved_Confirmation_MSG;
	
	@FindBy(id="delete_custom_raw_item_list_confirmation")
	public WebElement RemoveCustomListPopUp_Delete_BT;
	
	@FindBy(xpath="//div[@class='toast-message' and text()='Custom List has been deleted']")
	public WebElement RemoveCustomList_Confirmation_MSG;
	
	@FindBy(xpath="//input[@id='restore_custom_raw_item_list']")
	public WebElement RestoreLists_BT;
	
	@FindBy(xpath="//h1[text()='Deleted Custom Lists']")
	public WebElement DeletedCustomLists_Title;
	
	@FindBy(xpath="//input[@id='restore_custom_lists_confirmation']")
	public WebElement RestoreCustomListPopUp_RestoreCustomList_BT;
	
	
	//To Search and select a raw Item on "Add an Item" pop up page
		public CustomRawItemListsPage searchAndSelectRawItemOnAddAnItemPopUp(String wrin) throws InterruptedException
		{
			AddAnItemPopUp_EnterRawItemWrinOrDescription_TB.sendKeys(wrin);
			action.sendKeys(Keys.SPACE).build().perform(); 
			Thread.sleep(1500); 
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			int size=driver.findElements(By.xpath("//strong[text()='"+wrin+"']")).size();
			driver.findElement(By.xpath("(//strong[text()='"+wrin+"'])["+size+"]")).click();
			return PageFactory.initElements(driver, CustomRawItemListsPage.class);
		}
	
	//To verify that a particular WRIN is present on Custom raw item detail page
		
	public boolean isRawItemPresentOnCustomRawItemDeatilTable(String wrin) {
		boolean result = false;
		int size = driver.findElements(By.xpath("//table[@id='raw_list_table']/tbody/tr/th[1]")).size();
		for (int i = 1; i <= size; i++) {
			result = driver.findElement(By.xpath("//table[@id='raw_list_table']/tbody/tr["+ i + "]/th[1]")).getText().trim().contains(wrin);
			if (result) {
				result = true;
				break;
			} else if (i == size && (result = false)) {
				result = false;
				break;
			} else {
				continue;
			}
		}
		return result;
	}
	
	// To Create a Custome Raw List
	public CustomRawItemListsPage createACustomRawList(String listName)throws InterruptedException {
		AddList_BT.click();
		wait.until(ExpectedConditions.visibilityOf(CreateNewRawItemListPopup_Name_TB));
		Thread.sleep(2000);
		CreateNewRawItemListPopup_Name_TB.sendKeys(listName);
		CreateNewRawItemListPopup_Save_BT.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='add_raw_item_list_button']")));
		return PageFactory.initElements(driver, CustomRawItemListsPage.class);
	}

	// click on A raw Item List
	public CustomRawItemListsPage clickOnARawItemList(String listName) {
		// Click on the list
		driver.findElement(By.xpath("//table[@id='custom_raw_items_lists']/tbody/tr/td[1][text()='"+ listName + "']")).click();
		// Wait for the details page to be opened
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='raw_list_table']")));
		return PageFactory.initElements(driver, CustomRawItemListsPage.class);
	}

	// Add a raw Item in Custom List with the list name It will take the list name and WRIN as a argument
	public CustomRawItemListsPage addAWrinInCustomList(String listName,String wrin) throws InterruptedException {
		// click on the Raw item list
		clickOnARawItemList(listName);
		// click on Add Item button
		CustomRawItemDetail_AddItem_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddAnItemPopUp_Title));
		Thread.sleep(1500);
		searchAndSelectRawItemOnAddAnItemPopUp(wrin);
		Thread.sleep(2000);
		AddAnItemPopUp_Add_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddAnItemPopUp_Save_BT));
		AddAnItemPopUp_Save_BT.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='save_item_modal_button']")));
		CustomRawItemDetail_SaveList_BT.click();
		wait.until(ExpectedConditions.visibilityOf(CustomeRawItemLists_Title));
		return PageFactory.initElements(driver, CustomRawItemListsPage.class);
	}
	
	// Add a raw item in the opened custom list It will take WRIN as a argument
	public CustomRawItemListsPage addAWrinInCustomListRawItemTable(String wrin)throws InterruptedException {
		// click on Add Item button
		CustomRawItemDetail_AddItem_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddAnItemPopUp_Title));
		searchAndSelectRawItemOnAddAnItemPopUp(wrin);
		Thread.sleep(3000);
		AddAnItemPopUp_Add_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddAnItemPopUp_Save_BT));
		AddAnItemPopUp_Save_BT.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='save_item_modal_button']")));
		return PageFactory.initElements(driver, CustomRawItemListsPage.class);
	}
		
		public void deleteRawItemFromCustomList(String wrinId){
			driver.findElement(By.xpath("//table[@id='raw_list_table']//tr/th[contains(text(),'"+wrinId+"')]/following-sibling::th/input[contains(@class,'delete_raw_item')]")).click();
			wait.until(ExpectedConditions.visibilityOf(RemoveRawItemPopUp_Title));
			RemoveRawItemPopUp_Delete_BT.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='delete_raw_item_confirmation']")));
		}
	
	
		public void deleteCustomRawList(String listName){
			driver.findElement(By.xpath("//table[@id='custom_raw_items_lists']//tr/td[text()='"+listName+"']/following-sibling::td/a[@class='editor_remove']")).click();
			wait.until(ExpectedConditions.visibilityOf(RemoveCustomListPopUp_Delete_BT));
			RemoveCustomListPopUp_Delete_BT.click();
			wait.until(ExpectedConditions.visibilityOf(RemoveCustomList_Confirmation_MSG));
		}
	
		public void restoreCustomList(String listName) throws InterruptedException{
			wait.until(ExpectedConditions.visibilityOf(RestoreLists_BT));
			Thread.sleep(2000);
			RestoreLists_BT.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(DeletedCustomLists_Title));
			driver.findElement(By.xpath("//table[@id='deleted_custom_lists']//tr/td[text()='"+listName+"']/following-sibling::td/a[@class='restore_list']")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(RestoreCustomListPopUp_RestoreCustomList_BT));
			RestoreCustomListPopUp_RestoreCustomList_BT.click();
		}
		
		public boolean verifyCustomListIsDisplayed(String listName){
			return Base.isElementDisplayed(By.xpath("//table[@id='custom_raw_items_lists']//tr/td[text()='"+listName+"']"));
		}
}
