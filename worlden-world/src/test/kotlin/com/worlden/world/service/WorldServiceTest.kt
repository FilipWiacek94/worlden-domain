package com.worlden.world.service

import com.worlden.application.WorldService
import com.worlden.application.command.CreateWorldCommand
import com.worlden.application.event.WorldCreatedEvent
import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import com.worlden.domain.exception.WorldValidationException
import com.worlden.domain.vo.Genre
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.context.ApplicationEventPublisher

class WorldServiceTest {

    val worldRepository: WorldRepository = mockk()
    val eventPublisher: ApplicationEventPublisher = mockk()
    val worldService = WorldService(worldRepository, eventPublisher)

    @Test
    fun `should create world and publish event when name does not exist`() {
        val command = CreateWorldCommand("Middle Earth", "Middle Earth desc", setOf(Genre.URBAN_FANTASY))
        every { worldRepository.getByName("Middle Earth") } returns null
        every { worldRepository.save(any()) } answers { firstArg() }
        justRun { eventPublisher.publishEvent(any<Any>()) }

        val event = worldService.createWorld(command)

        assertEquals(WorldCreatedEvent::class, event::class)
        verify(exactly = 1) { worldRepository.save(any()) }
        verify(exactly = 1) { eventPublisher.publishEvent(any<Any>()) }
    }

    @Test
    fun `should publish event referencing the saved world id`() {
        val command = CreateWorldCommand("Middle Earth", "Middle Earth desc", setOf(Genre.URBAN_FANTASY))
        every { worldRepository.getByName("Middle Earth") } returns null
        val savedWorldSlot = slot<World>()
        every { worldRepository.save(capture(savedWorldSlot)) } answers { firstArg() }
        justRun { eventPublisher.publishEvent(any<Any>()) }

        val event = worldService.createWorld(command)

        assertEquals(savedWorldSlot.captured.id(), event.worldId)
    }

    @Test
    fun `should trim world name before checking for duplicates`() {
        val command = CreateWorldCommand("  Middle Earth  ", "Middle Earth desc", setOf(Genre.URBAN_FANTASY))
        every { worldRepository.getByName("Middle Earth") } returns null
        every { worldRepository.save(any()) } answers { firstArg() }
        justRun { eventPublisher.publishEvent(any<Any>()) }

        worldService.createWorld(command)

        verify(exactly = 1) { worldRepository.getByName("Middle Earth") }
    }

    @Test
    fun `should throw and not save when world name already exists`() {
        val command = CreateWorldCommand("Middle Earth", "Middle Earth desc", setOf(Genre.URBAN_FANTASY))
        val existingWorld = World.create(
            kotlin.uuid.Uuid.generateV4(),
            "Middle Earth",
            "Existing desc",
            setOf(Genre.FANTASY)
        )
        every { worldRepository.getByName("Middle Earth") } returns existingWorld

        assertThrows<WorldValidationException> {
            worldService.createWorld(command)
        }
        verify(exactly = 0) { worldRepository.save(any()) }
        verify(exactly = 0) { eventPublisher.publishEvent(any()) }
    }
}