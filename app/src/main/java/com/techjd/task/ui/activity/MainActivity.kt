package com.techjd.task.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techjd.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.reportAnIssue.setOnClickListener {
            val navigate = Intent(this@MainActivity, ReportAnIssueActivity::class.java)
            startActivity(navigate)
        }

        binding.contactUs.setOnClickListener {
            val navigate = Intent(this@MainActivity, ContactUsActivity::class.java)
            startActivity(navigate)
        }


    }
}