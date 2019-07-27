package com.example.androidgraphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllJobsRepository @Inject internal constructor(val bus: Bus, val apolloClient: ApolloClient) {

    fun getALLJobs() {
        Rx2Apollo.from(apolloClient.query(JobsQuery()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ handleSuccessfulResponse(it) },
                { handleErrorResponse(it) })
    }

    private fun handleSuccessfulResponse(response: Response<JobsQuery.Data>) {
        if (response.hasErrors()) return

        val allJobs = response.data()?.toObject()

        allJobs?.let {
            bus.post(Events.GetJobsSuccessfulEvent(it))
        }
    }

    private fun handleErrorResponse(error: Throwable) {
    }

    private fun JobsQuery.Data.toObject(): List<AllJobs> {
        val source = this
        val size = source.jobs.size
        val allJobs: MutableList<AllJobs> = mutableListOf()

        for (i in 0 until size) {
            val job = source.jobs[i]
            val id = job.id
            val title = job.title
            var type: String? = null
            if (job.remotes.isNullOrEmpty().not()) {
                type = job.remotes?.get(0)?.type
            }

            allJobs.add(AllJobs(id, title, type))
        }

        return allJobs
    }

}