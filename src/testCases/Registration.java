package testCases;



import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import jxl.read.biff.BiffException;
import utility.BrowserConfig;

public class Registration extends BrowserConfig {
	
public static String loggedInUsername=".//*[@id='container']/div/header/div[1]/div[1]/div/ul/li[8]/a";	
public static String signuppopupHeader="html/body/div[3]/div/div/div/div/div[1]/span/span/span";
public static String signuplink=".//*[@id='container']/div/header/div[1]/div[1]/div/ul/li[8]/a";
public static String entermobNoTxtbx="html/body/div[3]/div/div/div/div/div[2]/div/form/div[1]/input";
public static String Continuebtn="html/body/div[3]/div/div/div/div/div[2]/div/form/div[2]/button[1]";
public static String existingUserbtn="html/body/div[3]/div/div/div/div/div[2]/div/form/div[2]/button[2]";
public static String discardSignuppopup="html/body/div[3]/div/div/button";
public static String alreadyRegistered=".//*[@id='container']/div/div[1]/div/div/div[2]";


@BeforeClass
public static void extent()
{
	starttest("Regisration");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
}


@Test(priority=1, description="to check whether user is logged in or not")
  public void isLoggedIn() throws Exception   {
	  
	Thread.sleep(10000);
	  
	  if(!driver.findElement(By.xpath(signuplink)).getText().equalsIgnoreCase("Signup"))
	  {	  
		  Logger.log(LogStatus.WARNING, "Signup link is not available");
		  if(driver.findElement(By.xpath(loggedInUsername)).getText().matches("Hi(.*)!"))
		  {
			  Logger.log(LogStatus.SKIP, "Cannot proceed with Registration as user is already logged in");
			  throw new SkipException("User Already Logged in");
		  }
	  }
	  else if(driver.findElement(By.xpath(signuplink)).getText().equalsIgnoreCase("Signup"))
	  {
		  driver.findElement(By.xpath(signuplink)).click();
		  Logger.log(LogStatus.PASS, "Signup link is available");
		  Thread.sleep(5000);
	  }
	  else{
		  Logger.log(LogStatus.FAIL, "User not logged in and Signup link is also not available");
		  Assert.fail("Signupbutton is not available");
		  
	  }
  }

@Test (dependsOnMethods ={"isLoggedIn"})
public static void regPopupValidation() throws InterruptedException
{
	Thread.sleep(2000);
	if(driver.findElement(By.xpath(signuppopupHeader)).isDisplayed() &
	driver.findElement(By.xpath(entermobNoTxtbx)).isDisplayed()&
	driver.findElement(By.xpath(Continuebtn)).isDisplayed()&
	driver.findElement(By.xpath(existingUserbtn)).isDisplayed()&
	driver.findElement(By.xpath(discardSignuppopup)).isDisplayed())
	{
		Logger.log(LogStatus.PASS, "Proceed to registration");
	}
	else
		Logger.log(LogStatus.WARNING, "Something is missing in Registraion form");
	
}

@Test (dependsOnMethods ={"regPopupValidation"})
public static void regprocess() throws BiffException, IOException, InterruptedException
{
	driver.findElement(By.xpath(entermobNoTxtbx)).sendKeys(Excel("Sheet1",1,3));
	driver.findElement(By.xpath(Continuebtn)).click();
	Thread.sleep(1000);
	if(driver.findElement(By.xpath(alreadyRegistered)).isDisplayed())
	{
		Logger.log(LogStatus.FAIL, "User already registered");
		Assert.fail("User is already registered");
		
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
