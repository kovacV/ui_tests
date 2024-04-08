package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.Assertions

class DropdownPage {

    val dropDown: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//select"))

    fun getMenuItem(menuItem: String): SelenideElement {
        return Selenide.`$`(Selectors.byXpath("//select/option[text()='$menuItem']"))
    }

    fun verifyMenuItemIsSelected(menuItem: String) {
        val menuItemIsSelected = getMenuItem(menuItem).attr("selected").toBoolean()
        Assertions.assertTrue(menuItemIsSelected, "Отображен выбранный пункт всплываюзего меню")
    }

    fun openDropDownMenu(): DropdownPage {
        dropDown.click()
        return this
    }

    fun chooseMenuItem(menuItem: String): DropdownPage {
        getMenuItem(menuItem).click()
        return this
    }
}