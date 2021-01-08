package androidx.datastore.core

import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import okio.FileSystem

// Since the original tests are still just running in the JVM, here's a quick proof-of-concept to verify that things
// work from common
class CommonTestPlayground {
    @Test
    fun hello() = runBlocking {
        val dataStorePath = "test.datastore".toPath()

        val dataStore = DataStoreFactory.create(TestingSerializer()) { dataStorePath }

        dataStore.updateData { 3 }

        assertEquals(3, dataStore.data.first())

        FileSystem.SYSTEM.delete(dataStorePath)
    }
}
