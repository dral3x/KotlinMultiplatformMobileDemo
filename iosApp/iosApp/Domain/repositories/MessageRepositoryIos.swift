//
//  MessageRepositoryIos.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 18/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

extension MessageRepository {
    func wrap() -> MessageRepositoryIos {
        return MessageRepositoryIos(repository: self)
    }
}

// It simply wrap a regular repository and add few functions that returns Combine objects instead
// of coroutine ones, to ease integration and tests on iOS.
class MessageRepositoryIos: MessageRepository {
    
    let internalRepo: MessageRepository
    init(repository: MessageRepository) {
        internalRepo = repository
    }
    
    func getMessagesInRoom(roomId: Int32, completionHandler: @escaping ([Message]?, Error?) -> Void) {
        self.internalRepo.getMessagesInRoom(roomId: roomId, completionHandler: completionHandler)
    }
    
    func getMessagesInRoomFlow(roomId: Int32) -> Kotlinx_coroutines_coreFlow {
        return self.internalRepo.getMessagesInRoomFlow(roomId: roomId)
    }
    
    // Returns [Message]
    func getMessagesInRoom(roomId: Int) -> AnyPublisher<NSArray, Error> {
        return self.getMessagesInRoomFlow(roomId: Int32(roomId))
            .asPublisher(of: NSArray.self)
    }
        
}
