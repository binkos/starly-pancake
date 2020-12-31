package com.binkos.starlypancacke.app.ui.menu

import android.os.Bundle
import androidx.core.os.bundleOf
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.onClick
import com.binkos.starlypancacke.app.di.createOrganizationModule
import com.binkos.starlypancacke.app.di.foodModule
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.organization.CreateOrganizationFragment
import com.binkos.starlypancacke.domain.model.Food
import kotlinx.android.synthetic.main.fragment_food.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FoodFragment : BaseFragment() {

    val vm: FoodViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(foodModule)
    }

    override fun viewReady() {
        createFoodToolbar.setNavigationOnClickListener {
            vm.exit()
        }

        createFoodButton.onClick {
            vm.createFood(
                createFoodNameInputEditText.text.toString(),
                createFoodNDescription.text.toString(),
                createFoodPhotoIconInputEditText.text.toString(),
                createFoodPriceInputEditText.text.toString(),
                createFoodCaloriesInputEditText.text.toString()
            )
            vm.exit()
        }
    }

    override fun getLayout() = R.layout.fragment_food

    override fun onBackPressed() {
        vm.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(foodModule)
    }

    companion object {
        private const val FOOD_KEY = "FODD_KEY"

        fun getInstance(food: Food): CreateOrganizationFragment {
            return CreateOrganizationFragment().apply {
                arguments = bundleOf(Pair(FOOD_KEY, food))
            }
        }
    }
}