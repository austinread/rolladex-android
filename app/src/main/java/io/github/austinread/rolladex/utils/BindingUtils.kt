package io.github.austinread.rolladex.utils

import androidx.databinding.InverseMethod

class BindingUtils {
    companion object{
        private fun getAbilityModifier(score: Int) :Int{
            return score/2 - 5
        }

        private fun getSkillWithProficiency(abilityScore: Int, proficient: Boolean, proficiency: Int) :Int{
            return getAbilityModifier(abilityScore) + (if (proficient) proficiency else 0)
        }

        @JvmStatic
        fun getAbilityModifierString(score: Int) :String{
            val modifier = getAbilityModifier(score)

            return (if (modifier < 0) "$modifier" else "+$modifier")
        }

        @JvmStatic
        fun getSkillWithProficiencyStr(abilityScore: Int, proficient: Boolean, proficiency: Int) :String{
            val modifier = getSkillWithProficiency(abilityScore, proficient, proficiency)
            return modifier.toString()
        }

        @JvmStatic
        fun getPassiveStr(abilityScore: Int, proficient: Boolean, proficiency: Int) :String{
            val modifier = 10 + getSkillWithProficiency(abilityScore, proficient, proficiency)
            return modifier.toString()
        }

        @JvmStatic
        @InverseMethod("stringToInt")
        fun intToString(value: Int) : String{
            return value.toString()
        }

        @JvmStatic
        fun stringToInt(value: String) : Int{
            return (if (value.isEmpty()) 0 else Integer.parseInt(value))
        }
    }
}