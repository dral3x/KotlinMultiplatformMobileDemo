//
//  RxWrappers.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 30/11/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class KotlinError: LocalizedError {
    let throwable: KotlinThrowable
    init(_ throwable: KotlinThrowable) {
        self.throwable = throwable
    }
    var errorDescription: String? {
        get { throwable.message }
    }
}

func createDeferred<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    suspendWrapper: SuspendWrapper<T>,
    jobCallback: @escaping (Kotlinx_coroutines_coreJob) -> Void = { _ in }
) -> AnyPublisher<T, KotlinError> {
    return Deferred {
        Future { single in
            let job: Kotlinx_coroutines_coreJob = suspendWrapper.subscribe(
                scope: scope,
                onSuccess: { item in single(.success(item!)) },
                onThrow: { error in single(.failure(KotlinError(error))) }
            )
            jobCallback(job)
        }
    }
    .eraseToAnyPublisher()
}

func createPublisher<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    flowWrapper: FlowWrapper<T>,
    jobCallback: @escaping (Kotlinx_coroutines_coreJob) -> Void = { _ in }
) -> AnyPublisher<T, KotlinError> {
    var disposable: Kotlinx_coroutines_coreJob?
    return Deferred {
        Future { promise in
            let job: Kotlinx_coroutines_coreJob = flowWrapper.subscribe(
                scope: scope,
                onEach: { item in promise(.success(item!)) },
                onComplete: { /*observer.on(.completed)*/ },
                onThrow: { error in promise(.failure(KotlinError(error))) }
            )
            disposable = job
            jobCallback(job)
        }
    }
    .handleEvents(receiveCancel: {
        disposable?.cancel(cause: nil)
    })
    .eraseToAnyPublisher()
}
