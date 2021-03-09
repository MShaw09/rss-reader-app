/**
 * DO NOT CHANGE
 */

package com.android.example.rssreader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @field: Element(name = "link")
    var link: String = "",
    @field: Element(name = "description")
    var description: String = "",
    @field: Element(name = "pubDate", required = false)
    var pubDate: String = ""
) : Parcelable