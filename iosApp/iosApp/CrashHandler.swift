//
//  CrashHandler.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 01/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import shared

class CrashlyticsCrashHandler: CrashkiosCrashHandler {
    override func crashParts(
        addresses: [KotlinLong],
        exceptionType: String,
        message: String) {
        
        //TODO Crashlytics
//        let exceptionModel = ExceptionModel(name: exceptionType, reason: message)
//        exceptionModel.stackTrace = addresses.map {
//            StackFrame(address: UInt(truncating: $0))
//        }
//        Crashlytics.crashlytics().record(exceptionModel: exceptionModel)
    }
}
