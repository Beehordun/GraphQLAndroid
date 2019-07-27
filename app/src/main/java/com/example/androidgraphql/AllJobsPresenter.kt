package com.example.androidgraphql

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class AllJobsPresenter @Inject internal constructor(val allJobsRepository: AllJobsRepository, val bus: Bus) {

    private var  allJobsView: AllJobsMvpView? = null

    fun attachView(view: AllJobsMvpView) {
        allJobsView = view
        bus.register(this)
    }

    fun detachView() {
        allJobsView = null
        bus.unregister(this)
    }

    fun getAllJobs() {
        allJobsRepository.getALLJobs()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetALLJobsSuccessfulEvent(event: Events.GetJobsSuccessfulEvent) {
        println("Called ----> received success event")
        allJobsView?.refreshView(event.allJobs)
    }
}