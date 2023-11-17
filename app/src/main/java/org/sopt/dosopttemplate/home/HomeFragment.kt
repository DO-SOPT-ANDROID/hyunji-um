package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseFollowerDto
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.ServicePool
import org.sopt.dosopttemplate.adapter.FollowerAdapter
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.PersonInfo
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    private val mockFriendList = listOf<PersonInfo>(
        PersonInfo.MyInfo(
            profileImage = R.drawable.jordy, name = "엄현지", self_description = "나는야 행복한 나무늘보"
        ), PersonInfo.BirthdayInfo(
            profileImage = R.drawable.sloth, name = "허민회", self_description = "빠이팅"
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_happy,
            name = "파트장",
            self_description = "표정 풀자",
            music = "투게더!-잔나비"
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_cute,
            name = "박소현",
            self_description = "하이~",
            music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.sloth,
            name = "최준서",
            self_description = "갓생살자",
            music = "히어로-LUCY"
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_happy,
            name = "양예진",
            self_description = "면지 친구",
            music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_cute,
            name = "최유진",
            self_description = "면지 친구~",
            music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.sloth, name = "허민회", self_description = "빠이팅", music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_happy,
            name = "파트장",
            self_description = "표정 풀자",
            music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_cute,
            name = "박소현",
            self_description = "하이~",
            music = "등대-하현상"
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.sloth, name = "최준서", self_description = "갓생살자", music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_happy,
            name = "양예진",
            self_description = "면지 친구",
            music = null
        ), PersonInfo.FriendInfo(
            profileImage = R.drawable.jordy_cute,
            name = "최유진",
            self_description = "면지 친구~",
            music = null
        )

    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? { // 이제 반환하는 View가 Null일 수 없기 때문에, ?를 지워주셔도 됩니다.
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getUser() {
        ServicePool.reqresService.follower()
            .enqueue(object : retrofit2.Callback<ResponseFollowerDto> {
                override fun onResponse(
                    call: Call<ResponseFollowerDto>,
                    response: Response<ResponseFollowerDto>,
                ) {
                    if (response.isSuccessful) {
                        val followerAdapter = FollowerAdapter(requireContext())
                        binding.rvHomeFriends.adapter = followerAdapter
                        followerAdapter.setFriendList(response.body()!!.data)
                    }
                }

                override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {

                }

            })
    }
}