package testCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserConfig;

import org.openqa.selenium.By;


public class HomePage extends BrowserConfig {
	
public static String flipkartLogo=".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[1]/a/img";
public static String searchBox=".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/div/div[1]/div/input";
public static String title="Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com";

@BeforeClass
public static void extent()
{
	starttest("Home Page Validation");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
	}
@Test(description="title", priority=1)
public static void titleValidation()
{

	
	if(driver.getTitle().equalsIgnoreCase(title))
	{
		Logger.log(LogStatus.PASS, "Title is available");
	}
	else
		Logger.log(LogStatus.FAIL, "Title is not available");
	
	
}

@Test (description="Flipkart Logo Validation", priority=2)
  public static void LogoValidation()
  {
	  if(driver.findElement(By.xpath(flipkartLogo)).isDisplayed())
	  {
		  Logger.log(LogStatus.PASS, "Logo is available");
	  }
	  else
		  Logger.log(LogStatus.FAIL, "Logo is not available");
  }
  
  @Test (description="SearchBox Validation", dependsOnMethods="titleValidation")
  public static void searchBoxValidation()
  {
	  if(driver.findElements(By.xpath(searchBox)).isEmpty())
	  {
		  Logger.log(LogStatus.INFO, "Searchox is empty");
	  }
	  else
	  {
		  Logger.log(LogStatus.INFO, "Searchox is not empty");
	  }
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
