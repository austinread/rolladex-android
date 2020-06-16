package io.github.austinread.rolladex.utils

class BindingUtils {
    companion object{
        private fun getAbilityModifier(score: Int) :Int{
            return score/2 - 5
        }

        @JvmStatic
        fun getAbilityModifierString(score: Int) :String{
            val modifier = getAbilityModifier(score)

            return if (modifier < 0)
                "-$modifier"
            else{
                "+$modifier"
            }
        }
    }
}