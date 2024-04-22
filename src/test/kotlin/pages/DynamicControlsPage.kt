package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import pageElements.Button

class DynamicControlsPage {

    val remove: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Remove']")), "Remove")

    val add: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Add']")), "Add")

    val checkbox: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//input[@type='checkbox']")), "Checkbox")

    val enable: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Enable']")), "Enable")

    val disable: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Disable']")), "Disable")

    val input: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//input[@type='text']")), "Input")
}
