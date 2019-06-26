package dev.nogueiras.navigation.dashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.nogueiras.navigation.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var userLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        userLabel = findViewById(R.id.logged_user)

        intent?.extras?.let {
            val arguments = DashboardActivityArgs.fromBundle(it)
            userLabel.text = arguments.user.username
        }
    }
}
