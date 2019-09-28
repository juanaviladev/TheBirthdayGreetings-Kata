package com.greetings.adapters.logging

import com.greetings.core.logging.Logger
import com.greetings.core.utils.DrivenAdapter

@DrivenAdapter
class ConsoleLogger : Logger {
    
    override fun d(msg: String) {
        println("DEBUG: $msg")
    }

    override fun e(msg: String) {
        System.err.println("ERROR: $msg")
    }
    
}