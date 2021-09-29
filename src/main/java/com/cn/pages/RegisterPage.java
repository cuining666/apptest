package com.cn.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage {
    AndroidDriver<?> driver;
    Logger log = Logger.getLogger(RegisterPage.class.getName());

    public RegisterPage(AndroidDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="inputUsername")
    public MobileElement username_txt;
    @AndroidFindBy(id="inputEmail")
    public MobileElement email_txt;
    @AndroidFindBy(id="inputPassword")
    public MobileElement password_txt;
    @AndroidFindBy(id="inputName")
    public MobileElement name_txt;
    @AndroidFindBy(id="input_preferedProgrammingLanguage")
    public MobileElement language_sel;
    @AndroidFindBy(id="input_adds")
    public MobileElement accept_check;
    @AndroidFindBy(id="btnRegisterUser")
    public MobileElement register_btn;

    public RegisterVerifyPage register_success(String username, String email, String password, String name, String language, String check){
        log.info("在Register页面输入username");
        username_txt.sendKeys(username);
        log.info("在Register页面输入email");
        email_txt.sendKeys(email);
        log.info("在Register页面输入password");
        password_txt.sendKeys(password);
        log.info("在Register页面输入name");
        name_txt.clear();
        name_txt.sendKeys(name);
        log.info("在Register页面，单机选择语言");
        language_sel.click();
        log.info("在Register页面, 等待2s，弹出语言选择框");
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("在Register页面，弹出语言选择框，选择语言");
        checkedTextView(language);
        log.info("在Register页面，选择accept_check");
        if("Yes".equals(check)) {
            if(!accept_check.isSelected())
                accept_check.click();
        }
        log.info("在Register页面，单击注册");
        register_btn.click();
        return new RegisterVerifyPage(driver);
    }

    private void checkedTextView(String language) {
        //使用class属性选择所有的单选按钮，并存放在一个List中
        List<MobileElement> checkedTextViews = (List<MobileElement>) driver.findElementsByClassName("android.widget.CheckedTextView");
        //使用循环将list中的每个单选按钮进行遍历，查到text值为“Ruby”的单选按钮，如果该单选按钮未处于选中状态，则调用click方法进行选择
        for (MobileElement checkedTextView : checkedTextViews) {
            if(checkedTextView.getAttribute("text").equals(language)) {
                if(!checkedTextView.isSelected()) {
                    checkedTextView.click();
                    break;
                }
            }
        }
    }
}
