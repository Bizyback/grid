package ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.Platform
import util.distance
import util.getPadding
import util.platform

/**
 * project : grid
 * date    : Saturday 06/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */
class MainScreen(private val toggle: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        MainScreenContent(toggle = toggle)
    }
}

@Composable
fun MainScreenContent(toggle: () -> Unit) {

    val scope = rememberCoroutineScope()
    var initial by remember { mutableStateOf(true) }
    var points by remember { mutableStateOf(arrayOf<Offset?>(null, null, null)) }

    var job: Job? = null
    fun update(offset: Offset) {
        val arr = arrayOf(offset, points[0], points[1])
        points = arr
        job?.cancel()
        job = scope.launch {
            delay(250)
            val update = arrayOf(offset, null, null)
            points = update
        }
    }

    LaunchedEffect(points){
        println("Points updated -> $points")
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        Text(
            modifier = Modifier.align(Alignment.BottomCenter).padding(getPadding(32.dp)),
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            text = "The Grid",
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        update(offset = offset)
                        toggle()
                    }
                }
                .pointerInput(Unit) {
                    detectDragGestures { change: PointerInputChange, _: Offset ->
                        update(offset = change.position)
                    }
                },
            columns = GridCells.Fixed(11)
        ) {
            items(165) { item ->

                var offset by remember { mutableStateOf<Offset?>(null) }

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(20.dp)
                        .onGloballyPositioned { coordinates ->
                            offset = coordinates.positionInParent()
                            offset?.let { offset ->
                                if (item == 82 && initial) {
                                    update(offset = offset)
                                    initial = false
                                }
                            }
                        }
                ) {

                    val animatedSize by animateDpAsState(
                        targetValue = getSize(points = points, offset = offset)
                    )
                    val animatedColor by animateColorAsState(
                        targetValue = getColor(points = points, offset = offset)
                    )

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(animatedSize)
                            .background(animatedColor)
                            .align(Alignment.Center)
                    )
                }
            }
        }

    }

}

@Composable
fun getSize(points: Array<Offset?>, offset: Offset?): Dp {
    if (offset == null) return 5.dp
    val (closest, closer, close) = getDistance(points, offset)
    val size = when(platform){
        Platform.Android -> 125
        Platform.iOS -> 150
    }
    return when {
        closest < size -> 20.dp
        closer < 225 -> 10.dp
        close < 275 -> 7.5.dp
        else -> 5.dp
    }
}

@Composable
fun getColor(points: Array<Offset?>, offset: Offset?): Color {
    if (offset == null) return Color.Gray
    val (closest, closer, close) = getDistance(points, offset)
    val size = when(platform){
        Platform.Android -> 125
        Platform.iOS -> 150
    }
    return when {
        closest < size -> MaterialTheme.colorScheme.primary
        closer < 225 -> MaterialTheme.colorScheme.primary.copy(0.5f)
        close < 275 -> MaterialTheme.colorScheme.primary.copy(0.25f)
        else -> MaterialTheme.colorScheme.onBackground.copy(0.5f)
    }

}

private fun getDistance(
    points: Array<Offset?>,
    offset: Offset
): Triple<Float, Float, Float> {

    val layer1 = points[0]
    val layer2 = points[1]
    val layer3 = points[2]

    val distance1 = if (layer1 != null) distance(layer1, offset) else 2500f
    val distance2 = if (layer2 != null) distance(layer2, offset) else 2500f
    val distance3 = if (layer3 != null) distance(layer3, offset) else 2500f

    return Triple(distance1, distance2, distance3)

}
