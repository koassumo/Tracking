package com.igo.tracking.ui
import kotlinx.coroutines.*

object ProgressManager {
    private var job: Job? = null
    private var currentProgress: Int = 0

    fun startProgress(updateProgress: (Int) -> Unit) {
        job = CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                for (i in currentProgress..100) {
                    currentProgress = i
                    withContext(Dispatchers.Main) {
                        updateProgress(i)
                    }
                    delay(600)
                }
            }
        }
    }


    fun getCurrentProgress(): Int {
        return currentProgress
    }

    fun pauseProgress() {
        job?.cancel()
    }

    fun setCurrentProgress(progress: Int) {
        currentProgress = progress
    }
}