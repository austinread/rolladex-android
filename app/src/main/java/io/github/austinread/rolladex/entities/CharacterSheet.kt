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
    @ColumnInfo(name = "class") var CharacterClass: String
) : Parcelable