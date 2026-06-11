package com.worlden.world

import com.worlden.domain.World
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
            "TestWorld",
            "TestTestTestTest",
            mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION))

        assertEquals("TestWorld", world.name())
    }

    @Test
    fun `should throw when name is to short`() {
        assertThrows<IllegalArgumentException> {
            World.create(Uuid.generateV4(),
                "abd",
                "TestTestTestTest",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION))
        }
    }

    @Test
    fun `should throw when name is to long`() {
        assertThrows<IllegalArgumentException> {
            World.create(Uuid.generateV4(),
                "abdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd",
                "TestTestTestTest",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION))
        }
    }
}