package br.com.disapps.homepet.executor

import br.com.diefferson.domain.executor.PostExecutionContext
import kotlinx.coroutines.experimental.android.UI

class UIContext : PostExecutionContext {
    override val scheduler = UI
}