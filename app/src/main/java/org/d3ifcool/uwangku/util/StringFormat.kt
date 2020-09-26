package org.d3ifcool.uwangku.util

import java.text.DecimalFormat

object StringFormat{
    fun intToCurrency(valUnformat : Int) : String{
        return DecimalFormat("#,###").format(valUnformat)
    }
}