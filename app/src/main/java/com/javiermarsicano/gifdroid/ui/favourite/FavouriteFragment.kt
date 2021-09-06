package com.javiermarsicano.gifdroid.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javiermarsicano.gifdroid.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root = binding.root


        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}