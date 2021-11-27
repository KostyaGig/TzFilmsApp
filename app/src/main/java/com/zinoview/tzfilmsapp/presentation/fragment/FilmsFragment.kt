package com.zinoview.tzfilmsapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.zinoview.tzfilmsapp.R
import com.zinoview.tzfilmsapp.core.ResourceProvider
import com.zinoview.tzfilmsapp.databinding.FilmsFragmentBinding
import com.zinoview.tzfilmsapp.presentation.ClickedFilm
import com.zinoview.tzfilmsapp.presentation.FilmsWithCategories
import com.zinoview.tzfilmsapp.presentation.LayoutManager
import com.zinoview.tzfilmsapp.presentation.presenter.FilmsPresenter
import com.zinoview.tzfilmsapp.presentation.adapter.FilmsAdapter
import com.zinoview.tzfilmsapp.presentation.adapter.OnItemClickListener
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

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private lateinit var adapter: FilmsAdapter.Base
    private lateinit var layoutManager: LayoutManager

    private lateinit var filmsWithCategories: FilmsWithCategories
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        layoutManager = LayoutManager.Base()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.main_text)
        _binding = FilmsFragmentBinding.bind(view)

        adapter = FilmsAdapter.Base(object : OnItemClickListener<ClickedFilm> {

            override fun onItemClick(item: ClickedFilm) {
                val bundle = Bundle().apply {
                    putParcelable(FILM_KEY,item)
                }
                findNavController().navigate(R.id.action_filmsFragment_to_detailFilmFragment,bundle)
            }
        }, object : OnItemClickListener<String> {
            override fun onItemClick(item: String) {
                filmsPresenter.films(item)
                toolbar.title = resourceProvider.string(R.string.main_text) + "($item)"
            }
        })
        binding.filmsRecView.adapter = adapter

        filmsWithCategories = FilmsWithCategories.Base(resourceProvider)

        gridLayoutManager = GridLayoutManager(requireContext(),2)
        binding.filmsRecView.layoutManager = gridLayoutManager
    }

    override fun onStart() {
        super.onStart()
        filmsPresenter.subscribe(this)
        filmsPresenter.films()
    }

    override fun onPause() {
        super.onPause()
        filmsPresenter.unSubscribe()
    }

    override fun updateState(films: List<UiStateFilm>) {
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when(films[position]) {
                        is UiStateFilm.Progress,
                        is UiStateFilm.Failure,
                        is UiStateFilm.Category,
                        is UiStateFilm.Genre -> 2
                        else -> 1
                    }
                }
            }
        adapter.update(films)
    }

    override fun visibilityBackButton(): Boolean = false
}