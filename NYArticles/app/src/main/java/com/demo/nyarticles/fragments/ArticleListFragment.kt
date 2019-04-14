package com.demo.nyarticles.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.nyarticles.MainActivity
import com.demo.nyarticles.R
import com.demo.nyarticles.adapters.ArticleListAdapter
import com.demo.nyarticles.listeners.RecyclerViewClickListener
import com.demo.nyarticles.models.ArticlesModel
import com.demo.nyarticles.network.RequestCall
import kotlinx.android.synthetic.main.fragment_news_list.*

class ArticleListFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var articleListAdapter: ArticleListAdapter
    var articles : ArrayList<ArticlesModel.Result> = ArrayList()
    lateinit var requestCall: RequestCall
    var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCall = RequestCall(mainActivity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if(isFirst){
            requestCall.fetchPopularArticles(articleFetchListener)
            isFirst = false
        }

        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadActionBar()

        articleListAdapter = ArticleListAdapter(mainActivity, articles, recyclerViewClickListener)
        recycle_articles.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        recycle_articles.adapter = articleListAdapter

        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider)!!)
        recycle_articles.addItemDecoration(itemDecorator)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    var articleFetchListener = object : ArticleFetchListener{
        override fun onFetchSuccess(articlesModel: ArticlesModel) {
            if(articlesModel != null){
                articles.clear()
                articles.addAll(articlesModel.results!!)
                articleListAdapter.notifyDataSetChanged()
            }
        }

        override fun onFetchFailure(errorMsg: String) {

        }

    }

    var recyclerViewClickListener = object : RecyclerViewClickListener {
        override fun onItemClick(pos: Int) {
            var result = articles.get(pos)
            var articleWebFragment = ArticleWebFragment.newInstance(result.url!!)
            mainActivity.loadFragment(articleWebFragment, "webPage", true)
        }

    }

    interface ArticleFetchListener{
        fun onFetchSuccess(articlesModel: ArticlesModel)
        fun onFetchFailure(errorMsg:String)
    }

    fun loadActionBar(){
        val actionBar = mainActivity!!.supportActionBar
        val inflater = LayoutInflater.from(context)
        var customBar = inflater.inflate(R.layout.action_main, null)
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(false)
        actionBar!!.customView = customBar
        actionBar.show()
    }

    companion object {
        var fragment:ArticleListFragment ?= null

        // TODO: Customize parameter initialization
        fun newInstance():ArticleListFragment {
            if(fragment == null) {
                fragment = ArticleListFragment()
            }
            val args = Bundle()
            fragment?.setArguments(args)
            return fragment as ArticleListFragment
        }
    }
}
