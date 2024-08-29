import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentalcarsapp.Data.luxCars
import com.example.rentalcarsapp.Data.vipCars
import com.example.rentalcarsapp.Details.CarList
import com.example.rentalcarsapp.Details.TabLayout
import com.example.rentalcarsapp.VIewModel.CarSearchViewModel
import com.example.rentalcarsapp.ui.theme.Blur
import com.example.rentalcarsapp.ui.theme.montserrat_reg
import com.example.rentalcarsapp.ui.theme.poppins
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier,
    carSearchViewModel: CarSearchViewModel = koinViewModel()
) {
    val value by carSearchViewModel.searchQuery
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isFocus = remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            scrollBehavior = scrollBehavior,
            modifier = modifier
                .background(Blur)
                .padding(top = 25.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            ), title = {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 22.dp, end = 44.dp)
                        .border(width = 1.dp, color = White, shape = RoundedCornerShape(100.dp))
                        .onFocusChanged { focusState ->
                            isFocus.value = focusState.isFocused
                        },
                    value = value,
                    onValueChange = {
                        carSearchViewModel.updateSearchQuery(it)
                    },
                    shape = RoundedCornerShape(100.dp),
                    placeholder = {
                        if (!isFocus.value && value.isEmpty()) {
                            Text(
                                text = "Search",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = White
                            )
                        }
                    },
                    leadingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null,
                                tint = White,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .size(28.dp)
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Blur,
                        unfocusedContainerColor = Blur,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        color = White
                    )

                )
            }
        )
        TabLayout(
            modifier = Modifier
                .background(Blur)
                .weight(1f),
            isSelectedIndex = selectedTab,
            items = listOf(
                "Luxury Cars" to {
                    CarList(
                        cars = luxCars,
                        searchQuery = value,
                        carType = "Lux",
                        navController = navController
                    )
                },
                "VIP Cars" to {
                    CarList(
                        cars = vipCars,
                        searchQuery = value,
                        carType = "Vip",
                        navController = navController
                    )
                }
            ),
            color = Color.White,
            fontFamily = montserrat_reg,
            onTapClick = { selectedTab = it }
        )
    }
}


