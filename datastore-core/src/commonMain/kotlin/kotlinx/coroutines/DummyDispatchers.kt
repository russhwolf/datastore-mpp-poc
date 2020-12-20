package kotlinx.coroutines

// FIXME temp dispatcher since IO doesn't exist in common
val Dispatchers.IO get() = Dispatchers.Default
