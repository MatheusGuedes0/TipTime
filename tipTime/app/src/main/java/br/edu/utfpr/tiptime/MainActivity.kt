package br.edu.utfpr.tiptime

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{
            calculateButtonOnClick()
        }
    }

    private fun calculateButtonOnClick() {
        val stringInTextField: String = binding.constOfService.text.toString()
        val const: Double = stringInTextField.toDoubleOrNull() ?: return

        val selectedId = binding.tipOption.checkedRadioButtonId

        val tipPercentage = when ( selectedId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.15
        }

        var tip = const * tipPercentage

        var roundUp = binding.roundUpSwitch.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount_s, formattedTip)
    }
}