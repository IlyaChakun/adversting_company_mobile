package by.chekun.repository.database.entity

import com.google.gson.annotations.SerializedName

open class AbstractDto(

        @SerializedName("id")
        var id: Long = -1
)