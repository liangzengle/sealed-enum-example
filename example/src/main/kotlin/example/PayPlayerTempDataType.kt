package example

import com.livefront.sealedenum.GenSealedEnum
import example.framework.PlayerTempDataType

sealed class PayPlayerTempDataType<V> : PlayerTempDataType<V>() {

    @GenSealedEnum
    companion object

    object BooleanData : PayPlayerTempDataType<Boolean>() {
        override fun createDefaultValue(): Boolean {
            return false
        }
    }

    object IntData : PayPlayerTempDataType<Int>() {
        override fun createDefaultValue(): Int {
            return 1
        }
    }
}