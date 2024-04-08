package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import pageElements.Button

class DynamicControlsPage {

    val remove: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Remove']")), "Remove")

    val checkbox: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//input[@type='checkbox']")), "Checkbox")
}
