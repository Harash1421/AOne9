package com.example.aone9

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.attribute.AclEntry
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLanguage()
        setContentView(R.layout.activity_main)

        main_buChange.setOnClickListener {
            showChooseLanguage()
        }
    }
    //Function Show Choose Languages
    private  fun showChooseLanguage() {
        var list = arrayOf(
            resources.getString(R.string.Arabic), resources.getString(R.string.English),
            resources.getString(R.string.Deutsch), resources.getString(R.string.Spanish)
        )
        var alert = AlertDialog.Builder(this)
        alert.setTitle(resources.getString(R.string.ChooseLanguage))
        alert.setSingleChoiceItems(list, -1) { dialog, choose ->
            //Arabic
            if (choose == 0) {
                languages("ar")
                recreate()
            }
            //English
            if (choose == 1) {
                languages("en")
                recreate()
            }
            //Deutsch
            if (choose == 2) {
                languages("de")
                recreate()
            }
            //Spanish
            if (choose == 3) {
                languages("es")
                recreate()
            }
            dialog.dismiss()
        }
        var build = alert.create()
        build.show()
    }
    //Function Languages
    @SuppressLint("CommitPrefEdits")
    private fun languages(language:String){
        var locale = Locale(language)
        Locale.setDefault(locale)
        var config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,
        baseContext.resources.displayMetrics)

        var editor = getSharedPreferences("Language", Activity.MODE_PRIVATE).edit()
        editor.putString("Language_Current", language)
        editor.apply()
    }

    //Function Load Language
    private fun loadLanguage(){
        var editor = getSharedPreferences("Language", Activity.MODE_PRIVATE)
        var language = editor.getString("Language_Current", "en")
        languages(language.toString())
    }
}