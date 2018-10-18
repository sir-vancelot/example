package com.batman.example.presentation.misc


abstract class BatmanViewModel<View>(protected val navigation: BatmanNavigation = BatmanNavigation(), protected val commands: BatmanCommands = BatmanCommands(), protected val queries: BatmanQueries = BatmanQueries()) {
    /*
       Require that ViewModels have a View
    */
    open var view: View? = null

    // <editor-fold desc="Execute in response to the view's creation">
    open fun onViewCreated(view: View) {}
    // </editor-fold>

    // <editor-fold desc="Execute in response to the view's destruction">
    open fun onViewDestroyed() {}
    // </editor-fold>

    // <editor-fold desc="Read the models for the view">
    open fun populateView() {}
    // </editor-fold>

    // <editor-fold desc="Update the database with the models for the view">
    open fun updateDatastore() {}
    // </editor-fold>

    // <editor-fold desc="Handle user navigation">
    open fun navigateTo(tag: String) {
        navigation.navigateTo(tag)
    }
    // </editor-fold>
}