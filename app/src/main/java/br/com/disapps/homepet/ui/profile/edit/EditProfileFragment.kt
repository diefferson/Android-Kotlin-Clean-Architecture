package br.com.disapps.homepet.ui.profile.edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.ui.common.AppFragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.snowmanlabs.imagepickercropper.PickerActivity
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import com.google.firebase.auth.AuthResult




/**
 * Created by diefferson on 06/11/2017.
 */
class EditProfileFragment : AppFragment<IEditProfileView, EditProfilePresenter>() , IEditProfileView {

    private var mImageUri: Uri? = null

    private val mUser: User by lazy {
        HomePet.instance!!.preferences!!.getUser()
    }

    companion object {
        fun newInstance() = EditProfileFragment()
        const val IMAGE_REQUEST_CODE = 88
    }

    override val fragmentTag: String
        get() = EditProfileFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_edit_profile

    override fun createPresenter() = EditProfilePresenter(HomePet.instance!!.restApi!!, HomePet.instance!!.preferences!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        appActivityListener!!.displayArrowBack()

        appActivityListener!!.setTitle(getString(R.string.edit_profile))

        setupLoadingFragment(loading_view)

        fillUser(mUser)

        dv_camera_icon.setOnClickListener { startActivityForResult(PickerActivity.getIntent(context, getString(R.string.select_image)), IMAGE_REQUEST_CODE) }

        bt_save.setOnClickListener { saveUser() }
    }

    private fun fillUser(user : User){
        if(user.avatar != null){
            dv_profile_avatar.setImageURI(user.avatar)
        }
        et_name.setText(user.name)
        et_email.setText(user.email)
        et_phone.setText(user.phone)
        et_address.setText(user.address)
        et_city.setText(user.city)
        et_uf.setText(user.uf)
    }

    private fun saveUser(){
        if(validate()){
            mUser.name = et_name.text.toString()
            mUser.email = et_email.text.toString()
            mUser.phone = et_phone.text.toString()
            mUser.address = et_address.text.toString()
            mUser.city = et_city.text.toString()
            mUser.uf = et_uf.text.toString()
            getPresenter().patchUser(mUser)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK) {
            if (IMAGE_REQUEST_CODE == requestCode) {
                if (data.extras!!.containsKey("image")) {
                    mImageUri = data.extras!!.get("image") as Uri
                    mUser.imageUri = mImageUri
                    dv_profile_avatar.setImageURI(mImageUri.toString())
                }
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true

        if (TextUtils.isEmpty(et_name.text.toString())) {
            isValid = false
            til_name.error = getString(R.string.err_required_field)
        }

        if (TextUtils.isEmpty(et_email.getText().toString())) {
            isValid = false
            til_email.error = getString(R.string.err_required_field)
        }

        return isValid
    }

    override fun patchUserSuccessfull() {
        activity.onBackPressed()
    }


}