package util

import com.livefront.sealedenum.SealedEnum
import org.eclipse.collections.api.map.primitive.IntIntMap
import org.eclipse.collections.api.map.primitive.ObjectIntMap
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap
import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMap

class SealedEnumExt<T> internal constructor(enumSet: Set<SealedEnum<out T>>) {
    private val enumArray: Array<Any>
    private val nameToIndexMap: ObjectIntMap<String>

    init {
        val enumList = arrayListOf<T>()
        val nameMap = ObjectIntHashMap<String>()
        var index = 0
        for (o in enumSet) {
            val sealedEnum = o as SealedEnum<T>
            for (enum in sealedEnum.values) {
                val name = sealedEnum.nameOf(enum)
                enumList.add(enum)
                if (nameMap.containsKey(name)) {
                    throw IllegalArgumentException("Duplicate name: $name")
                }
                nameMap.put(name, index)
                index++
            }
        }
        enumArray = enumList.toArray(emptyArray())
        nameToIndexMap = nameMap
    }

    fun getByName(name: String): T? {
        val idx = nameToIndexMap.getIfAbsent(name, -1)
        if (idx == -1) return null
        return enumArray[idx] as T
    }

    fun getOrdinalByName(name: String): Int {
        val ordinal = nameToIndexMap.getIfAbsent(name, -1)
        if (ordinal == -1) throw IllegalArgumentException("Unknown enum: $name")
        return ordinal
    }
}