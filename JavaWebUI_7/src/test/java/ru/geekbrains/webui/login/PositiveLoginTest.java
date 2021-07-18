package ru.geekbrains.webui.login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUiTest;
import ru.geekbrains.webui.helper.ScreenshotMaker;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.configuration.Configuration.*;

@Feature("Login")
@Severity(SeverityLevel.BLOCKER)
public class PositiveLoginTest extends BaseUiTest {

    //@Disabled
    @Test
    @Description("Login with an existing user")
    @DisplayName("Login test")
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
                .enterLogin(LOGIN)
                .enterPassword(PASSWORD)
                .clickLoginButton()
                .checkUrl(BASE_URL);

      //  ScreenshotMaker.makeScreenshot(driver,"login.jpg");
    }
}
