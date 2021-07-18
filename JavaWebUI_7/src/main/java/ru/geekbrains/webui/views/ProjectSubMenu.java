package ru.geekbrains.webui.views;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.webui.base.BaseView;
import ru.geekbrains.webui.base.SubMenu;
import ru.geekbrains.webui.base.SubMenuButtons;
import ru.geekbrains.webui.enums.ProjectSubMenuButtons;
import ru.geekbrains.webui.pages.AllProjectsPage;

public class ProjectSubMenu extends SubMenu {

    public ProjectSubMenu(WebDriver driver) {
        super(driver);
    }

    @Step("click on sub menu button {buttons}")
    @Override
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ProjectSubMenuButtons) {
            switch ((ProjectSubMenuButtons) buttons) {
                case PROJECT_REQUEST:
                    driver.findElement(((ProjectSubMenuButtons) buttons).getBy()).click();
                    return new AllProjectsPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
