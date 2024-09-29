package com.example.rentalcarsapp.presentation.common

import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun Checkbox() {
    var isChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(25.dp)
            .background(Color.Transparent)
            .clickable { isChecked = !isChecked }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            val checkmarkPath = Path().apply {
                moveTo(size.width * 0.2f, size.height * 0.5f)
                lineTo(size.width * 0.4f, size.height * 0.7f)
                lineTo(size.width * 0.8f, size.height * 0.3f)
            }

            drawRoundRect(
                color = if (isChecked) Color.Black else Color.Gray.copy(0.5f),
                size = size,
                cornerRadius = CornerRadius(4.dp.toPx(), 2.dp.toPx()),
                style = Stroke(2.dp.toPx())
            )
            if (isChecked) {
                drawPath(
                    path = checkmarkPath.asComposePath(),
                    color = Color.Black,
                    style = Stroke(2.dp.toPx())
                )
            }
        }
    }
}