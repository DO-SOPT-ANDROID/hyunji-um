package org.sopt.dosopttemplate.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.model.UserInfo

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? { // 이제 반환하는 View가 Null일 수 없기 때문에, ?를 지워주셔도 됩니다.
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val receivedUserInfo = arguments?.getSerializable("userInfo") as? UserInfo
        val receivedUserInfo =
            activity?.intent?.getSerializableExtra("userInfo", UserInfo::class.java) as? UserInfo
                ?: return

        val id = receivedUserInfo.id
        val pw = receivedUserInfo.pw
        val nk = receivedUserInfo.nk
        val hm = receivedUserInfo.hm

        binding.tvMypageUserId.text = id
        binding.tvMypageUserNk.text = nk
        binding.tvMypageUserHome.text = hm
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}