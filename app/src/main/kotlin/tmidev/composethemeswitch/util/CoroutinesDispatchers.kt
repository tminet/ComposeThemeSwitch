package tmidev.composethemeswitch.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Interface that contains the following types of [CoroutineDispatcher]:
 * - default
 * - io
 * - main
 * - unconfined
 */
interface CoroutinesDispatchers {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

/**
 * Implementation of [CoroutinesDispatchers].
 * - default: [Default][Dispatchers.Default]
 * - io: [IO][Dispatchers.IO]
 * - main: [Main][Dispatchers.Main]
 * - unconfined: [Unconfined][Dispatchers.Unconfined]
 */
class CoroutinesDispatchersImpl @Inject constructor() : CoroutinesDispatchers {
    override val default = Dispatchers.Default
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val unconfined = Dispatchers.Unconfined
}