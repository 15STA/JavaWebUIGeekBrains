package ru.geekbrains.webui.views;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.webui.base.BaseView;
import ru.geekbrains.webui.base.SubMenu;
import ru.geekbrains.webui.base.SubMenuButtons;
import ru.geekbrains.webui.enums.ContractorSubMenuButtons;
import ru.geekbrains.webui.enums.ProjectSubMenuButtons;
import ru.geekbrains.webui.pages.AllContactPersonsPage;
import ru.geekbrains.webui.pages.AllProjectsPage;

public class CountersPartiesSubMenu extends SubMenu {

    public CountersPartiesSubMenu(WebDriver driver) {
        super(driver);
    }

    @Step("click on sub menu button {buttons}")
    @Override
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ContractorSubMenuButtons) {
            switch ((ContractorSubMenuButtons) buttons) {
                case CONTACT_PERSON_REQUEST:
                    driver.findElement(((ContractorSubMenuButtons) buttons).getBy()).click();
                    return new AllContactPersonsPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
