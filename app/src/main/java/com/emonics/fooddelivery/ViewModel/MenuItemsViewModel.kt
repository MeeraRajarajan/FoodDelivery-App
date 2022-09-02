package com.emonics.fooddelivery.ViewModel

import android.os.Parcel
import android.os.Parcelable

data class MenuItemsViewModel(val image: Int, val text: String?, val description: String?, val Price:Double, var Qty:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(text)
        parcel.writeString(description)
        parcel.writeDouble(Price)
        parcel.writeInt(Qty)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuItemsViewModel> {
        override fun createFromParcel(parcel: Parcel): MenuItemsViewModel {
            return MenuItemsViewModel(parcel)
        }

        override fun newArray(size: Int): Array<MenuItemsViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
