package com.emonics.fooddelivery.ViewModel

import android.os.Parcel
import android.os.Parcelable


data class CartViewModel(val text: String?, val Price:Double) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeDouble(Price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartViewModel> {
        override fun createFromParcel(parcel: Parcel): CartViewModel {
            return CartViewModel(parcel)
        }

        override fun newArray(size: Int): Array<CartViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
