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

class TriviaQuestion : Activity {
    private var id = 0
    var question: String
    var optA: String
    var optB: String
    var optC: String
    var optD: String
    var answer: String

    constructor(q: String, oa: String, ob: String, oc: String, od: String, ans: String) {
        question = q
        optA = oa
        optB = ob
        optC = oc
        optD = od
        answer = ans
    }

    constructor() {
        id = 0
        question = ""
        optA = ""
        optB = ""
        optC = ""
        optD = ""
        answer = ""
    }

    fun setId(i: Int) {
        id = i
    }
}