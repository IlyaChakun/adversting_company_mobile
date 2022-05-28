package by.chekun.presentation.activities.add

import android.R
import android.widget.Spinner
import by.chekun.domain.AddCarViewModel
import by.chekun.presentation.activities.add.brand.BrandArrayAdapter
import by.chekun.presentation.activities.add.brand.BrandSpinnerListener
import by.chekun.presentation.activities.add.brand.model.ModelSpinnerHolder
//import by.chekun.presentation.activities.add.chassis.EngineSpinnerHolder
//import by.chekun.presentation.activities.add.chassis.TransmissionSpinnerHolder
//import by.chekun.presentation.activities.add.chassis.WheelDriveSpinnerHolder
//import by.chekun.presentation.activities.add.equipment.ColorSpinnerHolder
//import by.chekun.presentation.activities.add.equipment.ConditionSpinnerHolder
//import by.chekun.presentation.activities.add.equipment.SafetiesMultipleSpinnerHolder
//import by.chekun.presentation.activities.add.interior.InteriorColorSpinnerHolder
//import by.chekun.presentation.activities.add.interior.InteriorMaterialSpinnerHolder
//import by.chekun.presentation.activities.add.interior.InteriorSpinnerHolder
import by.chekun.repository.database.entity.brand.BrandDto
import by.chekun.repository.database.entity.brand.BrandResponse
import by.chekun.repository.database.entity.car.chassis.ChassisComponent
import by.chekun.repository.database.entity.car.chassis.EngineTypeDto
import by.chekun.repository.database.entity.car.chassis.TransmissionTypeDto
import by.chekun.repository.database.entity.car.chassis.WheelDriveTypeDto
import by.chekun.repository.database.entity.car.equipment.ColorDto
import by.chekun.repository.database.entity.car.equipment.ConditionDto
import by.chekun.repository.database.entity.car.equipment.EquipmentComponent
import by.chekun.repository.database.entity.car.equipment.SafetyDto
import by.chekun.repository.database.entity.car.interior.InteriorColorDto
import by.chekun.repository.database.entity.car.interior.InteriorComponent
import by.chekun.repository.database.entity.car.interior.InteriorDto
import by.chekun.repository.database.entity.car.interior.InteriorMaterialDto
import by.chekun.utils.*
//import by.chekun.multispinner.MultiSpinnerSearch
//import by.chekun.multispinner.SingleSpinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpinnerHolder(private val viewModel: AddCarViewModel?,
                    private val addActivitySpinners: MutableMap<String, Spinner>) {

    fun initSpinners(context: AddCarActivity) {
        initBrandSpinner(context)
        initEquipment()
        initChassis()
        initInterior(context)

    }


    private fun initChassis() {
        val call = viewModel?.getChassis()

        call?.enqueue(object : Callback<ChassisComponent> {

            override fun onResponse(call: Call<ChassisComponent>, response: Response<ChassisComponent>) {

                val chassis = response.body()!!
                //////////////////////////////////////
                val engineList = chassis.engineTypes
                val newEngineList: MutableSet<EngineTypeDto> = HashSet()
                newEngineList.addAll(engineList)
//                val engineSpinnerHolder = EngineSpinnerHolder((addActivitySpinners[ENGINE_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                engineSpinnerHolder.initModelSpinnerHolderWithValues(newEngineList)
                //////////////////////////////////////
                val transmissionTypesList = chassis.transmissionTypes
                val newTransmissionTypesList: MutableSet<TransmissionTypeDto> = HashSet()
                newTransmissionTypesList.addAll(transmissionTypesList)
//                val transmissionTypeHolder = TransmissionSpinnerHolder((addActivitySpinners[TRANSMISSION_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                transmissionTypeHolder.initModelSpinnerHolderWithValues(newTransmissionTypesList)
                //////////////////////////////////////
                val wheelDriveTypes = chassis.wheelDriveTypes
                val newWheelDriveTypes: MutableSet<WheelDriveTypeDto> = HashSet()
                newWheelDriveTypes.addAll(wheelDriveTypes)
//                val wheelDriveSpinnerHolder = WheelDriveSpinnerHolder((addActivitySpinners[WHEEL_DRIVE_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                wheelDriveSpinnerHolder.initModelSpinnerHolderWithValues(newWheelDriveTypes)
                //////////////////////////////////////
            }


            override fun onFailure(call: Call<ChassisComponent>, t: Throwable) {

            }
        })
    }

    private fun initEquipment() {
        val call = viewModel?.getEquipment()

        call?.enqueue(object : Callback<EquipmentComponent> {

            override fun onResponse(call: Call<EquipmentComponent>, response: Response<EquipmentComponent>) {

                val equipment = response.body()!!
                //////////////////////////////////////
                val conditionList = equipment.conditions
                val newConditionList: MutableSet<ConditionDto> = HashSet()
                newConditionList.addAll(conditionList)
//                val conditionSpinnerHolder = ConditionSpinnerHolder((addActivitySpinners[CONDITION_SPINNER_KEY] as SingleSpinner?)!!)
//                conditionSpinnerHolder.initModelSpinnerHolderWithValues(newConditionList)
                //////////////////////////////////////
                val colorsList = equipment.colors
                val newColorList: MutableSet<ColorDto> = HashSet()
                newColorList.addAll(colorsList)
//                val colorSpinnerHolder = ColorSpinnerHolder((addActivitySpinners[COLOR_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                colorSpinnerHolder.initModelSpinnerHolderWithValues(newColorList)
                //////////////////////////////////////
                val safetiesList = equipment.safeties
                val newSafetiesList: MutableSet<SafetyDto> = HashSet()
                newSafetiesList.addAll(safetiesList)
               // SafetiesMultipleSpinnerHolder(addActivitySpinners[SAFETIES_MULTIPLE_SPINNER_KEY] as MultiSpinnerSearch).initModelSpinnerHolderWithValues(newSafetiesList)

            }


            override fun onFailure(call: Call<EquipmentComponent>, t: Throwable) {

            }
        })


    }

    private fun initInterior(context: AddCarActivity) {
        val call = viewModel?.getInterior()

        call?.enqueue(object : Callback<InteriorComponent> {

            override fun onResponse(call: Call<InteriorComponent>, response: Response<InteriorComponent>) {

                val equipment = response.body()!!
                //////////////////////////////////////
                val interiorColorsList = equipment.interiorColors
                val newInteriorColorsList: MutableSet<InteriorColorDto> = HashSet()
                newInteriorColorsList.addAll(interiorColorsList)
//                val interiorColorSpinnerHolder = InteriorColorSpinnerHolder((addActivitySpinners[INTERIOR_COLOR_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                interiorColorSpinnerHolder.initModelSpinnerHolderWithValues(newInteriorColorsList)
                //////////////////////////////////////
                val interiorMaterialsList = equipment.interiorMaterials
                val newInteriorMaterialList: MutableSet<InteriorMaterialDto> = HashSet()
                newInteriorMaterialList.addAll(interiorMaterialsList)
//                val interiorMaterialSpinnerHolder = InteriorMaterialSpinnerHolder((addActivitySpinners[INTERIOR_MATERIAL_TYPE_SPINNER_KEY] as SingleSpinner?)!!)
//                interiorMaterialSpinnerHolder.initModelSpinnerHolderWithValues(newInteriorMaterialList)
                //////////////////////////////////////
                val interiorList = equipment.interior
                val newInteriorList: MutableSet<InteriorDto> = HashSet()
                newInteriorList.addAll(interiorList)
//                val interiorSpinnerHolder = InteriorSpinnerHolder((addActivitySpinners[INTERIOR_MULTIPLE_SPINNER_KEY] as MultiSpinnerSearch?)!!)
//                interiorSpinnerHolder.initModelSpinnerHolderWithValues(newInteriorList)
            }


            override fun onFailure(call: Call<InteriorComponent>, t: Throwable) {

            }
        })


    }

    private fun initBrandSpinner(context: AddCarActivity) {
        val call = viewModel?.getBrands()

        call?.enqueue(object : Callback<BrandResponse> {

            override fun onResponse(call: Call<BrandResponse>, response: Response<BrandResponse>) {

                val responseList = response.body()?.brands
                val newList: MutableList<BrandDto> = ArrayList()

                newList.add(BrandDto(""))
                if (responseList != null) {
                    newList.addAll(responseList)
                }

                val adapter = BrandArrayAdapter(context,
                        R.layout.simple_spinner_dropdown_item,
                        newList.toTypedArray()
                )

                val modelSpinnerHolder = ModelSpinnerHolder(context, addActivitySpinners)
                BrandSpinnerListener(adapter, addActivitySpinners[BRAND_SPINNER_KEY]!!, modelSpinnerHolder)
            }

            override fun onFailure(call: Call<BrandResponse>, t: Throwable) {

            }
        })
    }


}