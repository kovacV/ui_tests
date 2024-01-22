package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement

class AddRemoveElementsPage {
    private val addElementButton: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//div/button[@onclick='addElement()']"))

    val deleteButton: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//button[contains(@class, 'added-manually')][text()='Delete']"))

    fun addElement(): AddRemoveElementsPage {
        addElementButton.click()
        return AddRemoveElementsPage()
    }
}