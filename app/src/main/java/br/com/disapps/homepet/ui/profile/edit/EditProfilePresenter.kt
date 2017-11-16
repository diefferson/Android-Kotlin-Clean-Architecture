package br.com.disapps.homepet.ui.profile.edit

import android.net.Uri
import android.util.Log
import android.widget.Toast
import br.com.disapps.homepet.BuildConfig
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.response.UserResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.io.File
import java.util.*


/**
 * Created by diefferson on 06/11/2017.
 */
class EditProfilePresenter(private val restApi: RestApi, private val preferences: Preferences) : MvpBasePresenter<IEditProfileView>(){

    private val disposables = CompositeDisposable()

    private val mStorageRef: StorageReference by lazy{
        FirebaseStorage.getInstance().reference
    }


    fun patchUser(user:User) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        if(user.imageUri!= null){
            uploadImage(user, user.imageUri!!)
        }else{
            updateUser(user)
        }

    }

    private fun uploadImage(user:User, file : Uri){

        val riversRef = mStorageRef.child("images/"+user.email!!.hashCode()+".jpg")

        riversRef.putFile(file)
                .addOnSuccessListener({ taskSnapshot ->
                    user.avatar = taskSnapshot.downloadUrl.toString()+"?alt=media"
                    updateUser(user)
                })
                .addOnFailureListener({
                    exception ->  exception.printStackTrace()
                    updateUser(user)
                })
    }

    private fun  updateUser(user:User){
        disposables.add(
        restApi.patchUser(preferences.authTokenWithPrefix, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<UserResponse>() {

                    override fun onNext(response: UserResponse) {
                        preferences.saveUser(response.content!!)
                        if (isViewAttached) {
                            view.dismissLoading()
                            view.patchUserSuccessfull()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            view.dismissLoading()
                            IErrorHandlerHelper.defaultErrorResolver(this@EditProfilePresenter.view, e)
                        }
                    }

                    override fun onComplete() {

                    }
                }))
    }


}