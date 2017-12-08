package br.com.disapps.homepet.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import java.net.HttpURLConnection
import br.com.disapps.homepet.util.rx.RxHttpError

/**
 * Created by diefferson.santos on 23/08/17.
 */

abstract class AppFragment<V : AppView, P : MvpPresenter<V>> : MvpFragment<V, P>(), IErrorHandlerView, AppView {

    protected var appActivityListener: IAppActivityListener? = null
        private set

    private var loadingView: View? = null

    override fun hasInternetConnection(): Boolean {
        return appActivityListener!!.hasInternetConnection()
    }

    fun setupLoadingFragment(loadingView: View) {
        this.loadingView = loadingView
    }

    override fun showLoading(cancelable: Boolean) {
        if (loadingView != null) {
            loadingView!!.visibility = View.VISIBLE
        }
    }

    override fun dismissLoading() {
        if (loadingView != null) {
            loadingView!!.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayout, container, false)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        appActivityListener = context as IAppActivityListener?
    }

    override fun error(error: RxHttpError) {
        when (error.errorCode) {
            RxHttpError.SOCKETTIMEOUT_CODE, RxHttpError.UNKNOWNHOST_CODE -> setMessageContent(error.errorMessage)
            RxHttpError.NO_CONNECTIVITY_CODE -> setMessageContent(error.errorMessage)
            HttpURLConnection.HTTP_UNAUTHORIZED -> setMessageContent("Usuário ou senha invalidos")
            HttpURLConnection.HTTP_CONFLICT -> setMessageContent("Usuário já cadastrado.")
            422 -> setMessageContent(error.errorMessage)
            else -> setMessageContent(error.errorMessage)
        }
    }

    override fun error(e: Throwable) {
        e.printStackTrace()
        setMessageContent(e.message!!)
    }

    protected fun setMessageContent(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    abstract val fragmentTag: String

    abstract val fragmentLayout: Int

}
