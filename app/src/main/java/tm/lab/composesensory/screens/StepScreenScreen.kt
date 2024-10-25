package tm.lab.composesensory.screens

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tm.lab.composesensory.StepCounterSensorVM

@Composable
fun StepCounterScreen(context: Context) {
    val vm: StepCounterSensorVM = viewModel()
    var accData = vm.sensorData.value
    var startOK  by rememberSaveable {  mutableStateOf(0)}

    LaunchedEffect(context) {
        vm.init(context)
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .background(Color.Black)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp),
            text = "StepcounterScreen"
        )
        SetPermission()
        Row() {
            Button(modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .weight(1f)
                .background(Color.Black),
                onClick = {
                    vm.start()
                    startOK = 1
                }
            ) {
                Text("Start stepcounter", color = Color.Yellow)
            }
            Button(modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .weight(1f)
                .background(Color.Black),
                onClick = {
                    vm.stop()
                    startOK = 0
                }
            ) {
                Text("Stop stepcounter", color = Color.Yellow)
            }
        }
        if (startOK != 0) {
            Text(text = "steps = ${accData}",
                color = Color.Yellow,
            )
        }
    }
}


@Composable
private fun SetPermission() {
    var permissionsActivityRecognisionGranted by remember { mutableStateOf(false) }

    val activityRecognitionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionsActivityRecognisionGranted = isGranted
        }
    )
    Button(onClick = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activityRecognitionLauncher.launch(android.Manifest.permission.ACTIVITY_RECOGNITION)
        }
    }) {
        Text(if (permissionsActivityRecognisionGranted) "Permission Granted" else "Request Permission", color = Color.Yellow)
    }
}

