package com.example.diceroller

import kotlin.random.Random
import org.junit.Assert.assertTrue
import org.junit.Test

class DiceRollerLogicTest {
    @Test
    fun rollDice_returns_a_value_between_one_and_six() {
        repeat(100) {
            val result = rollDice(Random(it))
            assertTrue(result in 1..6)
        }
    }

    @Test
    fun diceResourceForResult_maps_each_result_to_the_matching_drawable() {
        assertTrue(diceResourceForResult(1) == R.drawable.dice_1)
        assertTrue(diceResourceForResult(2) == R.drawable.dice_2)
        assertTrue(diceResourceForResult(3) == R.drawable.dice_3)
        assertTrue(diceResourceForResult(4) == R.drawable.dice_4)
        assertTrue(diceResourceForResult(5) == R.drawable.dice_5)
        assertTrue(diceResourceForResult(6) == R.drawable.dice_6)
    }
}
