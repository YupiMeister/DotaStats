package bmstu.badplayers.dotastats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bmstu.badplayers.dotastats.fragment.steamId
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        steam_id.text = steamId?.ID
    }
}