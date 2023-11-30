package com.andrii.movieapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @Json(name = "poster_path")
    val posterPath: String?,
    @PrimaryKey
    val id: Long?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val title: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    val overview: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    var watchedStatus: String = WatchedStatus.NOT_WATCHED.statusString
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
