package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step

class MenuItemPage {
    private val addRemoveElementsItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/add_remove_elements/']"))
    private val basicAuthItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/basic_auth']"))
    private val contextMenuItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/context_menu']"))
    private val challengingDomMenuItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/challenging_dom']"))

    private val brokenImagesMenuItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/broken_images']"))

    @Step("Open: страница Add/Remove Elements")
    fun openAddRemoveElementsItem() : AddRemoveElementsPage {
        addRemoveElementsItem.click()
        return AddRemoveElementsPage()
    }

    @Step("Open: страница Basic Auth")
    fun openBasicAuthItem() : BasicAuthPage {
        basicAuthItem.click()
        return BasicAuthPage()
    }

    @Step("Open: страница Context Menu")
    fun openContextMenuPage() : ContextMenuPage {
        contextMenuItem.click()
        return ContextMenuPage()
    }

    @Step("Open: страница Challenging DOM")
    fun openChallengingDomPage(): ChallengingDomPage {
        challengingDomMenuItem.click()
        return ChallengingDomPage()
    }

    @Step("Open: страница Broken Images")
    fun openBrokenImagesPage(): BrokenImagesPage {
        brokenImagesMenuItem.click()
        return BrokenImagesPage()
    }
}