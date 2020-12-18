//
//  PushNotificationServiceiOSImpl.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 15/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import shared

class PushNotificationServiceiOSImpl: PushNotificationService {
    
    func subscribeTo(topic: String) {
        print("Subscribe to: \(topic)")
        
        //TODO Messaging.messaging().subscribe(toTopic: topic)
    }
    
    func unsubscribeFrom(topic: String) {
        print("Unsubscribe from: \(topic)")
        
        //TODO Messaging.messaging().unsubscribe(fromTopic: topic)
    }
    
    var isEnabled: Bool = true
}
