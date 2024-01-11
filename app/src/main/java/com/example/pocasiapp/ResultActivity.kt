package com.example.pocasiapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import org.json.JSONObject
import java.net.URL
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*


class ResultActivity: AppCompatActivity() {
    var CITY: String = ""
    val API: String = "0dcebe68aba2d3951ecb64e29b06f8f2" // Use API key

    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var btn = findViewById<Button>(R.id.button)


        CITY = intent.getStringExtra("city").toString().unaccent().lowercase()
        weatherTask().execute()

        btn.setOnClickListener(){
            val intent = Intent(this, SearchActivity::class.java)
            //intent.putExtra("city", actv.text.toString())
            startActivity(intent)
        }

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            //findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Aktuální čas: "+ SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.GERMAN).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Minimální teplota: " + main.getString("temp_min")+"°C"
                val tempMax = "Maximální teplota: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                var address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                if (address.equals("Prague, CZ"))
                    address = "Praha, CZ"
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.wind).text =  updatedAtText
              //  findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                //findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
               // findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))

                //findViewById<TextView>(R.id.wind).text =  windSpeed
              //  findViewById<TextView>(R.id.pressure).text = pressure
              //  findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
               // findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

                //insertDataToDatabase(address, temp, updatedAtText)

            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }
    }

    /*private fun insertDataToDatabase(address: String, temp: String, updatedAtText: String) {
        val firstName = address
        val lastName = temp
        val age = updatedAtText

        if(inputCheck(firstName, lastName, age)){
            val user = User(0, firstName, lastName, age)
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Úšpěšně přidáno", Toast.LENGTH_LONG).show()
        }
    }*/

    private fun inputCheck(firstName: String, lastame: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastame) && TextUtils.isEmpty(age))
    }
}