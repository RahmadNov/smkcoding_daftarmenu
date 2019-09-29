package com.rhmd.daftarmenubyrhmd.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.rhmd.daftarmenubyrhmd.R
import com.rhmd.daftarmenubyrhmd.data.MenuDB
import com.rhmd.daftarmenubyrhmd.data.MenuDB.Companion
import com.rhmd.daftarmenubyrhmd.data.MenuModelMakan
import kotlinx.android.synthetic.main.new_fragment.buttonn
import kotlinx.android.synthetic.main.new_fragment.imagebutt
import kotlinx.android.synthetic.main.new_fragment.tambahharga
import kotlinx.android.synthetic.main.new_fragment.tambahnama
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddFragment:Fragment(),PermissionRequest.AcceptedListener,PermissionRequest.DeniedListener {
    override fun onPermissionsAccepted(permissions: Array<out String>) {
        showMessageDialog()
    }

    override fun onPermissionsDenied(permissions: Array<out String>) {
        requestPermissions()
    }

    companion object{
        fun getInstance():AddFragment{
            return AddFragment()
        }
    }
    val GALERY=1
    val CAMERA=2
    var imageData:ByteArray?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db= MenuDB.getInstance(context!!)

        imagebutt.setOnClickListener {
            checkVersion()
        }
        buttonn.setOnClickListener {
            simpanData(db)
        }
    }
    private fun simpanData(db:MenuDB?) : Job {
        return GlobalScope.launch {
            db?.menuDao()?.tambahMakanan(MenuModelMakan(
                namaMenu = tambahnama.text.toString(),
                hargaMenu = tambahharga.text.toString(),
                gambarMenu = imageData
            ))
        }
    }

    private fun checkVersion(){
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.M){
            requestPermissions()
        }else{
            showMessageDialog()
        }
    }
    private fun requestPermissions(){
        val request=permissionsBuilder(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).build()
        request.acceptedListener(this)
        request.deniedListener(this)
        request.send()
    }
    private fun showMessageDialog(){
        val pictureDialog=AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("silahkan pilih")
        val pictureDialogItems= arrayOf(
            "Ambil dari galeri","Ambil dari kamera")
        pictureDialog.setItems(pictureDialogItems){dialog,which->
            when(which){
            0-> pilihGalery()
            1-> pilihKamera()
        }
        }
    pictureDialog.show()
    }
    private fun pilihKamera() {
    val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA)
    }
    private fun pilihGalery() {
        val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,GALERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = Media
                        .getBitmap(activity!!.contentResolver, contentURI)
                    imagebutt.setImageBitmap(bitmap)

                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50 , stream)
                    imageData = stream.toByteArray()

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imagebutt.setImageBitmap(thumbnail)

            val stream = ByteArrayOutputStream()
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 50 , stream)
            imageData = stream.toByteArray()
        }
                }
            }
