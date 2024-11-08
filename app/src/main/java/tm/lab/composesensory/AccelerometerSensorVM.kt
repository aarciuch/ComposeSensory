package tm.lab.composesensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

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