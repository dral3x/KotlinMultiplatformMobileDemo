import SwiftUI
import podStoriesKit

struct ContentView: View {
    
    @ObservedObject var viewModel: RoomViewModel = RoomViewModel()

    var body: some View {
        Text(viewModel.text)
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
