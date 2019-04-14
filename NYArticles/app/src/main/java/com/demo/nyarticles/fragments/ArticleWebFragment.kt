package com.demo.nyarticles.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.demo.nyarticles.MainActivity
import com.demo.nyarticles.R
import kotlinx.android.synthetic.main.fragment_news_web.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ArticleWebFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadActionBar()
        var webUrl = arguments!!.getString("webUrl")

        web_news.settings.javaScriptEnabled = true
        web_news.settings.domStorageEnabled = true
        web_news.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        web_news.webChromeClient = WebChromeClient()
        web_news.webViewClient = WebViewClient()
        web_news.loadUrl(webUrl)
    }


    companion object {
        var fragment:ArticleWebFragment ?= null

        // TODO: Customize parameter initialization
        fun newInstance(webUrl: String):ArticleWebFragment {
            if(fragment == null) {
                fragment = ArticleWebFragment()
            }
            val args = Bundle()
            args.putString("webUrl", webUrl)
            fragment?.setArguments(args)
            return fragment as ArticleWebFragment
        }
    }

    fun loadActionBar(){
        val actionBar = mainActivity!!.supportActionBar
        val inflater = LayoutInflater.from(context)
        var customBar = inflater.inflate(R.layout.action_main, null)
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.customView = customBar
        actionBar.show()
    }

}
