package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.api.RemoteAPI
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.ui.news.Events
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InputStream
import java.util.concurrent.Executor

class EventsRepositoryImpl(
    private val assetDataSource: AssetReader<EventSerialized>,
    private val executor: Executor,
    private val remoteAPI: RemoteAPI,
) : EventsRepository {

    override fun readEvents(inputStream: InputStream): Observable<Events> =
        Observable
            .create { emitter ->
                Thread.sleep(EXECUTOR_TIMEOUT)
                val events = readEventsSynchronous(inputStream)
                emitter.onNext(events)
            }
            .subscribeOn(Schedulers.from(executor))

    override fun getEvents(): Observable<Events> =
        remoteAPI.getEvents()
            .subscribeOn(Schedulers.io())
            .map { it.toDomainModelList() }

    private fun readEventsSynchronous(inputStream: InputStream): Events =
        assetDataSource.readList(inputStream).toDomainModelList()
}