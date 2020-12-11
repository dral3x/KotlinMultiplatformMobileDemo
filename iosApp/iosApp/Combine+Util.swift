//
//  Combine+Util.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 01/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Combine

typealias CancelBag = Set<AnyCancellable>

extension CancelBag {
    mutating func cancelAll() {
        forEach { $0.cancel() }
        removeAll()
    }
}

struct AnyObserver<Output, Failure: Error> {
    let onNext: ((Output) -> Void)
    let onError: ((Failure) -> Void)
    let onComplete: (() -> Void)
}

struct Disposable {
    let dispose: () -> Void
}

extension AnyPublisher {
    static func create(subscribe: @escaping (AnyObserver<Output, Failure>) -> Disposable) -> Self {
        let subject = PassthroughSubject<Output, Failure>()
        var disposable: Disposable?
        return subject
                .handleEvents(receiveSubscription: { subscription in
                    disposable = subscribe(AnyObserver(
                            onNext: { output in subject.send(output) },
                            onError: { failure in subject.send(completion: .failure(failure)) },
                            onComplete: { subject.send(completion: .finished) }
                    ))
                }, receiveCancel: { disposable?.dispose() })
                .eraseToAnyPublisher()
    }
}