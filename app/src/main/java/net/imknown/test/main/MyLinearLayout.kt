package net.imknown.test.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LifecycleOwner
import net.imknown.test.R
import net.imknown.test.databinding.ViewMyLinearLayoutBinding

class MyLinearLayout(mContext: Context, mAttributeSet: AttributeSet) :
    LinearLayout(mContext, mAttributeSet) {

    init {
        initBinding()
    }

    lateinit var binding: ViewMyLinearLayoutBinding

    private fun initBinding() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_my_linear_layout,
            this,
            true
        )

        binding.lifecycleOwner = context as LifecycleOwner
    }

    fun setMainViewModel(mainViewModel: MainViewModel) {
        binding.finalMainViewModel = mainViewModel
    }
}

@BindingAdapter("myChecked")
fun CheckBox.setMyChecked(myChecked: Boolean?) {
    myChecked?.let {
        isChecked = it
    }
}

@InverseBindingAdapter(attribute = "myChecked")
fun CheckBox.getMyChecked() = isChecked

@BindingAdapter("myCheckedAttrChanged")
fun CheckBox.listenMyChecked(listener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ ->
        listener.onChange()
    }
}