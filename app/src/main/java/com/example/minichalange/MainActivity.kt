package com.example.minichalange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hitungBB()
    }

    fun hitungBB(){
        val handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val notif = msg.obj as String
                tvTampil.text = notif
            }
        }
        Thread(Runnable {
            btnhitung.setOnClickListener {
                val berat = edtbb.text.toString().toInt()
                val tinggi = edttinggi.text.toString().toFloat()
                val hasil = (berat/(tinggi*tinggi))
                var result = ""
                if (hasil < 18.5){
                    result = "Kurus"
                }else if (hasil>18.5 && hasil<24.9){
                    result = "Normal"
                }else if (hasil>25 && hasil<29.9){
                    result = "Overweight"
                }else if (hasil>=30){
                    result = "Obesitas"
                }

                val nootif = Message.obtain()
                nootif.obj = result
                nootif.target = handler
                nootif.sendToTarget()
            }
        }).start()
    }
}