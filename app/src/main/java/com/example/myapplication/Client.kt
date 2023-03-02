package com.example.myapplication

import android.annotation.SuppressLint
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class Client : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client)

        val wifiMan: WifiManager = this.getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInf: WifiInfo = wifiMan.getConnectionInfo()
        val ipAddress = wifiInf.ipAddress
        Log.d("IP", ipAddress.toString())
        val ip = String.format(
            "%d.%d.%d.%d",
            ipAddress and 0xff,
            ipAddress shr 8 and 0xff,
            ipAddress shr 16 and 0xff,
            ipAddress shr 24 and 0xff
        )

        val ipAddr = byteArrayOf(10.toByte(), 0.toByte(), 2.toByte(), 16.toByte())
        val addr = InetAddress.getByAddress(ipAddr)
        var buffer = ByteArray(256)


        findViewById<TextView>(R.id.textView5).setText("Device ip address: $ip\nTarget Device ip: $addr")
        val send: Button = findViewById(R.id.button_send)

        send.setOnClickListener {
            val msg: String = findViewById<EditText>(R.id.textBox).text.toString()
            buffer = msg.toByteArray()
            val datagramSocket = DatagramSocket()
            val datagramPacket = DatagramPacket(buffer, buffer.size, addr, 1235)
            if (msg != "") {
                val show = Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
                datagramSocket.send(datagramPacket)
                Log.d("msg sent", msg)
            }

            findViewById<EditText>(R.id.textBox).setText("")

        }
    }
}