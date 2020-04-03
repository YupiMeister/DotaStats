package bmstu.badplayers.dotastats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bmstu.badplayers.dotastats.steamweb.SteamOpenID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val steamOpenID = SteamOpenID()
        }
    }
}
