package com.example.foodorder.utilise

import android.content.Context
import android.widget.Toast

object Common {
    fun mackToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun String.makeMeSimple(): String{
        var result = ""
        this.split(" ").take(3).forEach {
            result += "$it "
        }
        return result
    }
}