package bmstu.badplayers.dotastats.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import bmstu.badplayers.dotastats.ProfileActivity
import bmstu.badplayers.dotastats.R
import butterknife.ButterKnife

class WebViewFragment : Fragment() {
    companion object {
        const val TAG = "WebViewFragment"
        const val REALMPARAM = "PageLogin"
    }

    private var mWebView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)

        mWebView = view.findViewById(R.id.webview) as WebView
        mWebView!!.settings.javaScriptEnabled = true

        val endpoint = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALMPARAM + "&" +
                "openid.return_to=https://" + REALMPARAM + "/signin/";

        mWebView!!.loadUrl(endpoint)

        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                view: WebView?, url: String?,
                favicon: Bitmap?
            ) {
                val Url = Uri.parse(url)
                Log.d("Started URL:", Url.authority as String)

                if (Url.authority!!.contains(REALMPARAM.toLowerCase())) {
                    mWebView!!.stopLoading()
                    val userAccountUrl: Uri = Uri.parse(Url.getQueryParameter("openid.identity"))
                    val userId = userAccountUrl.lastPathSegment

                    Toast.makeText(activity?.applicationContext, "Start!", Toast.LENGTH_SHORT).show();
                    // Do whatever you want with the user's steam id
                    Log.d("MySteamId", userId as String)
                    steamId = Steam_ID(userId)
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                val Url = Uri.parse(url)
                if (Url.authority!!.contains(REALMPARAM.toLowerCase())) {
                    Toast.makeText(activity?.applicationContext, "Finish!", Toast.LENGTH_SHORT).show();
                    startActivity(Intent(activity, ProfileActivity::class.java))
                }
            }
        }
    }
}
