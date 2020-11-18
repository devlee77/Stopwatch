package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0                                // 0.01초마다 1씩 오르는 변수
    private var running = false                         // 스톱워치 실행이 되면 true가 됨
    private var timerTask: Timer? = null                // Timer객체를 가리키는 참조변수
    private var lap = 1                                 // 저장이 몇 개 됐는지 알리는 변수
    private var lastTimeBackPressed: Long = -1500       // 이전에 버튼을 눌렀을 때 시간


    private fun start() {
        timerTask = timer(period = 10) {
            time++;
            val hour = time / 100 / 3600
            val min = time / 100 / 60
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                tv_hour.text = "$hour"
                tv_min.text = "$min"
                tv_sec.text = "$sec"
                tv_milli.text = "$milli"
            }
        }
    }

    private fun pause() {
        timerTask?.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
