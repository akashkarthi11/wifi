package com.karthi.panasonicremote

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private lateinit var ir: ConsumerIrManager
    private lateinit var status: TextView
    private var temp = 24
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); ir = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager; screen() }
    private fun screen() {
        val root = LinearLayout(this).apply { orientation=1; gravity=Gravity.CENTER_HORIZONTAL; setPadding(24,32,24,24); setBackgroundColor(0xFF101827.toInt()) }
        val sc = ScrollView(this); val body = LinearLayout(this).apply { orientation=1; gravity=Gravity.CENTER_HORIZONTAL }; sc.addView(body)
        fun text(s:String,sp:Float,color:Int,bold:Boolean=false)=TextView(this).apply { text=s; textSize=sp; setTextColor(color); gravity=Gravity.CENTER; setPadding(8,12,8,12); if(bold)setTypeface(null,1) }
        fun btn(s:String, action:()->Unit)=Button(this).apply { text=s; isAllCaps=false; setOnClickListener { action() } }
        body.addView(text("PANASONIC AC REMOTE",23f,-1,true))
        body.addView(text("CS-VU18AKYF",15f,0xFFB9C4D5.toInt()))
        body.addView(text("$temp°C",52f,-1,true))
        val temps=LinearLayout(this).apply { gravity=Gravity.CENTER }
        temps.addView(btn("−") { temp=(temp-1).coerceAtLeast(16); screen() }); temps.addView(btn("+") { temp=(temp+1).coerceAtMost(30); screen() }); body.addView(temps)
        body.addView(btn("POWER") { explain("Power") })
        val modes=LinearLayout(this).apply { gravity=Gravity.CENTER }
        listOf("COOL","AUTO","DRY","FAN").forEach { modes.addView(btn(it) { explain(it) }) }; body.addView(modes)
        body.addView(btn("FAN SPEED / SWING") { explain("Fan and swing") })
        status=text(if(ir.hasIrEmitter()) "IR hardware detected; model codes not configured" else "No IR emitter detected on this phone",14f,0xFFFFD28A.toInt())
        body.addView(status)
        body.addView(text("WI-FI / MATTER",19f,-1,true))
        body.addView(btn("MATTER SETUP HELP") { AlertDialog.Builder(this).setTitle("Matter pairing").setMessage("Connect your phone to 2.4 GHz Wi-Fi. The AC must enter pairing mode using its supported SMART command. Matter commissioning is not implemented in this prototype.").setPositiveButton("OK",null).show() })
        body.addView(text("Prototype only: no Panasonic IR frames or Matter commissioning are implemented yet. Buttons do not operate the AC.",13f,0xFFB9C4D5.toInt()))
        root.addView(sc,LinearLayout.LayoutParams(-1,-1)); setContentView(root)
    }
    private fun explain(cmd:String) { val m=if(!ir.hasIrEmitter()) "This phone has no IR transmitter." else "$cmd selected, but exact Panasonic IR codes are not installed; no signal sent."; Toast.makeText(this,m,Toast.LENGTH_LONG).show() }
}
