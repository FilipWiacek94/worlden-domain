package com.worlden.world.domain

import com.worlden.domain.World
import com.worlden.domain.vo.Genre
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.uuid.Uuid

class WorldTest {

    @Test
    fun `should create world with valid data`() {
        val world = World.Companion.create(
            Uuid.Companion.generateV4(),
            "Middle Earth",
            "Middle Earth desc",
            mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION))

        Assertions.assertEquals("Middle Earth", world.name())
    }

    @Test
    fun `should throw when name is to short`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "abd",
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when name is to long`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "a".repeat(22),
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when desc is to short`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "Middle Earth",
                "a",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when desc is to long`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "Middle Earth",
                "a".repeat(1001),
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when name is empty`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "",
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when name is blank`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                " ",
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when desc is empty`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                "",
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }

    @Test
    fun `should throw when desc is blank`() {
        assertThrows<IllegalArgumentException> {
            World.Companion.create(
                Uuid.Companion.generateV4(),
                " ",
                "Middle Earth desc",
                mutableSetOf(Genre.URBAN_FANTASY, Genre.SCIENCE_FICTION)
            )
        }
    }
}