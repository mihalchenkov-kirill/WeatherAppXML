package com.example.weatherappxml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappxml.MainViewModel
import com.example.weatherappxml.adapters.WeatherAdapter
import com.example.weatherappxml.adapters.WeatherModel
import com.example.weatherappxml.databinding.FragmentDaysBinding

class DaysFragment : Fragment(), WeatherAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        updateDaysInfo()
    }

    private fun updateDaysInfo() {
        model.liveDataList.observe(viewLifecycleOwner) {
            adapter.submitList(it.subList(1, it.size))
        }
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(this@DaysFragment)
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(item: WeatherModel) {
        model.liveDataCurrent.value = item
    }
}