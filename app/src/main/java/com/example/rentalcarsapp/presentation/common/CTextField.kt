package com.example.rentalcarsapp.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentalcarsapp.ui.theme.poppins

@Composable
fun CTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "",
    keyboardActions: KeyboardActions

) {

    var isSelected by remember { mutableStateOf(false) }

    Column {
        Text(text = label, fontSize = 18.sp, fontFamily = poppins, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isSelected) {
                            Modifier.border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(12.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .onFocusChanged { focusState ->
                        isSelected = focusState.isFocused
                    },
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                keyboardActions = keyboardActions,
                trailingIcon = trailingIcon,
                placeholder = {
                    if (!isSelected && value.isEmpty()) {
                        Text(
                            text = placeholderText, fontSize = 16.sp,
                            fontFamily = poppins,
                            color = Color.Gray
                        )
                    }
                },
                leadingIcon = leadingIcon,
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(fontFamily = poppins, fontSize = 19.sp, color = Color.Black),
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        backgroundColor = Color.Black,
                        handleColor = Color.Black
                    ),
                    cursorColor = Color.Black,
                    focusedContainerColor = if (isSelected) Color.White else Color.Gray.copy(0.2f),
                    unfocusedContainerColor = if (isSelected) Color.White else Color.Gray.copy(0.2f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                )
            )
        }
    }
}