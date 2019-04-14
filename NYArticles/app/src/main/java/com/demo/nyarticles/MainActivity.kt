package com.demo.nyarticles

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.demo.nyarticles.fragments.ArticleListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.linear_main_container, ArticleListFragment.newInstance(), "mymenu")
                .commit()

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(false)
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setDisplayShowCustomEnabled(true)
    }

    internal var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        }else{
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)


        }
    }

    fun loadFragment(fragment: Fragment, name: String, addToStack:Boolean) {
        val fragmentTag = name

        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(name, 0)

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            val ft = manager.beginTransaction()
            ft.replace(R.id.linear_main_container, fragment, fragmentTag)
            //            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top)
            if(addToStack) {
                ft.addToBackStack(name)
            }
            ft.commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
