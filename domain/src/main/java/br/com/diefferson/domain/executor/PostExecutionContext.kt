package br.com.diefferson.domain.executor

import kotlin.coroutines.experimental.CoroutineContext

interface PostExecutionContext{
    val scheduler: CoroutineContext
}