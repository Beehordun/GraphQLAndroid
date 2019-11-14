package com.example.androidgraphql

import com.example.androidgraphql.model.AllJobs

interface AllJobsMvpView {
   fun refreshView(allJobs: List<AllJobs>)
}