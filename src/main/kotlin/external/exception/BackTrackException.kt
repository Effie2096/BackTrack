package effie.soft.external.exception

class BackTrackException(
    override val message: String,
    override val cause: Throwable? = null,
    val errorCode: BackTrackErrorCodes
) : Exception(message, cause) {

    override fun toString(): String {
        return buildString {
            append(super.toString() + "\n")
            if (cause != null) {
                append("\tCause: " + (cause.cause ?: " ").toString() + (cause.message ?: " "))
            }
            append("\n")
        }
    }

}