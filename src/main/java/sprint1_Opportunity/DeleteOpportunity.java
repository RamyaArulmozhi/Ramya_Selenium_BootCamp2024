package sprint1_Opportunity;

import java.time.Duration;
import java.util.List;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class DeleteOpportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Disable Web Notification
		
		ChromeOptions option = new ChromeOptions();
		
		option.addArguments("--disable-notifications");
		
		//Launch Browser
		
		WebDriver driver = new ChromeDriver(option);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Login to Salesforce Application
		
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
		
		//Searching the Opportunity Name
		
		String oppName="Salesforce Automation by Ramya A";
		
		WebElement searchOpp = driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));
		
		searchOpp.sendKeys(oppName);
		
		Thread.sleep(3000);
		
		searchOpp.sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		
		//Delete Icon
		
		List<WebElement> ddSearch = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));
		
		for(WebElement ele: ddSearch)
		{
			String text = ele.findElement(By.xpath("th//a")).getText();
			if(oppName.equals(text))
			{
				List<WebElement> ddCols = ele.findElements(By.xpath("td"));
				System.out.println(ddCols.size());	
				
				WebElement eleIcon = ddCols.get(ddCols.size()-1);
				
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//p[@class='slds-text-body--small']")).click();
				
				System.out.println("Tool Tip clicked");
				
				Thread.sleep(3000);
				
				WebElement eleClick = eleIcon.findElement(By.xpath(".//a"));
				
				eleClick.click();
				
				Thread.sleep(5000);
				
				driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Delete']")).click();
				
				Thread.sleep(5000);
				
				break;
			}
		}
		
		//DialogBox
		
		WebElement alertDel = driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']//span"));
		
		alertDel.click();
		
		Thread.sleep(3000);
		
		//Verify the Toast Message
		
		WebElement toastmsg = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		
		String toastText = toastmsg.getText();
		
		System.out.println("The Deleted message is as follows " + toastText);
		
		Thread.sleep(5000);
		
		//Verify Whether Oppurtunity is Deleted using Oppurtunity Name
		
		int beginIndex=toastText.indexOf('"')+1;
		
		int endIndex=toastText.indexOf('"', beginIndex);
		
		String message=toastText.substring(beginIndex, endIndex);
		
		System.out.println(message);
		
		Thread.sleep(2000);
		
		Assert.assertEquals(message, oppName);
		
		// Expected Result: Oppurtunity should be Successfully deleted
		
		System.out.println("Opportunity is Deleted Successfully");
		
		

	}

}
