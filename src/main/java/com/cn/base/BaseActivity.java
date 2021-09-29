package com.cn.base;

import com.cn.pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseActivity {

    AndroidDriver<?> driver;
    Logger log = Logger.getLogger(HomePage.class.getName());
    private AppiumDriverLocalService service;

    public AndroidDriver<?> getDriver() {
        return driver;
    }

    /**
     * 启动浏览器并打开测试界面
     * @param context
     */
    @BeforeClass
    public void startTest(ITestContext context) throws MalformedURLException {
        PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
        log.info("------------------------------启动Appium服务------------------------------");
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        if(service == null || !service.isRunning()){
            throw new AppiumServerHasNotBeenStartedLocallyException("An Appium server node is not started!");
        }
        log.info("------------------------------测试用例执行开始------------------------------");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy S8");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"io.selendroid.testapp.HomeScreenActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN,true);
        capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,true);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterClass
    public void endTest() {
        log.info("------------------------------测试用例执行结束------------------------------");
        driver.quit();
        if(service !=null)
            service.stop();



        
    }
}
