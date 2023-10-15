package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

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

        binding.buttonSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getStringResult.launch(intent)
        }

        binding.buttonLogin.setOnClickListener {
            if (binding.editTextId.text.toString() == id) {
                if (binding.editTextPass.text.toString() == pw) {
                    Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                    val resultIntent = Intent(this, MainActivity::class.java)
                    resultIntent.putExtra("mainId", binding.editTextId.text.toString())
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


