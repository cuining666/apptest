package com.cn.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    AndroidDriver<?> driver;
    Logger log = Logger.getLogger(HomePage.class.getName());

    public HomePage(AndroidDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="startUserRegistration")
    public MobileElement startRegister_btn;

    public RegisterPage navigate_register_page(){
        log.info("在HomePage，点击进入register页面");
        this.startRegister_btn.click();
        return new RegisterPage(driver);
    }
}
