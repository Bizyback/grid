package ui.screen

import androidx.compose.ui.geometry.Offset
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * project : grid
 * date    : Saturday 06/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */
data class MainScreenState(
    val map: Map<Int, Offset?> = emptyMap(),
    val randoms: List<Offset> = emptyList()
)

class MainScreenModel : StateScreenModel<MainScreenState>(MainScreenState()) {

    private val matrix: Array<Array<Int>> = Array(15) { Array(11) { 0 } }

    init {
        populateMatrix()
    }

    private fun populateMatrix() {
        val rows = 15
        val cols = 11
        var counter = 1
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                println("counter -> $counter @ [$i, $j]")
                matrix[i][j] = counter++
                if (counter > rows * cols) break
            }
        }
    }

    fun onValueChangeOffset(offset: Offset) {
        val update = state.value.map.toMutableMap()
        update[1] = update[0]
        update[2] = update[1]
        update[0] = offset
        mutableState.update { it.copy(map = update) }
        clearOffsets(time = 200)
    }

    private var job: Job? = null
    private fun clearOffsets(time: Long, offset: Offset? = null) {
        job?.cancel()
        job = screenModelScope.launch {
            delay(time)
            val current = state.value.map.toMutableMap()
            offset?.let { current[0] = it }
            current[1] = null
            current[2] = null
            mutableState.update { it.copy(map = current, randoms = emptyList()) }
            job = null
        }
    }

}