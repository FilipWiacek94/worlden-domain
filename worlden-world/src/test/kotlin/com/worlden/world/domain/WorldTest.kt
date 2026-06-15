package com.worlden.world.domain

import com.worlden.domain.World
import com.worlden.domain.exception.WorldValidationException
import com.worlden.domain.vo.Genre
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.uuid.Uuid

class WorldTest {

    @Test
    fun `should create world with valid data`() {
        val world = World.create(
            Uuid.generateV4(),
            "Middle Earth",
            "Middle Earth desc",
            mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
        )
        assertEquals("Middle Earth", world.name())
    }

    @Test
    fun `should throw when name is too short`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "abd", "Middle Earth desc", mutableSetOf())
        }
    }

    @Test
    fun `should throw when name is too long`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "a".repeat(22), "Middle Earth desc", mutableSetOf())
        }
    }

    @Test
    fun `should throw when name is empty`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "", "Middle Earth desc", mutableSetOf())
        }
    }

    @Test
    fun `should throw when name is blank`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), " ", "Middle Earth desc", mutableSetOf())
        }
    }

    @Test
    fun `should throw when description is too short`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "Middle Earth", "a", mutableSetOf())
        }
    }

    @Test
    fun `should throw when description is too long`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "Middle Earth", "a".repeat(1001), mutableSetOf())
        }
    }

    @Test
    fun `should throw when description is empty`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "Middle Earth", "", mutableSetOf())
        }
    }

    @Test
    fun `should throw when description is blank`() {
        assertThrows<WorldValidationException> {
            World.create(Uuid.generateV4(), "Middle Earth", " ", mutableSetOf())
        }
    }
}