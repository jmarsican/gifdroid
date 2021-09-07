package com.javiermarsicano.gifdroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.gifdroid.base.BaseMVPFragment
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : BaseMVPFragment<MainScreenContract.View, MainScreenContract.Presenter>(), MainScreenContract.View {

    @Inject
    lateinit var mPresenter: MainScreenContract.Presenter

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun getPresenter(): MainScreenContract.Presenter = mPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun layoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun clearResults() {
        TODO("Not yet implemented")
    }

    override fun addTrendingResults(results: List<Content>) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}