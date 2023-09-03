package com.example.oshikita

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val fullname: String = "Nama lengkap",
    val nicknames: Array<String> = arrayOf("nickname"),
    val fanbase: String = "Fanbase",
    val generation: Int = 0,
    val jiko: String = "Jiko",
    val description: String = "Deskripsi",
    val photo: Int = -1
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (fullname != other.fullname) return false
        if (!nicknames.contentEquals(other.nicknames)) return false
        if (fanbase != other.fanbase) return false
        if (generation != other.generation) return false
        if (jiko != other.jiko) return false
        if (description != other.description) return false
        if (photo != other.photo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fullname.hashCode()
        result = 31 * result + nicknames.contentHashCode()
        result = 31 * result + fanbase.hashCode()
        result = 31 * result + generation
        result = 31 * result + jiko.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + photo
        return result
    }
}
