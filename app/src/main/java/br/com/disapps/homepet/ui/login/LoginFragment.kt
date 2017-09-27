package br.com.disapps.homepet.ui.login

import android.os.Bundle

import android.text.TextUtils
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.profile.ProfileFragment
import br.com.disapps.homepet.ui.signup.SignupFragment
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by diefferson.santos on 31/08/17.
 */

class LoginFragment : AppFragment<ILoginView, LoginPresenter>(), ILoginView {

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override val fragmentTag: String
        get() = LoginFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_login

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(HomePet.instance!!.restApi!!, HomePet.instance!!.preferences!!)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appActivityListener!!.setTitle("Entrar")
        setupLoadingFragment(loading_view)

        login_bt.setOnClickListener { login() }
        signup_bt.setOnClickListener { signup() }
    }

    private fun login() {
        if (validate()) {
            getPresenter().login(email.text.toString(), password.text.toString())
        }
    }

    private fun signup() {
        appActivityListener!!.replaceAndBackStackFragment(SignupFragment.newInstance())
    }

    override fun loginSuccessfull() {
        appActivityListener!!.replaceFragment(ProfileFragment.newInstance())
    }

    private fun validate(): Boolean {

        var valid = true

        if (TextUtils.isEmpty(email.text.toString())) {
            valid = false
            email.error = "Campo obrigatorio"
        }

        if (TextUtils.isEmpty(password!!.text.toString())) {
            valid = false
            password.error = "Campo obrigatorio"
        }

        return valid
    }


}
