package tests

import BaseTest
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import com.codeborne.selenide.logevents.SelenideLogger.step
import config.ConfigManager
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.MenuItemPage


class Test1 : BaseTest() {

    @Test
    @DisplayName("После открытия сайта - в строке поиска отображен ожидаемый url")
    fun urlShouldBeEqualToExpectedUrl() {
        step("Проверка URL в поисковой строке") {
            webdriver().shouldHave(url(ConfigManager.prop.getProperty("URL")))
        }
    }

    @Test
    @DisplayName("После нажатия на кнопку 'Add Element' - добавляется кнопка 'Delete'")
    fun testNoName() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openAddRemoveElementsItem()
            .addElement()
            .deleteButton.isDisplayed
    }
}