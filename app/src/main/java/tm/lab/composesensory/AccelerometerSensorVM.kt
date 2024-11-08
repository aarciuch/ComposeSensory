package tm.lab.composesensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AccelerometerSensorVM : ViewModel() {

    lateinit var _context: Context
    private var _sensorData = mutableStateOf(Triple(0f,0f,0f))
    var sensorData = _sensorData
    lateinit var  _sensorManager : SensorManager
    lateinit var _sensor : Sensor
    lateinit var _sensorListener : SensorEventListener

    fun init(context: Context) {
        _context = context
        _sensorManager = _context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        _sensor = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!

        _sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    Log.i("ACC", "${it.values[0]}:${it.values[1]}:${it.values[2]}")
                    _sensorData.value = Triple(it.values[0], it.values[1], it.values[2])
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }
    }

    fun start() {
       _sensorManager.registerListener(_sensorListener, _sensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop() {
       _sensorManager.unregisterListener(_sensorListener)
    }
}