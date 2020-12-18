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

class RoomViewModel: ObservableObject {
    
    @Published var text: String = Greeting().greeting()
    
    let repository: MessageRepositoryIos
    let manager: MessageManager
    
    init(
        repository: MessageRepositoryIos = InjectorCenter.inject(MessageRepository.self).wrap(),
        manager: MessageManager = InjectorCenter.inject(MessageManager.self)
    ) {
        self.repository = repository
        self.manager = manager
    }
    
    private var cancellables = Set<AnyCancellable>()
    
    func startObserving() {
        print("RoomViewModel startObserving()")

        /*
        // Coroutine -> Callback
        let repo = InjectorCenter.inject(MessageRepository.self)
        repo.getMessagesInRoom(roomId: 18631166) { messages, error in
            if let messages = messages {
                self.text = String(describing: messages[0])
            }
        }
        */
        
        // Coroutine -> Combine
        repository.getMessagesInRoom(roomId: 18631166)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { (event) in
                
                print("receiveCompletion: \(event)")
                
            }, receiveValue: { (value) in
                guard let messages = value as? [Message] else { return }
                
                print("Got \(messages.count) messages")
                self.text = String(describing: messages[0].text)
            })
            .store(in: &cancellables)
        
        manager.observeMessageSendStateChange()
            .asNeverEndingPublisher(of: MessageSendStateChangeEvent.self)
            .receive(on: DispatchQueue.main)
            .sink { (event) in
                print("Got event \(event)")
                self.text = "\(event.state)"
            }
            .store(in: &cancellables)
    }
    
    func stopObserving() {
        print("RoomViewModel stopObserving()")

        cancellables.cancelAll()
    }

    func sendMessage() {
        print("RoomViewModel sendMessage()")

        manager.sendMessageInRoom(
            message: Message(messageId: 1, authorId: 42, authorFullname: "Sandro", text: "Ciao!"),
            roomId: 18631166
        )
    }
}
