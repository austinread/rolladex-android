package io.github.austinread.rolladex.utils

import androidx.databinding.InverseMethod
import java.lang.Math.abs

class BindingUtils {
    companion object{
        private fun getAbilityModifier(score: Int) :Int{
            return score/2 - 5
        }

        @JvmStatic
        fun getAbilityModifierString(score: Int) :String{
            val modifier = getAbilityModifier(score)

            return (if (modifier < 0) "$modifier" else "+$modifier")
        }

        @JvmStatic
        @InverseMethod("stringToInt")
        fun intToString(value: Int) : String{
            return value.toString()
        }

        @JvmStatic
        fun stringToInt(value: String) : Int{
            return Integer.parseInt(value)
        }
    }
}