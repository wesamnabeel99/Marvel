import com.wesam.marvel.model.network.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object StateHandler {

    suspend fun <T> handleRequestState(makeApiCall: suspend () -> Response<T>): Flow<State<T>> {
        return flow {
            emit(State.Loading)
            val response = makeApiCall()
            try {
                if (response.isSuccessful) {
                    val isResponseEmpty = checkResponseBody(response)
                    if (isResponseEmpty) {
                        emit(State.Error("EMPTY JSON DUE TO BUG IN API"))
                        makeApiCall()
                    } else {
                        emit(State.Success(response.body()!!))
                    }
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (t:Throwable) {
                emit(State.Error(t.message.toString()))
            }
        }
    }

    private fun <T> checkResponseBody(response: Response<T>) = response.body().toString() == ""

}

