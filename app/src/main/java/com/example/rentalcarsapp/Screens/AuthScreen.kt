package com.example.rentalcarsapp.Screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentalcarsapp.Details.CTextField
import com.example.rentalcarsapp.Details.Checkbox
import com.example.rentalcarsapp.Details.PasswordValidMessage
import com.example.rentalcarsapp.Details.TabLayout
import com.example.rentalcarsapp.Model.AuthState
import com.example.rentalcarsapp.R
import com.example.rentalcarsapp.VIewModel.AuthViewModel
import com.example.rentalcarsapp.ui.theme.Blur
import com.example.rentalcarsapp.ui.theme.poppins
import es.dmoral.toasty.Toasty
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(navController: NavController) {
    val selectedTap = remember { mutableIntStateOf(0) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true

    }


    AnimatedVisibility(
        startAnimation,
        enter = fadeIn(
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
                .verticalScroll(rememberScrollState())
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
                    .padding(top = 80.dp, bottom = 80.dp)
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
                Text(
                    text = "Welcome To",
                    fontSize = 35.sp,
                    fontFamily = poppins,
                    color = Color.Black.copy(0.8f),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Car Rental",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = poppins,
                    color = Color.Black,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Place of exclusive and luxury cars",
                    fontSize = 19.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(40.dp))
                TabLayout(isSelectedIndex = selectedTap.intValue,
                    items = listOf(
                        "Sign In" to {
                            SignIn(navController = navController)
                        },
                        "Sign Up" to {
                            SignUp(navController = navController)
                        }
                    ),
                    onTapClick = { selectedTap.intValue = it },
                    modifier = Modifier,
                    color = Color.Black,
                    fontFamily = poppins
                )
            }
        }
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SignIn(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel(),
) {

    val authState = viewModel.authState.observeAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val email by viewModel.signInEmail.observeAsState("")
    val password by viewModel.signInPassword.observeAsState("")
    val visualPassword = remember { mutableStateOf(false) }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("main")
                Toasty.success(context, "You have successfully logged in", Toast.LENGTH_LONG).show()
            }

            is AuthState.Error -> {
                Toasty.error(context, "Incorrect login and/or password", Toast.LENGTH_LONG).show()
            }

            else -> Unit
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        CTextField(
            label = "Email Address",
            value = email,
            onValueChange = { viewModel.updateSignInEmail(it) },
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
        Spacer(modifier = Modifier.height(22.dp))
        CTextField(
            label = "Password",
            value = password,
            onValueChange = { viewModel.updateSignInPassword(it) },
            visualTransformation = if (visualPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { visualPassword.value = !visualPassword.value },
                    painter = painterResource(id = if (visualPassword.value) R.drawable.eye_open else R.drawable.eye_off),
                    contentDescription = null, tint = Color.Black
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(start = 5.dp)
                )
            },
            placeholderText = "Enter your password"
        )
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(start = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox()
                // I know that Firebase automatically saves the value true XD
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Remember Me")
            }
            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                fontFamily = poppins,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource(),
                    onClick = { navController.navigate("resetPassword") })
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toasty.error(context, "Email or/and Password can't be empty", Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.signIn(email, password)
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
                text = "Sign In",
                fontSize = 20.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SignUp(navController: NavController, viewModel: AuthViewModel = koinViewModel()) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val authState = viewModel.authState.observeAsState()
    val context = LocalContext.current

    val passwordLength by viewModel.passwordLength
    val containsUpperCase by viewModel.containsUpperCase
    val containsDigit by viewModel.containsDigit
    val email by viewModel.signUpEmail.observeAsState("")
    val password by viewModel.signUpPassword.observeAsState("")

    val visualPassword = remember { mutableStateOf(false) }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Registered -> {
                navController.navigate("main")
                Toasty.success(context, "Registration successful", Toast.LENGTH_LONG).show()
            }

            is AuthState.Error -> {
                Toasty.error(
                    context, "Login or Password entered incorrectly", Toast.LENGTH_LONG
                ).show()
            }

            else -> Unit
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        CTextField(
            label = "Email Address",
            value = email,
            onValueChange = { viewModel.updateSignUpEmail(it) },
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(
                        start = 5.dp
                    )
                )
            },
            placeholderText = "Enter email address"
        )
        Spacer(modifier = Modifier.height(22.dp))
        CTextField(
            label = "Password",
            value = password,
            onValueChange = { viewModel.updatePasswordSignUp(it) },
            visualTransformation = if (visualPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { visualPassword.value = !visualPassword.value },
                    painter = painterResource(id = if (visualPassword.value) R.drawable.eye_open else R.drawable.eye_off),
                    contentDescription = null, tint = Color.Black
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }), leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(
                        start = 5.dp
                    )
                )
            }, placeholderText = "Enter password"
        )
        Spacer(modifier = Modifier.height(14.dp))
        PasswordValidMessage(
            passwordLength = passwordLength,
            upperCase = containsUpperCase,
            digit = containsDigit
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toasty.error(context, "Email or/and Password can't be empty", Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.signUp(email, password)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blur.copy(0.8f),
                contentColor = Color.White
            ),
            shape = ShapeDefaults.Medium
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

