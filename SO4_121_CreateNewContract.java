package week1.day1;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SO4_121_CreateNewContract {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//Steps:1. Login to https://login.salesforce.com
		
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("mars@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(3000);
		
		//2. Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(3000);
		
		//3. Click View All and click 'Contract' from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//label[contains(text(),'Search apps or items')]/following::input")).sendKeys("contract");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[@class='slds-truncate']/mark")).click();
		
		//4. Click on the Dropdown icon in the Contract tab
		 driver.findElement(By.xpath(" (//a[@title='Recently Viewed | Contracts']/following::a)[1]")).click();
		 		
		
		//5. Click on New Contract
		 JavascriptExecutor executor = (JavascriptExecutor)driver; 
		 executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//a[@role='menuitem'])[1]")));
		 
		 Thread.sleep(3000);
		 //driver.findElement(By.xpath("(//a[@role='menuitem'])[1]")).click();
		
		 
		 //6. Select the accounts listed on the'Account Name' field
		 WebElement accno = driver.findElement(By.xpath("//input[@title='Search Accounts']"));
		 accno.sendKeys("test");
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//div[@title='test']")).click();
		 		 
		//7.Select the Contract Start Date as Tommorow's Date
		//driver.findElement(By.xpath(" (//div[@class='form-element']/input)[1]")).sendKeys("10/01/2021");
		 driver.findElement(By.xpath("(//span[text()='Date Picker']/parent::a)[1]")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//td[@class='slds-is-selected uiDayInMonthCell']/span")).click();
		
		//8.Enter Contract Term (months) as' 6 '
		driver.findElement(By.xpath("//input[@class='input uiInputSmartNumber']")).sendKeys("6");
		
		//9.Click save and Verify the Contract Name
		driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[2]")).click();
		Thread.sleep(3000);
		
		//10.Get the Contract number
		String contract1 = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		System.out.println("Your contract" + contract1 + "has been created successfully");
		String contract2 = "00000109";
		if(contract1.equals(contract2)) {
			System.out.println("Your contract" + contract1 + "matches successfully");
		}
		//11.Get the difference between  the Contract Start Date and End Date and Check it matches the Contract Term.
		
//WebElement table = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']"));
//List<WebElement> tablerow = table.findElements(By.tagName("tr"));
//System.out.println(tablerow.size());
//List<WebElement> tableCol = table.findElements(By.tagName("td"));
//System.out.println(tableCol.size());
		
		String date1 = driver.findElement(By.xpath("(//li[@data-aura-class='forceHighlightsDesktopListRecordItem']/following::span[@data-aura-class='uiOutputDate'])[1]")).getText();
        String date2 = driver.findElement(By.xpath("(//li[@data-aura-class='forceHighlightsDesktopListRecordItem']/following::span[@data-aura-class='uiOutputDate'])[2]")).getText();
System.out.println(date1);
System.out.println(date2);


String[] arrOfDate1 = date1.split("/");
String[] arrOfDate2 = date2.split("/");

int fromMonth = Integer.parseInt(arrOfDate1[0]);
int fromDate = Integer.parseInt(arrOfDate1[1]);
int fromYear = Integer.parseInt(arrOfDate1[2]);

int toMonth = Integer.parseInt(arrOfDate2[0]);
int toDate = Integer.parseInt(arrOfDate2[1]);
int toYear = Integer.parseInt(arrOfDate2[2]);

LocalDate start_date= LocalDate.of(fromYear, fromMonth, fromDate);

// End date
LocalDate end_date= LocalDate.of(toYear, toMonth, toDate);

Period diff= Period.between(start_date,end_date);

// Print the date difference
// in years, months, and days
System.out.print("Difference "+ "between two dates is: ");

// Print the result
System.out.println(diff.getMonths());
int months = diff.getMonths();

String contText = driver.findElement(By.xpath("(//li[@data-aura-class='forceHighlightsDesktopListRecordItem']/following::span[@data-aura-class='uiOutputNumber'])")).getText();
int contNum = Integer.parseInt(contText);
if(contNum==months) {
	System.out.println("Contract months re equal");
}
else {
	System.out.println("Not matching contract and dates");
}
	}

}
