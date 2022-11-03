package com.example.cse227_android_kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SQLLiteExample : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqllite_example)

        // below code is to add on click
        // listener to our add name button
        val addName: Button = findViewById(R.id.addName)
        val printName: Button = findViewById(R.id.printName)
        val enterName: EditText = findViewById(R.id.enterName)
        val enterAge: EditText = findViewById(R.id.enterAge)
        val Name: TextView = findViewById(R.id.Name)
        val Age:TextView= findViewById(R.id.Age)
        val del:Button= findViewById(R.id.delete)
        addName.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = SQLLiteDBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterName.text.clear()
            enterAge.text.clear()
        }

        // below code is to add on click
        // listener to our print name button
        printName.setOnClickListener{

            Name.text=""
            Age.text=""
            // creating a DBHelper class
            // and passing context to it
            val db = SQLLiteDBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
        del.setOnClickListener{
            val db = SQLLiteDBHelper(this, null)
            db.delAll()
        }
    }
}
