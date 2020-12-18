//
//  CoroutineHelpers.swift
//  iosAppTests
//
//  Created by Alessandro "Sandro" Calzavara on 18/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

@testable import iosApp
@testable import shared

class NeverCoroutineFlow: Kotlinx_coroutines_coreFlow {
    func collect(collector: Kotlinx_coroutines_coreFlowCollector, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        // Do not emit anything
        completionHandler(nil, nil)
    }
}

class EmptyCoroutineFlow: Kotlinx_coroutines_coreFlow {
    func collect(collector: Kotlinx_coroutines_coreFlowCollector, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        collector.emit(value: nil, completionHandler: completionHandler)
    }
}

class JustCoroutineFlow<T: Any>: Kotlinx_coroutines_coreFlow {
    let value: T
    init(value: T) {
        self.value = value
        //self.value = ConcurrencyHelpersKt.freeze(value) as! T
        //ConcurrencyHelpersKt.freeze(self)
    }
    
    func collect(collector: Kotlinx_coroutines_coreFlowCollector, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        let value = ConcurrencyHelpersKt.freeze(self.value)
        // Do not freeze collector
        ConcurrencyHelpersKt.freeze(completionHandler)
        //DispatchQueue.main.async {
            collector.emit(value: value, completionHandler: completionHandler)
        //}
    }
}
