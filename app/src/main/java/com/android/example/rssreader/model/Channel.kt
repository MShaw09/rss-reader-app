/**
 * DO NOT CHANGE
 */

package com.android.example.rssreader.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import retrofit2.http.Path

@Root(name = "channel", strict = false)
class Channel @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @field: Element(name = "description")
    var description: String = "",
    @field: ElementList(name = "item", inline = true)
    var items: MutableList<Item>? = null
)