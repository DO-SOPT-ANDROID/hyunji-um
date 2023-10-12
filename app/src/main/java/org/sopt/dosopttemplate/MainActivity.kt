package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userid = binding.userId
        val mainid = intent.getStringExtra("mainId")
        userid.setText(mainid)
        val usernk = binding.userNk
        val mainnk = intent.getStringExtra("mainNk")
        usernk.setText(mainnk)
        val userhome = binding.userHome
        val mainhome = intent.getStringExtra("mainHome")
        userhome.setText(mainhome)
    }
}