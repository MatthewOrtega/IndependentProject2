package com.example.independentproject2

import android.support.v7.app.AppCompatActivity
import android.view.View

class Time_Up : AppCompatActivity() {
    var playAgainButton: Button? = null
    var timeUpText: TextView? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time__up)
        //Initialize
        playAgainButton = findViewById(R.id.playAgainButton)
        timeUpText = findViewById(R.id.timeUpText) as TextView?

        //play again button onclick listener
        playAgainButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Time_Up, MainGameActivity::class.java)
            startActivity(intent)
            finish()
        })

        //Setting typefaces for textview and button
        val typeface: Typeface = Typeface.createFromAsset(getAssets(), "fonts/Cambria.ttf")
        timeUpText.setTypeface(typeface)
        playAgainButton.setTypeface(typeface)
    }

    fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}