package util

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * project : grid
 * date    : Saturday 06/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */

fun distance(offset1: Offset, offset2: Offset): Float {
    val dx = offset1.x - offset2.x
    val dy = offset1.y - offset2.y
    return sqrt(dx * dx + dy * dy)
}

fun Offset.isWithinRadius(offset: Offset, radius: Int): Boolean {
    val distance = distance(this, offset)
    return distance <= radius
}

fun getRandomOffsetWithinRadius(center: Offset, radius: Double, numPoints: Int): List<Offset> {
    val randomOffsets = mutableListOf<Offset>()

    repeat(numPoints) {
        val randomAngle = 2 * PI * Random.nextDouble()
        val randomRadius = radius * sqrt(Random.nextDouble())

        val randomX = center.x + randomRadius * cos(randomAngle)
        val randomY = center.y + randomRadius * sin(randomAngle)

        randomOffsets.add(Offset(randomX.toFloat(), randomY.toFloat()))
    }

    return randomOffsets
}
