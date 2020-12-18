import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject fileprivate(set) var viewModel: RoomViewModel = RoomViewModel()

    var body: some View {
        VStack {
            Text(viewModel.text)
            Button("SEND", action: {
                self.viewModel.sendMessage()
            })
        }
        .onAppear {
            self.viewModel.startObserving()
        }
        .onDisappear {
            self.viewModel.stopObserving()
        }
    }
    
}

#if DEBUG
class RoomViewModelPreview: RoomViewModel, Builder {
    func setText(_ txt: String) {
        self.text = txt
    }
    
    override func startObserving() {
        // Do nothing
    }
    override func stopObserving() {
        // Do nothing
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            ContentView(viewModel: RoomViewModelPreview().apply {
                $0.setText("Text Preview")
            })
            
            ContentView(viewModel: RoomViewModelPreview())
        }
    }
}
#endif
