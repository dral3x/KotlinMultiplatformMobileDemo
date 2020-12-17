package com.spreaker.kmm.shared.domain.bus

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class EventBusTest {

    @Test
    fun testEventQueueCreation() {
        // Given
        val queue1 = EventQueue<String>()
        val queue2 = EventQueue<String>()

        // Then
        assertNotEquals(queue1, queue2)
        assertNotEquals(queue1.id, queue2.id)
    }

    @Test
    fun testGetObservableQueue() {
        // Given
        val queue = EventQueue<String>()
        val bus = EventBus()

        // When
        val flow = bus.queue(queue)

        // Then
        assertNotNull(flow)
    }

    @Test
    fun testCollectEventFromQueue() = runBlocking {
        // Given
        val queue = EventQueue<String>()
        val bus = EventBus()

        // When
        launch {
            bus.publish(queue, "Hello")
        }
        val event = bus.queue(queue).first()

        // Then
        assertEquals("Hello", event)
    }

    @Test
    fun testCollectEventsFromQueue() = runBlocking {
        // Given
        val queue = EventQueue<String>()
        val bus = EventBus()
        val events = mutableListOf<String>()

        // When
        launch {
            bus.publish(queue, "Hello A")
            yield()
            bus.publish(queue, "Hello B")
            yield()
            bus.publish(queue, "Hello C")
        }
        bus.queue(queue).take(3).toCollection(events)

        // Then
        assertEquals(3, events.size)
        assertEquals("Hello A", events[0])
        assertEquals("Hello B", events[1])
        assertEquals("Hello C", events[2])
    }

    @Test
    fun testMultipleCollector() = runBlocking {
        // Given
        val queue = EventQueue<String>()
        val bus = EventBus()
        val eventsFrom1 = mutableListOf<String>()
        val eventsFrom2 = mutableListOf<String>()

        // When
        val job1 = launch {
            bus.queue(queue).take(2).toCollection(eventsFrom1)
        }
        val job2 = launch {
            bus.queue(queue).take(3).toCollection(eventsFrom2)
        }
        launch {
            bus.publish(queue, "Hello A")
            yield()
            bus.publish(queue, "Hello B")
            yield()
            bus.publish(queue, "Hello C")
        }
        job1.join()
        job2.join()

        // Then
        assertEquals(2, eventsFrom1.size)
        assertEquals(3, eventsFrom2.size)
    }
}