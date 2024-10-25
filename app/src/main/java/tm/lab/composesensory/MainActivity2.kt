package tm.lab.composesensory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tm.lab.composesensory.retrofit.ApiInterface
import tm.lab.composesensory.ui.theme.ComposeSensoryTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
           val apiInterface = ApiInterface.create().getTable()
           ComposeSensoryTheme {
                StartScreen2(apiInterface,
                    modifier = Modifier)
           }
        }
    }
}

@Composable
fun StartScreen2(api : Call<List<String>>, modifier: Modifier) {
    val mContext = LocalContext.current
    var info by remember { mutableStateOf("") }
    LaunchedEffect(api) {
        api.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>,
            ) {
                Log.i("RETROFIT", "${call} ${response.body()}")
                info = "SUCCESS: ${call} \nRESPONSE: ${response.body()}"
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("RETROFIT", "${call} ${t.message}")
                info = "FAILURE: ${call} \n ${t.message}"
               }
        })
    }
    LazyColumn (

    ) {
        item() {
            Text(
                text = "Start screen 2",
                modifier = modifier
                    .background(Color.White)
            )
        }
        item {
            Text(
                text = "${info}",
                modifier = modifier
                    .background(Color.White)
            )
        }
    }
}
