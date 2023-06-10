package com.shubhasai.biocraft

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.shubhasai.biocraft.ViewModels.ProfileViewModel
import com.shubhasai.biocraft.databinding.FragmentCardBinding
import com.shubhasai.biocraft.databinding.FragmentProfileBinding
import java.io.File
import java.io.FileOutputStream

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var viewModel: ProfileViewModel
    val text:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getUser().observe(viewLifecycleOwner) { user ->
            // Update UI with user data
            if (user != null) {
                text.add("Hi, I am ")
                text.add(user.name + " ")
                text.add("I am from ")
                text.add(user.locality)
                text.add(user.dist)
                text.add(user.state)
                text.add(" ")
                text.add("Here are my Social Profiles where we can connect: ")
                text.add(user.linkedin)
                text.add(user.instagram)
                text.add(user.facebook)
                text.add(user.github)
                text.add(user.youtube)
                binding.userName.text = user.name
                binding.address.text = user.locality+", "+user.dist+", "+user.state
                binding.email.text = user.email
                binding.phone.text = user.phone
            }
            val bitmap = generateQRCode(text.toString(), 500, R.color.darkblue)
            binding.profileQr.setImageBitmap(bitmap)
        }
        binding.btnShare.setOnClickListener {
            sharehealthpassport()
        }
        return binding.root
    }
    private fun generateQRCode(data: String, size: Int, color: Int): Bitmap? {
        try {
            val writer = QRCodeWriter()
            val bitMatrix: BitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) color else Color.WHITE)
                }
            }
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }
    private fun getImageUri(bitmap: Bitmap): Uri {
        val imageFile = File(requireActivity().cacheDir, "qr_image.png")
        val outputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()
        return FileProvider.getUriForFile(requireActivity(), "com.shubhasai.biocraft.fileprovider", imageFile)
    }
    private fun sharehealthpassport(){
        //val qrBitmap = (binding.qrCode.drawable as BitmapDrawable).bitmap
        val qrBitmap = createBitmapFromView(binding.cardview)

// Create the share intent
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*" // Set the MIME type to image
            putExtra(Intent.EXTRA_STREAM, getImageUri(qrBitmap)) // Add the QR code image as an attachment
            putExtra(Intent.EXTRA_TEXT, text.toString()) // Add some text as the message body
        }

// Show the share sheet
        startActivity(Intent.createChooser(shareIntent, "Share Your Profile"))
    }
    fun createBitmapFromView(view: View): Bitmap {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}