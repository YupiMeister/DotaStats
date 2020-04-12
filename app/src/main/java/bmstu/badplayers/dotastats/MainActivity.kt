package bmstu.badplayers.dotastats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger
import android.util.Log
import bmstu.badplayers.dotastats.fragment.LoginFragment
import bmstu.badplayers.dotastats.fragment.MyClickListener
import bmstu.badplayers.dotastats.fragment.WebViewFragment

class MainActivity : AppCompatActivity(), MyClickListener {
    private var loginFragment: LoginFragment? = null
    private var webViewFragment: WebViewFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginFragment = supportFragmentManager.findFragmentByTag(LoginFragment.TAG) as LoginFragment?
        webViewFragment = supportFragmentManager.findFragmentByTag(WebViewFragment.TAG) as WebViewFragment?

        if (loginFragment == null) {
            loginFragment = LoginFragment()
        }

        if (webViewFragment == null) {
            webViewFragment = WebViewFragment()
        }

        showLoginFragment()
    }

    private fun showLoginFragment() {
        if (supportFragmentManager.findFragmentByTag(LoginFragment.TAG) != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.content, loginFragment!!, LoginFragment.TAG).commit()
    }

    override fun onSoloClick() {
        showWebViewFragment()
    }

    //вынести нахуй отсюда? не? Пока нет
    private fun showWebViewFragment() {
        if (supportFragmentManager.findFragmentByTag(WebViewFragment.TAG) != null) {
            Log.wtf(MainActivity::class.java.toString(), "")
            return
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, webViewFragment!!, WebViewFragment.TAG)
            .addToBackStack(WebViewFragment.TAG)
            .commit()
    }
}