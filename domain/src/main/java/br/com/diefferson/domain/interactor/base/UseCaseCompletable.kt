package br.com.diefferson.domain.interactor.base

import br.com.diefferson.domain.executor.ContextExecutor
import br.com.diefferson.domain.executor.PostExecutionContext
import kotlinx.coroutines.experimental.*

/**
 * Abstract class for a UseCaseCompletable.
 */
abstract class UseCaseCompletable<in Params> internal constructor(
        private val contextExecutor: ContextExecutor,
        private val postExecutionContext: PostExecutionContext) {

    private val executionJob = Job()

    internal abstract suspend fun buildUseCaseObservable(params: Params)

    /**
     * Executes the current use case.
     */
    fun execute(params: Params, onError: (e: Throwable) -> Unit = {}, onComplete: () -> Unit = {}) {
        launch(contextExecutor.scheduler, parent = executionJob) {
            try {
                buildUseCaseObservable(params)
                withContext(postExecutionContext.scheduler) {
                    onComplete()
                }
            } catch (e: Exception) {
                withContext(postExecutionContext.scheduler) {
                    onError(e)
                }
            }
        }
    }

    /**
     * Dispose execution
     */
    fun dispose() {
        executionJob.cancel()
    }
}