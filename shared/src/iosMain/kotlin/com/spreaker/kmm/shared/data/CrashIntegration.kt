package com.spreaker.kmm.shared.data

import co.touchlab.crashkios.CrashHandler
import co.touchlab.crashkios.setupCrashHandler


fun crashInit(handler: CrashHandler) {
    setupCrashHandler(handler)
}