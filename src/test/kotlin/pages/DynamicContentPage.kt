package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide

class DynamicContentPage {

    val images: ElementsCollection
        get() = Selenide.`$$`(Selectors.byXpath("//div[@id='content']/div[@class='row']/div[@class='large-2 columns']/img"))

    val textBlocks: ElementsCollection
        get() = Selenide.`$$`(Selectors.byXpath("//div[@id='content']/div[@class='row']/div[@class='large-10 columns']"))
}