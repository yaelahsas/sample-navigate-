package com.example.testingninik

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.testingninik.app.ApiConfig
import com.example.testingninik.databinding.FragmentEbookDonasiBinding
import com.example.testingninik.model.ResponModel
import com.nbsp.materialfilepicker.MaterialFilePicker
import com.nbsp.materialfilepicker.ui.FilePickerActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EbookDonasi.newInstance] factory method to
 * create an instance of this fragment.
 */
class EbookDonasi : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentEbookDonasiBinding
    var selectedImage: Uri? = null
    var pdfPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ebook_donasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEbookDonasiBinding.bind(view)

        binding.pilihFile.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "application/pdf"
//            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 1)

            launchPicker()
        }
        binding.btnKirimPengajuan.setOnClickListener {
            uploadFile("35", binding.edtJudulBuku.text.toString(), pdfPath)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("datanya", data.toString())

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //the image URI
//            selectedImage = data!!.data

            val path = data?.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
            if (path != null) {
                Log.d("Path: ", path)
                pdfPath = path
                Toast.makeText(requireActivity(), "Picked file: $path", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadFile(
        id_donatur: String,
        judul_buku: String,
        filePath: String
    ) {
        pDialog = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()
        //creating a file
        val file = File(filePath)

        //creating request body for file
        val requestFile: RequestBody =
            RequestBody.create(
                MediaType.parse("application/pdf"),
                file
            )
        val descBody = RequestBody.create(MediaType.parse("text/plain"), id_donatur)
        val descBody2 = RequestBody.create(MediaType.parse("text/plain"), judul_buku)


        //creating our api

        //creating a call and calling the upload image method
        ApiConfig.instanceRetrofit.donasi_ebook(
            requestFile,
            descBody,
            descBody2
        ).enqueue(object : Callback<ResponModel> {
            override fun onResponse(
                call: Call<ResponModel>,
                response: Response<ResponModel>
            ) {
                if (response.isSuccessful) {
                    hideDialog()
                    if (response.body()?.message.equals("success")) {
                        Toast.makeText(
                            requireActivity(),
                            "File Uploaded Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Some error occurred...",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    var pDialog: SweetAlertDialog? = null
    private fun showDialog() {
        if (!pDialog?.isShowing!!) pDialog?.show()
    }

    private fun hideDialog() {
        if (pDialog?.isShowing!!) pDialog!!.dismiss()
    }

    //    private fun getFileName(uri: Uri): String? {
//        var result: String? = null
//        if (uri.scheme == "content") {
//            val cursor: Cursor? =
//                requireActivity().contentResolver.query(uri, null, null, null, null)
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            } finally {
//                cursor?.close()
//            }
//        }
//        if (result == null) {
//            result = uri.lastPathSegment
//        }
//        return result
//    }
    private fun launchPicker() {
        MaterialFilePicker()
            .withSupportFragment(this)
            .withRequestCode(1)
            .withHiddenFiles(true)
            .withFilter(Pattern.compile(".*\\.pdf$"))
            .withTitle("Select PDF file")
            .start()
    }

}