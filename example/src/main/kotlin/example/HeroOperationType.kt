package example

import com.livefront.sealedenum.GenSealedEnum
import example.framework.OperationType


sealed class HeroOperationType(idx: Int) : OperationType(GameModuleId.Hero, idx) {

    @GenSealedEnum
    companion object

    object OP1 : HeroOperationType(1)
    object OP2 : HeroOperationType(2)
}