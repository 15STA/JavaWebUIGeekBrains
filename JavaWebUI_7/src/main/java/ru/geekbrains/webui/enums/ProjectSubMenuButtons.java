package ru.geekbrains.webui.enums;

import org.openqa.selenium.By;
import ru.geekbrains.webui.base.SubMenuButtons;

public enum ProjectSubMenuButtons implements SubMenuButtons {

    PROJECT_REQUEST(".//span[@class='title' and text()='Все проекты']");

    private final By by;

    ProjectSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
