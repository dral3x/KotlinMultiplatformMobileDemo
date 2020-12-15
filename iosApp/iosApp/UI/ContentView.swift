import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel: RoomViewModel = RoomViewModel()

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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
