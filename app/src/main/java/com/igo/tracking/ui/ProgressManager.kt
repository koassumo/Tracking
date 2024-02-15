package com.igo.tracking.ui
import kotlinx.coroutines.*

object ProgressManager {
    private var job: Job? = null
    private var job2: Job? = null
    private var currentProgress: Int = 0
    private var currentProgress2: Int = 0
    private var onCompleteListener: (() -> Unit)? = null
    private var onCompleteListener2: (() -> Unit)? = null

    fun startProgress(updateProgress: (Int) -> Unit) {
        job = CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                for (i in currentProgress..100) {
                    currentProgress = i
                    withContext(Dispatchers.Main) {
                        updateProgress(i)
                    }
                    delay(100)
                }
                onCompleteListener?.invoke()
            }
        }
    }

    fun startProgress2(updateProgress: (Int) -> Unit) {
        job2 = CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                for (i in currentProgress2..100) {
                    currentProgress2 = i
                    withContext(Dispatchers.Main) {
                        updateProgress(i)
                    }
                    delay(100)
                }
                onCompleteListener2?.invoke()
            }
        }
    }
    fun setOnCompleteListener(listener: () -> Unit) {
        onCompleteListener = listener
    }
    fun setOnCompleteListener2 (listener: () -> Unit) {
        onCompleteListener2 = listener
    }
    fun removeOnCompleteListener() {
        onCompleteListener = null
    }

   fun removeOnCompleteListener2() {
        onCompleteListener2 = null
    }





    fun getCurrentProgress(): Int {
        return currentProgress
    }

    fun getCurrentProgress2(): Int {
        return currentProgress2
    }

    fun pauseProgress() {
        job?.cancel()
    }

    fun setCurrentProgress(progress: Int) {
        currentProgress = progress
    }
}