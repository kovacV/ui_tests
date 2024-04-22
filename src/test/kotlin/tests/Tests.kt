package tests

import BaseTest
import actions.dragAndDropTo
import com.codeborne.selenide.*
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import com.codeborne.selenide.logevents.SelenideLogger.step
import config.ConfigManager
import io.qameta.allure.Allure
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import pages.DynamicLoadingPage
import pages.DynamicallyLoadedPageElementsPage
import pages.MenuItemPage
import java.time.Duration.of
import java.time.Duration.ofSeconds

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

        Allure.step(
            "Текстовые блоки и images до обновления страницы полностью отличаются от текстовых блоков и images " +
                    "после обновления страницы"
        ).run {
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
    @DisplayName("Элементы должны появляться и исчезать по нажатию на button")
    fun elementsShouldBecomeEnabledOrDisabledByClickingOnButton() {
        val dynamicControlsPage = MenuItemPage().openDynamicControlsPage()

        dynamicControlsPage.remove.click()
        Allure.step(
            "Элемент ${dynamicControlsPage.checkbox.name} должен исчезнуть после нажатия на кнопку ${dynamicControlsPage.remove.name}"
        ).run {
            dynamicControlsPage.checkbox.elem.shouldBe(visible.negate(), ofSeconds(4))
        }

        dynamicControlsPage.add.click()
        Allure.step(
            "Элемент ${dynamicControlsPage.checkbox.name} должен появиться после нажатия на кнопку ${dynamicControlsPage.add.name}"
        ).run {
            dynamicControlsPage.checkbox.elem.shouldBe(visible, ofSeconds(4))
        }

        dynamicControlsPage.enable.click()
        Allure.step(
            "Элемент ${dynamicControlsPage.input.name} должен стать enabled после нажатия на кнопку ${dynamicControlsPage.enable.name}"
        ).run {
            dynamicControlsPage.input.elem.shouldBe(enabled, ofSeconds(4))
        }

        dynamicControlsPage.disable.click()
        Allure.step(
            "Элемент ${dynamicControlsPage.input.name} должен стать disabled после нажатия на кнопку ${dynamicControlsPage.disable.name}"
        ).run {
            dynamicControlsPage.input.elem.shouldBe(enabled.negate(), ofSeconds(4))
        }
    }

    @Test
    @DisplayName("Проверка, что элемент на странице не отображен, но есть в DOM")
    fun elementShouldNotVisibleButShouldBeInADom() {
        val dynamicLoadingPage = MenuItemPage().openDynamicLoadingPage()

        var hiddenText = dynamicLoadingPage
            .openExample1Page()
            .pressStart()
            .hiddenText

        hiddenText.shouldBe(exist, ofSeconds(0))
        hiddenText.shouldBe(visible, ofSeconds(6))
    }

    @Test
    @DisplayName("Проверка, что элемент на странице не отображен и отсуствует в DOM")
    fun elementShouldNotVisibleButShouldNotBeInADom() {
        val dynamicLoadingPage = MenuItemPage().openDynamicLoadingPage()

        var hiddenText = dynamicLoadingPage
            .openExample2Page()
            .pressStart()
            .hiddenText

        hiddenText.shouldBe(exist.negate(), ofSeconds(0))
        hiddenText.shouldBe(visible, ofSeconds(6))
    }

    @Test
    @DisplayName("Модальное окно должно быть закрыто и не препядствует дальнейшим действиям с элементами")
    fun modalWindowShouldBeClosedAndNotPreventFurtherActionsWithElements() {
        val entryAdPage = MenuItemPage()
            .openEntryAdPage()

        entryAdPage.modalWindow.shouldBe(visible)
        entryAdPage.closeModalWindow()
        entryAdPage.modalWindow.shouldBe(visible.negate())
        entryAdPage.clickHereHref.click()
        entryAdPage.modalWindow.shouldBe(visible)
        Thread.sleep(2000)
    }
}