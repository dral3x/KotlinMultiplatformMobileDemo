//
//  InjectionCenter.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 01/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation

public class InjectorCenter {
    
    public static let sharedInstance = InjectorCenter()

    private var container = [String: AnyObject]()
        
    public func get<Element>(_ key: Element.Type) -> Element {
        //swiftlint:disable:next force_cast
        return self.container[String(describing: key)] as! Element
    }
    
    public func put(_ content: AnyObject) {
        self.put(content, as: type(of: content))
    }
    
    public func put(_ content: AnyObject, as key: AnyClass) {
        self.container[String(describing: key)] = content
    }
    
    public static func inject<Element>(_ key: Element.Type) -> Element {
        return InjectorCenter.sharedInstance.get(key)
    }
}
