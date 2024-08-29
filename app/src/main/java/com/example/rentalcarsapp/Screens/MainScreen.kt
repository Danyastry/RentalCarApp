package com.example.rentalcarsapp.Screens

import CarScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentalcarsapp.Data.bottomBarItem
import com.example.rentalcarsapp.Details.BottomBar
import com.example.rentalcarsapp.Model.AuthState
import com.example.rentalcarsapp.VIewModel.AuthViewModel
import com.example.rentalcarsapp.ui.theme.Blur
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel()
) {

    val auth = authViewModel.authState.observeAsState()
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val hazeState = remember { HazeState() }
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())

    LaunchedEffect(auth.value) {
        when (auth.value) {
            is AuthState.Unauthenticated -> {
                navController.navigate("home")
            }

            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    AnimatedVisibility(
        visible = startAnimation,
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
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Blur)
                .pointerInput(Unit) {
                    detectTapGestures {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                CarScreen(
                    navController = navController,
                    scrollBehavior = scrollBehavior,
                    modifier = Modifier.hazeChild(state = hazeState)
                )
            },
            bottomBar = {
                Column {
                    BottomBar(
                        bottomBarItems = bottomBarItem.mapIndexed { index, bottomBarItem ->
                            bottomBarItem.copy(selected = index == selectedIndex)
                        }, onItemSelected = {
                            selectedIndex = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                            .padding(bottom = 26.dp)
                            .hazeChild(state = hazeState, shape = RoundedCornerShape(26.dp))
                    )
                }
            }
        )
        { _ -> }
    }
}


