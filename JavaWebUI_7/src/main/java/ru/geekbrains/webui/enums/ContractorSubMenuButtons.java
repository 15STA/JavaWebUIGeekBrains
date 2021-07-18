package ru.geekbrains.webui.enums;

import org.openqa.selenium.By;
import ru.geekbrains.webui.base.SubMenuButtons;

public enum ContractorSubMenuButtons implements SubMenuButtons {

    CONTACT_PERSON_REQUEST(".//span[@class='title' and text()='Контактные лица']");

    private final By by;

    ContractorSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
