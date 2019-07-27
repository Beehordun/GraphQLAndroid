package com.example.androidgraphql

class Events {

    data class GetJobsSuccessfulEvent(val allJobs: List<AllJobs>)
}