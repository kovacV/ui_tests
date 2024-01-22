package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement

class MenuItemPage {
    private val addRemoveElements: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/add_remove_elements/']"))

    fun openAddRemoveElementsItem() : AddRemoveElementsPage {
        addRemoveElements.click()
        return AddRemoveElementsPage()
    }
}