 package sprint1_Opportunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class OpportunitiesSortOrderByCloseDate {

	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		//Disable Web Notifications
		
		ChromeOptions option= new ChromeOptions();
		
		option.addArguments("--disable-notifications");
		
		//Launch Browser
		
		WebDriver driver=new ChromeDriver(option);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to Salesforce Application
		
		driver.get("https://login.salesforce.com/");
		
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		
		driver.findElement(By.id("Login")).click();
		
		//Click on toggle menu button from the left corner
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		Thread.sleep(5000);
		
		//Click View All
		
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		
		//Click Sales from App Launcher
		
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("sales");
		
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		Thread.sleep(7000);
		
		
		
		//JavaScript executor
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Click on Opportunity Tab
		
		WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		
		js.executeScript("arguments[0].click();",opportunity);
		
		Thread.sleep(3000);
		
		//Select the table view
		
		driver.findElement(By.xpath("//button[@title='Select list display']")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[@role='menuitemcheckbox']//span[text()='Table']")).click();
		
		Thread.sleep(5000);
		
		//Closed Date Sorting
		
		WebElement closeDate = driver.findElement(By.xpath("//span[text()='Close Date']/parent::a"));
		
		closeDate.click();
		
		Thread.sleep(3000);
		
		closeDate.click();
		
		Thread.sleep(3000);
		
		//Verify the Opportunities displayed in ascending order by Close date
		
		Date previousParseDate=null;
		
		List<WebElement> oppTable = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));
		
		for(WebElement ele : oppTable)
		{
			List<WebElement> allCol = ele.findElements(By.xpath("td"));
			
			WebElement closeDateCol = allCol.get(allCol.size()-3);
			
			WebElement closeDateValue = closeDateCol.findElement(By.xpath(".//span[@data-aura-class='uiOutputDate']"));
			
			String dateVal = closeDateValue.getText();
			
			System.out.println(dateVal);
			
			Thread.sleep(3000);
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			Date parseDate = sdf.parse(dateVal);
			
			//System.out.println(parseDate);
			
			Assert.assertTrue(previousParseDate==null||parseDate.after(previousParseDate)||parseDate.equals(previousParseDate));
			
			previousParseDate=parseDate;
			
			}
		
		//Expected Result:Opportunities should be displayed in ascending order by Close dateStep

		
		System.out.println("The Opportunities are displayed in ascending order by Close date");
		
	
		
		
	}

}
