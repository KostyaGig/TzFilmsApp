package com.zinoview.tzfilmsapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.zinoview.tzfilmsapp.R
import com.zinoview.tzfilmsapp.databinding.DetailFilmFragmentBinding
import com.zinoview.tzfilmsapp.presentation.ClickedFilm
import com.zinoview.tzfilmsapp.presentation.core.BaseFragment
import com.zinoview.tzfilmsapp.presentation.core.log

class DetailFilmFragment : BaseFragment(R.layout.detail_film_fragment) {

    private var _binding: DetailFilmFragmentBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = DetailFilmFragmentBinding.bind(view)

        arguments?.let { bundle ->

            val clickedFilm = checkNotNull(bundle.getParcelable<ClickedFilm>(FILM_KEY))

            clickedFilm.fillUi(binding.filmImage,binding.filmName,binding.filmYear,binding.filmRate,binding.filmDescription)
            clickedFilm.handleToolbarTitle(toolbar)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_detailFilmFragment_to_filmsFragment)
            }
        })
    }

    override fun visibilityBackButton(): Boolean = true

}