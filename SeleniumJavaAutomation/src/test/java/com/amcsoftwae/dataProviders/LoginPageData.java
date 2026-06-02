package com.amcsoftwae.dataProviders;

import com.amcsoftware.models.Homepage;
import com.amcsoftware.utils.JsonReader;
import org.testng.annotations.DataProvider;

public class LoginPageData {

    private static final String FILE = "testData/HomePage.json";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return JsonReader.provide(FILE, Homepage.class, Homepage::getLogin);
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return JsonReader.provide(FILE, Homepage.class, Homepage::getHomePage);
    }

    @DataProvider(name = "navigationData")
    public Object[][] getNavigationData() {
        return JsonReader.provide(FILE, Homepage.class, Homepage::getNavigation);
    }

    @DataProvider(name = "pageContentData")
    public Object[][] getPageContentData() {
        return JsonReader.provide(FILE, Homepage.class, Homepage::getPageContent);
    }
}
