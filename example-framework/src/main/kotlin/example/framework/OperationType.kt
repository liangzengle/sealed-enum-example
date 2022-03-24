package example.framework

import util.IdEnum

abstract class OperationType(val moduleId: Int, val idx: Int) : IdEnum {
    val id: Int = moduleId * 1000 + idx

    override fun id(): Int {
        return id
    }
}