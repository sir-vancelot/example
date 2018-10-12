package com.batman.example.presentation.example2

import com.batman.example.presentation.ExampleNavigation

class Example2ViewModel(private val exampleNavigation: ExampleNavigation) {
    fun showExample1View() {
        exampleNavigation.showExampleView1()
    }
}