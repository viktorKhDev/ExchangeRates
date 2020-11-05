package com.viktor.kh.dev.exchangerates.di

import com.viktor.kh.dev.exchangerates.ui.MainActivity
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.repository.Repository
import com.viktor.kh.dev.exchangerates.services.graph.GraphData
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import com.viktor.kh.dev.exchangerates.ui.CoursesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,MainPresenterModule::class,NetworkModule::class,DataBaseModule::class])
interface Component {

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(networkService: NetworkService)
    fun inject(coursesFragment: CoursesFragment)
    fun inject(mainAdapter: MainAdapter)
    fun inject(repository : Repository)
    fun inject(graphData: GraphData)

}