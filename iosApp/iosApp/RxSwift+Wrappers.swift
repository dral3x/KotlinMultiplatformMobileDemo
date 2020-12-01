//
//  RxSwift+Wrappers.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 01/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import RxSwift
import shared

func createSingle<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    suspendWrapper: SuspendWrapper<T>,
    jobCallback: @escaping (Kotlinx_coroutines_coreJob) -> Void = { _ in }
) -> Single<T> {
    return Single<T>.create { single in
        let job: Kotlinx_coroutines_coreJob = suspendWrapper.subscribe(
            scope: scope,
            onSuccess: { item in single(.success(item!)) },
            onThrow: { error in single(.error(KotlinError(error))) }
        )
        jobCallback(job)
        return Disposables.create { job.cancel(cause: nil) }
    }
}

func createObservable<T>(
    scope: Kotlinx_coroutines_coreCoroutineScope,
    flowWrapper: FlowWrapper<T>,
    jobCallback: @escaping (Kotlinx_coroutines_coreJob) -> Void = { _ in }
) -> Observable<T> {
    return Observable<T>.create { observer in
        let job: Kotlinx_coroutines_coreJob = flowWrapper.subscribe(
            scope: scope,
            onEach: { item in observer.on(.next(item!)) },
            onComplete: { observer.on(.completed) },
            onThrow: { error in observer.on(.error(KotlinError(error))) }
        )
        jobCallback(job)
        return Disposables.create { job.cancel(cause: nil) }
    }
}
