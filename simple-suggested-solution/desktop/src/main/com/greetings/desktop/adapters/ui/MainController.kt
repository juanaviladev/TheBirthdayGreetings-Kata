package com.greetings.desktop.adapters.ui

import com.greetings.core.usecases.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class MainController(private val window: MainWindow,
                     private val findAll: FindAll,
                     private val sendGreetings: SendGreetings,
                     private val storageSettings: StorageSettings) : ActionListener {

    init {
        refresh()
    }

    private fun refresh() {
        val result = findAll.invoke(FindAllRequest())
        window.showEmployees(result.employees)
    }

    override fun actionPerformed(e: ActionEvent) {
        when {
            e.actionCommand == "change_db" -> {
                val selectedFile = window.showFileSelector(storageSettings.employeesDB)
                selectedFile?.also {
                    storageSettings.employeesDB = it
                    refresh()
                }
            }
            e.actionCommand == "send" -> {
                val result = sendGreetings.invoke(SendGreetingsRequest())
                window.showInfoMessage(result.sentNotifications.toString() + " greetings emails have been sent")
            }
            e.actionCommand == "refresh" -> refresh()
            else -> window.showErrorMessage("Unknown error")
        }
    }
}
