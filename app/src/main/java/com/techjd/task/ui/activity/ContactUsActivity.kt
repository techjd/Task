package com.techjd.task.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.techjd.task.api.swaggerhubApi
import com.techjd.task.databinding.ActivityContactUsBinding
import com.techjd.task.model.UserDetails.UserDetails
import com.techjd.task.viewmodel.ContactUsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.typeOf


@AndroidEntryPoint
class ContactUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactUsBinding

    @Inject
    lateinit var swaggerhubApi: swaggerhubApi

    lateinit var contactUsViewModel: ContactUsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()


        contactUsViewModel = ViewModelProvider(this).get(ContactUsViewModel::class.java)


        binding.back.setOnClickListener {
            finish()
        }

        binding.send.setOnClickListener {
            val userDet = UserDetails(
                FullName = binding.editTextFullName.text.trim().toString(),
                email = binding.editTextEmailText.text.trim().toString(),
                message = binding.editTextMessage.text.trim().toString()
            )

            if (binding.editTextFullName.text.trim()
                    .toString() == "" || binding.editTextEmailText.text.trim()
                    .toString() == "" || binding.editTextMessage.text.trim().toString() == ""
            ) {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            } else {

                binding.progressBar.visibility = View.VISIBLE

                contactUsViewModel.submitContactUs(Gson().toJson(userDet))
                    .observe(this, { response ->
                        if (response != null) {
                            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                            binding.apply {
                                editTextFullName.setText("")
                                editTextEmailText.setText("")
                                editTextMessage.setText("")
                            }
                        } else {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                    })
            }
        }

    }
}