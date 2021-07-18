package ru.geekbrains.webui.navigation;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.webui.base.BaseUiTest;
import ru.geekbrains.webui.configuration.Configuration;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.configuration.Configuration.BASE_URL;

@Feature("Navigation")
public class NavigationTest extends BaseUiTest {

    //@Disabled
    @ParameterizedTest
    @MethodSource("navigationTabProvider")
    public void checkMainNavigationTabsTest(NavigationBarTabs barTab) {
        new LoginPage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .checkUrl(BASE_URL)
                .getPageNavigation()
                .checkTabVisibility(barTab);
    }

    static NavigationBarTabs[] navigationTabProvider() {
        return NavigationBarTabs.values();
    }

}

