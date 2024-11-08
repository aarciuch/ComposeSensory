package tm.lab.composesensory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
           ComposeSensoryTheme {
                StartScreen2(modifier = Modifier)
           }
        }
    }
}

@Composable
fun StartScreen2( modifier: Modifier) {
    var info by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(emptyList<String>()) }
    var info2 by remember { mutableStateOf("") }
    var lista2 by remember { mutableStateOf(emptyArray<List<String>>()) }
    val api = ApiInterface.create().getTable()
    val api2 = ApiInterface.create().getDB()
    LaunchedEffect(api) {
        api.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>,
            ) {
                lista = response.body()!!
                Log.i("RETROFIT", "${call} ${response.body()}")
                info = "SUCCESS: ${call} \nRESPONSE: ${response.body()}"
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("RETROFIT", "${call} ${t.message}")
                info = "FAILURE: ${call} \n ${t.message}"
               }
        })
    }
    LaunchedEffect(api2) {
        api2.enqueue(object : Callback<Array<List<String>>> {
            override fun onResponse(
                call: Call<Array<List<String>>>,
                response: Response<Array<List<String>>>,
            ) {
                lista2 = response.body()!!
                Log.i("RETROFIT", "${call} ${response.body()}")
                info2 = "SUCCESS: ${call}}"
            }

            override fun onFailure(call: Call<Array<List<String>>>, t: Throwable) {
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
        item() {
            Text(
                text = "Z pliku:",
                fontSize = 30.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .background(Color.White)
            )
        }
        item {
            Text(
                text = "$info",
                modifier = modifier
                    .background(Color.White)
            )
        }
        items(lista) { imie->
            Text(text = imie,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.Yellow)
            )
        }
        item {
            Text(
                text = "Z bazy:",
                fontSize = 30.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .background(Color.White)
            )
        }
        item {
            Text(
                text = "$info2",
                modifier = modifier
                    .background(Color.White)
            )
        }
        items(lista2) { elem->
            Text(text = "${elem}",
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.Yellow)
            )
        }
    }
}
