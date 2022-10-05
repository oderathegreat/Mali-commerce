package com.example.malishopping

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

  private lateinit var reg_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reg_button = findViewById(R.id.btnReg)
        reg_button.setOnClickListener {
     //move to register page
               var i = Intent(this, RegisterActivity::class.java)
               startActivity(i)
               finish()

        }





    }



}