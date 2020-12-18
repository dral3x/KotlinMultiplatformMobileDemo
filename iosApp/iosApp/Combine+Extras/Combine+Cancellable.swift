//
//  Combine+Cancellable.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 18/12/2020.
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
