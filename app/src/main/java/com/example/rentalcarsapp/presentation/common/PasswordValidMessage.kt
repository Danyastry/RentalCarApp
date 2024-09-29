package com.example.rentalcarsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.GppGood
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentalcarsapp.ui.theme.Green
import com.example.rentalcarsapp.ui.theme.poppins

@Composable
fun PasswordValidMessage(passwordLength: Boolean, upperCase: Boolean, digit: Boolean) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (passwordLength) Icons.Rounded.GppGood else Icons.Rounded.ErrorOutline,
                contentDescription = null, tint = if (passwordLength) Green else Color.Red
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Minimum 6 characters",
                color = if (passwordLength) Green else Color.Red,
                fontSize = 12.sp, fontFamily = poppins
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (upperCase) Icons.Rounded.GppGood else Icons.Rounded.ErrorOutline,
                contentDescription = null, tint = if (upperCase) Green else Color.Red
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "At least one uppercase letter",
                color = if (upperCase) Green else Color.Red,
                fontSize = 12.sp, fontFamily = poppins
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (digit) Icons.Rounded.GppGood else Icons.Rounded.ErrorOutline,
                contentDescription = null, tint = if (digit) Green else Color.Red
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "At least one digit",
                color = if (digit) Green else Color.Red,
                fontSize = 12.sp, fontFamily = poppins
            )
        }
    }
}