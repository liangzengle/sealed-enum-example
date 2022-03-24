package util

import com.livefront.sealedenum.SealedEnum
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

object SealedEnumUtil {

    private val classScanner = ClassScanner(emptyList(), emptyList())

    private val sealedEnums by lazy { classScanner.getInstantiatableSubclasses(SealedEnum::class.java) }

    private val sealedIdEnumExtMap = ConcurrentHashMap<Class<*>, SealedIdEnumExt<*>>()
    private val sealedEnumExtMap = ConcurrentHashMap<Class<*>, SealedEnumExt<*>>()

    fun <T> getSealedEnums(type: Class<T>): Set<SealedEnum<out T>> {
        return sealedEnums.filter {
            val genericSuperClass = it.genericInterfaces[0] as ParameterizedType
            val typeArg = genericSuperClass.actualTypeArguments[0]
            val rawType = if (typeArg is Class<*>) typeArg else (typeArg as ParameterizedType).rawType as Class<*>
            type.isAssignableFrom(rawType)
        }.map { it.kotlin.objectInstance!! as SealedEnum<out T> }.toSet()
    }

    fun <T : IdEnum> getSealedIdEnumExt(type: Class<T>): SealedIdEnumExt<T> {
        val ext = sealedEnumExtMap[type]
        if (ext != null) {
            return ext as SealedIdEnumExt<T>
        }
        return sealedIdEnumExtMap.computeIfAbsent(type) {
            SealedIdEnumExt(getSealedEnums(type))
        } as SealedIdEnumExt<T>
    }

    fun <T> getSealedEnumExt(type: Class<T>): SealedEnumExt<T> {
        val ext = sealedEnumExtMap[type]
        if (ext != null) {
            return ext as SealedEnumExt<T>
        }
        return sealedEnumExtMap.computeIfAbsent(type) {
            SealedEnumExt(getSealedEnums(type))
        } as SealedEnumExt<T>
    }
}