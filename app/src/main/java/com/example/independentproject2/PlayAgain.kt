package com.example.independentproject2

import android.widget.TextView
import android.os.Bundle
import com.example.independentproject2.R
import android.content.Intent
import com.example.independentproject2.MainGameActivity
import android.graphics.Typeface
import android.app.Activity
import com.example.independentproject2.TriviaQuizHelper
import com.example.independentproject2.TriviaQuestion
import android.os.CountDownTimer
import com.example.independentproject2.GameWon
import com.example.independentproject2.PlayAgain
import com.example.independentproject2.Time_Up
import android.graphics.drawable.ColorDrawable
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.view.View
import android.widget.Button

class PlayAgain : Activity() {
    var playAgain: Button? = null
    var wrongAnsText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_again)
        //Initialize
        playAgain = findViewById<View>(R.id.playAgainButton) as Button
        wrongAnsText = findViewById<View>(R.id.wrongAns) as TextView

        //play again button onclick listener
        playAgain!!.setOnClickListener {
            val intent = Intent(this@PlayAgain, MainGameActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Setting typefaces for textview and button
        val typeface = Typeface.createFromAsset(assets, "fonts/Cambria.ttf")
        playAgain!!.setTypeface(typeface)
        wrongAnsText!!.setTypeface(typeface)
    }

    override fun onBackPressed() {
        finish()
    }
}