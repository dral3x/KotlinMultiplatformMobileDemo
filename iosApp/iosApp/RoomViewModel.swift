//
//  RoomViewModel.swift
//  iosApp
//
//  Created by Alessandro "Sandro" Calzavara on 27/11/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import Combine
import RxSwift
import podStoriesKit

class RoomViewModel: ObservableObject {
    
    @Published private(set) var text: String = Greeting().greeting()
    
    private var cancellables = Set<AnyCancellable>()
    private var disposables = DisposeBag()
    
    func startObserving() {
        let repository = MessageRepositoryIos(repository: MessageRepositoryImpl(client: ApiClientBuilder().defaultHttpClient()))
        
        // Combine
//        createDeferred(
//            scope: repository.scope,
//            suspendWrapper: repository.getMessagesInRoomSuspended(roomId: 18631166)
//        )
        createPublisher(
            scope: repository.scope,
            flowWrapper: repository.getMessagesInRoomFlow(roomId: 18631166)
        )
        .sink(receiveCompletion: { (event) in
            
            print("receiveCompletion: \(event)")
            
        }, receiveValue: { (value) in
            
            print("receiveValue: \(value)")
            
        })
        .store(in: &cancellables)
        
        // RxSwift
        /*
        createSingle(
            scope: repository.scope,
            suspendWrapper: repository.getMessagesInRoomSuspended(roomId: 18631166)
        )
        .asObservable()
        .subscribe(
            onNext: { thing in
                NSLog("next: \(thing)")
            },
            onError: { (error: Error) in
                NSLog("error: \(error.localizedDescription)")
            },
            onCompleted: {
                NSLog("complete!")
            },
            onDisposed: {
                NSLog("disposed!")
            }
        )
        .disposed(by: self.disposables)
        */
    }
    
    func stopObserving() {
        cancellables.cancelAll()
    }
    
}
