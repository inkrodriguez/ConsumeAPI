package com.inkrodriguez.consumeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inkrodriguez.consumeapi.api.myData
import com.inkrodriguez.consumeapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://cep.awesomeapi.com.br/"
//const val API_KEY = "7HJYf1TBeKCki0VKuJ62OV1eNBl43Qfx"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.button.setOnClickListener {
            getMyData()
        }

    }

    private fun getMyData() {

        var editCep = binding.editCep.text

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(editCep.toString().toInt())

        retrofitData.enqueue(object: Callback<myData>{
            override fun onResponse(call: Call<myData>, response: Response<myData>) {

                var responseBody = response.body()!!

                binding.tvCep.text = responseBody.cep
                binding.tvAdressType.text = responseBody.address_type
                binding.tvAdressName.text = responseBody.address_name
                binding.tvAdress.text = responseBody.address
                binding.tvState.text = responseBody.state
                binding.tvDistrict.text = responseBody.district
                binding.tvCity.text = responseBody.city
                binding.tvCityIbge.text = responseBody.city_ibge
                binding.tvDDD.text = responseBody.ddd

            }

            override fun onFailure(call: Call<myData>, t: Throwable) {
                Toast.makeText(applicationContext, "$t", Toast.LENGTH_SHORT).show()
            }

        })

    }
}