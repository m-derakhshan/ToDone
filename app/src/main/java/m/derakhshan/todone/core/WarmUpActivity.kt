package m.derakhshan.todone.core

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import m.derakhshan.todone.core.presentation.MainActivity

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class WarmUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}