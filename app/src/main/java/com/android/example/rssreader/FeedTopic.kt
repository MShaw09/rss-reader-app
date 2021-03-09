/**
 * DO NOT CHANGE
 */

package com.android.example.rssreader

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FeedTopic (
    val topic: String,
    val link: String
) : Parcelable