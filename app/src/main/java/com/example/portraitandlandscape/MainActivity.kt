package com.example.portraitandlandscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

// Puppet Constant for sending data (Key) to SquareActivity
const val EXTRA_SQUARE_SIZE = "com.example.portraitandlandscape.seekBarTappingSquare.SQUARE_SIZE"

class MainActivity : AppCompatActivity() {
    // Components
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarLabel: TextView
    private lateinit var showSquareButton: Button

    private val squareResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        // Lambda function
        result -> handleSquareResult(result)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize variables
        seekBar = findViewById(R.id.seek_bar)
        seekBarLabel = findViewById(R.id.seek_bar_label)
        showSquareButton = findViewById(R.id.show_square_button)

        val initialProgress = seekBar.progress
        updateLabel(initialProgress)

        // TODO add listener to update label as seekbar changes.
        seekBar.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener{
                                        // Component: COMPONENTNAME?, progress, USERCHANGE/CODECHANGED:
            override fun onProgressChanged(seekbarComponent: SeekBar?, progress: Int, fromUser: Boolean) {
                updateLabel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        showSquareButton.setOnClickListener {
        // Call for function
            showSquare()
        }


    }
    // Function for seekBar value
    private fun updateLabel(progress: Int) {
        // Read the progress value from seekBar
        seekBarLabel.text = getString(R.string.seekbar_value_message, progress)

    }

    private fun showSquare() {
        // Launch the SquareActivity
            // Find class definition::extension type
        val showSquareIntent = Intent(this, SquareActivity::class.java)

        // Adding Extra for SquareActivity
        // Tell the SquareActivity how large the square should be
        // based on the progress of the Seekbar by sending data to the SquareActivity
        // by using Extras-Wk5 PP
                                // (Name of key, value)
        showSquareIntent.putExtra(EXTRA_SQUARE_SIZE, seekBar.progress)

        // Call function StartActivity
//        startActivity(showSquareIntent)
        squareResultLauncher.launch(showSquareIntent)
        // In order to launch an Activity, have to tell which activity to launch
            // Closes MainActivity and launches SquareActivity

        /*
        Kotlin style Scope Function
            // Creates new intent
        Intent(this, SquareActivity::class.java).apply {
        // Lambda function
            this.putExtra(EXTRA_SQUARE_SIZE, seekBar.progress)
            squareResultLauncher.launch(this)
         */

    }
    private fun handleSquareResult(result: ActivityResult) {
        // Display results to user
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val tapped = intent?.getBooleanExtra(EXTRA_TAPPED_INSIDE_SQUARE, false) ?:false
            val message = if (tapped) {
                getString(R.string.tapped_square)
            }else {
                getString(R.string.missed_square)
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, getString(R.string.did_not_try), Toast.LENGTH_SHORT).show()
        }
    }
}