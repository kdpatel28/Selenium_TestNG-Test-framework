package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BrowserConfig {
	public static WebDriver driver;
	public static String path="C:\\Users\\KRUNAL\\workspace\\Demo_TestNG\\Data.xls";
	public static ExtentReports reports=new ExtentReports("C:\\Users\\KRUNAL\\workspace\\Demo_TestNG\\reports.htm",true);
	public static ExtentTest Logger;
	
	public static void configBrowser(String browser,String url)
	{
		if(browser.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\eclipse\\jars\\chromedriver.exe");
			driver= new ChromeDriver();
		
		}
		else
			driver= new FirefoxDriver();
		
		Logger.log(LogStatus.PASS, "Launched browser");
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.get(url);
		 Logger.log(LogStatus.PASS, "Launched"+"   "+url);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 Logger.log(LogStatus.PASS, "Launched browser");
		
	}
	
	public static String Excel(String sheet,int column,int row) throws BiffException, IOException {
		 Workbook Excel = Workbook.getWorkbook(new File(path));
		 Sheet data = Excel.getSheet(sheet);
		 String searchValue = data.getCell(column, row).getContents();
		 System.out.println(searchValue);
	     return searchValue;
	     
		 
	}

public static void starttest(String testcasename)
{
	
	Logger=reports.startTest(testcasename);
	
}

public static void endtest(ExtentTest Logger)
{
	reports.endTest(Logger);
}

public static String captureScreenshot(WebDriver driver,String Screenshot)
{
	try
	{
		TakesScreenshot Ts=(TakesScreenshot) driver;
		File source=Ts.getScreenshotAs(OutputType.FILE);
		String dest="C:\\Users\\KRUNAL\\workspace\\Demo_TestNG\\Screenshots\\"+Screenshot+".png";
		File destination =new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}
	catch (Exception e)
	{
		System.out.println("Exception while taking screenshot"+e.getMessage());
		return e.getMessage();
	}
}
}
