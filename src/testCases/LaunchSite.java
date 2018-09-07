package testCases;

import utility.BrowserConfig;
import org.testng.annotations.BeforeSuite;



import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;

public class LaunchSite extends BrowserConfig{
	
	
  @BeforeSuite
  
  public void launchSite(ITestContext context) {
	  reports.config().documentTitle("Report")
      .reportName("Automation Summary Report")
      .reportHeadline("-FlipKart")
      .insertCustomStyles(".test { border:2px solid #444; }");
	starttest("launch Website");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
	  String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
	  BrowserConfig.configBrowser(seleniumBrowser, seleniumUrl);
	endtest(Logger);
  }

  @AfterSuite
  public void closeSite() {
	  reports.flush();	  
	  driver.close();
  }

}
