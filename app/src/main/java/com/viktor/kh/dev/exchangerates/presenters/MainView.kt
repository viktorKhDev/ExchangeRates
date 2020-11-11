package com.viktor.kh.dev.exchangerates.presenters


import com.viktor.kh.dev.exchangerates.data.DataForCourses

interface MainView {

    fun initList(dataForCoursesFragment: DataForCourses)
    fun error(text: String)

}