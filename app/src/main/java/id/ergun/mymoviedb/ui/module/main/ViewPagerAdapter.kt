package id.ergun.mymoviedb.ui.module.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by alfacart on 27/11/19.
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

  lateinit var data: MutableList<Fragment>
  lateinit var pageTitle: MutableList<String>

  override fun getItem(position: Int): Fragment {
    return data[position]
  }

  override fun getCount(): Int {
    return data.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return pageTitle[position]
  }

}