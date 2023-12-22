package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.home.HomeActivity
import org.sopt.dosopttemplate.model.UserInfo

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var getStringResult: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nk: String
    private lateinit var hm: String
    private val authViewModel: AuthViewModel by viewModels()
    private var userInfo: UserInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

        getStringResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    userInfo = UserInfo(
                        id = data?.getStringExtra("passId").orEmpty(),
                        pw = data?.getStringExtra("passPw").orEmpty(),
                        nk = data?.getStringExtra("passNk").orEmpty(),
                        hm = data?.getStringExtra("passHm").orEmpty()
                    )
                }
            }
        binding.btnLoginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getStringResult.launch(intent)
        }

        login()
    }

    private fun login() {
        binding.btnLoginStart.setOnClickListener {
            val id = binding.etLoginIdText.text.toString()
            val password = binding.etLoginPwText.text.toString()

            authViewModel.login(
                id = id,
                password = password
            )
            observerLoginResult()
        }
    }

    private fun observerLoginResult() {
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ).putExtra("userInfo", userInfo)
                )
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

    }
}