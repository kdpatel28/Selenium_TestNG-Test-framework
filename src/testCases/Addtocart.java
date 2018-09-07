package testCases;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserConfig;

public class Addtocart extends BrowserConfig {
  
	public static String addtoCartButton=".//*[@id='container']/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div/div/div/div[2]/div/ul/li[1]/button";
	public static String Cartbtn=".//*[@id='container']/div/header/div[1]/div[2]/div/div/a";
	

@BeforeClass
public static void extent()
{
	starttest("Add to cart");
	}
@AfterClass
public static void teardown()
{
	endtest(Logger);
	
	}
	
	@Test
  public void addtoCart() throws Exception {
		Thread.sleep(5000);
		if(driver.findElement(By.xpath(addtoCartButton)).isDisplayed()){
		driver.findElement(By.xpath(addtoCartButton)).click();
		Logger.log(LogStatus.PASS, "Add to cart is present, Product added");
		}
		else
			Logger.log(LogStatus.FAIL, "Add to cart is not present");
  }
	
	@Test(dependsOnMethods={"addtoCart"})
	  public void cartPage() throws Exception {
			Thread.sleep(5000);
			if(driver.findElement(By.xpath(Cartbtn)).isDisplayed()){
			driver.findElement(By.xpath(Cartbtn)).click();
			Logger.log(LogStatus.PASS, "User navigated to cart section");
			}
			else
				Logger.log(LogStatus.FAIL, "User unable to navigate to cart section");
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
