package com.example.malishopping

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var imagePic:ImageView
    var volleyRequest: RequestQueue? =null
    val _weblink = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagePic = findViewById(R.id.imgId)
        volleyRequest = Volley.newRequestQueue(this)
        fetchdata(_weblink)


        diplayLogoImg()





    }

    private fun diplayLogoImg() {
        //runs as a background thread
        Thread(Runnable {

            val image_url = URL("https://developer.android.com/images/kotlin/cards/kotlin-bootcamp.png")
            val httpConnection = image_url.openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            val inputStream = httpConnection.inputStream
            val bitImage = BitmapFactory.decodeStream(inputStream)
            imagePic.setImageBitmap(bitImage)

        }).start()


    }

    private fun fetchdata(url: String) {
        val arrayReq = JsonArrayRequest(Request.Method.GET, url,null,
            {
                    response: JSONArray ->
                try {
                    Log.d("Response-->" , response.toString())

                    for (x in 0 until response.length()) {
                        //create a variable to loop to our objects
                        var showArray = response.getJSONArray(x)
                    }

                } catch (e:JSONException) {
                    e.printStackTrace()
                }
            },
            {
                    error: VolleyError? ->
                try {
                    Log.d("Error-->" , error.toString())
                } catch (e:JSONException) {
                    e.printStackTrace()
                }
            })

        volleyRequest!!.add(arrayReq)
    }
}