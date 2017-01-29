package com.reodeveloper.marvelheroes.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(layoutRes: Int, attachToRoot : Boolean = false) : View {
  return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(imageUrl: String){
  Picasso.with(context).load(imageUrl).into(this)
}