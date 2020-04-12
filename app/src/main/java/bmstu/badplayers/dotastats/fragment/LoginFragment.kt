package bmstu.badplayers.dotastats.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bmstu.badplayers.dotastats.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), MyClickListener {

    companion object {
        const val TAG = "LoginFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_steam.setOnClickListener {
            onSoloClick()
        }
    }

    override fun onSoloClick() {
        if (activity == null || activity !is MyClickListener) {
            return
        }

        (activity as MyClickListener).onSoloClick()
    }
}