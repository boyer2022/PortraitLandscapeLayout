package com.example.portraitandlandscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Components
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarLabel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize variables
        seekBar = findViewById(R.id.seek_bar)
        seekBarLabel = findViewById(R.id.seek_bar_label)

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


    }
    // Function for seekBar value
    private fun updateLabel(progress: Int) {
        // Read the progress value from seekBar
        seekBarLabel.text = getString(R.string.seekbar_value_message, progress)

    }
}