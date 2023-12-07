package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Dto.RequestDto.RequestLoginDto
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseLoginDto
import org.sopt.dosopttemplate.ServicePool.authServiceLogin
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.home.HomeActivity
import org.sopt.dosopttemplate.model.UserInfo
import retrofit2.Call
import retrofit2.Response

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
/*            authServiceLogin.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            val data: ResponseLoginDto = response.body()!!
                            val userId = data.id
                            Toast.makeText(
                                this@LoginActivity,
                                "로그인이 성공하였고 유저의 ID는 $userId 입니둥",
                                Toast.LENGTH_SHORT,
                            ).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                })*/
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
                    )
                )
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


