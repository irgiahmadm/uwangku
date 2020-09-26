package org.d3ifcool.uwangku.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.d3ifcool.uwangku.ExpenseFragment
import org.d3ifcool.uwangku.IncomeFragment
import org.d3ifcool.uwangku.R


class SectionsPagerAdapter(private val context:Context, fm:FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.income_text, R.string.expense_text)

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment? =  null
        when(position){
            0 -> fragment =  IncomeFragment()
            1 -> fragment =  ExpenseFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }


}