package com.jintin.clerk.app.ui

import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.clerk.R
import com.jintin.clerk.dagger.component.ViewerComponent
import com.jintin.clerk.obj.ClerkLog
import com.jintin.clerk.viewmodel.LogListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject


class LogListFragment : Fragment() {
    @Inject
    lateinit var factory: LogListViewModel.Factory
    private lateinit var viewModel: LogListViewModel
    private val adapter = LogListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewerComponent.init().inject(this)
        viewModel = ViewModelProviders.of(this, factory)[LogListViewModel::class.java]
        viewModel.getList()
            .observe(this, Observer<List<ClerkLog>> {
                emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                adapter.setData(it)
            })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.jintin.clerk.R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewerComponent.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_clear -> {
                viewModel.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}