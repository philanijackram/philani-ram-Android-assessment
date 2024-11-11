package com.glucode.about_you.about.viewmodel

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.util.DataBindingAdapterUtil

class ProfileViewViewModel(var engineer: Engineer, var fragment: Fragment) : BaseObservable() {
    var photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>

    init {
        photoPickerLauncher = registerPhotoPickerLauncher(fragment);
    }

    fun registerPhotoPickerLauncher(fragment: Fragment): ActivityResultLauncher<PickVisualMediaRequest> {
        return fragment.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                handleSelectedImage(uri)
            }
        }
    }

    fun handleSelectedImage(uri: Uri) {
        engineer.defaultImageName = uri.toString()
        notifyChange()
    }

    fun launchPhotoPicker() {
        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    @Bindable
    fun getName(): String = engineer.name

    @Bindable
    fun getRole(): String = engineer.role

    @Bindable
    fun getYears(): String = engineer.quickStats.years.toString()

    @Bindable
    fun getCoffees(): String = engineer.quickStats.coffees.toString()

    @Bindable
    fun getBugs(): String = engineer.quickStats.bugs.toString()

    fun getProfilePicture(): Uri {
        return Uri.parse(engineer.defaultImageName)
    }

    fun onClickImage(view: View) {
        launchPhotoPicker()
    }
}

