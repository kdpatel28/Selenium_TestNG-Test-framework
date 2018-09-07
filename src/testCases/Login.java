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

public class Login extends BrowserConfig {
	
public static String loginLink=".//*[@id='container']/div/header/div[1]/div[1]/div/ul/li[9]/a";
public static String loginpopup="html/body/div[3]/div/div/div/div/div[1]/span/span/span";
public static String loginUsername="html/body/div[3]/div/div/div/div/div[2]/div/form/div[1]/input";
public static String loginPassword="html/body/div[3]/div/div/div/div/div[2]/div/form/div[2]/input";
public static String loginbtn="html/body/div[3]/div/div/div/div/div[2]/div/form/div[3]/button";


@BeforeClass
public static void extent()
{
	starttest("login");
	Logger.assignCategory("Regression");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
	}

@Test(priority =1)
  public static boolean loginbtn() {
	
	if(driver.findElement(By.xpath(loginLink)).isDisplayed())
	{
		Logger.log(LogStatus.INFO, "Login link is present");
		return true;
	}
	Logger.log(LogStatus.INFO, "login link not present");
	 return false;
  }
	
@Test(priority=2)
	public static void loginStatus()
	{
		if(!loginbtn())
		{
			Logger.log(LogStatus.SKIP, "Skipping registration as user logged in");
			throw new SkipException("User logged in");
			
		}
		else{
			Logger.log(LogStatus.INFO, "user not logged in, Proceed to Login");
		}
	}
	
@Test (dependsOnMethods= {"loginStatus"})
	public static void loginpopupvalidation() throws InterruptedException
	{
		if(driver.findElement(By.xpath(loginLink)).isDisplayed())
		{
			driver.findElement(By.xpath(loginLink)).click();
			Logger.log(LogStatus.PASS, "Click on login link");
			Thread.sleep(2000);
		}
		else
		{
			Logger.log(LogStatus.FAIL, "Click on login link");
			Assert.fail("Login Button is not available");
		}
		if(driver.findElement(By.xpath(loginpopup)).isDisplayed() & driver.findElement(By.xpath(loginUsername)).isDisplayed() &
		driver.findElement(By.xpath(loginPassword)).isDisplayed())
		{
			Logger.log(LogStatus.PASS, "username and password field available");	
		}
		else
		{
			Logger.log(LogStatus.FAIL, "username or password field is missing");	
		}
			
		
	}

@Test (dependsOnMethods= {"loginpopupvalidation"})
public static void loginprocess() throws BiffException, IOException
{
	
	driver.findElement(By.xpath(loginUsername)).sendKeys(Excel("Sheet1",1,0));
	driver.findElement(By.xpath(loginPassword)).sendKeys(Excel("Sheet1",1,1));
	
	if(driver.findElement(By.xpath(loginbtn)).isDisplayed())
	{
		driver.findElement(By.xpath(loginbtn)).click();
		Logger.log(LogStatus.PASS, "Clicked on Login");
	}
	else
	{
		Logger.log(LogStatus.FAIL, "Login button not available, unable to proceed");
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

/*@DataProvider
public Object[][] getData()
{
	Object[][] Login = new Object[0][2];
	Login[0][0]=Excel("Sheet1",1,)
	Login[0][1]=
			
	return Login;
}*/

}
