package com.example.androidgraphql

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidgraphql.model.AllJobs
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AllJobsActivity : BaseActivity(), AllJobsMvpView{

    @Inject
    lateinit var allJobsPresenter: AllJobsPresenter

    private var jobsList: MutableList<AllJobs> = mutableListOf()
    private lateinit var adapter: AllJobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureLayout()
    }

    override fun performInjection() {
        appComponent.inject(this)
    }

    private fun configureLayout() {
        adapter = AllJobsAdapter(jobsList)
        allJobsRv.adapter = adapter
        allJobsRv.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        allJobsPresenter.attachView(this)
        allJobsPresenter.getAllJobs()
    }

    override fun onDestroy() {
        super.onDestroy()
        allJobsPresenter.detachView()
    }

    override fun refreshView(allJobs: List<AllJobs>) {
        jobsList.clear()
        jobsList.addAll(allJobs)
        adapter.notifyDataSetChanged()
    }
}
