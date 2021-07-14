package ru.geekbrains.webui.ContactPerson;

import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUiTest;
import ru.geekbrains.webui.configuration.Configuration;
import ru.geekbrains.webui.enums.ContractorSubMenuButtons;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.AllContactPersonsPage;
import ru.geekbrains.webui.pages.LoginPage;

public class ContactPersonCreateTest extends BaseUiTest {

    @Test
    public void createNewProjectPositiveTest(){
        AllContactPersonsPage contactPersonScreen = (AllContactPersonsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.COUNTER_PARTIES)
                .clickSubMenuButton(ContractorSubMenuButtons.CONTACT_PERSON_REQUEST);

        contactPersonScreen
                .clickOnCreateContactPerson()
                .checkPageTitle()
                .enterLastName("Potter")
                .enterFirstName("Harry")
                .pointOrganization("Bins-Haley")
                .enterJobTitle("wizard")
                .clickSafeAndClose()
                .checkContactPersonPopUp();
    }
}
