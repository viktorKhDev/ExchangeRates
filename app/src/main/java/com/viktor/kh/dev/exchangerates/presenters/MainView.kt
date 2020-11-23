package com.viktor.kh.dev.exchangerates.presenters


import com.viktor.kh.dev.exchangerates.data.DataCourses

interface MainView {

    fun initList(dataCoursesFragment: DataCourses)
    fun error(text: String)

}