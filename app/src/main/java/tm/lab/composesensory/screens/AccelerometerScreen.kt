package tm.lab.composesensory.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tm.lab.composesensory.AccelerometerSensorVM

@Composable
fun AccelerometerScreen(context: Context) {
    val vm : AccelerometerSensorVM = viewModel()
    var accData = vm.sensorData.value
    var startOK  by rememberSaveable {  mutableStateOf(0)}

    LaunchedEffect(context) {
        vm.init(context)
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .background(Color.Black)
    ) {
        Text(modifier = Modifier
            .padding(top = 8.dp, start = 16.dp),
            text = "AccelerometerScreen")

        Row() {
            Button(modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .weight(1f),
                onClick = {
                    vm.start()
                    startOK = 1
                }
            ) {
                Text("Start accelerometer", color = Color.Yellow)
            }
            Button(modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .weight(1f),
                onClick = {
                    vm.stop()
                    startOK = 0
                }
            ) {
                Text("Stop accelerometer", color = Color.Yellow)
            }
        }
        if (startOK != 0) {
            Text(text = "X = ${accData.first} Y = ${accData.second} Z = ${accData.third}",
                color = Color.Yellow )
        }
    }
}
