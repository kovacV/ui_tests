package tests

import BaseTest
import actions.dragAndDropTo
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import com.codeborne.selenide.logevents.SelenideLogger.step
import config.ConfigManager
import io.qameta.allure.Allure
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.MenuItemPage

class Tests : BaseTest() {

    @Test
    @DisplayName("После открытия сайта - в строке поиска отображен ожидаемый url")
    fun urlShouldBeEqualToExpectedUrl() {
        step("Проверка URL в поисковой строке") {
            webdriver().shouldHave(url(ConfigManager.prop.getProperty("URL")))
        }
    }

    @Test
    @DisplayName("После нажатия на кнопку 'Add Element' - добавляется кнопка 'Delete'")
    fun deleteButtonShouldBeDisplayedAfterClickAddButton() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openAddRemoveElementsItem()
            .addElement()
            .deleteButton.isDisplayed
    }

    @Test
    @DisplayName("Пользователь должен быть авторизованным после ввода валидных кредов")
    fun userShouldBeAuthorizedAfterLogInWithValidCredentials() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openBasicAuthItem()
            .authorize("admin", "admin")
            .authorizationIsSuccessful()
    }

    @Test
    @DisplayName("При вводе невалидных кредов - отображается пустая страница")
    fun userShouldNotBeAuthorizedAfterLogInWithInvalidCredentials() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openBasicAuthItem()
            .authorize("invalid", "invalid")
            .authorizationIsFailed()
    }

    @Test
    @DisplayName("При вызове контекстного меню на box - вызывается алерт с текстом 'You selected a context menu'")
    fun contextMenuOnBoxShouldOpenAlertWithText() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openContextMenuPage()
            .callContextMenu()
            .verifyContextMenuIsOpen()
    }

    @Test
    @DisplayName("При нажатии на кнопку с меняющимся ID - должно измениться числовое значение в блоке canvas")
    fun canvasValueShouldBeChangedAfterClickButton() {
        val challengingDomPage = MenuItemPage().openChallengingDomPage()

        val oldValue = challengingDomPage.getAnswerValue()
        challengingDomPage.blueButton.click()
        val newValue = challengingDomPage.getAnswerValue()

        Allure.step("Циферное значение поля Answer изменено").run { assertNotEquals(oldValue, newValue) }
    }

    @Test
    @DisplayName("Элементы на странице должны быть image")
    fun imagesShouldBeNotBroken() {
        val brokenImagesPage = MenuItemPage().openBrokenImagesPage()

        val images = brokenImagesPage.images


        for (image in images) {
            assertTrue(image.isImage, "image is broken")
        }
    }

    @Test
    @DisplayName("После нажатия на чекбокс - чекбокс должен быть выделен")
    fun afterClickOnCheckBoxItShouldBeSelected() {
        val checkboxesPage = MenuItemPage().openCheckboxesPage()

        val checkBox = checkboxesPage
            .getCheckBox(1)
            .click()
        assertTrue(checkBox.isSelected)
    }

    @Test
    @DisplayName("Должен быть осуществлен переход по исчезающей кнопке на страницау /gallery")
    fun galleryPageShouldBeOpenByClickingOnGalleryButton() {
        MenuItemPage()
            .openDisappearingElementsPage()
            .openGallery()

        Allure.step("Страница /gallery открыта").run {
            webdriver().shouldHave(url(ConfigManager.prop.getProperty("URL") + "/gallery/"))
        }
    }

    @Test
    @DisplayName("Псоле drag n drop хедер элемента \"а\" должен быть равен \"B\"")
    fun theElementsShouldBeSwapped() {
        val dragAndDropPage = MenuItemPage().openDragAndDropPage()
        dragAndDropPage.a.dragAndDropTo(dragAndDropPage.b)

        Allure.step("Хедер элмента а = \"B\"").run {
            assertEquals(dragAndDropPage.getHeader("a").text(), "B")
        }
    }

    @Test
    @DisplayName("Во всплывающем меню должен быть отображен выбранный пункт")
    fun choseMenuItemShouldBeDisplayed() {
        val dropdownPage = MenuItemPage().openDropdownPage()
        val menuItem = "Option 1"

        dropdownPage
            .openDropDownMenu()
            .chooseMenuItem(menuItem)
            .verifyMenuItemIsSelected(menuItem)
    }

    @Test
    @DisplayName("images и текст должны отличаться до и после обновления страницы")
    fun imagesAndTextsShouldBeDifferentBeforeAndAfterReloadPage() {
        val dynamicContentPage = MenuItemPage().openDynamicContentPage()

        val imagesBefore = dynamicContentPage.images.attributes("src")
        val textBlocksBefore = dynamicContentPage.textBlocks.texts()

        Selenide.refresh()

        val imagesAfter = dynamicContentPage.images.attributes("src")
        val textBlocksAfter = dynamicContentPage.textBlocks.texts()

        Allure.step("Текстовые блоки и images до обновления страницы полностью отличаются от текстовых блоков и images " +
                "после обновления страницы").run {
            assertAll("",
                {
                    assertNotEquals(
                        imagesAfter, imagesBefore, "images должны отличаться"
                    )
                },
                {
                    assertTrue(
                        textBlocksAfter.intersect(textBlocksBefore.toSet()).isEmpty(),
                        "Текстовые блоки должны отличаться"
                    )
                })
        }
    }

    @Test
    @DisplayName("Во всплывающем меню должен быть отображен выбранный пункт")
    fun testNhJIW() {
        val dropdownPage = MenuItemPage().openDynamicControlsPage()



        //dropdownPage.remove.click()
    dropdownPage.checkbox.elem
    }

    /*
        public static Condition css(final String propName, final String propValue) {
        return new Condition("css") {
            @Override
            public boolean apply(WebElement element) {
                return propValue.equalsIgnoreCase(element.getCssValue(propName));
            }

            @Override
            public String actualValue(WebElement element) {
                return element.getCssValue(propName);
            }
        };
    }
     */
}