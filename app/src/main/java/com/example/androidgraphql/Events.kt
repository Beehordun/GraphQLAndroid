package com.example.androidgraphql

import com.example.androidgraphql.model.AllJobs

class Events {

    data class GetJobsSuccessfulEvent(val allJobs: List<AllJobs>)
}