package by.chekun.presentation.activities.add

import android.widget.Spinner
import by.chekun.domain.AddAdvertisementViewModel

class SpinnerHolder(private val viewModel: AddAdvertisementViewModel?,
                    private val addActivitySpinners: MutableMap<String, Spinner>) {

    fun initSpinners(context: AddAdvertisementActivity) {
//        initBrandSpinner(context)
//        initEquipment()
//        initChassis()
//        initInterior(context)

    }


//    private fun initBrandSpinner(context: AddAdvertisementActivity) {
//        val call = viewModel?.getBrands()
//
//        call?.enqueue(object : Callback<BrandResponse> {
//
//            override fun onResponse(call: Call<BrandResponse>, response: Response<BrandResponse>) {
//
//                val responseList = response.body()?.brands
//                val newList: MutableList<BrandDto> = ArrayList()
//
//                newList.add(BrandDto(""))
//                if (responseList != null) {
//                    newList.addAll(responseList)
//                }
//
//                val adapter = BrandArrayAdapter(context,
//                        R.layout.simple_spinner_dropdown_item,
//                        newList.toTypedArray()
//                )
//
//                val modelSpinnerHolder = ModelSpinnerHolder(context, addActivitySpinners)
//                BrandSpinnerListener(adapter, addActivitySpinners[BRAND_SPINNER_KEY]!!, modelSpinnerHolder)
//            }
//
//            override fun onFailure(call: Call<BrandResponse>, t: Throwable) {
//
//            }
//        })
//    }


}