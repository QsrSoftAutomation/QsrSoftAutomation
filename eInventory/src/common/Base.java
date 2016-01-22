package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sprint2.AbstractTest;

public class Base extends AbstractTest {

	// Method to return the today's date in MM/DD/YYYY format
	public static String returnTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		return todayDate;
	}

	// To verify element is displayed or not
	public static boolean isElementDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println("Catch " + element);
			return false;
		}
	}
	
	// This overloaded method will take the locater of element as argument and verify element is displayed or not
	public static boolean isElementDisplayed(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// To generate five digit random number
	public static String randomNumberFiveDigit() {
		Random r = new Random(System.currentTimeMillis());
		int i = 10000 + r.nextInt(20000);
		return Integer.toString(i);
	}
	
	//Generate N Digit Random Number
	public static int generateNdigitRandomNumber(int n){
		Random randGen = new Random();
		Double startNum = Math.pow(10, n-1);
		int num1 = startNum.intValue();
		Double endNum = Math.pow(10, n);
		int num2 = endNum.intValue();
		int range = num2 - num1 + 1;
		int randomNum = randGen.nextInt(range) + num1;
		return randomNum;
	}
	
	public static String getMonthName(int Month) {
		String month;
		if (Month == 1) {
			month = "January";
		} else if (Month == 2) {
			month = "February";
		} else if (Month == 3) {
			month = "March";
		} else if (Month == 4) {
			month = "April";
		} else if (Month == 5) {
			month = "May";
		} else if (Month == 6) {
			month = "June";
		} else if (Month == 7) {
			month = "July";
		} else if (Month == 8) {
			month = "August";
		} else if (Month == 9) {
			month = "September";
		} else if (Month == 10) {
			month = "October";
		} else if (Month == 11) {
			month = "November";
		} else {
			month = "December";
		}
		return month;
	}
	
	// This method will take date as argument and return month from date
	public static int getMonthFromDate(String date) {
		String currentMonth = date.split("/")[0];
		if (currentMonth.startsWith("0")) {
			currentMonth = currentMonth.replace("0", "");
		}
		int month = ((Integer.parseInt(currentMonth)) - 1);
		return month;
	}

	// This method will take date as argument and return day from date
	public static int getDayFromDate(String date) {
		String currentDay = date.split("/")[1];
		if (currentDay.startsWith("0")) {
			currentDay = currentDay.replace("0", "");
		}
		int day = Integer.parseInt(currentDay);
		return day;
	}
	
	// This method will take date as argument and return Year from date
	public static int getYearFromDate(String date) {
		String currentYear = date.split("/")[2];
		return Integer.parseInt(currentYear);
	}
	// This method will take hour and minutes as parameter and return 15 minutes earlier time
	public static String get15MinuteTimeSlice(int hour, int minute) {
		int nextMinute;
		if (minute == 0) {
			minute = 60;
			hour--;
		}
		nextMinute = minute - 15;
		String hourValue = String.valueOf(hour);
		if (hourValue.length() == 1) {
			hourValue = "0" + hourValue;
		}
		String minuteValue = String.valueOf(nextMinute);
		if (minuteValue.equals("0")) {
			minuteValue = "00";
		}
		return hourValue + ":" + minuteValue;

	}
		
	// This method will return hour value from the time(HH:MM)
	public static int getHourFromTime(String time) {
		return Integer.parseInt(time.split(":")[0]);
	}

	// This method will return Minutes value from the time(HH:MM)
	public static int getMinuteFromTime(String time) {
		return Integer.parseInt(time.split(":")[1]);
	}
	
	public static String getCurrentTimeStamp() {
		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
		String timeStamp ="";
		int hour = getHourFromTime(currentTime);
		int minutes = Integer.parseInt(currentTime.split(":")[1]);
		if (minutes >= 0 && minutes < 15) {
			timeStamp = (hour + ":" + "00");
		} else if (minutes >= 15 && minutes < 30) {
			timeStamp = (hour + ":" + "15");
		} else if (minutes >= 30 && minutes < 45) {
			timeStamp = (hour + ":" + "30");
		} else if (minutes >= 45 && minutes <= 59) {
			timeStamp = (hour + ":" + "45");
		}

		return timeStamp;
	}
	
	public static void executeJavaScript(String script){
		((JavascriptExecutor) driver).executeScript(script);
	}
	
}
