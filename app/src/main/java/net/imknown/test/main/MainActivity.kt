package net.imknown.test.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.imknown.test.EventObserver
import net.imknown.test.R
import net.imknown.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.mainActivity = this
        // binding.setVariable(BR.mainActivity, this)
        binding.activityMainViewModel = mainViewModel
        // binding.setVariable(BR.activityMainViewModel, mainViewModel)

        mainViewModel.myCheckedLiveData.observe(this, EventObserver {
            mainViewModel.print("myCheckedLiveData value: ${it.any}")
        })
    }

    fun modifyView() {
        binding.mainInclude.myLinearLayout.binding.checkBox.toggle()
    }
}