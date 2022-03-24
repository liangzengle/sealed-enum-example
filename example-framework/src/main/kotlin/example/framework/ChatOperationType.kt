package example.framework

import com.livefront.sealedenum.GenSealedEnum
import util.SealedEnumUtil

sealed class ChatOperationType(idx: Int) : OperationType(FrameworkModuleId.Chat, idx) {
    @GenSealedEnum
    companion object


    object ChatItemConsume: ChatOperationType(1)
}

fun main() {
    for (sealedEnum in SealedEnumUtil.getSealedEnums(OperationType::class.java)) {
        println(sealedEnum)
    }
}