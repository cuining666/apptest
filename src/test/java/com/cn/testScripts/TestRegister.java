package com.cn.testScripts;

import com.cn.base.BaseActivity;
import com.cn.contant.Contants;
import com.cn.pages.HomePage;
import com.cn.pages.RegisterPage;
import com.cn.pages.RegisterVerifyPage;
import com.cn.util.DataProviderFromCsv;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class TestRegister extends BaseActivity {
    Logger log = Logger.getLogger(TestRegister.class.getName());

    @DataProvider(name = "RegisterData", parallel = true)
    public Object[][] getRegisterDta() throws IOException {
        return DataProviderFromCsv.getTestData(Contants.FILE_PATH + Contants.FILE_NAME);
    }

    @Test(dataProvider = "RegisterData")
    public void testRegistSuccess(String username, String email, String password, String name, String language, String accept, String verifyusername, String verifyemail, String verifypassword, String verifyname, String verifylanguage, String verifyaccept) {
        HomePage homePage = null;
        RegisterPage registerPage = null;
        RegisterVerifyPage registerVerifyPage = null;
        try {
            log.info("初始化home页面");
            homePage = new HomePage(getDriver());
            log.info("点击注册按钮跳转的注册页面");
            registerPage = homePage.navigate_register_page();
            log.info("调用注册方法，传入参数");
            registerVerifyPage = registerPage.register_success(username, email, password, name, language, accept);
            log.info("等待2s");
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (AssertionError error) {
            log.info("调用注册成功方法失败");
            Assert.fail("调用注册成功方法失败");
        }
        try {
            log.info("注册验证页面是否包含输入的username值");
            Assert.assertEquals(registerVerifyPage.get_username_value(), verifyusername);
        } catch (AssertionError error) {
            Assert.fail("username断言失败，查看username值是否正确");
        }
        try {
            log.info("注册验证页面是否包含输入的name值");
            Assert.assertEquals(registerVerifyPage.get_name_value(), verifyname);
        } catch (AssertionError error) {
            Assert.fail("name断言失败，查看name值是否正确");
        }
        try {
            log.info("注册验证页面是否包含输入的email值");
            Assert.assertEquals(registerVerifyPage.get_email_value(), verifyemail);
        } catch (AssertionError error) {
            Assert.fail("email断言失败，查看email值是否正确");
        }
        try {
            log.info("注册验证页面是否包含输入的password值");
            Assert.assertEquals(registerVerifyPage.get_password_value(), verifypassword);
        } catch (AssertionError error) {
            Assert.fail("password断言失败，查看password值是否正确");
        }
        try {
            log.info("注册验证页面是否包含输入的language值");
            Assert.assertEquals(registerVerifyPage.get_language_value(), verifylanguage);
        } catch (AssertionError error) {
            Assert.fail("language断言失败，查看language值是否正确");
        }
        try {
            log.info("注册验证页面是否包含输入的accept值");
            Assert.assertEquals(registerVerifyPage.get_acceptAdds_value(), verifyaccept);
        } catch (AssertionError error) {
            Assert.fail("accept断言失败，查看accept值是否正确");
        }
        log.info("所有断言成功，测试用例注册执行成功");
    }
}
