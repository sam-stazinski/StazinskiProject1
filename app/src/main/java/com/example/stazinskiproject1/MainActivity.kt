package com.example.stazinskiproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var working: String? = null
        var past: String? = null

        val window = findViewById<TextView>(R.id.window)


        findViewById<Button>(R.id.one)
            .setOnClickListener {
                Log.d("BUTTONS", "1")
                working = numberPressed("1", working)
                window.text = working
            }

        findViewById<Button>(R.id.two)
            .setOnClickListener {
                Log.d("BUTTONS", "2")
                working = numberPressed("2", working)
                window.text = working
            }

        findViewById<Button>(R.id.three )
            .setOnClickListener {
                Log.d("BUTTONS", "3")
                working = numberPressed("3", working)
                window.text = working
            }

        findViewById<Button>(R.id.four)
            .setOnClickListener {
                Log.d("BUTTONS", "4")
                working = numberPressed("4", working)
                window.text = working
            }

        findViewById<Button>(R.id.five)
            .setOnClickListener {
                Log.d("BUTTONS", "5")
                working = numberPressed("5", working)
                window.text = working
            }

        findViewById<Button>(R.id.six)
            .setOnClickListener {
                Log.d("BUTTONS", "6")
                working = numberPressed("6", working)
                window.text = working
            }

        findViewById<Button>(R.id.seven)
            .setOnClickListener {
                Log.d("BUTTONS", "7")
                working = numberPressed("7", working)
                window.text = working
            }

        findViewById<Button>(R.id.eight)
            .setOnClickListener {
                Log.d("BUTTONS", "8")
                working = numberPressed("8", working)
                window.text = working
            }

        findViewById<Button>(R.id.nine)
            .setOnClickListener {
                Log.d("BUTTONS", "9")
                working = numberPressed("9", working)
                window.text = working
            }

        findViewById<Button>(R.id.dot)
            .setOnClickListener {
                Log.d("BUTTONS", ".")
                working = numberPressed(".", working)
                window.text = working
            }

        findViewById<Button>(R.id.zero)
            .setOnClickListener {
                Log.d("BUTTONS", "0")
                working = numberPressed("0", working)
                window.text = working
            }

        findViewById<Button>(R.id.c)
            .setOnClickListener {
                Log.d("BUTTONS", "C")
                working = null
                past = null
                window.text = "0"
            }

        findViewById<Button>(R.id.sign)
            .setOnClickListener {
                Log.d("BUTTONS", "sign")
                working = toggleNegative(working)
                if (working == null) window.text = "0"
                else window.text = working
            }

        findViewById<Button>(R.id.percent)
            .setOnClickListener {
                Log.d("BUTTONS", "percent")
                working = divideBy100(working)
                if (working == null) window.text = "0"
                else window.text = working
            }

        findViewById<Button>(R.id.plus)
            .setOnClickListener {
                Log.d("BUTTONS", "plus")
                past = calculate(past, "($working)")
                window.text = past
                past = "($past)+"
                working = null
                Log.d("new past", past.toString())
                Log.d("new working", working.toString())
            }

        findViewById<Button>(R.id.equals)
            .setOnClickListener {
                Log.d("BUTTONS", "equals")
                past = calculate(past, "($working)")
                window.text = past
                working = null
                Log.d("new past", past.toString())
                Log.d("new working", working.toString())
            }


    }

    // This is supposed to do most of the mathematics for the program, but unfortunately I ran out of time
    // Takes in two Strings and/or Nulls, returns a string or a null
    fun calculate(past: String?, working: String?): String? {
        Log.d("calculate past", past.toString())
        Log.d("calculate working", working.toString())
        if (past == null && working == null) return null
        if (past == null) return working?.removeSurrounding("(", ")")
        if (working == null) return past.removeSurrounding("(", ")")

        // Regex pattern to match and destructure the input strings
        val pattern = """\(([-]?\d+)\)([+x/-])\(([-]?\d+)\)""".toRegex()

        val matchResult = pattern.matchEntire("$past$working") ?: return "$past$working" // Return the concatenated strings if they don't match the pattern

        // Destructure the match result to extract values and operator
        val (value1, operation, value2) = matchResult.destructured

        return when (operation) {
            "+" -> (value1.toDouble() + value2.toDouble()).toString()
            "-" -> (value1.toDouble() - value2.toDouble()).toString()
            "x" -> (value1.toDouble() * value2.toDouble()).toString()
            "/" -> {
                if (value2.toDouble() == 0.0) {
                    return null // Prevent division by zero
                }
                (value1.toDouble() / value2.toDouble()).toString()
            }
            else -> null
        }
    }

    // This just addes on numbers to the existing number
    // Takes in a String and a String or Null, returns a String
    fun numberPressed(key: String, working: String?): String {
        Log.d("numberPressedFunction", working + key)
        return if (working == null) {
            key
        } else {
            working + key
        }
    }

    // This toggles the negative sign on the working number
    // Takes in a String or Null, returns a String or Null

    fun toggleNegative(s: String?): String? {
        if (s == null) return null

        return if (s.startsWith("-")) {
            s.substring(1)
        } else {
            "-$s"
        }
    }

    // This function divides the working number by 100
    // Takes in a String or Null, returns a String or Null
    fun divideBy100(s: String?): String? {
        if (s == null) return null

        return try {
            val result = s.toDouble() / 100
            result.toString()
        } catch (e: NumberFormatException) {
            null
        }
    }

}