package com.example.portraitandlandscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
const val EXTRA_TAPPED_INSIDE_SQUARE = "com.example.portraitandlandscape.TAPPED_INSIDE_SQUARE"

class SquareActivity : AppCompatActivity() {

    // Create variables to create square image
    private lateinit var squareImage: ImageView
    // Create variable when missing tap square
    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square)

        squareImage = findViewById(R.id.square)
        // Container View tap Initialize
        container = findViewById(R.id.container)

        // Reading the number sent from MainActivity
            // Variable name = intent.get variable type(name of Key, default value)
        var squareSize = intent.getIntExtra(EXTRA_SQUARE_SIZE, 100)
            // Set for 0 size
        if (squareSize == 0) {
            squareSize = 1 * 10
        }
        // Setting squareSize as the image size
        squareImage.layoutParams.width = squareSize
        squareImage.layoutParams.height = squareSize

        // EventListener for the square image clicking on
        squareImage.setOnClickListener {
            // Boolean
                // Function call
            squareTapped(true)
        }
        // EventListener for missing the square and tapping the container
        container.setOnClickListener {
            squareTapped(false)
        }
    }

    // Function for tapping on square
    private fun squareTapped(didTapSquare: Boolean) {
        // Send message back to MainActivity with Boolean value
            // end square activity.
        // Make sure the EventListeners are working by sending a Toast message
       // Toast.makeText(this, "Square Tapped $didTapSquare", Toast.LENGTH_SHORT).show()
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_TAPPED_INSIDE_SQUARE, didTapSquare)

        setResult(RESULT_OK, resultIntent)
        // finish means end this activity
        finish()
        /*
        Kotlin:

        Intent().apply{
            putExtra(EXTRA_TAPPED_INSIDE_SQUARE, didTapSquare)
            setResult(RESULT_OK, this)
            finish()
        }
         */

    }
}