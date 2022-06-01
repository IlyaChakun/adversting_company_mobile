package by.chekun.repository.database.entity.advertisement.view

enum class AdvertisementType {
    advertising_in_stores,
    TV_advertising,
    radio_advertising,
    outdoor_advertising,
    internet_advertising;

    companion object {

        fun fromOrbisString(type: String): AdvertisementType {
            return when (type) {
                "Реклама в магазине" -> advertising_in_stores
                "ТВ реклама" -> TV_advertising
                "Радио реклама" -> radio_advertising
                "Уличная реклама" -> outdoor_advertising
                "Интернет реклама" -> internet_advertising
                else -> advertising_in_stores
            }
        }

        fun fromStringToType(type: AdvertisementType): String {
            return when (type) {
                advertising_in_stores -> "Реклама в магазине"
                TV_advertising -> "ТВ реклама"
                radio_advertising -> "Радио реклама"
                outdoor_advertising -> "Уличная реклама"
                internet_advertising -> "Интернет реклама"
                else -> "Реклама в магазине"
            }
        }


    }
}


