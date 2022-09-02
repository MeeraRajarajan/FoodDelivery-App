package com.emonics.fooddelivery.callback

import com.emonics.fooddelivery.ItemsViewModel
import com.emonics.fooddelivery.ViewModel.MenuItemsViewModel


interface OnClickHomeItemListner {
    fun onClickHomeItem(item: ItemsViewModel)
}