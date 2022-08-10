package com.example.practicaltest.dataSource.network.model

import org.simpleframework.xml.*

@Root(name = "feed", strict = false)
data class TopSongsResponse @JvmOverloads constructor(
    @field: ElementList(inline = true)
    @param:ElementList(name = "item", inline = true, required = true)
    var songsList: List<Entry>? = null
)

@Root(name = "entry", strict = false)
class Entry {

    @field: Element(name = "title")
    var title: String = ""

    @field: ElementList(name = "link", inline = true, required = false)
    var link: List<Link>? = null

    @field: Element(name = "category", required = false)
    var category: Category? = null

    @field: Element(name = "artist", required = false)
    var artist: Artist? = null

    @field: Element(name = "price", required = false)
    var price: Price? = null

    @field: ElementList(name = "image", inline = true, required = false)
    var image: List<Image>? = null


}

@Root(name = "category", strict = false)
data class Category(

    @field: Attribute(name = "term")
    @param:Attribute(name = "term")
    var term: String = "",
)

@Root(name = "link", strict = false)
data class Link(
    @field: Attribute(name = "href")
    @param:Attribute(name = "href")
    var linkValue: String = "",

    @field: Attribute(name = "type")
    @param:Attribute(name = "type")
    var type: String = "",
)


@Root(name = "image", strict = false)
class Image {

    @field: Attribute(name = "height")
    var height: Int = 0

    @field:  Text(required = false)
    var value: String? = ""
}

@Root(name = "artist", strict = false)
class Artist {

    @field:  Text(required = false)
    var value: String? = ""
}

@Root(name = "price", strict = false)
class Price {

    @field:  Text(required = false)
    var value: String? = ""
}





