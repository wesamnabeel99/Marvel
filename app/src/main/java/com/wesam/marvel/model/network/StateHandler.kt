import android.util.Log
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object StateHandler {
    suspend fun <T> wrapWithFlow(request: suspend () -> Response<BaseResponse<T>?>): Flow<Response<BaseResponse<T>?>?> {
        return flow {

            try {
                val response = request()
                if (response.isSuccessful) {
                    emit(response)
                } else {
                    emit(null)
                }
            } catch (throwable: Throwable) {
                emit(null)
                Log.i("RESPONSE", "${throwable.message}")
            }

        }
    }
}