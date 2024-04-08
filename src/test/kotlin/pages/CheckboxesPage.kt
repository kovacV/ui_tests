package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import pageElements.Button

class CheckboxesPage {

    fun getCheckBox(i: Int): Button {
        return Button(Selenide.`$`(Selectors.byXpath("//form[@id='checkboxes']//input[@type='checkbox'][$i]")),"checkBox $i")
    }
}