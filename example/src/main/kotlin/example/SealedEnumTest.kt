package example

import example.framework.OperationType
import example.framework.PlayerTempDataType
import util.SealedEnumUtil

fun main() {
    val sealedEnumExt = SealedEnumUtil.getSealedIdEnumExt(OperationType::class.java)
    println(sealedEnumExt.getById(HeroOperationType.OP1.id))
    check(sealedEnumExt.getById(HeroOperationType.OP1.id) == HeroOperationType.OP1)

    val playerTempDataEnumExt = SealedEnumUtil.getSealedEnumExt(PlayerTempDataType::class.java)
    val playerTempDataType =playerTempDataEnumExt.getByName(PayPlayerTempDataType.IntData.name)
    println(playerTempDataType)
}