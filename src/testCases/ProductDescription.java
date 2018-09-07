package testCases;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserConfig;

public class ProductDescription extends BrowserConfig {
	
	public static String searchresult=".//*[@id='container']/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/div[1]/div";
	public static String searchresultprice=".//*[@id='container']/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/div[1]/div[1]/div/a[3]/div/div[1]";
	public static String productDescriptionname=".//*[@id='container']/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div[1]/div/h1";
	public static String productDescriptionprice=".//*[@id='container']/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div[3]/div/div/div[1]";
	


@BeforeClass
public static void extent()
{
	starttest("selecting desired product and view description");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
}

	
	@Test
	  public void SelectProduct() throws Exception {
		Thread.sleep(5000);
		
			if(driver.findElement(By.xpath(searchresult.concat("["+Excel("Sheet1",1,4 )+"]/div/a[2]"))).isDisplayed())
			{
				driver.findElement(By.xpath(searchresult.concat("["+Excel("Sheet1",1,4 )+"]/div/a[2]"))).click();
				Logger.log(LogStatus.PASS, "Produc is idenified and proceeded to description page");
			}
			else
				Logger.log(LogStatus.FAIL, "Product is not identified");
				
	  
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
