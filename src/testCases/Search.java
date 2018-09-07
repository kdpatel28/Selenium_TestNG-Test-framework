package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import jxl.read.biff.BiffException;
import utility.BrowserConfig;

public class Search extends BrowserConfig{
  
	public static String searchbox=".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/div/div[1]/div/input"; 
	public static String searchbtn=".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/div/div[2]/button";
	public static String searchresultheader=".//*[@id='container']/div/div[2]/div[2]/div/div[2]/div[1]/div/div[2]/h1/span/span[8]/span";
	
	

@BeforeClass
public static void extent()
{
	starttest("Search Product");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
}

	
	@Test(priority=1)
  public void searchProduct() throws BiffException, IOException, InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(searchbox)).sendKeys(Excel("Sheet1",1,2));
		
		if(driver.findElement(By.xpath(searchbtn)).isDisplayed())
		{
			driver.findElement(By.xpath(searchbtn)).click();
			Logger.log(LogStatus.PASS, "user proceeded with search");
		}
		else
			Logger.log(LogStatus.FAIL, "user unable to proceed with search");
  }
	
	@Test(dependsOnMethods={"searchProduct"})
	  public void searchPagevalidation() throws BiffException, IOException {
			if(driver.findElement(By.xpath(searchresultheader)).getText().equalsIgnoreCase(Excel("Sheet1",1,2)))
			{
				Logger.log(LogStatus.PASS, "search result displayed correctly");	
			}
			else
				Logger.log(LogStatus.FAIL, "search result not displayed correctly");
			
	  }
	

@AfterMethod
public void teardown(ITestResult result)
{
	if(result.getStatus()==ITestResult.FAILURE)
	{
	String Screenshotpath =captureScreenshot(driver, result.getName());
	String Image=Logger.addScreenCapture(Screenshotpath);
	Logger.log(LogStatus.FAIL, result.getName()+"Failed", Image);
	}
}

	
	
}
