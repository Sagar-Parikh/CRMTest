package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	static EventFiringWebDriver e_driver;
	static WebEventListener eventListener;

	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
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
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
			
		}else {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\drivers\\msedgedriver.exe");
			 driver = new EdgeDriver();	
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
//		wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.EXPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
		
		
		
	}
	
	public static void end() {
		driver.close();
	}

}
