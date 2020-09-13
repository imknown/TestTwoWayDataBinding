package net.imknown.test.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.imknown.test.Event
import net.imknown.test.Wrapper

class MainViewModel : ViewModel() {
    companion object {
        private const val DEFAULT_LOG = ""
        private const val DEFAULT_LOG_INDEX = 0
    }

    private var index = DEFAULT_LOG_INDEX

    val textLiveData = MutableLiveData(DEFAULT_LOG)
    val myCheckedLiveData = MutableLiveData(Event(Wrapper(false)))

    fun modifyModel() {
        myCheckedLiveData.value?.let {
            // Hacky: use wrapper to avoid unnecessary callback of observer
            val wrapper = it.peekContent()
            wrapper.any = !wrapper.any
            myCheckedLiveData.value = it
        }
    }

    fun emptyLog() {
        index = DEFAULT_LOG_INDEX
        textLiveData.value = DEFAULT_LOG
    }

    fun print(msg: String) {
        textLiveData.value = """
            |${++index} $msg
            |${textLiveData.value}
            """.trimMargin()
    }
}