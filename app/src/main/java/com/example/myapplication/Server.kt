package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket

class Server : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server)
        receive()
    }
    private fun receive(){
//        Log.d("server", "server receive method")
//        val message = "Hello there" // your message
//        findViewById<TextView>(R.id.textView3).setText("Message Sent: $message")
        val handler: Handler = Handler()
        val receivePacket = object : Runnable {
            override fun run() {
                val buffer = ByteArray(256)
                val datagramSocket = DatagramSocket(1235)
                while(true) {
                    try {
                        val datagramPacket = DatagramPacket(buffer, buffer.size)
                        datagramSocket.receive(datagramPacket) // receive data from client and save in datagramPacket
                        val messageFromClient = String(datagramPacket.data, 0, datagramPacket.length)
                        Log.d("Message from Client",messageFromClient)
                        findViewById<TextView>(R.id.textView3).text="Message Sent: $messageFromClient"
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                  handler.postDelayed(this, 0)
            }
        }
        handler.post(receivePacket)
    }
}