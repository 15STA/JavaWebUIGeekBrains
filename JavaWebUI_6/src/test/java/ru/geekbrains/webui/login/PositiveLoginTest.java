package ru.geekbrains.webui.login;

import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUiTest;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.configuration.Configuration.*;

public class PositiveLoginTest extends BaseUiTest {

    @Test
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
                .enterLogin(LOGIN)
                .enterPassword(PASSWORD)
                .clickLoginButton()
                .checkUrl(BASE_URL);
    }
}
