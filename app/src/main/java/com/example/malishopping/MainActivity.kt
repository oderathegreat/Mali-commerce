package com.example.malishopping

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

  private lateinit var login:Button
  private lateinit var register:Button
  private lateinit var email_inp:EditText
  private lateinit var pass_inp:EditText

  var volleyRequest : RequestQueue? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.btn_login)
        email_inp = findViewById(R.id.emailedt)
        pass_inp = findViewById(R.id.edt_pass)
        register = findViewById(R.id.btnReg)

        register.setOnClickListener {

            var i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }



      login.setOnClickListener {

           loginUser()
      }



    }

    private fun loginUser() {
        val email = email_inp.text.toString()
        val password = pass_inp.text.toString()

        if (email.isBlank() || password.isBlank()) {

            Toast.makeText(this, "One of your fields is empty", Toast.LENGTH_SHORT).show()

        } else {
            //submit data to remote server
            val base_url =  "https://a175-197-237-88-131.eu.ngrok.io/mali_shop/login.php"
            val requestQueue = Volley.newRequestQueue(this)
            val stringRequest = object : StringRequest(Request.Method.POST,base_url,
                Response.Listener { response ->

                    //Toast.makeText(this, "Login Success $email and $password",Toast.LENGTH_SHORT).show()
                    Log.d("Respones--->", response.toString() )


                },
                Response.ErrorListener { error->

                    Log.d("Error--->", error.toString())
                }
            ){
                override fun getParams(): HashMap<String,String>{
                    val map = HashMap<String,String>()
                    map["email"] = email
                    map["password"] = password
                    return map
                }
            }
            requestQueue.add(stringRequest)

        }

        }
    }


