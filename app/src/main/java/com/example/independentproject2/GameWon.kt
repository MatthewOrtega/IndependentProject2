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

class GameWon : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_won)
    }

    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    fun PlayAgain(view: View?) {
        val intent = Intent(this@GameWon, MainGameActivity::class.java)
        startActivity(intent)
        finish()
    }
}