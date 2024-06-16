package sprint1_Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.Assertion;

public class CreateNewOpportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		//Disable the WebNotifications
		
		ChromeOptions option=new ChromeOptions();
		
		option.addArguments("--disable-notifications");
		
		//Launch the browser and maximize
		
		WebDriver driver=new ChromeDriver(option);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.manage().window().maximize();
		
		//Login to Salesforce
		
		driver.get("https://login.salesforce.com/");
		
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		
		driver.findElement(By.id("Login")).click();
		
		//Click on toggle menu button from the left corner
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		Thread.sleep(5000);
		
		//Click view All and click Sales from App Launcher
		
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		//Click on Opportunity tab
		
		WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",opportunity);
		
		Thread.sleep(3000);
		
		//Click on New button
		
		driver.findElement(By.xpath("//a[@title='New']")).click();
		
		Thread.sleep(3000);
		
		//Enter the opportunity Name
		
		WebElement oppname=driver.findElement(By.xpath("//input[@name='Name']"));
		
		oppname.sendKeys("Salesforce Automation by Ramya A");
		
		//Get the Opportunity Name Text and store it
		
		String oppText = oppname.getAttribute("value");
		
		System.out.println("The expected text is " + oppText);
		
		//Select Today's date as close Date
		
		SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		
		String todayDate = format.format(Calendar.getInstance().getTime());
		
		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(todayDate);	
		
		Thread.sleep(3000);
		
		//Select Stage as "Need Analysis"
		
		WebElement element = driver.findElement(By.xpath("//button[@aria-label='Stage - Current Selection: --None--']"));
		
		element.click();
		
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
		
		Thread.sleep(3000);
		
		//Save the Opportunity created
		
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		Thread.sleep(5000);
		
		//Verify the printed Toast Message
		
		//New Opportunity should be created with name as  'Salesforce Automation by Your Name' 
		
		WebElement toastmsg = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		
		String toastText = toastmsg.getText();
		
		System.out.println("The created message is as follows " + toastText);
		
		Thread.sleep(5000);
		
		//verifying the Opportunity name entered matches with the Opportunity Name created
		
		int beginIndex= toastText.indexOf('"') +1;
	
		int endIndex= toastText.indexOf('"', beginIndex);
	
		String subStringOppName=toastText.substring(beginIndex, endIndex);
	
		System.out.println(subStringOppName);
	
		Thread.sleep(3000);
	
		Assert.assertEquals(subStringOppName, oppText);
		
		System.out.println("Verified Opportunity Name, Opportunity Name matches with the Toast created");
		
		Thread.sleep(4000);
		
		//Expected Result:New Opportunity should be created with name as  'Salesforce Automation by Your Name'
		
		System.out.println("New Opportunity is created as "+toastText+ " "+"as expected");
	
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


	}

}
