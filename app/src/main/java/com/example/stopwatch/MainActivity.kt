package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0                                // 0.01초마다 1씩 오르는 변수
    private var checkRunning = false                         // 스톱워치 실행이 되면 true가 됨
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

    private fun reset() {
        timerTask?.cancel()
        if (checkRunning)
            checkRunning = false

        time = 0
        lapLayout.removeAllViews()
        tv_hour.text = "0"
        tv_min.text = "0"
        tv_sec.text = "0"
        tv_milli.text = "00"
        lap = 1;
    }

    private fun recordLap() {
        val laptime = this.time

        val textView = TextView(this)
        val string =
            "$lap LAP : ${laptime / 100 / 3600}:${laptime / 100 / 60}:${laptime / 100}.${laptime % 100}"
        textView.text=string
        lapLayout.addView(textView,0)
        lap++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_start.setOnClickListener {
            if(!checkRunning)
                start()
            else
                pause()
            checkRunning = !checkRunning
        }

        bt_lap.setOnClickListener {
            recordLap()
        }

        bt_reset.setOnClickListener {
            reset()
        }
    }
}
