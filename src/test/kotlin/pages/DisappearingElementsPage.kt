package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import pageElements.Button

class DisappearingElementsPage {

    val home: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//ul/li/a[text()='Home']")), "Home")

    val about: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//ul/li/a[text()='About']")), "About")

    val contactUs: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//ul/li/a[text()='Contact Us']")), "Contact Us")

    val portfolio: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//ul/li/a[text()='Portfolio']")), "Portfolio")

    val gallery: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//ul/li/a[text()='Gallery']")), "Gallery")

    fun openHome(): MenuItemPage {
        home.click()
        return MenuItemPage()
    }

    fun openGallery() {
        while (gallery.elem.isDisplayed.not()) {
            Selenide.refresh()
        }
        gallery.click()
    }
}