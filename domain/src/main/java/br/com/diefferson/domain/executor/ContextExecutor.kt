package br.com.diefferson.domain.executor

import kotlin.coroutines.experimental.CoroutineContext

interface ContextExecutor {
    val scheduler: CoroutineContext
}