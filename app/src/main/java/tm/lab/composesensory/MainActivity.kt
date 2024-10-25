package tm.lab.composesensory

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.lab.composesensory.screens.AccelerometerScreen
import tm.lab.composesensory.screens.Screens
import tm.lab.composesensory.screens.StartScreen
import tm.lab.composesensory.screens.StepCounterScreen
import tm.lab.composesensory.ui.theme.ComposeSensoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //enableEdgeToEdge()
        setContent {
            ComposeSensoryTheme  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.StartScreen.name) {
        composable(route = Screens.StartScreen.name) {
            StartScreen(navController)
        }
        composable(route = Screens.AccelerometerScreen.name) {
            AccelerometerScreen(LocalContext.current)
        }
        composable(route = Screens.StepCounterScreen.name) {
            StepCounterScreen(LocalContext.current)
        }
    }
}