package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

@SuppressWarnings("deprecation")
public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	static EventFiringWebDriver e_driver;
	static WebEventListener eventListener;

	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\New folder\\Selenium_Workspace\\CRMTest\\src\\main\\java\\com\\crm\\"
					+ "qa\\config\\config.properties");
			prop.load(ip);	
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\New folder\\SeleniumJars\\geckodriver.exe");
			 driver = new FirefoxDriver();
			
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\New folder\\SeleniumJars\\chromedriver.exe");
			 driver = new ChromeDriver();
			
		}else {
			System.setProperty("webdriver.edge.driver", "C:\\New folder\\SeleniumJars\\msedgedriver.exe");
			 driver = new EdgeDriver();	
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.EXPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
		
		
		
	}
	
	public static void end() {
		driver.close();
	}

}