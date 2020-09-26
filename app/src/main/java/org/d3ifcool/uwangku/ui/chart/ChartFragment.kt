package org.d3ifcool.uwangku.ui.chart


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager

import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.adapter.SectionsPagerAdapter
import org.d3ifcool.uwangku.databinding.FragmentChartBinding

/**
 * A simple [Fragment] subclass.
 */
class ChartFragment : Fragment() {

    lateinit var binding : FragmentChartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chart, container,false)

        val sectionsPagerAdapter = SectionsPagerAdapter(this.requireContext(),childFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }


}
