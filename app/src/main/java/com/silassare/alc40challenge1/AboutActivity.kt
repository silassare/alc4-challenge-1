package com.silassare.alc40challenge1

import android.content.Context
import android.net.ConnectivityManager
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        web_view.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = object : WebViewClient() {

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    handler?.proceed()
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    progress_bar.visibility = View.VISIBLE
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    progress_bar.visibility = View.GONE
                }
            }

        }

        if (isConnected) {
            web_view.loadUrl(getString(R.string.about_url))
        } else {
            Snackbar.make(
                root, getString(R.string.check_internet),
                Snackbar.LENGTH_INDEFINITE
            ).setAction(getString(R.string.reload)) {
                web_view.loadUrl(getString(R.string.about_url))
            }.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

val Context.isConnected: Boolean
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager
            .activeNetworkInfo?.isConnected == true && connectivityManager.activeNetworkInfo != null
    }