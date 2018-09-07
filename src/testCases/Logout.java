package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserConfig;

public class Logout extends BrowserConfig {
	
	public static String Logoutlink=".//*[@id='fk-mainhead-id']/div[1]/div/div[1]/div/ul/li/ul/li[10]/a";
	public static String Userdropdown=".//*[@id='fk-mainhead-id']/div[1]/div/div[1]/div/ul/li/a";
	
	

@BeforeClass
public static void extent()
{
	starttest("Logout");
	Logger.assignCategory("Regression");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
	}

  @Test
  public void UserLogout() throws InterruptedException {
	 
	  if(driver.findElement(By.xpath(Userdropdown)).isDisplayed())
	  {  
	  WebElement element = driver.findElement(By.xpath(Userdropdown));
	  
      Actions action = new Actions(driver);

      action.moveToElement(element).build().perform();
      Thread.sleep(3000);     
      if(driver.findElement(By.xpath(Logoutlink)).isDisplayed()){
      driver.findElement(By.xpath(Logoutlink)).click();
	  Logger.log(LogStatus.PASS, "user Logout");
	  }
	  }
	  else
		  Logger.log(LogStatus.FAIL, "unable to Logout as user not Logged In");

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
