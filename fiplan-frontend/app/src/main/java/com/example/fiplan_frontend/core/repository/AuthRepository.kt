import com.example.fiplan_frontend.core.config.service.ApiService
import com.example.fiplan_frontend.core.api.ApiResponseModel
import com.example.fiplan_frontend.core.api.RepositoryResponseModel
import com.example.fiplan_frontend.auth.model.AuthRequestModel
import com.example.fiplan_frontend.auth.model.login.LoginResponseModel
import com.google.gson.Gson
import okhttp3.ResponseBody

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(request: AuthRequestModel): RepositoryResponseModel<LoginResponseModel> {
        return try {
            val response = apiService.login(request)

            if (response.isSuccessful) {
                val responseBody = response.body()
                RepositoryResponseModel.Success(
                    success = true,
                    message = responseBody?.message ?: "Operasi berhasil",
                    data = responseBody?.data,
                )

            } else {
                val errorBody = parseErrorBody(response.errorBody())
                RepositoryResponseModel.Error(
                    success = false,
                    message = errorBody.message,
                    error = errorBody.error.toString()
                )
            }

        } catch (e: Exception) {
            RepositoryResponseModel.Error(
                success = false,
                message = "Kesalahan tidak diketahui",
                error = e.localizedMessage ?: "Error"
            )
        }
    }

    suspend fun register(request: AuthRequestModel): RepositoryResponseModel<Unit> {
        return try {
            val response = apiService.register(request)

            if (response.isSuccessful) {
                val responseBody = response.body()
                RepositoryResponseModel.Success(
                    success = true,
                    message = responseBody?.message ?: "Operasi berhasil"
                )

            } else {
                val errorBody = parseErrorBody(response.errorBody())
                RepositoryResponseModel.Error(
                    success = false,
                    message = errorBody.message,
                    error = errorBody.error.toString()
                )
            }

        } catch (e: Exception) {
            RepositoryResponseModel.Error(
                success = false,
                message = "Kesalahan tidak diketahui",
                error = e.localizedMessage ?: "Error"
            )
        }

    }

    private fun parseErrorBody(errorBody: ResponseBody?): ApiResponseModel<Unit> {
        return try {
            Gson().fromJson(errorBody?.string(), ApiResponseModel::class.java) as ApiResponseModel<Unit>
        } catch (e: Exception) {
            ApiResponseModel(status = "Failed", message = "Parsing error response gagal", error = e.localizedMessage)
        }
    }
}