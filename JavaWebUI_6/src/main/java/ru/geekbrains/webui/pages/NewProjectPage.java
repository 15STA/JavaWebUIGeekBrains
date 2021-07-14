package ru.geekbrains.webui.pages;

import ch.qos.logback.core.joran.action.NewRuleAction;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.geekbrains.webui.base.BaseView;

public class NewProjectPage extends BaseView {

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement pageTitle;

    @FindBy(xpath = ".//input[@name='crm_project[name]']")
    private WebElement projectNameInput;

    @FindBy(xpath = "//span[@class='select2-chosen' and text() = 'Укажите организацию']")
    private WebElement  findOrganizationClick;

    @FindBy(xpath = "//input[@class='select2-input select2-focused']")  //input[contains (@class,'select2-input')]
    private WebElement organizationInputForSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement selectedOrganizationClick;

    @FindBy(name = "crm_project[type]")
    private WebElement checkProjectType;

    @FindBy(name = "crm_project[priority]")
    private WebElement dropdownProjectPriority;

    @FindBy(name = "crm_project[financeSource]")
    private WebElement dropdownProjectFinanceSource;

    @FindBy(name = "crm_project[businessUnit]")
    private WebElement dropdownProjectBusinessUnit;

    @FindBy(name = "crm_project[curator]")
    private WebElement dropdownProjectCurator;

    @FindBy(name = "crm_project[rp]")
    private WebElement dropdownProjectRp;

    @FindBy(name = "crm_project[manager]")
    private WebElement dropdownProjectManager;

    @FindBy(xpath = "//div[@class='btn-group']/button[@class='btn btn-success action-button']")
    private WebElement buttonSafeAndClose;

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage checkPageTitle(){
        wait10second.until(ExpectedConditions.visibilityOf(pageTitle));
        Assertions.assertEquals(pageTitle.getText(), "Создать проект");
        return this;
    }

    public NewProjectPage enterProjectName(String projectName){
        projectNameInput.sendKeys(projectName);
        return this;
    }

    public NewProjectPage pointOrganization(String organization) {
        findOrganizationClick.click();
        organizationInputForSearch.clear();
        organizationInputForSearch.sendKeys(organization);
        wait10second.until(ExpectedConditions.visibilityOf(selectedOrganizationClick));
        selectedOrganizationClick.click();
        return this;
    }

    public NewProjectPage clickProjectType(){
        checkProjectType.click();
        return this;
    }

    public NewProjectPage selectProjectPriority(int value){
        Select selectDropdownProjectPriority = new Select(dropdownProjectPriority);
        selectDropdownProjectPriority.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectProjectFinanceSource(int value){
        Select selectDropdownProjectFinanceSource = new Select(dropdownProjectFinanceSource);
        selectDropdownProjectFinanceSource.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectProjectBusinessUnit(int value){
        Select selectDropdownProjectBusinessUnit = new Select(dropdownProjectBusinessUnit);
        selectDropdownProjectBusinessUnit.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectProjectCurator(int value){
        Select selectDropdownProjectCurator = new Select(dropdownProjectCurator);
        selectDropdownProjectCurator.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectProjectRp(int value) {
        Select selectDropdownProjectRp = new Select(dropdownProjectRp);
        selectDropdownProjectRp.selectByValue(String.valueOf(value));
        return this;
    }

    public NewProjectPage selectProjectManager(int value) {
        Select selectDropdownProjectManager = new Select(dropdownProjectManager);
        selectDropdownProjectManager.selectByValue(String.valueOf(value));
        return this;
    }

    public AllProjectsPage clickSafeAndClose() {
        buttonSafeAndClose.click();
        return new AllProjectsPage(driver);
    }
}

