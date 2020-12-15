package com.spreaker.kmm.androidApp.di


object InjectionCenter {
    var container: MutableMap<String, Any> = mutableMapOf()

    fun <T: Any> inject(key: Class<out T>): T {
        val content = container[key.name] as T?
        if (content != null) {
            return content
        }
        throw IllegalArgumentException("Instance of class $key is missing.")
    }

    fun <T: Any> put(content: T) {
        put(content, content::class.java)
    }

    fun <T: Any> put(content: T, key: Class<out T>) {
        container[key.name] = content
    }
}