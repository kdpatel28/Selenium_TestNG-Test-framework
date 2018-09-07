package testCases;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserConfig;

public class Removefromcart extends BrowserConfig {
	
	public static String removelink=".//*[@id='cartpage-cart-tab-content']/table/tbody/tr/td[1]/table/tbody/tr[2]/td/div[2]/a[2]";
	public static String Emptycartmsg=".//*[@id='cartpage-cart-tab-content']/div[2]/div[1]";
 

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

	@Test(priority=1)
  public void Removeitemfromcart() throws Exception {
	  
		Thread.sleep(2000);
	  if(driver.findElement(By.xpath(removelink)).isDisplayed())
	  {
		  
		  driver.findElement(By.xpath(removelink)).click();
		  Logger.log(LogStatus.PASS, "remove link is present");
		
	  }
	  else if(driver.findElement(By.xpath(Emptycartmsg)).isDisplayed())
	  {
		  System.out.println("cart is empty");
		  Logger.log(LogStatus.FAIL, "remove product button isunavailable , it might be duee to cookies please clear it and retry");
	  }
	  
  }
  
  @Test(dependsOnMethods={"Removeitemfromcart"})
  public void emptyCart() throws Exception {
	  Thread.sleep(2000);
	if(driver.findElement(By.xpath(Emptycartmsg)).isDisplayed())
	{
		
		Logger.log(LogStatus.PASS, "product is removed");
	}
	else
		Logger.log(LogStatus.FAIL, "Product is not removed");
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
