package com.jintin.clerk.app.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.clerk.app.R
import com.jintin.clerk.app.dagger.component.ViewerComponent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import com.jintin.clerk.app.viewmodel.LogListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 * LogListFragment to show log list
 */
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
                if (it.isNotEmpty() && activity.getBool(PrefKey.AUTO_SCROLL)) {
                    recyclerView.scrollToPosition(it.size - 1)
                }
            })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewerComponent.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                viewModel.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}