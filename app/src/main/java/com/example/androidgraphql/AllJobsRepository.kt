package com.example.androidgraphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.example.androidgraphql.model.AllJobs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllJobsRepository @Inject internal constructor(val bus: Bus, private val apolloClient: ApolloClient) {

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
        error.printStackTrace()
    }

    private fun JobsQuery.Data.toObject(): List<AllJobs> {
        val source = this
        val allJobs: MutableList<AllJobs> = mutableListOf()

        for (job in source.jobs) {
            var type: String? = null

            if (job.remotes.isNullOrEmpty().not()) {
                type = job.remotes?.get(0)?.type
            }
            allJobs.add(AllJobs(job.id, job.title, type))
        }

        return allJobs
    }
}
