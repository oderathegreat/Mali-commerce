package com.example.malishopping

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegisterActivity : AppCompatActivity() {
    private lateinit var _Username:EditText
    private lateinit var _Phone:EditText
    private lateinit var _Email:EditText
    private lateinit var _Pass:EditText
    private lateinit var _Confirmpass:EditText
    private lateinit var _submitData:Button

    var volleyRequestQueue : RequestQueue? = null
    var dialog: ProgressDialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        _submitData = findViewById(R.id.btnCreate)

        _Username = findViewById(R.id.edtUsername)
        _Phone = findViewById(R.id.edtPhone)
        _Email = findViewById(R.id.edtEmail)
        _Pass = findViewById(R.id.edtPass)
        _Confirmpass = findViewById(R.id.edtConfPass)

        //
        _submitData.setOnClickListener {

            registerUser()

        }
    }

    private fun registerUser() {
        val username = _Username.text.toString().trim()
        val phone = _Phone.text.toString().trim()
        val email = _Email.text.toString().trim()
        val password = _Pass.text.toString().trim()
        val confpassword = _Confirmpass.text.toString().trim()

        if (username.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank() || confpassword.isBlank()) {

            Toast.makeText(this, "One of your Inputs is empty", Toast.LENGTH_SHORT).show()
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password Length Should be At-least 6 Characters", Toast.LENGTH_SHORT).show()
        }

        if (password != confpassword) {
            Toast.makeText(this, "Sorry Password is not matching", Toast.LENGTH_SHORT).show()
        }

        else{

            val base_url =  "https://a175-197-237-88-131.eu.ngrok.io/mali_shop/register.php"
            val requestQueue = Volley.newRequestQueue(this)
            val stringRequest = object : StringRequest(Request.Method.POST,base_url,
                Response.Listener { response ->

                    Toast.makeText(this, "Success User Created",Toast.LENGTH_SHORT).show()
                    Log.d("Respones--->", response.toString() )
                    var i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()

                },
                Response.ErrorListener { error->

                    Log.d("Error--->", error.toString())
                }
            ){
                override fun getParams(): HashMap<String,String>{
                    val map = HashMap<String,String>()
                    map["username"] = username
                    map["phone"] = phone
                    map["email"] = email
                    map["password"] = password
                    return map
                }
            }
            requestQueue.add(stringRequest)

        }





    }

}