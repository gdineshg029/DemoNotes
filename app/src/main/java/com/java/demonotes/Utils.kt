package com.java.demonotes

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun Context.showMessage(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

