package com.example.independentproject2

import android.app.Dialog
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import java.util.*

class MainGameActivity : AppCompatActivity() {
    var buttonA: Button? = null
    var buttonB: Button? = null
    var buttonC: Button? = null
    var buttonD: Button? = null
    var questionText: TextView? = null
    var triviaQuizText: TextView? = null
    var timeText: TextView? = null
    var resultText: TextView? = null
    var coinText: TextView? = null
    var triviaQuizHelper: TriviaQuizHelper? = null
    var currentQuestion: TriviaQuestion? = null
    var list: List<TriviaQuestion?>? = null
    var qid = 0
    var timeValue = 20
    var coinValue = 0
    var countDownTimer: CountDownTimer? = null
    var tb: Typeface? = null
    var sb: Typeface? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_main)

        //Initializing variables
        questionText = findViewById(R.id.triviaQuestion) as TextView?
        buttonA = findViewById(R.id.buttonA)
        buttonB = findViewById(R.id.buttonB)
        buttonC = findViewById(R.id.buttonC)
        buttonD = findViewById(R.id.buttonD)
        triviaQuizText = findViewById(R.id.triviaQuizText) as TextView?
        timeText = findViewById(R.id.timeText) as TextView?
        resultText = findViewById(R.id.resultText) as TextView?
        coinText = findViewById(R.id.coinText) as TextView?

        //Setting typefaces for textview and buttons
        tb = Typeface.createFromAsset(getAssets(), "fonts/Cambria.ttf")
        sb = Typeface.createFromAsset(getAssets(), "fonts/Cambria.ttf")
        triviaQuizText.setTypeface(sb)
        questionText.setTypeface(tb)
        buttonA.setTypeface(tb)
        buttonB.setTypeface(tb)
        buttonC.setTypeface(tb)
        buttonD.setTypeface(tb)
        timeText.setTypeface(tb)
        resultText.setTypeface(sb)
        coinText.setTypeface(tb)

        //Our database helper class
        triviaQuizHelper = TriviaQuizHelper(this)
        //Make database writable
        triviaQuizHelper!!.writableDatabase

        //It will check if the questions are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (triviaQuizHelper.getAllOfTheQuestions().size == 0) {

            triviaQuizHelper!!.allQuestion()
        }

        list = triviaQuizHelper.getAllOfTheQuestions()

        //Shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list)

        //currentQuestion will hold the question, 4 options, and answer for particular id
        currentQuestion = list!![qid]

        //countDownTimer
        countDownTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timeText.setText(timeValue.toString() + "\"")

                //With each iteration decrement the time by 1 sec
                timeValue -= 1

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    resultText.setText(getString(R.string.timeup))

                    //Since user is out of time he won't be able to click any buttons
                    disableButton()
                }
            }

            //Now user is out of time
            override fun onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp()
            }
        }.start()

        //This method will set the question and four options
        updateQueAndOptions()
    }

    fun updateQueAndOptions() {

        //This method will setText for question and options
        questionText.setText(currentQuestion.getQuestion())
        buttonA.setText(currentQuestion.getOptA())
        buttonB.setText(currentQuestion.getOptB())
        buttonC.setText(currentQuestion.getOptC())
        buttonD.setText(currentQuestion.getOptD())
        timeValue = 20

        //Now since the user has ans correct just reset timer back for another question by cancel and start
        countDownTimer.cancel()
        countDownTimer.start()

        //set the value of coin text
        coinText.setText(coinValue.toString())
        //Now since user has ans correct increment the coinvalue
        coinValue++
    }

    //Onclick listener for first button
    fun buttonA(view: View?) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptA() == currentQuestion.getAnswer()) {
            buttonA.setButtonColor(
                ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.lightGreen
                )
            )
            //Check if user has not exceeds the question limit
            if (qid < list!!.size - 1) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton()

                //Show the dialog that ans is correct
                correctDialog()
            } else {
                gameWon()
            }
        } else {
            gameLostPlayAgain()
        }
    }

    //Onclick listener for sec button
    fun buttonB(view: View?) {
        if (currentQuestion.getOptB() == currentQuestion.getAnswer()) {
            buttonB.setButtonColor(
                ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.lightGreen
                )
            )
            if (qid < list!!.size - 1) {
                disableButton()
                correctDialog()
            } else {
                gameWon()
            }
        } else {
            gameLostPlayAgain()
        }
    }

    //Onclick listener for third button
    fun buttonC(view: View?) {
        if (currentQuestion.getOptC() == currentQuestion.getAnswer()) {
            buttonC.setButtonColor(
                ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.lightGreen
                )
            )
            if (qid < list!!.size - 1) {
                disableButton()
                correctDialog()
            } else {
                gameWon()
            }
        } else {
            gameLostPlayAgain()
        }
    }

    //Onclick listener for fourth button
    fun buttonD(view: View?) {
        if (currentQuestion.getOptD() == currentQuestion.getAnswer()) {
            buttonD.setButtonColor(
                ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.lightGreen
                )
            )
            if (qid < list!!.size - 1) {
                disableButton()
                correctDialog()
            } else {
                gameWon()
            }
        } else {
            gameLostPlayAgain()
        }
    }

    //This method will navigate from current activity to GameWon
    fun gameWon() {
        val intent = Intent(this, GameWon::class.java)
        startActivity(intent)
        finish()
    }

    //this method will navigate user to the activity PlayAgain
    fun gameLostPlayAgain() {
        val intent = Intent(this, PlayAgain::class.java)
        startActivity(intent)
        finish()
    }

    //this method will navigate user to the activity Time_Up
    fun timeUp() {
        val intent = Intent(this, Time_Up::class.java)
        startActivity(intent)
        finish()
    }

    protected fun onRestart() {
        super.onRestart()
        countDownTimer.start()
    }

    //When activity is destroyed then this will cancel the timer
    protected fun onStop() {
        super.onStop()
        countDownTimer.cancel()
    }

    //This will pause the time
    protected fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

    fun onBackPressed() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

    //This dialog is show to the user after he answers correctly
    fun correctDialog() {
        val dialogCorrect = Dialog(this@MainGameActivity)
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (dialogCorrect.window != null) {
            val colorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialogCorrect.window!!.setBackgroundDrawable(colorDrawable)
        }
        dialogCorrect.setContentView(R.layout.dialog_correct)
        dialogCorrect.setCancelable(false)
        dialogCorrect.show()

        //Setting type faces
        correctText.setTypeface(sb)
        buttonNext.setTypeface(sb)

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(View.OnClickListener { //This will dismiss the dialog
            dialogCorrect.dismiss()
            //it will increment the question number
            qid++
            //get the question and 4 option and store in the currentQuestion
            currentQuestion = list!![qid]
            //Now this method will set the new question and 4 options
            updateQueAndOptions()
            //reset the color of buttons back to white
            resetColor()
            //Enable button - remember we had disable them when user ans was correct in there particular button methods
            enableButton()
        })
    }

    //This method will make button color white again since our one button color was turned green
    fun resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
    }

    //This method will disable all the option button
    fun disableButton() {
        buttonA.setEnabled(false)
        buttonB.setEnabled(false)
        buttonC.setEnabled(false)
        buttonD.setEnabled(false)
    }

    //This method will all enable the option buttons
    fun enableButton() {
        buttonA.setEnabled(true)
        buttonB.setEnabled(true)
        buttonC.setEnabled(true)
        buttonD.setEnabled(true)
    }
}