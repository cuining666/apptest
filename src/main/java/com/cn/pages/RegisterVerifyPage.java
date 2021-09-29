package com.cn.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class RegisterVerifyPage {
    AndroidDriver<?> driver;
    Logger log = Logger.getLogger(RegisterVerifyPage.class.getName());

    public RegisterVerifyPage(AndroidDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="label_username_data")
    public MobileElement label_username_data;
    @AndroidFindBy(id="label_email_data")
    public MobileElement label_email_data;
    @AndroidFindBy(id="label_name_data")
    public MobileElement label_name_data;
    @AndroidFindBy(id="label_password_data")
    public MobileElement label_password_data;
    @AndroidFindBy(id="label_preferedProgrammingLanguage_data")
    public MobileElement label_preferedProgrammingLanguage_data;
    @AndroidFindBy(id="label_acceptAdds_data")
    public MobileElement label_acceptAdds_data;

    public String get_name_value() {
        log.info("在RegisterVerifyPage，通过输入获取name值");
        return label_name_data.getText().toString();
    }

    public String get_username_value() {
        log.info("在RegisterVerifyPage，通过输入获取username值");
        return label_username_data.getText().toString();
    }

    public String get_email_value() {
        log.info("在RegisterVerifyPage，通过输入获取email值");
        return label_email_data.getText().toString();
    }

    public String get_language_value() {
        log.info("在RegisterVerifyPage，通过输入获取language值");
        return label_preferedProgrammingLanguage_data.getText().toString();
    }

    public String get_acceptAdds_value() {
        log.info("在RegisterVerifyPage，通过输入获取accept值");
        return label_acceptAdds_data.getText().toString();
    }

    public String get_password_value() {
        log.info("在RegisterVerifyPage，通过输入获取password值");
        return label_password_data.getText().toString();
    }
}
