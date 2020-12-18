//
//  Swift+Also.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 18/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation

protocol Builder {}

extension Builder {
    
    @inline(__always) func apply(block: (Self) -> ()) -> Self {
        block(self)
        return self
    }
    
    @inline(__always) func with<R>(block: (Self) -> R) -> R {
        return block(self)
    }
}

extension NSObject: Builder {}
