package tm.lab.composesensory.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tm.lab.composesensory.MainActivity2


@Composable
fun StartScreen(navController: NavController) {
    val mContext = LocalContext.current
    Column(modifier = Modifier
        .background(Color.Black)) {
        Text(modifier = Modifier
            .padding(top = 8.dp, start = 16.dp),
            text = "StartScreen")
        Button (modifier = Modifier
            .padding(8.dp),
            onClick = {
            navController.navigate(route = Screens.AccelerometerScreen.name)
        }) {
            Text(text = "Go to Accelerometer screen", color = Color.Yellow)
        }
        Button (modifier = Modifier
            .padding(8.dp),
            onClick = {
                navController.navigate(route = Screens.StepCounterScreen.name)
            }) {
            Text(text = "Go to Stepcounter screen", color = Color.Yellow)
        }
        Button (modifier = Modifier
            .padding(8.dp),
            onClick = {
               mContext.startActivity(Intent(mContext, MainActivity2::class.java))
            }) {
            Text(text = "Go to Retrofit", color = Color.Yellow)
        }
    }
}