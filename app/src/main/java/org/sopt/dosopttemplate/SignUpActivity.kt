package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignupStart.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("확인") { dialog, which ->
            }
            val fixNickname = binding.etSignupNicknameText.text?.filter { !it.isWhitespace() }
            val fixHome = binding.etSignupHomeText.text?.filter { !it.isWhitespace() }
            if (binding.etSignupIdText.length() < 6 || binding.etSignupIdText.length() > 10) {
                builder.setTitle("ID 조건").setMessage("ID는 6~10 글자만 가능합니다.")
                binding.etSignupId.error = getString(R.string._6_10)
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = null
                builder.show()
            } else if (binding.etSignupPwText.length() < 8 || binding.etSignupPwText.length() > 12) {
                builder.setTitle("비밀번호 조건").setMessage("비밀번호는 8~12 글자만 가능합니다.")
                binding.etSignupId.error = null
                binding.etSignupPw.error = getString(R.string._8_12)
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = null
                builder.show()
            } else if (fixNickname?.isEmpty() == true) {
                builder.setTitle("닉네임 조건").setMessage("닉네임은 한 글자 이상만 가능합니다.")
                binding.etSignupId.error = null
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = getString(R.string._1_)
                binding.etSignupHome.error = null
                builder.show()
            } else if (fixHome?.isEmpty() == true) {
                builder.setTitle("거주지 조건").setMessage("거주지는 한 글자 이상만 가능합니다.")
                binding.etSignupId.error = null
                binding.etSignupPw.error = null
                binding.etSignupNickname.error = null
                binding.etSignupHome.error = getString(R.string._1_)
                builder.show()
            } else {
                Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val passId = binding.etSignupIdText.text.toString()
                val passPw = binding.etSignupPwText.text.toString()
                val passNk = binding.etSignupNicknameText.text.toString()
                val passHm = binding.etSignupHomeText.text.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("passId", passId)
                resultIntent.putExtra("passPw", passPw)
                resultIntent.putExtra("passNk", passNk)
                resultIntent.putExtra("passHm", passHm)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
