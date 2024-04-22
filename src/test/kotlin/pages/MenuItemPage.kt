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
    private val checkboxesItem: SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/checkboxes']"))
    private val disappearingElementsItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/disappearing_elements']"))
    private val dragAndDropItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/drag_and_drop']"))

    private val dropdownItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/dropdown']"))

    private val dynamicContentItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/dynamic_content']"))

    private val dynamicControlsItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/dynamic_controls']"))

    private val dynamicLoadingItem : SelenideElement
        get() = `$`(Selectors.byXpath("//a[@href='/dynamic_loading']"))

    private val entryAdItem = `$`(Selectors.byXpath("//a[@href='/entry_ad']"))

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

    @Step("Open: страница checkboxes")
    fun openCheckboxesPage(): CheckboxesPage {
        checkboxesItem.click()
        return CheckboxesPage()
    }

    @Step("Open: страница Disappearing Elements")
    fun openDisappearingElementsPage(): DisappearingElementsPage {
        disappearingElementsItem.click()
        return DisappearingElementsPage()
    }

    @Step("Open: страница Drag and Drop")
    fun openDragAndDropPage(): DragAndDropPage {
        dragAndDropItem.click()
        return DragAndDropPage()
    }

    @Step("Open: страница Dropdown")
    fun openDropdownPage(): DropdownPage {
        dropdownItem.click()
        return DropdownPage()
    }

    @Step("Open: страница Dynamic Content")
    fun openDynamicContentPage(): DynamicContentPage {
        dynamicContentItem.click()
        return DynamicContentPage()
    }

    @Step("Open: страница Dynamic Controls")
    fun openDynamicControlsPage(): DynamicControlsPage {
        dynamicControlsItem.click()
        return DynamicControlsPage()
    }

    @Step("Open: страница Dynamic Loading")
    fun openDynamicLoadingPage(): DynamicLoadingPage {
        dynamicLoadingItem.click()
        return DynamicLoadingPage()
    }

    @Step("Open: страница Dynamic Loading")
    fun openEntryAdPage(): EntryAdPage {
        entryAdItem.click()
        return EntryAdPage()
    }
}