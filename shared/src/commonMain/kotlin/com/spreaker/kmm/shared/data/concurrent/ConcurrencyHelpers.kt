package com.spreaker.kmm.shared.data.concurrent

expect fun <T> T.freeze(): T

expect val <T> T.isFrozen: Boolean

expect fun Any.ensureNeverFrozen()

expect fun Any.ensureMainScope()