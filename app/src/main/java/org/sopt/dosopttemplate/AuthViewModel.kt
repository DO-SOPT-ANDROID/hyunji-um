package org.sopt.dosopttemplate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.Dto.RequestDto.RequestLoginDto
import org.sopt.dosopttemplate.Dto.RequestDto.RequestSignUpDto
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseLoginDto
import org.sopt.dosopttemplate.ServicePool.authServiceLogin
import org.sopt.dosopttemplate.ServicePool.authServiceSignup
import java.util.regex.Pattern

class AuthViewModel : ViewModel() {

    private val loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = _loginSuccess

    private val _signupSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signupSuccess: MutableLiveData<Boolean> = _signupSuccess

    val isButtonEnabled = MutableLiveData<Boolean>()

    private fun updateButtonEnabled() {
        val isIdValid = isMatchId.value ?: Pair(false, "")
        val isPwValid = isMatchPw.value ?: Pair(false, "")
        val isNkValid = isMatchNk.value ?: Pair(false, "")
        val isHmValid = isMatchHm.value ?: Pair(false, "")

        isButtonEnabled.value =
            isIdValid.first && isPwValid.first && isNkValid.first && isHmValid.first
    }


    val signupId = MutableLiveData<String>()
    private val isMatchId: LiveData<Pair<Boolean, String>> = signupId.map { id ->
        matchId(id)
    }
    val idValidationResult: LiveData<Pair<Boolean, String>> = isMatchId

    val signupPw = MutableLiveData<String>()
    private val isMatchPw: LiveData<Pair<Boolean, String>> = signupPw.map { pw ->
        matchPw(pw)
    }
    val pwValidationResult: LiveData<Pair<Boolean, String>> = isMatchPw

    val signupNk = MutableLiveData<String>()
    private val isMatchNk: LiveData<Pair<Boolean, String>> = signupNk.map { nk ->
        matchNk(nk)
    }
    val nkValidationResult: LiveData<Pair<Boolean, String>> = isMatchNk

    val signupHm = MutableLiveData<String>()
    private val isMatchHm: LiveData<Pair<Boolean, String>> = signupHm.map { hm ->
        matchHm(hm)
    }
    val hmValidationResult: LiveData<Pair<Boolean, String>> = isMatchHm

    init {
        isMatchId.observeForever { updateButtonEnabled() }
        isMatchPw.observeForever { updateButtonEnabled() }
        isMatchNk.observeForever { updateButtonEnabled() }
        isMatchHm.observeForever { updateButtonEnabled() }
    }

    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                authServiceLogin.login(RequestLoginDto(id, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    loginResult.value = it.body()
                    loginSuccess.value = true
                } else {
                    loginSuccess.value = false
                }
            }.onFailure {
                loginSuccess.value = false
            }
        }
    }


    private fun matchId(id: String): Pair<Boolean, String> {
        val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        val patternID = Pattern.compile(idPattern)
        val matcher = patternID.matcher(id)
        return when {
            matcher.find() -> Pair(true, "")
            else -> Pair(false, "영문,숫자 포함 6~10 글자")
        }
    }

    private fun matchPw(pw: String): Pair<Boolean, String> {
        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
        val patternPW = Pattern.compile(pwPattern)
        val matcher = patternPW.matcher(pw)
        return when {
            matcher.find() -> Pair(true, "")
            else -> Pair(false, "영문,숫자,특수문자 포함 6~12 글자")
        }
    }

    private fun matchNk(nk: String): Pair<Boolean, String> {
        val fixNickname = nk.filter { !it.isWhitespace() }
        return when {
            fixNickname.isNotEmpty() -> Pair(true, "")
            else -> Pair(false, "한 글자 이상")
        }
    }

    private fun matchHm(hm: String): Pair<Boolean, String> {
        val fixHome = hm.filter { !it.isWhitespace() }
        return when {
            fixHome.isNotEmpty() -> Pair(true, "")
            else -> Pair(false, "한 글자 이상")
        }
    }

    fun signup() {
        Log.e("signup", "signup들어옴")
        val id = signupId.value
        val pw = signupPw.value
        val nk = signupNk.value
        Log.e("signup", id.toString())

        if (id != null && pw != null && nk != null) {
            viewModelScope.launch {
                runCatching {
                    authServiceSignup.signup(RequestSignUpDto(id, pw, nk))
                }.onSuccess {
                    signupSuccess.value = it.isSuccessful
                }.onFailure {
                    signupSuccess.value = false
                }
            }
        } else {
            // Handle the case where one of the values is empty
            signupSuccess.value = false
        }
    }
}