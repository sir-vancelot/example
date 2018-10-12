package com.batman.example.presentation.example1

import com.batman.example.presentation.ExampleNavigation

class Example1ViewModel(private val exampleNavigation: ExampleNavigation) {
    fun showExample2View() {
        exampleNavigation.showExampleView2()
    }
}