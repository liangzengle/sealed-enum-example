package util

import com.livefront.sealedenum.SealedEnum
import org.eclipse.collections.api.map.primitive.IntIntMap
import org.eclipse.collections.api.map.primitive.ObjectIntMap
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap
import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMap

class SealedIdEnumExt<T : IdEnum> internal constructor(enumSet: Set<SealedEnum<out T>>) {
    private val enumArray: Array<IdEnum>
    private val idToIndexMap: IntIntMap
    private val nameToIndexMap: ObjectIntMap<String>

    init {
        val enumList = arrayListOf<T>()
        val idMap = IntIntHashMap()
        val nameMap = ObjectIntHashMap<String>()
        var index = 0
        for (o in enumSet) {
            val sealedEnum = o as SealedEnum<T>
            for (enum in sealedEnum.values) {
                val id = enum.id()
                val name = sealedEnum.nameOf(enum)
                enumList.add(enum)
                if (idMap.containsKey(id)) {
                    throw IllegalArgumentException("Duplicate id: $id")
                }
                if (nameMap.containsKey(name)) {
                    throw IllegalArgumentException("Duplicate name: $name")
                }
                idMap.put(id, index)
                nameMap.put(name, index)
                index++
            }
        }
        enumArray = enumList.toTypedArray()
        idToIndexMap = idMap
        nameToIndexMap = nameMap
    }

    fun getById(id: Int): T? {
        val idx = idToIndexMap.getIfAbsent(id, -1)
        if (idx == -1) return null
        return enumArray[idx] as T
    }

    fun getByName(name: String): T? {
        val idx = nameToIndexMap.getIfAbsent(name, -1)
        if (idx == -1) return null
        return enumArray[idx] as T
    }

    fun getOrdinal(enum: T): Int {
        val ordinal = idToIndexMap.getIfAbsent(enum.id(), -1)
        if (ordinal == -1) throw IllegalArgumentException("Unknown enum: $enum")
        return ordinal
    }
}