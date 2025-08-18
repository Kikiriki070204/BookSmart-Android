package com.example.booksmartapp.select.BibliotecasRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.LibroBiblioteca;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.models.Prestamos;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.models.requests.ChangePassRequest;
import com.example.booksmartapp.models.requests.PrestamosRequest;
import com.example.booksmartapp.models.requests.SearchRequest;
import com.example.booksmartapp.register.routes.AuthRoutes;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.ErrorResponse;
import com.example.booksmartapp.responses.LibroUbicacionResponse;
import com.example.booksmartapp.responses.LoginResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.responses.VerifyResponse;
import com.example.booksmartapp.retrofit.auth_request;
import com.example.booksmartapp.retrofit.business_request;
import com.example.booksmartapp.retrofit.auth_header_request;
import com.example.booksmartapp.select.routes.BibliotecaRoutes;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BibliotecasRepository {
    private Retrofit retrofit, headerRetrofit;
    private String token;
    private int id;
    private SessionManager sessionManager;


    public BibliotecasRepository(Context context) {
        sessionManager = SessionManager.getInstance();
        token = sessionManager.getToken(context);
        if(sessionManager.getUsuario(context) == null)
        {
            id = sessionManager.getUser().getId();
        }
        else
        {
            id = sessionManager.getUsuario(context).getId();
        }
        setRetrofit();
    }

    private void setRetrofit() {
        retrofit = business_request.getRetrofit(token);
    }

    private void authRetrofit()
    {
        headerRetrofit = auth_header_request.getRetrofitWithInterceptor(token);
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


    public MutableLiveData<ApiResponse<List<Prestamo>>> getPrestamos(int bibliotecaId, int usuarioId) {
        setRetrofit();
        BibliotecaRoutes prestamoRoute = retrofit.create(BibliotecaRoutes.class);
        MutableLiveData<ApiResponse<List<Prestamo>>> result = new MutableLiveData<>();
        prestamoRoute.getPrestamosByBiblioteca(bibliotecaId, usuarioId).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiResponse<List<Prestamo>>> call, Response<ApiResponse<List<Prestamo>>> response) {
                ApiResponse<List<Prestamo>> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<List<Prestamo>> errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse<List<Prestamo>>> call, Throwable t) {
            result.setValue(null);
            }
        });
        return result;
    }

    public MutableLiveData<ApiResponse<Usuario>> getUsuarioInfo()
    {
        authRetrofit();
        AuthRoutes authRoute = headerRetrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<Usuario>> result = new MutableLiveData<>();

        authRoute.getUserInfo().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<Usuario>> call, Response<ApiResponse<Usuario>> response) {
                ApiResponse<Usuario> usuarioApiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(usuarioApiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<Usuario> errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse<Usuario>> call, Throwable t) {
            result.setValue(null);
            }
        });
        return result;
    }

    public MutableLiveData<ApiResponse> changePassword(ChangePassRequest request) {
        authRetrofit();
        AuthRoutes authRoute = headerRetrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse> result = new MutableLiveData<>();

        authRoute.cambiarContrasena(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ApiResponse changeResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(changeResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                result.setValue(null);
            }
        });
    return result;
    }

    public MutableLiveData<ApiResponse<Prestamo>> getPrestamo(int prestamoId) {
        setRetrofit();
        BibliotecaRoutes prestamoRoute = retrofit.create(BibliotecaRoutes.class);
        MutableLiveData<ApiResponse<Prestamo>> result = new MutableLiveData<>();

        prestamoRoute.getPrestamoById(prestamoId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<Prestamo>> call, Response<ApiResponse<Prestamo>> response) {
                ApiResponse<Prestamo> prestamoApiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(prestamoApiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<Prestamo> errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse<Prestamo>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<ApiResponse<List<LibroBiblioteca>>> getLibrosByNameSearch(SearchRequest request)
    {
        setRetrofit();
        BibliotecaRoutes searchRoute = retrofit.create(BibliotecaRoutes.class);
        MutableLiveData<ApiResponse<List<LibroBiblioteca>>> result = new MutableLiveData<>();
        searchRoute.getLibrosBiblioteca(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<List<LibroBiblioteca>>> call, Response<ApiResponse<List<LibroBiblioteca>>> response) {
                ApiResponse<List<LibroBiblioteca>> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<List<LibroBiblioteca>> errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse<List<LibroBiblioteca>>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<ApiResponse<LibroUbicacionResponse>> getLibroUbicacion(int id)
    {
        setRetrofit();
        BibliotecaRoutes libroRoute = retrofit.create(BibliotecaRoutes.class);
        MutableLiveData<ApiResponse<LibroUbicacionResponse>> result = new MutableLiveData<>();

        libroRoute.getLibroUbicacion(id).enqueue(new Callback<ApiResponse<LibroUbicacionResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LibroUbicacionResponse>> call, Response<ApiResponse<LibroUbicacionResponse>> response) {
                ApiResponse<LibroUbicacionResponse> ubicacionApiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(ubicacionApiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<LibroUbicacionResponse> errorApiResponse = new ApiResponse<>();
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
            public void onFailure(Call<ApiResponse<LibroUbicacionResponse>> call, Throwable t) {
            result.setValue(null);
            }
        });
        return result;
    }

}
