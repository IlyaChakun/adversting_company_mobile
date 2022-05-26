package by.chekun.repository.database.entity.brand

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class BrandResponse {

    @SerializedName("brands")
    @Expose
    val brands: List<BrandDto>? = null

}