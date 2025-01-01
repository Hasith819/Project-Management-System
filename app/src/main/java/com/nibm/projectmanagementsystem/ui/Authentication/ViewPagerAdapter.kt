package com.nibm.projectmanagementsystem.ui.Authentication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nibm.projectmanagementsystem.ui.Authentication.SignIn.SignInTabFragment
import com.nibm.projectmanagementsystem.ui.Authentication.SignUp.SignUpTabFragment


class ViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SignUpTabFragment()
        } else {
            SignInTabFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
