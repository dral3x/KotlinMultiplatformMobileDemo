package com.spreaker.kmm.androidApp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spreaker.kmm.androidApp.R
import com.spreaker.kmm.shared.domain.Greeting
import com.spreaker.kmm.shared.domain.models.Message

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val model: RoomViewModel by viewModels { ViewModelFactory.getInstance() }
        model.getAllMessages(42).observe(this, Observer<List<Message>> { messages ->
            // Update UI
            tv.text = messages[0].toString()
        })
    }

}