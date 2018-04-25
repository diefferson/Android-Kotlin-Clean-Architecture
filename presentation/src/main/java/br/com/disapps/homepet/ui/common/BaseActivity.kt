package br.com.disapps.homepet.ui.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by diefferson on 09/03/2018.
 */
abstract class BaseActivity: AppCompatActivity(){

    abstract val viewModel: BaseViewModel
    abstract val activityLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}