package com.techjd.task.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.techjd.task.api.swaggerhubApi

import com.techjd.task.databinding.ActivityReportAnIssueBinding
import com.techjd.task.viewmodel.ReportAnIssueViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ReportAnIssueActivity : AppCompatActivity() {

    @Inject
    lateinit var swaggerhubApi: swaggerhubApi

    private lateinit var binding: ActivityReportAnIssueBinding

    private lateinit var reportAnIssueViewModel: ReportAnIssueViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportAnIssueBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        reportAnIssueViewModel = ViewModelProvider(this).get(ReportAnIssueViewModel::class.java)


        binding.back.setOnClickListener {
            finish()
        }

        binding.send.setOnClickListener {

            if (binding.editTextSubjec.text.trim()
                    .toString() == "" || binding.editTextDescriptionText.text.trim()
                    .toString() == ""
            ) {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE

                reportAnIssueViewModel.reportIssue(
                    title = binding.editTextSubjec.text.trim().toString(),
                    description = binding.editTextDescriptionText.text.trim().toString()
                ).observe(this, { response ->
                    if (response != null) {
                        Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                        binding.apply {
                            editTextSubjec.setText("")
                            editTextDescriptionText.setText("")
                        }
                    } else {
                        binding.progressBar.visibility = View.VISIBLE

                    }
                })
            }
        }
    }
}