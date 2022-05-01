import android.util.Log
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object StateHandler {
    suspend fun <T> wrapWithFlow(request: suspend () -> Response<BaseResponse<T>?>): Flow<State<Response<BaseResponse<T>?>>> {
        return flow {
            try {
                emit(State.Loading)
                val response = request()
                if (response.isSuccessful) {
                    emit(State.Success(response))
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }
}