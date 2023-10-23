package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.model.Friend
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.FriendAdapter
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    private val mockFriendList =listOf<Friend>(
        Friend(
            profileImage = R.drawable.ic_home_white_24,
            name = "엄현지",
            self_description = "안녕안녕",
        ),
        Friend(
            profileImage = R.drawable.jordy,
            name = "허민회",
            self_description = "빠이팅",
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            name = "파트장",
            self_description = "표정 풀자",
        ),
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
        val friendAdapter = FriendAdapter(requireContext())
        binding.rvFriends.adapter= friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}