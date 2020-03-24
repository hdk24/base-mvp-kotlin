package com.hdk24.basemvp.presentation.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val context: Context = itemView.context

    abstract fun bind(data: T)
}
