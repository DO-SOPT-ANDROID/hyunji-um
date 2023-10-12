package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val userid = binding.editTextId
        val passid = intent.getStringExtra("passId")
        userid.setText(passid)
        val userpw = binding.editTextPass
        val passpw = intent.getStringExtra("passPw")
        userpw.setText(passpw)
        val passNk = intent.getStringExtra("passNk")
        val passHome = intent.getStringExtra("passHome")

        binding.buttonLogin.setOnClickListener {

            if (binding.editTextId.length() >= 6) {
                if (binding.editTextPass.length() in 8..12) {
                    Snackbar.make(
                        binding.root,
                        "로그인에 성공했습니다",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("mainId", binding.editTextId.text.toString())
                    intent.putExtra("mainNk", passNk)
                    intent.putExtra("mainHome", passHome)
                    startActivity(intent)
                } else {
                    Snackbar.make(
                        binding.root,
                        "비밀번호가 잘못되었습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디가 잘못되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}