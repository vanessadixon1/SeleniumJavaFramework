package com.amcsoftwae.tescases;

import com.amcsoftwae.base.BaseTest;
import com.amcsoftwae.dataProviders.LoginPageData;
import com.amcsoftware.driver.DriverFactory;
import com.amcsoftware.models.LoginItem;
import com.amcsoftware.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

/**
 * Dummy test for just testing how this framework works
 *
 */
public class HomePageTest extends BaseTest {
    @Test(dataProvider = "loginData", dataProviderClass = LoginPageData.class)
    public void testHomePage(LoginItem login) throws InterruptedException {
        System.out.println(login.getDescription() + login.getPassword());

        DriverFactory.getDriver().get("https://www.amazon.com");
        Thread.sleep(10000);
        DriverFactory.getDriver().quit();
    }
    @Test
    public void googleTest() throws InterruptedException {
        HomePage homePage = new HomePage();
        DriverFactory.getDriver().get("https://www.google.com");
        homePage.inputDataAndSearch("hey");
    }
    @Test
    public void failTest()  {
        Assert.fail();
    }
}
