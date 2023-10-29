package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var getStringResult: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nk: String
    private lateinit var hm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStringResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    id = data?.getStringExtra("passId") ?: ""
                    pw = data?.getStringExtra("passPw") ?: ""
                    nk = data?.getStringExtra("passNk") ?: ""
                    hm = data?.getStringExtra("passHm") ?: ""
                }
            }

        binding.btnLoginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getStringResult.launch(intent)
        }

        binding.btnLoginStart.setOnClickListener {
            if (binding.etLoginId.text.toString() == id) {
                if (binding.etLoginPw.text.toString() == pw) {
                    Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                    val resultIntent = Intent(this, HomeActivity::class.java)
                    resultIntent.putExtra("mainId", binding.etLoginId.text.toString())
                    resultIntent.putExtra("mainNk", nk)
                    resultIntent.putExtra("mainHm", hm)
                    startActivity(resultIntent)
                } else {
                    Snackbar.make(
                        binding.root, "비밀번호가 잘못되었습니다.", Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root, "아이디가 잘못되었습니다.", Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}


