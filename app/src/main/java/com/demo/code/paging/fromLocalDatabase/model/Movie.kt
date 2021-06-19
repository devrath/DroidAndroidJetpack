package com.demo.code.paging.fromLocalDatabase.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val rating: Double,
    @SerializedName("release_date") val releaseDate: String,
    val ranking: Int)