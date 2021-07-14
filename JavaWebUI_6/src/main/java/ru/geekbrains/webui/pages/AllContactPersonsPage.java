package ru.geekbrains.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.webui.base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllContactPersonsPage extends BaseView {


    @FindBy(xpath = ".//a[@title='Создать контактное лицо']")
    private WebElement createContactPersonButton;

    public AllContactPersonsPage(WebDriver driver) {
        super(driver);
    }

    public ContactPersonCreatePage clickOnCreateContactPerson() {
        createContactPersonButton.click();
        return new ContactPersonCreatePage(driver);
    }

    public AllContactPersonsPage checkContactPersonPopUp() {
        String message = wait10second.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='message']"))).getText();
        System.out.println(message);
        assertTrue(message.contains("Контактное лицо сохранено"));
        return this;
    }


}
