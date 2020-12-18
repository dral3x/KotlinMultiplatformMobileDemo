//
//  RoomViewModelTests.swift
//  iosAppTests
//
//  Created by Alessandro "Sandro" Calzavara on 18/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import XCTest
import Combine
@testable import iosApp
@testable import shared

class RoomViewModelTests: XCTestCase {

    var repository: MessageRepositoryIosMock!
    var manager: MessageManagerMock!
    var viewModel: RoomViewModel!
    
    private var cancellables = Set<AnyCancellable>()
    
    override func setUpWithError() throws {
        repository = MessageRepositoryIosMock()
        manager = MessageManagerMock()
        viewModel = RoomViewModel(repository: repository, manager: manager)
    }

    override func tearDownWithError() throws {
        cancellables.cancelAll()
    }

    func testInitialTextValue() throws {
         XCTAssertEqual("Hello, iOS 14.2!", viewModel.text)
    }
    
    func testTextChangeOnLoad() throws {
        let expectation = self.expectation(description: "waiting text updated")

        // Given
        repository.getMessagesResponses.append(
            Just<NSArray>([Message(messageId: 1, authorId: 2, authorFullname: "A", text: "Bye bye")]).setFailureType(to: Error.self).eraseToAnyPublisher()
        )
        viewModel.$text
            .dropFirst() // Ignore initial value
            .sink { (value) in
                print("Got \(value)")
                expectation.fulfill()
            }
            .store(in: &cancellables)
        
        // When
        viewModel.startObserving()
        
        // Then
        wait(for: [expectation], timeout: 1)
        XCTAssertEqual("Bye bye", viewModel.text)
    }
    
    func testSendMesage() throws {
        // When
        viewModel.sendMessage()
        
        // Then
        XCTAssertEqual("Ciao!", manager.lastMessageSent?.text)
    }

}

class MessageRepositoryMock: MessageRepository {
    func getMessagesInRoom(roomId: Int32, completionHandler: @escaping ([Message]?, Error?) -> Void) {
        // Ignored
    }
    
    func getMessagesInRoomFlow(roomId: Int32) -> Kotlinx_coroutines_coreFlow {
        return NeverCoroutineFlow()
    }
}

class MessageRepositoryIosMock: MessageRepositoryIos {

    convenience init() {
        self.init(repository: MessageRepositoryMock())
    }
    
    var getMessagesResponses = [AnyPublisher<NSArray, Error>]()
    override func getMessagesInRoom(roomId: Int) -> AnyPublisher<NSArray, Error> {
        if getMessagesResponses.isEmpty {
            return Empty(completeImmediately: false).eraseToAnyPublisher()
        }
        return getMessagesResponses.removeFirst()
    }
}

class MessageManagerMock: MessageManager {
    func observeMessageSendStateChange() -> Kotlinx_coroutines_coreFlow {
        return NeverCoroutineFlow()
    }
    
    var lastMessageSent: Message?
    func sendMessageInRoom(message: Message, roomId: Int32) {
        self.lastMessageSent = message
    }
}
