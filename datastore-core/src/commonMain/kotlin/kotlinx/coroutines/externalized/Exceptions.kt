package kotlinx.coroutines.externalized

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job

/**
 * Thrown by cancellable suspending functions if the [Job] of the coroutine is cancelled or completed
 * without cause, or with a cause or exception that is not [CancellationException]
 * (see [Job.getCancellationException]).
 */
internal class JobCancellationException(
    private val job: Job
) : CancellationException(null) {
    override fun toString(): String = "${super.toString()}; job=$job"
    override fun equals(other: Any?): Boolean =
        other === this ||
                other is JobCancellationException && other.message == message && other.job == job && other.cause == cause
    override fun hashCode(): Int =
        (message!!.hashCode() * 31 + job.hashCode()) * 31 + (cause?.hashCode() ?: 0)
}
