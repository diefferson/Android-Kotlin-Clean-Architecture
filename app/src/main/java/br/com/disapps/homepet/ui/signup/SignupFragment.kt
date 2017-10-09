package br.com.disapps.homepet.ui.signup

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.ui.common.AppFragment
import kotlinx.android.synthetic.main.fragment_signup.*

/**
 * Created by diefferson.santos on 31/08/17.
 */

class SignupFragment : AppFragment<ISignUpView, SignupPresenter>(), ISignUpView {

    override val fragmentTag: String
        get() = SignupFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_signup

    override fun createPresenter() = SignupPresenter(HomePet.instance!!.restApi!!, HomePet.instance!!.preferences!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appActivityListener!!.setTitle("Cadastrar")

        appActivityListener!!.displayArrowBack()

        setupLoadingFragment(loading_view)

        signup_bt.setOnClickListener { signup()}
    }

    private fun signup() {
        if (validate()) {
            getPresenter().signup(name.text.toString(), email.text.toString(), password.text.toString())
        }
    }

    private fun validate(): Boolean {
        var valid = true

        if (TextUtils.isEmpty(name.text.toString())) {
            valid = false
            name.error = "Campo obrigatorio"
        }

        if (TextUtils.isEmpty(email.text.toString())) {
            valid = false
            email.error = "Campo obrigatorio"
        }

        if (TextUtils.isEmpty(password.text.toString())) {
            valid = false
            password.error = "Campo obrigatorio"
        }

        if (TextUtils.isEmpty(password_validate.text.toString())) {
            valid = false
            password_validate.error = "Campo obrigatorio"
        }

        if (password_validate.text.toString() != password.text.toString()) {
            valid = false
            password_validate.error = "Senhas não conferem"
            password.error = "Senhas não conferem"
        }

        return valid
    }

    override fun signupSucess() {
        Snackbar.make(password_validate, "Usuário criado com sucesso!", Snackbar.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = SignupFragment()
    }

}
