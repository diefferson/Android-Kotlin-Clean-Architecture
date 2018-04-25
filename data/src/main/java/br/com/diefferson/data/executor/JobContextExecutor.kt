package br.com.diefferson.data.executor

import br.com.diefferson.domain.executor.ContextExecutor
import kotlinx.coroutines.experimental.CommonPool

class JobContextExecutor : ContextExecutor {
    override val scheduler = CommonPool
}