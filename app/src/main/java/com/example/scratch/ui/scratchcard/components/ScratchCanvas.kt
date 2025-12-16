package com.example.scratch.ui.scratchcard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scratch.R

// Simple scratch canvas with image overlay
@Composable
fun ScratchCanvas(
    modifier: Modifier = Modifier,
    onScratchProgress: (Float) -> Unit
) {
    var scratchPaths by remember { mutableStateOf(listOf<Path>()) }
    var currentPath by remember { mutableStateOf<Path?>(null) }
    var totalScratchedArea by remember { mutableStateOf(0f) }
    var canvasSize by remember { mutableStateOf(Rect.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
            .graphicsLayer { alpha = 0.99f }
    ) {
        // Scratch overlay image
        Image(
            painter = painterResource(id = R.drawable.google_scratch_card),
            contentDescription = "Scratch to reveal",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Scratch erase layer
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            currentPath = Path().apply { moveTo(offset.x, offset.y) }
                        },
                        onDrag = { change, _ ->
                            currentPath?.let { path ->
                                path.lineTo(change.position.x, change.position.y)
                                currentPath = Path().apply { addPath(path) }

                                val totalArea = canvasSize.width * canvasSize.height
                                if (totalArea > 0) {
                                    totalScratchedArea += 800f
                                    onScratchProgress(0.005f)
                                }
                            }
                        },
                        onDragEnd = {
                            currentPath?.let { scratchPaths = scratchPaths + it }
                            currentPath = null
                        }
                    )
                }
        ) {
            canvasSize = Rect(0f, 0f, size.width, size.height)

            val strokeWidth = 50.dp.toPx()

            // Erase scratched areas
            scratchPaths.forEach { path ->
                drawPath(
                    path = path,
                    color = Color.Transparent,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    ),
                    blendMode = BlendMode.Clear
                )
            }

            currentPath?.let { path ->
                drawPath(
                    path = path,
                    color = Color.Transparent,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    ),
                    blendMode = BlendMode.Clear
                )
            }
        }
    }
}


