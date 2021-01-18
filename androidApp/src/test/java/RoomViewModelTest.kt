import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.spreaker.kmm.androidApp.ui.room.RoomViewModel
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
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
        Dispatchers.setMain(Dispatchers.Unconfined)

        reoository = mock(MessageRepository::class.java)
        manager = mock(MessageManager::class.java)
        viewModel = RoomViewModel(reoository, manager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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

    @Test
    fun testTextChangeOnLoad() {
        // Given
        var observer = mock(Observer::class.java) as Observer<String>
        viewModel.text.observeForever(observer)
        var messages = listOf(Message(1, null, "A", "Bye bye"))
        Mockito.`when`(reoository.getMessagesInRoomFlow(anyInt())).thenReturn(flowOf(messages))
        Mockito.`when`(manager.observeMessageSendStateChange()).thenReturn(emptyFlow())
        reset(observer)

        // When
        viewModel.onViewCreated()

        // Then
        verify(observer).onChanged("Bye bye")
    }

    @Test
    fun testSendMessage() {
        // When
        viewModel.sendMessage()

        // Then
        verify(manager).sendMessageInRoom(anyObject(), anyInt())
    }
}