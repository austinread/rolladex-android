package io.github.austinread.rolladex.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Constructor

@Entity(tableName = "character")
@Parcelize
data class CharacterSheet(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "name") var Name: String,
    @ColumnInfo(name = "level") var Level: Int,
    @ColumnInfo(name = "race") var Race: String,
    @ColumnInfo(name = "class") var CharacterClass: String,

    @ColumnInfo(name = "subclass") var SubClass: String = "",
    @ColumnInfo(name = "background") var Background: String = "",
    @ColumnInfo(name = "alignment") var Alignment: String = "True Neutral",
    @ColumnInfo(name = "xp") var XP: Int = 0,

    @ColumnInfo(name = "inspiration") var Inspiration: Boolean = false,
    @ColumnInfo(name = "proficiency") var Proficiency: Int = 2,
    @ColumnInfo(name = "strength") var Strength: Int = 10,
    @ColumnInfo(name = "dexterity") var Dexterity: Int = 10,
    @ColumnInfo(name = "constitution") var Constitution: Int = 10,
    @ColumnInfo(name = "intelligence") var Intelligence: Int = 10,
    @ColumnInfo(name = "wisdom") var Wisdom: Int = 10,
    @ColumnInfo(name = "charisma") var Charisma: Int = 10,

    @ColumnInfo(name = "proficientSavingStr") var ProficientSavingStr: Boolean = false,
    @ColumnInfo(name = "proficientSavingDex") var ProficientSavingDex: Boolean = false,
    @ColumnInfo(name = "proficientSavingCon") var ProficientSavingCon: Boolean = false,
    @ColumnInfo(name = "proficientSavingInt") var ProficientSavingInt: Boolean = false,
    @ColumnInfo(name = "proficientSavingWis") var ProficientSavingWis: Boolean = false,
    @ColumnInfo(name = "proficientSavingChar") var ProficientSavingChar: Boolean = false,

    @ColumnInfo(name = "proficientAcrobatics") var ProficientAcrobatics: Boolean = false,
    @ColumnInfo(name = "proficientAnimalHandling") var ProficientAnimalHandling: Boolean = false,
    @ColumnInfo(name = "proficientArcana") var ProficientArcana: Boolean = false,
    @ColumnInfo(name = "proficientAthletics") var ProficientAthletics: Boolean = false,
    @ColumnInfo(name = "proficientDeception") var ProficientDeception: Boolean = false,
    @ColumnInfo(name = "proficientHistory") var ProficientHistory: Boolean = false,
    @ColumnInfo(name = "proficientInsight") var ProficientInsight: Boolean = false,
    @ColumnInfo(name = "proficientIntimidation") var ProficientIntimidation: Boolean = false,
    @ColumnInfo(name = "proficientInvestigation") var ProficientInvestigation: Boolean = false,
    @ColumnInfo(name = "proficientMedicine") var ProficientMedicine: Boolean = false,
    @ColumnInfo(name = "proficientNature") var ProficientNature: Boolean = false,
    @ColumnInfo(name = "proficientPerception") var ProficientPerception: Boolean = false,
    @ColumnInfo(name = "proficientPerformance") var ProficientPerformance: Boolean = false,
    @ColumnInfo(name = "proficientPersuasion") var ProficientPersuasion: Boolean = false,
    @ColumnInfo(name = "proficientReligion") var ProficientReligion: Boolean = false,
    @ColumnInfo(name = "proficientSlightOfHand") var ProficientSlightOfHand: Boolean = false,
    @ColumnInfo(name = "proficientStealth") var ProficientStealth: Boolean = false,
    @ColumnInfo(name = "proficientSurvival") var ProficientSurvival: Boolean = false


) : Parcelable