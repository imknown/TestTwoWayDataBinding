package net.imknown.test.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    companion object {
        private const val DEFAULT_LOG_INDEX = 0
        private const val DEFAULT_LOG = "$DEFAULT_LOG_INDEX --Endless--"
    }

    private var index = DEFAULT_LOG_INDEX

    val textLiveData = MutableLiveData(DEFAULT_LOG)
    val myCheckedLiveData = MutableLiveData(false)

    fun modifyModel() {
        myCheckedLiveData.value?.let {
            myCheckedLiveData.value = !it
        }
    }

    fun fetchModel() {
        printLog("myCheckedLiveData value: ${myCheckedLiveData.value}")
    }

    fun emptyLog() {
        index = DEFAULT_LOG_INDEX
        textLiveData.value = DEFAULT_LOG
    }

    private fun printLog(msg: String) {
        textLiveData.value = """
            |${++index} $msg
            |${textLiveData.value}
            """.trimMargin()
    }
}