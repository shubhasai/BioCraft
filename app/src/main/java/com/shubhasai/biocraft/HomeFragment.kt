package com.shubhasai.biocraft

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.shubhasai.biocraft.databinding.FragmentHomeBinding
import com.shubhasai.biocraft.models.achievements


class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding:FragmentHomeBinding
    private lateinit var clipboardManager: ClipboardManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val linkedintext = sharedPreferences.getString("linkedin", " ")
        val whatsapptext = sharedPreferences.getString("whatsapp", " ")
        binding.tvLinkedinmessage.setText(linkedintext)
        binding.tvWhatsApp.setText(whatsapptext)
        binding.bntCopylinkedinmsg.setOnClickListener {
            val text = binding.tvLinkedinmessage.text.toString()
            val clipData = ClipData.newPlainText("TextView Text", text)
            clipboardManager.setPrimaryClip(clipData)
        }
        binding.btnWhatsappTextCopy.setOnClickListener {
            val text = binding.tvWhatsApp.text.toString()
            val clipData = ClipData.newPlainText("TextView Text", text)
            clipboardManager.setPrimaryClip(clipData)
        }
        binding.editLinkedin.setOnClickListener {
            openLinkedinDrawer()
        }
        binding.editWhatapp.setOnClickListener {
            openWhatsappDrawer()
        }
        return binding.root
    }
    private fun openLinkedinDrawer() {
        val linkedindrawerView = layoutInflater.inflate(R.layout.add_linkedintext, null)
        val linkedindrawer = activity?.let { BottomSheetDialog(it) }
        if (linkedindrawer != null) {
            linkedindrawer.setContentView(linkedindrawerView)
            linkedindrawer.window?.setGravity(Gravity.BOTTOM)
            linkedindrawer.show()

            val btnSave = linkedindrawer.findViewById<Button>(R.id.btn_savelinkedinmsg)
            btnSave?.setOnClickListener {
                retrieveAchievementField(linkedindrawerView)
                linkedindrawer.dismiss()
            }
        }

    }

    private fun retrieveAchievementField(linkedindrawer: View) {
        val msg =
            linkedindrawer.findViewById<TextInputEditText>(R.id.etlinkedinmsg).text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("linkedin", msg)
        editor.apply()
        // Do something with the retrieved achievement details
        // For example, you can log them or pass them to another function
        // or save them to a database
    }
    private fun openWhatsappDrawer() {
        val whatsappdrawerView = layoutInflater.inflate(R.layout.add_whatsapptext, null)
        val whatsappd = activity?.let { BottomSheetDialog(it) }
        if (whatsappd != null) {
            whatsappd.setContentView(whatsappdrawerView)
            whatsappd.window?.setGravity(Gravity.BOTTOM)
            whatsappd.show()

            val btnSave = whatsappd.findViewById<Button>(R.id.btn_saveWhatsappMsg)
            btnSave?.setOnClickListener {
                retrievewhatsappField(whatsappdrawerView)
                whatsappd.dismiss()
            }
        }

    }

    private fun retrievewhatsappField(whatsappdrawer: View) {
        val msg =
            whatsappdrawer.findViewById<TextInputEditText>(R.id.etwhatsappnmsg).text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("whatsapp", msg)
        editor.apply()
        // Do something with the retrieved achievement details
        // For example, you can log them or pass them to another function
        // or save them to a database
    }
}