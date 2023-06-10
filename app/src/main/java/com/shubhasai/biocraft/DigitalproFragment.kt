package com.shubhasai.biocraft

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.print.PrintAttributes
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.shubhasai.biocraft.ViewModels.ProfileViewModel
import com.shubhasai.biocraft.adapters.AcheivementAdapter
import com.shubhasai.biocraft.adapters.EducationAdapter
import com.shubhasai.biocraft.adapters.ExtracurricularAdapter
import com.shubhasai.biocraft.adapters.LanguageAdapter
import com.shubhasai.biocraft.adapters.ProjectAdapter
import com.shubhasai.biocraft.adapters.SkillsAdapter
import com.shubhasai.biocraft.adapters.WorkExperienceAdapter
import com.shubhasai.biocraft.databinding.FragmentDigitalproBinding
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.achievements
import com.shubhasai.biocraft.models.extracurricular
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.projects
import com.shubhasai.biocraft.models.skills
import com.shubhasai.biocraft.models.workexperience
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DigitalproFragment : Fragment(), EducationAdapter.EdcDelClicked, WorkExperienceAdapter.WorkDelClicked
    , ProjectAdapter.ProjectDelClicked, LanguageAdapter.LangDelClicked, SkillsAdapter.SkillDelClicked,
    AcheivementAdapter.AcheivementDelClicked
    , ExtracurricularAdapter.ActivityDelClicked  {
    private lateinit var binding:FragmentDigitalproBinding
    private lateinit var viewModel: ProfileViewModel
    private var name:String = ""
    var userid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDigitalproBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getUser().observe(viewLifecycleOwner) { user ->
            // Update UI with user data
            if (user != null) {
                Log.d("name",user.name)
                userid = user.id
                name = user.name
                binding.tvuserName.setText(user.name)
                binding.tvLeetcode.setText(user.leetcode)
                binding.tvemail.setText(user.email)
                binding.tvphone.setText(user.phone)
                binding.tvLink.setText(user.link1)
                binding.tvgithub.setText(user.github)
                binding.tvlinkedin.setText(user.linkedin)
                binding.tvlink2.setText(user.link2)
                binding.tvstackoverflow.setText(user.stackOverflow)
                binding.tvdribble.setText(user.dribble)
                binding.tvyoutube.setText(user.youtube)
                binding.tvaddress.setText(user.locality + ", "+user.dist+", "+user.state+", "+user.pincode)
                if(user.dribble == "") binding.icondribble.visibility = View.INVISIBLE
                if(user.link2 == "") binding.iconlink2.visibility = View.INVISIBLE
                if(user.youtube == "") binding.iconYoutube.visibility = View.INVISIBLE
                if(user.stackOverflow == "") binding.iconstackoverflow.visibility = View.INVISIBLE
                if(user.leetcode == "") binding.iconleetcode.visibility = View.INVISIBLE
                if(user.dribble == "" && user.link2 == "" && user.youtube == "" &&user.stackOverflow == ""  ){
                    binding.tvProfiles.visibility = View.INVISIBLE
                }
            }
        }
        val edu:ArrayList<Education> = ArrayList()
        val work:ArrayList<workexperience> = ArrayList()
        val project:ArrayList<projects> = ArrayList()
        val lang:ArrayList<language> = ArrayList()
        val skill:ArrayList<skills> = ArrayList()
        val activities:ArrayList<extracurricular> = ArrayList()
        val acheivement:ArrayList<achievements> = ArrayList()
        val eduadapter = EducationAdapter(activity,edu,this,2)
        val workadapter = WorkExperienceAdapter(activity,work,this,2)
        val projectadapter = ProjectAdapter(activity,project,this,2)
        val languageadapter = LanguageAdapter(activity,lang,this,2)
        val skilladapter = SkillsAdapter(activity,skill,this,2)
        val extraadapter = ExtracurricularAdapter(activity,activities,this,2)
        val acheivementadapter = AcheivementAdapter(activity,acheivement,this,2)
        binding.rvEducation.layoutManager = LinearLayoutManager(activity)
        binding.rvEducation.adapter = eduadapter
        binding.rvWorkexp.layoutManager = LinearLayoutManager(activity)
        binding.rvWorkexp.adapter = workadapter
        binding.rvProject.layoutManager = LinearLayoutManager(activity)
        binding.rvProject.adapter = projectadapter
        binding.rvactivity.layoutManager = LinearLayoutManager(activity)
        binding.rvactivity.adapter = extraadapter
        binding.rvAcheivements.layoutManager = LinearLayoutManager(activity)
        binding.rvAcheivements.adapter = acheivementadapter
        viewModel.getallEducation().observe(viewLifecycleOwner) { education ->
            if (education != null) {
                binding.one.visibility = View.VISIBLE
                binding.one1.visibility = View.VISIBLE
                edu.clear()
                for(data in education){
                    edu.add(data)
                }
                eduadapter.notifyDataSetChanged()
            }
            else{
                binding.one.visibility = View.GONE
                binding.one1.visibility = View.GONE
            }
        }
        viewModel.getallExp().observe(viewLifecycleOwner) { explist ->
            if (explist != null) {
                binding.three.visibility = View.VISIBLE
                binding.three3.visibility = View.VISIBLE
                work.clear()
                for(data in explist){
                    work.add(data)
                }
                workadapter.notifyDataSetChanged()
            }
            else{
                binding.three.visibility = View.GONE
                binding.three3.visibility = View.GONE
            }
        }
        viewModel.getallLangs().observe(viewLifecycleOwner) { language ->
            if(language!= null){
                lang.clear()
                for(data in language){
                    lang.add(data)
                    val chip = Chip(activity)
                    chip.text = data.nameOfTheLanguage
                    chip.isClickable = true
                    chip.textSize = 22F
                    chip.isCheckable = false
                    chip.setChipBackgroundColorResource(R.color.dgblue)
                    chip.setTextColor(resources.getColor(R.color.white))
                    binding.tvLanguage.addView(chip)
                }
                languageadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallSkill().observe(viewLifecycleOwner) { s ->
            if (s != null) {
                binding.two.visibility = View.VISIBLE
                binding.two2.visibility = View.VISIBLE
                skill.clear()
                for(data in s){
                    skill.add(data)
                    val chip = Chip(activity)
                    chip.text = data.nameOfTheSkill
                    chip.isClickable = true
                    chip.textSize = 20F
                    chip.isCheckable = false
                    chip.setChipBackgroundColorResource(R.color.dgblue)
                    chip.setTextColor(resources.getColor(R.color.white))
                    binding.skillChipGroup.addView(chip)
                }
                skilladapter.notifyDataSetChanged()
            }
            else{
                binding.two.visibility = View.GONE
                binding.two2.visibility = View.GONE
            }
        }
        viewModel.getallAchievement().observe(viewLifecycleOwner) { ach ->
            if (ach != null) {
                binding.six.visibility = View.VISIBLE
                binding.six6.visibility = View.VISIBLE
                acheivement.clear()
                for(data in ach){
                    acheivement.add(data)
                }
                acheivementadapter.notifyDataSetChanged()
            }
            else{
                binding.six.visibility = View.GONE
                binding.six6.visibility = View.GONE
            }
        }
        viewModel.getallProject().observe(viewLifecycleOwner) { pro ->
            if (pro != null) {
                binding.four.visibility = View.VISIBLE
                binding.four4.visibility = View.VISIBLE
                project.clear()
                for(data in pro){
                    project.add(data)
                }
                projectadapter.notifyDataSetChanged()
            }
            else{
                binding.four.visibility = View.GONE
                binding.four4.visibility = View.GONE
            }
        }
        viewModel.getallActivity().observe(viewLifecycleOwner) { act ->
            if (act != null) {
                binding.five.visibility = View.VISIBLE
                binding.five5.visibility = View.VISIBLE
                activities.clear()
                for(data in act){
                    activities.add(data)
                }
                extraadapter.notifyDataSetChanged()
            }
            else{
                binding.five.visibility = View.GONE
                binding.five5.visibility = View.GONE
            }
        }
        binding.btnGenerate.setOnClickListener {
            createPdfAndShare(binding.pdflayout)
        }
        binding.btnDownload.setOnClickListener {
            Toast.makeText(requireContext(), "Wait for Few seconds.It is Downloading", Toast.LENGTH_SHORT).show()
            checkPermissionAndCreatePdf()
        }
        return binding.root
    }
    private val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1

    private fun checkPermissionAndCreatePdf() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, create PDF and share/download

            val delayMillis: Long = 5000 // 5 seconds

            val handler = Handler()
            handler.postDelayed({
                // Code to be executed after the delay
                // Put your desired code here
                createPdfAndDownload(binding.pdflayout)
            }, delayMillis)


        } else {
            // Permission is not granted, request it
            requestPermission()
        }
    }

    private fun requestPermission() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (shouldShowRequestPermissionRationale(permission)) {
            // Explain why the permission is needed (optional)
            AlertDialog.Builder(requireContext())
                .setTitle("Permission Needed")
                .setMessage("This permission is required to save the PDF file to your device.")
                .setPositiveButton("OK") { _, _ ->
                    // Request the permission
                    requestStoragePermission()
                }
                .setNegativeButton("Cancel", null)
                .show()
        } else {
            // Request the permission
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        requestPermissions(arrayOf(permission), WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, create PDF and share/download
                val delayMillis: Long = 1000 // 5 seconds

                val handler = Handler()
                handler.postDelayed({
                    // Code to be executed after the delay
                    // Put your desired code here
                    createPdfAndDownload(binding.pdflayout)
                }, delayMillis)
            } else {
                // Permission is denied
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createPdfAndShare(view: View) {
        // Create a new PDF document
        val document = PdfDocument()
        val displayMetrics = view.resources.displayMetrics
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 595f, displayMetrics).toInt()
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 842f, displayMetrics).toInt()
        // Create a page info with the desired attributes
        val printAttrs = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()
        val viewHeight = view.rootView.height

// Calculate the number of pages needed
        val totalPages = Math.ceil(view.measuredHeight.toDouble() / height).toInt()

// Create each page
        for (currentPage in 0 until totalPages) {
            val pageInfo = PdfDocument.PageInfo.Builder(width, height, currentPage + 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas

            // Translate the canvas to the appropriate offset
            val offsetY = -currentPage * height
            canvas.translate(0f, offsetY.toFloat())

            // Draw the view on the canvas
            view.draw(canvas)

            // Finish the page
            document.finishPage(page)
        }

        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            document.writeTo(byteArrayOutputStream)
            document.close()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            val pdfData: ByteArray = byteArrayOutputStream.toByteArray()
            val pdfUri = activity?.let { context ->
                val tempFile = File(context.cacheDir, "$name _Resume.pdf")
                val fileOutputStream = FileOutputStream(tempFile)
                fileOutputStream.write(pdfData)
                fileOutputStream.close()
                FileProvider.getUriForFile(context, "com.shubhasai.biocraft.fileprovider", tempFile)
            }
            shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri)
            startActivity(Intent.createChooser(shareIntent, "Share Resume"))
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(activity, "Error creating PDF", Toast.LENGTH_SHORT).show()
        }
    }
    private fun createPdfAndDownload(view: View) {
        val document = PdfDocument()
        val displayMetrics = view.resources.displayMetrics
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 595f, displayMetrics).toInt()
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 842f, displayMetrics).toInt()
        // Create a page info with the desired attributes
        val printAttrs = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()
        val viewHeight = view.rootView.height

// Calculate the number of pages needed
        val totalPages = Math.ceil(viewHeight.toDouble() / height).toInt()

// Create each page
        for (currentPage in 0 until totalPages) {
            val pageInfo = PdfDocument.PageInfo.Builder(width, height, currentPage + 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas

            // Translate the canvas to the appropriate offset
            val offsetY = -currentPage * height
            canvas.translate(0f, offsetY.toFloat())

            // Draw the view on the canvas
            view.draw(canvas)

            // Finish the page
            document.finishPage(page)
        }

        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            document.writeTo(byteArrayOutputStream)
            document.close()

            val pdfData: ByteArray = byteArrayOutputStream.toByteArray()

            // Save the PDF to the Downloads directory
            val externalFilesDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val file = File(externalFilesDir, "$name Resume.pdf")
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(pdfData)
            fileOutputStream.close()

            if (externalFilesDir != null) {
                Toast.makeText(requireContext(), "PDF saved to ${externalFilesDir.absoluteFile}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error creating PDF", Toast.LENGTH_SHORT).show()
        }
    }
}