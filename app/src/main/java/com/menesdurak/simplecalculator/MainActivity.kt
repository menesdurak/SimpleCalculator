package com.menesdurak.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.menesdurak.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var isZeroAllowed = false
    private var isPlusAllowed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv0.setOnClickListener {
            if (isZeroAllowed) {
                var calculations = binding.tvCalculations.text.toString()
                calculations += 0
                binding.tvCalculations.text = calculations
            }
        }
        binding.tv1.setOnClickListener(this)
        binding.tv2.setOnClickListener(this)
        binding.tv3.setOnClickListener(this)
        binding.tv4.setOnClickListener(this)
        binding.tv5.setOnClickListener(this)
        binding.tv6.setOnClickListener(this)
        binding.tv7.setOnClickListener(this)
        binding.tv8.setOnClickListener(this)
        binding.tv9.setOnClickListener(this)

        binding.tvPlus.setOnClickListener {
            if (isPlusAllowed) {
                var calculations = binding.tvCalculations.text.toString()
                calculations += " + "
                binding.tvCalculations.text = calculations
                isZeroAllowed = false
                isPlusAllowed = false
            }
        }

        binding.tvAC.setOnClickListener {
            binding.tvCalculations.text = ""
            binding.tvResult.text = "0"
            isZeroAllowed = false
            isPlusAllowed = false
        }

        binding.tvDelete.setOnClickListener {
            var calculations = binding.tvCalculations.text.toString()
            if (calculations.isNotBlank()) {
                calculations = if (calculations.last() == ' ') {
                    calculations.dropLast(3)
                } else {
                    calculations.dropLast(1)
                }
                binding.tvCalculations.text = calculations
            }

        }

        binding.tvEqual.setOnClickListener {
            val calculations = binding.tvCalculations.text.toString()
            val splitCalculations = calculations.split(" ")
            var result = 0
            splitCalculations.forEach {
                result += (it.toIntOrNull() ?: 0)
            }
            binding.tvResult.text = result.toString()
            binding.tvCalculations.text = "$result + "
            isZeroAllowed = false
            isPlusAllowed = false
        }

    }

    override fun onClick(v: View?) {
        isZeroAllowed = true
        isPlusAllowed = true
        var calculations = binding.tvCalculations.text.toString()
        calculations += findViewById<TextView>(v!!.id).text
        binding.tvCalculations.text = calculations
    }
}