package com.zinoview.tzfilmsapp.presentation.fragment

import android.os.Bundle
import android.view.View
import com.zinoview.tzfilmsapp.R
import com.zinoview.tzfilmsapp.databinding.FilmsFragmentBinding
import com.zinoview.tzfilmsapp.presentation.LayoutManager
import com.zinoview.tzfilmsapp.presentation.presenter.FilmsPresenter
import com.zinoview.tzfilmsapp.presentation.adapter.FilmsAdapter
import com.zinoview.tzfilmsapp.presentation.core.BaseFragment
import com.zinoview.tzfilmsapp.presentation.presenter.view.FilmsView
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm
import javax.inject.Inject

class FilmsFragment : BaseFragment(R.layout.films_fragment), FilmsView {

    private var _binding: FilmsFragmentBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    @Inject
    lateinit var filmsPresenter: FilmsPresenter

    private lateinit var adapter: FilmsAdapter.Base
    private lateinit var layoutManager: LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        layoutManager = LayoutManager.Base()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.main_text)
        _binding = FilmsFragmentBinding.bind(view)

        adapter = FilmsAdapter.Base()
        binding.filmsRecView.adapter = adapter

        filmsPresenter.subscribe(this)
        filmsPresenter.films()
    }

    override fun updateState(films: List<UiStateFilm>) {
        val first = films.first()
        val layoutManager = layoutManager.layoutManager(requireContext(),first)
        binding.filmsRecView.layoutManager = layoutManager
        adapter.update(films)
    }
}