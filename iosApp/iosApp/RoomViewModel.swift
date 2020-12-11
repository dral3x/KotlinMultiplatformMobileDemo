//
//  RoomViewModel.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 27/11/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

//TODO Dependency injection
class RoomViewModel: ObservableObject {
    
    @Published private(set) var text: String = Greeting().greeting()
    
    private var cancellables = Set<AnyCancellable>()
    
    func startObserving() {
        print("RoomViewModel startObserving()")

        let repo: MessageRepository = MessageRepositoryImpl(client: HttpClientFactory().defaultHttpClient())
        let repository = MessageRepositoryIos(repository: repo)

        /*
        // Coroutine
        repo.getMessagesInRoom(roomId: 18631166) { messages, error in
            if let messages = messages {
                self.text = String(describing: messages[0])
            }
        }
        */
        
        // Coroutine -> Combine
        createPublisher(
            scope: repository.scope,
            flowWrapper: repository.getMessagesInRoomFlow(roomId: 18631166)
        )
        .receive(on: DispatchQueue.main)
        .sink(receiveCompletion: { (event) in
            
            print("receiveCompletion: \(event)")
            
        }, receiveValue: { (value) in
            guard let messages = value as? [Message] else { return }

            print("Got \(messages.count) messages")
            self.text = String(describing: messages[0].text)
        })
        .store(in: &cancellables)
    }
    
    func stopObserving() {
        print("RoomViewModel stopObserving()")

        cancellables.cancelAll()
    }

    func sendMessage() {
        print("RoomViewModel sendMessage()")

        let manager: MessageManager = MessageManagerImpl()

        manager.sendMessageInRoom(
            message: Message(messageId: 1, authorId: 42, authorFullname: "Sandro", text: "Ciao!"),
            roomId: 18631166
        )
    }
}
