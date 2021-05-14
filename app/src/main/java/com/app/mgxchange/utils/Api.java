package com.app.mgxchange.utils;

import com.app.mgxchange.models.LoginUserResponse;
import com.app.mgxchange.models.RegisterUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register-user.php")
    Call<RegisterUserResponse> registerUser(
        @Field("firstname") String firstName,
        @Field("lastname") String lastName,
        @Field("method") String method,
        @Field("contact") String contact,
        @Field("address") String address,
        @Field("email") String email,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login-user.php")
    Call<LoginUserResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
//
//    @FormUrlEncoded
//    @POST("login-admin.php")
//    Call<LoginAdminResponse> loginAdmin(
//            @Field("email") String email,
//            @Field("password") String password
//    );
//
//    @GET("fetch-users.php")
//    Call<UserListResponse> fetchAllUsers();
//
//    @GET("complain-list-admin.php")
//    Call<ComplainListResponse> fetchAllComplains();
//
//    @GET("fetch-sell-products-active.php")
//    Call<ActiveSellProductListResponse> fetchAllActiveSellProducts();
//
//    @GET("fetch-loan-products-active.php")
//    Call<ActiveLoanProductListResponse> fetchAllActiveLoanProducts();
//
//    @GET("fetch-sell-products-inactive.php")
//    Call<InActiveSellProductListResponse> fetchAllInActiveSellProducts();
//
//    @GET("fetch-loan-products-inactive.php")
//    Call<InActiveLoanProductListResponse> fetchAllInActiveLoanProducts();
//
//    @FormUrlEncoded
//    @POST("delete-loan-product.php")
//    Call<DeleteProductResponse> deleteLoanProduct(
//            @Field("id") String productID
//    );
//
//    @FormUrlEncoded
//    @POST("delete-sell-product.php")
//    Call<DeleteProductResponse> deleteSellProduct(
//            @Field("id") String productID
//    );
}
