/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.datastore.core

import okio.IOException
import okio.Sink
import okio.Source
import okio.buffer
import kotlin.jvm.Volatile

internal class TestingSerializer(
    @Volatile var failReadWithCorruptionException: Boolean = false,
    @Volatile var failingRead: Boolean = false,
    @Volatile var failingWrite: Boolean = false,
    override val defaultValue: Byte = 0
) : Serializer<Byte> {
    override fun readFrom(input: Source): Byte {
        if (failReadWithCorruptionException) {
            throw CorruptionException(
                "CorruptionException",
                IOException()
            )
        }

        if (failingRead) {
            throw IOException("I was asked to fail on reads")
        }

        val buffer = input.buffer()
        return if (buffer.exhausted()) {
            0
        } else {
            buffer.readByte()
        }.also { buffer.close() }
    }

    override fun writeTo(t: Byte, output: Sink) {
        if (failingWrite) {
            throw IOException("I was asked to fail on writes")
        }
        output.buffer().writeByte(t.toInt()).close()
    }
}
