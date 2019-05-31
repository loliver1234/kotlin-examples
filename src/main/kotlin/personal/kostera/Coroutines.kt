package personal.kostera

import kotlinx.coroutines.*
import kotlin.concurrent.thread

// GlobalScope coroutines
// Lunching coroutines in GlobalScope is just like we usually do with threads (they are always global)

fun globalScopeCoroutine(delayMs: Long, sleepMs: Long, value1: String, value2: String): String {
    var joinedValue = ""
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        delay(delayMs)              // non-blocking delay for 1 second (default time unit is ms)
        joinedValue += value1       // concat value after delay
    }
    joinedValue += value2           // main thread continues while coroutine is delayed
    Thread.sleep(sleepMs)           // block main thread for x seconds to keep JVM alive
    return joinedValue
}

fun globalScopeCoroutine2(delayMs: Long, sleepMs: Long, value1: String, value2: String): String {
    var joinedValue = ""
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        delay(delayMs)              // non-blocking delay for 1 second (default time unit is ms)
        joinedValue += value1       // concat value after delay
    }
    joinedValue += value2           // main thread continues while coroutine is delayed
    runBlocking {
        // but this expression blocks the main thread
        delay(sleepMs)              // while we delay for 2 seconds to keep JVM alive
    }
    return joinedValue
}

fun globalScopeWaitForJob(delayMs: Long, value1: String, value2: String): String {
    var joinedValue = ""
    val job = GlobalScope.launch {
        // launch a new coroutine and keep a reference to its Job
        delay(delayMs)
        joinedValue += value1
    }
    joinedValue += value2
    runBlocking {
        job.join()                      // wait until child coroutine completes
    }
    return joinedValue
}

// Local scope coorutines

fun localScopeWithNestedCoroutine(delayMs1: Long, delayMs2: Long, delayMs3: Long): List<String> {
    val executionSteps: MutableList<String> = ArrayList()
    runBlocking {
        // runBlockingScope
        launch {
            delay(delayMs1)
            executionSteps.add("runBlockingScopeLaunch")
        }
        coroutineScope {
            // Creates a nestedCoroutineScope
            launch {
                delay(delayMs2)
                executionSteps.add("nestedCoroutineScopeLaunch")
            }
            delay(delayMs3)
            executionSteps.add("nestedCoroutineScope")
        }
        executionSteps.add("runBlockingScope")
    }
    return executionSteps
}

fun localScopeWithNestedCoroutine2(delayMs1: Long, delayMs2: Long, delayMs3: Long): List<String> {
    val executionSteps: MutableList<String> = ArrayList()
    runBlocking {
        // runBlockingScope
        launch {
            delay(delayMs1)
            executionSteps.add("runBlockingScopeLaunch")
        }
        coroutineScope {
            // Creates a nestedCoroutineScope
            launch {
                suspendingFunction(delayMs2, executionSteps)
            }
            delay(delayMs3)
            executionSteps.add("nestedCoroutineScope")
        }
        executionSteps.add("runBlockingScope")
    }
    return executionSteps
}

suspend fun suspendingFunction(delayMs1: Long, executionSteps: MutableList<String>) {
    delay(delayMs1)
    executionSteps.add("nestedCoroutineScopeLaunch")
}

// Coroutines ARE LIGHT-WEIGHT!

fun performanceCoroutines(count: Int) = runBlocking {
    val timeStart = System.currentTimeMillis()
    repeat(count) {
        // launch a lot of coroutines
        launch {
            delay(1000L)
            print(".")
        }
    }
    val timeEnd = System.currentTimeMillis()
    println("Coroutines creation took: ${timeEnd - timeStart} ms")
}

fun performanceThreads(count: Int) {
    val timeStart = System.currentTimeMillis()
    repeat(count) {
        // launch a lot of coroutines
        thread {
            Thread.sleep(1000L)
            print(".")
        }
    }
    val timeEnd = System.currentTimeMillis()
    println("Threads creation took: ${timeEnd - timeStart} ms")
}