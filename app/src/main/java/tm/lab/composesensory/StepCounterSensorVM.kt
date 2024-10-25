package tm.lab.composesensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.system.exitProcess

class StepCounterSensorVM : ViewModel() {
    lateinit var _context: Context
    private var _sensorData = mutableStateOf(0f)
    var sensorData = _sensorData
    lateinit var  _sensorManager : SensorManager
    lateinit var _sensor : Sensor
    lateinit var _sensorListener : SensorEventListener

    fun init(context: Context) {
        _context = context
        _sensorManager = _context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (_sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!! != null) {
           _sensor=  _sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!!
        }
        if (_sensor != null) {
            _sensorListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.let {
                        _sensorData.value = it.values[0]
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }
            }
        } else {
            Log.i("STEP", "STEPCOUNTER IS NOT AVAILABLE")
            exitProcess(0)
        }
    }

    fun start() {
        _sensorManager.registerListener(_sensorListener, _sensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop() {
        _sensorManager.unregisterListener(_sensorListener)
    }

}