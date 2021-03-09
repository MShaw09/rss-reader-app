/**
 * DO NOT CHANGE
 */

package com.android.example.rssreader.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "rss", strict = false)
class RSSWrapper @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: Channel? = null
)