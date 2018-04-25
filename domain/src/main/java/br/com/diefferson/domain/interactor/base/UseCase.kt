package br.com.diefferson.domain.interactor.base


import br.com.diefferson.domain.executor.ContextExecutor
import br.com.diefferson.domain.executor.PostExecutionContext
import kotlinx.coroutines.experimental.*

/**
 * Abstract class for a UseCaseCompletable that returns an instance of a [T].
 */
abstract class UseCase<T, in Params> internal constructor(
        private val contextExecutor: ContextExecutor,
        private val postExecutionContext: PostExecutionContext) {

    private val executionJob = Job()

    /**
     * Builds a [T] which will be used when the current [UseCaseCallback] is executed.
     */
    internal abstract suspend fun buildUseCaseObservable(params: Params): T

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params,onError: (e: Throwable) -> Unit = {},  onSuccess: (T) -> Unit = {}) {

        launch(contextExecutor.scheduler, parent = executionJob) {
            try {
                val response  = buildUseCaseObservable(params)

                withContext(postExecutionContext.scheduler) {
                    onSuccess(response)
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