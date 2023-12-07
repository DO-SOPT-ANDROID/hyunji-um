package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Dto.RequestDto.RequestSignUpDto
import org.sopt.dosopttemplate.ServicePool.authServiceSignup
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.home.HomeActivity
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern

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
    }

    private fun signup() {
        binding.btnSignupStart.setOnClickListener {
            val id = binding.etSignupIdText.text.toString()
            val password = binding.etSignupPwText.text.toString()
            val nickname = binding.etSignupNicknameText.text.toString()
            val home = binding.etSignupHomeText.text.toString()
            binding.etSignupId.error = getString(R.string._6_10)
/*            authViewModel.signup(
                id = id,
                password = password,
                nickname = nickname,
                home = home
            )*/
            observerSignUpResult()
        }
    }

    private fun observerSignUpResult() {
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                Toast.makeText(this@SignUpActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val passId = binding.etSignupIdText.text.toString()
                val passPw = binding.etSignupPwText.text.toString()
                val passNk = binding.etSignupNicknameText.text.toString()
                val passHm = binding.etSignupHomeText.text.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("passId", passId)
                resultIntent.putExtra("passPw", passPw)
                resultIntent.putExtra("passNk", passNk)
                resultIntent.putExtra("passHm", passHm)
                setResult(AppCompatActivity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this@SignUpActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
/*
val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
val patternID = Pattern.compile(idPattern)
val patternPW = Pattern.compile(pwPattern)
val builder = AlertDialog.Builder(this)
            val fixNickname =
                binding.etSignupNicknameText.text?.filter { !it.isWhitespace() }
            val fixHome =
                binding.etSignupHomeText.text?.filter { !it.isWhitespace() }

            val matcher = patternID.matcher(id)
            val matcher2 = patternPW.matcher(password)
            if (!matcher.find()) {
                builder.setTitle("ID 조건").setMessage("ID:영문,숫자 포함 6~10 글자")
                binding.etSignupId.error = getString(R.string._6_10)
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = null
                builder.show()
            } else if (!matcher2.find()) {
                builder.setTitle("비밀번호 조건").setMessage("비밀번호:영문,숫자,특수문자 포함 6~12 글자")
                binding.etSignupId.error = null
                binding.etSignupPw.error = getString(R.string._8_12)
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = null
                builder.show()
            } else if (fixNickname?.isEmpty() == true) {
                builder.setTitle("닉네임 조건").setMessage("닉네임:한 글자 이상")
                binding.etSignupId.error = null
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = getString(R.string._1_)
                binding.etSignupHome.error = null
                builder.show()
            } else if (fixHome?.isEmpty() == true) {
                builder.setTitle("거주지 조건").setMessage("거주지:한 글자 이상")
                binding.etSignupId.error = null
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = getString(R.string._1_)
                builder.show()
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "회원가입에 성공하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()
                val passId = binding.etSignupIdText.text.toString()
                val passPw = binding.etSignupPwText.text.toString()
                val passNk = binding.etSignupNicknameText.text.toString()
                val passHm = binding.etSignupHomeText.text.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("passId", passId)
                resultIntent.putExtra("passPw", passPw)
                resultIntent.putExtra("passNk", passNk)
                resultIntent.putExtra("passHm", passHm)
                setResult(AppCompatActivity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
})*/
