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

func createNeverEndingPublisher<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    flowWrapper: FlowWrapper<T>
) -> AnyPublisher<T, Never> {
    let subject = PassthroughSubject<T, Never>()
    var job: Kotlinx_coroutines_coreJob?
    return subject
        .handleEvents(
            receiveSubscription: { (subscription) in
                job = flowWrapper.subscribe(
                    scope: scope,
                    onEach: { (item) in subject.send(item!) },
                    onComplete: { /* it should never happen */ },
                    onThrow: { _ in /* it should never happen */ }
                )
            },
            receiveCancel: {
                job?.cancel(cause: nil)
            })
        .eraseToAnyPublisher()
}

func createPublisher<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    flowWrapper: FlowWrapper<T>
) -> AnyPublisher<T, Error> {
    let subject = PassthroughSubject<T, Error>()
    var job: Kotlinx_coroutines_coreJob?
    return subject
        .handleEvents(
            receiveSubscription: { (subscription) in
                job = flowWrapper.subscribe(
                    scope: scope,
                    onEach: { (item) in subject.send(item!) },
                    onComplete: { subject.send(completion: .finished) },
                    onThrow: { (error) in subject.send(completion: .failure(KotlinError(error))) }
                )
            },
            receiveCancel: {
                job?.cancel(cause: nil)
            })
        .eraseToAnyPublisher()
}

extension Kotlinx_coroutines_coreFlow {
    func asNeverEndingPublisher<T: AnyObject>(
        of output: T.Type,
        inScope scope: Kotlinx_coroutines_coreCoroutineScope = CoroutineScopes().main
    ) -> AnyPublisher<T, Never> {
        return createNeverEndingPublisher(scope: scope, flowWrapper: FlowWrapper<T>(flow: self))
    }
    
    func asPublisher<T: AnyObject>(of output: T.Type) -> AnyPublisher<T, Error> {
        return createPublisher(scope: CoroutineScopes().io, flowWrapper: FlowWrapper<T>(flow: self))
    }
}
