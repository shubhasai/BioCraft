package com.shubhasai.biocraft

import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.shubhasai.biocraft.ViewModels.ProfileViewModel
import com.shubhasai.biocraft.adapters.AcheivementAdapter
import com.shubhasai.biocraft.adapters.EducationAdapter
import com.shubhasai.biocraft.adapters.ExtracurricularAdapter
import com.shubhasai.biocraft.adapters.LanguageAdapter
import com.shubhasai.biocraft.adapters.ProjectAdapter
import com.shubhasai.biocraft.adapters.SkillsAdapter
import com.shubhasai.biocraft.adapters.WorkExperienceAdapter
import com.shubhasai.biocraft.databinding.FragmentProfileBinding
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.achievements
import com.shubhasai.biocraft.models.extracurricular
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.projects
import com.shubhasai.biocraft.models.skills
import com.shubhasai.biocraft.models.userprofile
import com.shubhasai.biocraft.models.workexperience
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(),EducationAdapter.EdcDelClicked,WorkExperienceAdapter.WorkDelClicked
,ProjectAdapter.ProjectDelClicked,LanguageAdapter.LangDelClicked,SkillsAdapter.SkillDelClicked,AcheivementAdapter.AcheivementDelClicked
,ExtracurricularAdapter.ActivityDelClicked{
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    var userid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModel.getUser().observe(viewLifecycleOwner) { user ->
            // Update UI with user data
            if (user != null) {
                Log.d("name",user.name)
                userid = user.id
                binding.etname.setText(user.name)
                binding.etState.setText(user.state)
                binding.etDistrict.setText(user.dist)
                binding.etLocality.setText(user.locality)
                binding.etPincode.setText(user.pincode)
                binding.etemail.setText(user.email)
                binding.etphone.setText(user.phone)
                binding.etlinkedin.setText(user.linkedin)
                binding.etGithub.setText(user.github)
                binding.etLeetCode.setText(user.leetcode)
                binding.etstackoverflow.setText(user.stackOverflow)
                binding.etDribble.setText(user.dribble)
                binding.etFacebbok.setText(user.facebook)
                binding.etInstagram.setText(user.instagram)
                binding.etYoutube.setText(user.youtube)
                binding.etLink1.setText(user.link1)
                binding.etLink2.setText(user.link2)

            }
        }
        binding.btnSave.setOnClickListener {
            val name = binding.etname.text.toString()
            val state = binding.etState.text.toString()
            val dist = binding.etDistrict.text.toString()
            val locality = binding.etLocality.text.toString()
            val pincode = binding.etPincode.text.toString()
            val email = binding.etemail.text.toString()
            val phone = binding.etphone.text.toString()
            val linkedin = binding.etlinkedin.text.toString()
            val github = binding.etGithub.text.toString()
            val leetcode = binding.etLeetCode.text.toString()
            val stackoverflow = binding.etstackoverflow.text.toString()
            val dribble = binding.etDribble.text.toString()
            val facebook = binding.etFacebbok.text.toString()
            val insta = binding.etInstagram.text.toString()
            val youtube = binding.etYoutube.text.toString()
            val link1 = binding.etLink1.text.toString()
            val link2 = binding.etLink2.text.toString()

            val user = userprofile(1,name,email,phone,linkedin, github, leetcode,stackoverflow,dribble, facebook,insta,youtube, link1, link2, state,dist, locality, pincode)
            Log.d("name added",user.name)
            if(userid == 0){
                Log.d("name added",user.name)
                viewModel.insertUser(user)
            }
            else{
                Log.d("name Updated",user.name)
                viewModel.updateUser(user)
            }

        }
        val edu:ArrayList<Education> = ArrayList()
        val work:ArrayList<workexperience> = ArrayList()
        val project:ArrayList<projects> = ArrayList()
        val lang:ArrayList<language> = ArrayList()
        val skill:ArrayList<skills> = ArrayList()
        val activities:ArrayList<extracurricular> = ArrayList()
        val acheivement:ArrayList<achievements> = ArrayList()
        val eduadapter = EducationAdapter(activity,edu,this,0)
        val workadapter = WorkExperienceAdapter(activity,work,this,0)
        val projectadapter = ProjectAdapter(activity,project,this,0)
        val languageadapter = LanguageAdapter(activity,lang,this,0)
        val skilladapter = SkillsAdapter(activity,skill,this,0)
        val extraadapter = ExtracurricularAdapter(activity,activities,this,0)
        val acheivementadapter = AcheivementAdapter(activity,acheivement,this,0)
        binding.rvEducation.layoutManager = LinearLayoutManager(activity)
        binding.rvEducation.adapter = eduadapter
        binding.rvWorkexp.layoutManager = LinearLayoutManager(activity)
        binding.rvWorkexp.adapter = workadapter
        binding.rvProjects.layoutManager = LinearLayoutManager(activity)
        binding.rvProjects.adapter = projectadapter
        binding.rvLanguage.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding.rvLanguage.adapter = languageadapter
        binding.rvSkills.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding.rvSkills.adapter = skilladapter
        binding.rvextraActivity.layoutManager = LinearLayoutManager(activity)
        binding.rvextraActivity.adapter = extraadapter
        binding.rvAcheivements.layoutManager = LinearLayoutManager(activity)
        binding.rvAcheivements.adapter = acheivementadapter
        binding.btnAddeducation.setOnClickListener{
            openBottomDrawer()
        }
        binding.btnAddworkexp.setOnClickListener {
            openWorkBottomDrawer()
        }
        binding.btnAddprojects.setOnClickListener {
            openProjectDrawer()
        }
        binding.btnAddlanguage.setOnClickListener {
            openLanguageDrawer()
        }
        binding.btnAddskills.setOnClickListener {
            openSkillDrawer()
        }
        binding.btnAddextracurricular.setOnClickListener {
            openExtracurricularDrawer()
        }
        binding.btnAddachievements.setOnClickListener {
            openAchievementDrawer()
        }
        viewModel.getallEducation().observe(viewLifecycleOwner) { education ->
            if (education != null) {
                edu.clear()
                for(data in education){
                    edu.add(data)
                }
                eduadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallExp().observe(viewLifecycleOwner) { explist ->
            if (explist != null) {
                work.clear()
                for(data in explist){
                    work.add(data)
                }
                workadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallLangs().observe(viewLifecycleOwner) { language ->
            if(language!= null){
                lang.clear()
                for(data in language){
                    lang.add(data)
                }
                languageadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallSkill().observe(viewLifecycleOwner) { s ->
            if (s != null) {
                skill.clear()
                for(data in s){
                    skill.add(data)
                }
                skilladapter.notifyDataSetChanged()
            }
        }
        viewModel.getallAchievement().observe(viewLifecycleOwner) { ach ->
            if (ach != null) {
                acheivement.clear()
                for(data in ach){
                    acheivement.add(data)
                }
                acheivementadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallProject().observe(viewLifecycleOwner) { pro ->
            if (pro != null) {
                project.clear()
                for(data in pro){
                    project.add(data)
                }
                projectadapter.notifyDataSetChanged()
            }
        }
        viewModel.getallActivity().observe(viewLifecycleOwner) { act ->
            if (act != null) {
                activities.clear()
                for(data in act){
                    activities.add(data)
                }
                extraadapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }
    private fun openBottomDrawer() {
        val bottomDrawerView = layoutInflater.inflate(R.layout.add_education_drawerlayout, null)
        val bottomDrawer = activity?.let { BottomSheetDialog(it) }
        if (bottomDrawer != null) {
            bottomDrawer.setContentView(bottomDrawerView)
            bottomDrawer.window?.setGravity(Gravity.BOTTOM)
            bottomDrawer.show()

            val btnSave = bottomDrawer.findViewById<Button>(R.id.btn_saveedu)
            btnSave?.setOnClickListener {
                retrieveTextFields(bottomDrawerView)
                bottomDrawer.dismiss()
            }
        }

    }

    private fun retrieveTextFields(bottomDrawerView: View) {
        val instituteName = bottomDrawerView.findViewById<EditText>(R.id.etinstitutename).text.toString()
        val degreeName = bottomDrawerView.findViewById<EditText>(R.id.etDegree).text.toString()
        val specialization = bottomDrawerView.findViewById<EditText>(R.id.etspecialisation).text.toString()
        val grade = bottomDrawerView.findViewById<EditText>(R.id.etGrade).text.toString().toDouble()
        val startingMonth = bottomDrawerView.findViewById<EditText>(R.id.etStartingmonth).text.toString()
        val startingYear = bottomDrawerView.findViewById<EditText>(R.id.etStartingyear).text.toString()
        val endingMonth = bottomDrawerView.findViewById<EditText>(R.id.etEndingmonth).text.toString()
        val endingYear = bottomDrawerView.findViewById<EditText>(R.id.etEndingyear).text.toString()
        val education = Education(
            nameOfTheInstitute = instituteName,
            degree = degreeName,
            specialisation = specialization,
            startMonth = startingMonth,
            endMonth = endingMonth,
            startYear = startingYear,
            endYear = endingYear,
            grade = grade // Your grade value
        )
        viewModel.addEducation(education)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
        // Do something with the retrieved text fields
        // For example, you can log them or pass them to another function
        // or save them to a database
    }
    private fun openWorkBottomDrawer() {
        val bottomDrawerView = layoutInflater.inflate(R.layout.add_workexperience_drawerlayout, null)
        val bottomDrawer = activity?.let { BottomSheetDialog(it) }
        if (bottomDrawer != null) {
            bottomDrawer.setContentView(bottomDrawerView)
            bottomDrawer.window?.setGravity(Gravity.BOTTOM)
            bottomDrawer.show()

            val btnSave = bottomDrawer.findViewById<Button>(R.id.btn_savework)
            btnSave?.setOnClickListener {
                retrieveworkTextFields(bottomDrawerView)
                bottomDrawer.dismiss()
            }
        }

    }

    private fun retrieveworkTextFields(bottomDrawerView: View) {
        val organisationName =
            bottomDrawerView.findViewById<TextInputEditText>(R.id.etorganisationname).text.toString()
        val position = bottomDrawerView.findViewById<TextInputEditText>(R.id.etWorkposition).text.toString()
        val workDescriptions =
            bottomDrawerView.findViewById<TextInputEditText>(R.id.etWorkdes).text.toString()
        val htmlFormattedText: String = Html.toHtml(SpannableStringBuilder(workDescriptions))
        val startingMonth =
            bottomDrawerView.findViewById<TextInputEditText>(R.id.etworkStartingmonth).text.toString()
        val startingYear =
            bottomDrawerView.findViewById<TextInputEditText>(R.id.etworkStartingyear).text.toString()
        val endingMonth = bottomDrawerView.findViewById<TextInputEditText>(R.id.etworkEndingmonth).text.toString()
        val endingYear = bottomDrawerView.findViewById<TextInputEditText>(R.id.etworkEndingyear).text.toString()
        val work = workexperience(
            nameOfTheOrganisation = organisationName,
            position = position,
            work = htmlFormattedText,
            startMonth = startingMonth,
            endMonth = endingMonth,
            startYear = startingYear,
            endYear = endingYear,
        )
        viewModel.addExp(work)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()

    }
    private fun openProjectDrawer() {
        val projectDrawerView = layoutInflater.inflate(R.layout.add_projects_drawerlayout, null)
        val projectDrawer = activity?.let { BottomSheetDialog(it) }
        if (projectDrawer != null) {
            projectDrawer.setContentView(projectDrawerView)
            projectDrawer.window?.setGravity(Gravity.BOTTOM)
            projectDrawer.show()

            val btnSave = projectDrawer.findViewById<Button>(R.id.btn_saveproject)
            btnSave?.setOnClickListener {
                retrieveProjectFields(projectDrawerView)
                projectDrawer.dismiss()
            }
        }

    }

    private fun retrieveProjectFields(projectDrawerView: View) {
        val projectName =
            projectDrawerView.findViewById<TextInputEditText>(R.id.etProjectName).text.toString()
        val link = projectDrawerView.findViewById<TextInputEditText>(R.id.etprojectlink).text.toString()
        val learnings =
            projectDrawerView.findViewById<TextInputEditText>(R.id.etprojectlearning).text.toString()
        val projectDescriptions =
            projectDrawerView.findViewById<TextInputEditText>(R.id.etProjectDescription).text.toString()

        // Do something with the retrieved text fields
        // For example, you can log them or pass them to another function
        // or save them to a database
        val work = projects(
            nameOfTheProject = projectName,
            description = projectDescriptions,
            link = link,
            learned = learnings
        )
        viewModel.addProject(work)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
    }
    private fun openLanguageDrawer() {
        val languageDrawerView = layoutInflater.inflate(R.layout.add_language_drawerlayout, null)
        val languageDrawer = activity?.let { BottomSheetDialog(it) }
        if (languageDrawer != null) {
            languageDrawer.setContentView(languageDrawerView)
            languageDrawer.window?.setGravity(Gravity.BOTTOM)
            languageDrawer.show()

            val btnSave = languageDrawer.findViewById<Button>(R.id.btn_savelanguage)
            btnSave?.setOnClickListener {
                retrieveLanguageField(languageDrawerView)
                languageDrawer.dismiss()
            }
        }

    }

    private fun retrieveLanguageField(languageDrawerView: View) {
        val languagename =
            languageDrawerView.findViewById<TextInputEditText>(R.id.etLanguage).text.toString()

        val language = language(
            nameOfTheLanguage = languagename
        )
        viewModel.addLang(language)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
        // Do something with the retrieved language
        // For example, you can log it or pass it to another function
        // or save it to a database
    }
    private fun openSkillDrawer() {
        val skillDrawerView = layoutInflater.inflate(R.layout.add_skills_drawerlayout, null)
        val skillDrawer = activity?.let { BottomSheetDialog(it) }
        if (skillDrawer != null) {
            skillDrawer.setContentView(skillDrawerView)
            skillDrawer.window?.setGravity(Gravity.BOTTOM)
            skillDrawer.show()

            val btnSave = skillDrawer.findViewById<Button>(R.id.btn_saveskill)
            btnSave?.setOnClickListener {
                retrieveSkillFields(skillDrawerView)
                skillDrawer.dismiss()
            }
        }

    }

    private fun retrieveSkillFields(skillDrawerView: View) {
        val skillName =
            skillDrawerView.findViewById<TextInputEditText>(R.id.etskillname).text.toString()

        val skillRating =
            skillDrawerView.findViewById<TextInputEditText>(R.id.etskillrating).text.toString()
        val skill = skills(
            nameOfTheSkill = skillName,
            rating = skillRating.toFloat()
        )
        viewModel.addSkill(skill)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
        // Do something with the retrieved skill name and rating
        // For example, you can log them or pass them to another function
        // or save them to a database
    }
    private fun openExtracurricularDrawer() {
        val extracurricularDrawerView = layoutInflater.inflate(R.layout.add_extracurricular_activity_drawerlayout, null)
        val extracurricularDrawer = activity?.let { BottomSheetDialog(it) }
        if (extracurricularDrawer != null) {
            extracurricularDrawer.setContentView(extracurricularDrawerView)
            extracurricularDrawer.window?.setGravity(Gravity.BOTTOM)
            extracurricularDrawer.show()

            val btnSave = extracurricularDrawer.findViewById<Button>(R.id.btn_saveactivity)
            btnSave?.setOnClickListener {
                retrieveExtracurricularFields(extracurricularDrawerView)
                extracurricularDrawer.dismiss()
            }
        }

    }

    private fun retrieveExtracurricularFields(extracurricularDrawerView: View) {
        val activityName =
            extracurricularDrawerView.findViewById<TextInputEditText>(R.id.etactivityname).text.toString()

        val activityDescription =
            extracurricularDrawerView.findViewById<TextInputEditText>(R.id.etextracurrdescription).text.toString()
        val addactivity = extracurricular(
            nameOfTheActivity = activityName,
            description = activityDescription
        )
        viewModel.addActivity(addactivity)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
        // Do something with the retrieved activity name and description
        // For example, you can log them or pass them to another function
        // or save them to a database
    }
    private fun openAchievementDrawer() {
        val achievementDrawerView = layoutInflater.inflate(R.layout.add_acheivements_drawerlayout, null)
        val achievementDrawer = activity?.let { BottomSheetDialog(it) }
        if (achievementDrawer != null) {
            achievementDrawer.setContentView(achievementDrawerView)
            achievementDrawer.window?.setGravity(Gravity.BOTTOM)
            achievementDrawer.show()

            val btnSave = achievementDrawer.findViewById<Button>(R.id.btn_saveacheivements)
            btnSave?.setOnClickListener {
                retrieveAchievementField(achievementDrawerView)
                achievementDrawer.dismiss()
            }
        }

    }

    private fun retrieveAchievementField(achievementDrawerView: View) {
        val achievementDetails =
            achievementDrawerView.findViewById<TextInputEditText>(R.id.etacheivement).text.toString()
        val achievement = achievements(
            nameOfTheAcheivement = achievementDetails
        )
        viewModel.addAchievement(achievement)
        Toast.makeText(activity,"Added Successfully",Toast.LENGTH_SHORT).show()
        // Do something with the retrieved achievement details
        // For example, you can log them or pass them to another function
        // or save them to a database
    }

    override fun onacheivementDelclicked(ach: achievements) {
        viewModel.deleteAchievement(ach)
    }

    override fun onactivityDelclicked(act: extracurricular) {
        viewModel.deleteActivity(act)
    }

    override fun oneworkDelclicked(work: workexperience) {
        viewModel.deleteExp(work)
    }

    override fun oneduDelclicked(edu: Education) {
        viewModel.deleteEducation(edu)
    }

    override fun onlangDelclicked(lang: language) {
        viewModel.deleteLang(lang)
    }

    override fun onskilldelClicked(skill: skills) {
        viewModel.deleteSkill(skill)
    }

    override fun onprojectclicked(project: projects) {
        viewModel.deleteProject(project)
    }
}