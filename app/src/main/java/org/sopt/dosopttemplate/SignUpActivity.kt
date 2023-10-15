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

        binding.buttonSignupStart.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("확인") { dialog, which ->
            }
            val fixNickname = binding.etNickname.text.filter { !it.isWhitespace() }
            val fixHome = binding.etHome.text.filter { !it.isWhitespace() }
            if (binding.etId.length() < 6 || binding.etId.length() > 10) {
                builder.setTitle("ID 조건").setMessage("ID는 6~10 글자만 가능합니다.")
                builder.show()
            } else if (binding.etPw.length() < 8 || binding.etPw.length() > 12) {
                builder.setTitle("비밀번호 조건").setMessage("비밀번호는 8~12 글자만 가능합니다.")
                builder.show()
            } else if (fixNickname.isEmpty()) {
                builder.setTitle("닉네임 조건").setMessage("닉네임은 한 글자 이상만 가능합니다.")
                builder.show()
            } else if (fixHome.isEmpty()) {
                builder.setTitle("거주지 조건").setMessage("거주지는 한 글자 이상만 가능합니다.")
                builder.show()
            } else {
                Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val passId = binding.etId.text.toString()
                val passPw = binding.etPw.text.toString()
                val passNk = binding.etNickname.text.toString()
                val passHm = binding.etHome.text.toString()
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
