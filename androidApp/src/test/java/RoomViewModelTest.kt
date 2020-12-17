import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.spreaker.kmm.androidApp.ui.RoomViewModel
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class RoomViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var reoository: MessageRepository
    @Mock
    private lateinit var manager: MessageManager
    private lateinit var viewModel: RoomViewModel

    @Before
    fun setUp() {
        reoository = mock(MessageRepository::class.java)
        manager = mock(MessageManager::class.java)
        viewModel = RoomViewModel(reoository, manager)
    }

    @Test
    fun testInitialTextValue() {
        // Given
        var observer = mock(Observer::class.java) as Observer<String>

        // When
        viewModel.text.observeForever(observer)

        // Then
        verify(observer).onChanged(anyString())
    }

//    @Test
//    fun testSendMessage() {
//        // When
//        viewModel.sendMessage()
//
//        // Then
//        verify(manager).sendMessageInRoom(any(Message::class.java), anyInt())
//    }
}