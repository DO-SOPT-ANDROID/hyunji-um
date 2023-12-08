package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

        signup()
        observerSignUpResult()
    }

    private fun signup() {
        binding.btnSignupStart.setOnClickListener {
            authViewModel.signup()
        }
        observerSignUpResult()
    }

    private fun observerSignUpResult() {
        authViewModel.signupSuccess.observe(this) {isSuccess ->
            if (isSuccess) {
                Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()

                // Access values from LiveData
                val passId = authViewModel.signupId.value
                val passPw = authViewModel.signupPw.value
                val passNk = authViewModel.signupNk.value
                val passHm = authViewModel.signupHm.value

                val resultIntent = Intent()
                resultIntent.putExtra("passId", passId)
                resultIntent.putExtra("passPw", passPw)
                resultIntent.putExtra("passNk", passNk)
                resultIntent.putExtra("passHm", passHm)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "서버 에러 발생", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
