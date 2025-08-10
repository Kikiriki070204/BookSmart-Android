package com.example.booksmartapp.select.BibliotecasRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.register.routes.AuthRoutes;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.ErrorResponse;
import com.example.booksmartapp.responses.LoginResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.responses.VerifyResponse;
import com.example.booksmartapp.retrofit.auth_request;
import com.example.booksmartapp.retrofit.business_request;
import com.example.booksmartapp.select.routes.BibliotecaRoutes;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BibliotecasRepository {
    private Retrofit retrofit;
    private String token;
    private int id;
    private SessionManager sessionManager;


    public BibliotecasRepository(Context context) {
        sessionManager = SessionManager.getInstance();
        token = sessionManager.getToken(context);
        id = sessionManager.getUsuario().getId();
        setRetrofit();
    }

    private void setRetrofit() {
        retrofit = business_request.getRetrofit(token);
    }

    public MutableLiveData<ApiResponse<Bibliotecas>> getBibliotecas() {
        setRetrofit();
        BibliotecaRoutes bibliotecaRoutes = retrofit.create(BibliotecaRoutes.class);
        MutableLiveData<ApiResponse<Bibliotecas>> result = new MutableLiveData<>();

        bibliotecaRoutes.getBibliotecas(id).enqueue(new retrofit2.Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<Bibliotecas>> call, Response<ApiResponse<Bibliotecas>> response) {
                ApiResponse<Bibliotecas> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<Bibliotecas> errorApiResponse = new ApiResponse<>();
                            errorApiResponse.setStatus(errorResponse.getStatus());
                            errorApiResponse.setMsg(errorResponse.getMsg());
                            errorApiResponse.setData(null);

                            result.setValue(errorApiResponse);
                        } catch (Exception e) {
                            e.printStackTrace();
                            result.setValue(null);
                        }
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ApiResponse<Bibliotecas>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

}
