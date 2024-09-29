package com.example.rentalcarsapp.presentation.auth.resetPassword

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.rentalcarsapp.presentation.common.CTextField
import com.example.rentalcarsapp.domain.model.AuthState
import com.example.rentalcarsapp.R
import com.example.rentalcarsapp.presentation.auth.AuthViewModel
import com.example.rentalcarsapp.ui.theme.Blur
import com.example.rentalcarsapp.ui.theme.poppins
import es.dmoral.toasty.Toasty

@Composable
fun ResetPasswordScreen(viewModel: AuthViewModel = viewModel(), navController: NavController) {

    val authState = viewModel.authState.observeAsState()

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.password_reset))

    var startAnimation by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.ResetPassword -> {
                navController.navigate("auth")
                Toasty.success(
                    context,
                    "Great, we sent you a link to reset your password",
                    Toast.LENGTH_LONG
                ).show()
            }

            is AuthState.Error -> Toasty.error(
                context,
                "Incorrect or invalid email address",
                Toast.LENGTH_LONG
            ).show()

            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    AnimatedVisibility(
        visible = startAnimation, enter = fadeIn(
            animationSpec = tween(
                durationMillis = 1000,
                easing = EaseInOut
            )
        ) + expandHorizontally(
            expandFrom = Alignment.CenterHorizontally,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        ) + fadeIn(
            tween(
                durationMillis = 1000,
                easing = EaseInOut
            )
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        ) + shrinkHorizontally(
            tween(durationMillis = 1000, easing = EaseInOut)
        ) + fadeOut(animationSpec = tween(1000, easing = EaseInOut))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.horizontalGradient(listOf(Blur, Blur.copy(0.5f))))
                .pointerInput(Unit) {
                    detectTapGestures {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, bottom = 80.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.White)
                    .padding(top = 50.dp)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = (-180).dp)
                        .clickable(indication = null, interactionSource = remember {
                            MutableInteractionSource()
                        }, onClick = { navController.popBackStack() })
                )
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier.size(320.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Reset Your Password",
                    fontSize = 30.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Enter your email address below\nand we will send you a link\nto reset your password.",
                    fontSize = 19.sp,
                    fontFamily = poppins,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray.copy(0.8f)
                )
                Spacer(modifier = Modifier.height(30.dp))
                CTextField(
                    label = "Email Address",
                    value = email,
                    onValueChange = { email = it },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Color.Black, modifier = Modifier.padding(start = 5.dp)
                        )
                    },
                    placeholderText = "Enter your email"
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        if (email.isEmpty()) {
                            Toasty.error(context, "Email can't be empty", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            viewModel.resetPassword(email)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blur.copy(0.8f),
                        contentColor = Color.White
                    ),
                    shape = ShapeDefaults.Medium
                ) {
                    Text(
                        text = "Send Verification Code",
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
