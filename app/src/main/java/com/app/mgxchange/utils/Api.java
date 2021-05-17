package com.app.mgxchange.utils;

import com.app.mgxchange.models.ActiveLoanProductListResponse;
import com.app.mgxchange.models.ActiveSellProductListResponse;
import com.app.mgxchange.models.LoginUserResponse;
import com.app.mgxchange.models.RegisterUserResponse;
import com.app.mgxchange.models.UserAddLoanProductResponse;
import com.app.mgxchange.models.UserAddSellProductResponse;
import com.app.mgxchange.models.UserProfileImageResponse;

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

    @FormUrlEncoded
    @POST("fetch-loan-products-active-user.php")
    Call<ActiveLoanProductListResponse> getLoanProductList(
            @Field("userid") String userID
    );

    @FormUrlEncoded
    @POST("fetch-sell-products-active-user.php")
    Call<ActiveSellProductListResponse> getSellProductList(
            @Field("userid") String userID
    );

    @FormUrlEncoded
    @POST("update-user-profile-image.php")
    Call<UserProfileImageResponse> updateUserImage(
            @Field("userid") String userID,
            @Field("encoded_image") String encodedImage
    );

    @FormUrlEncoded
    @POST("add-sell-product-user.php")
    Call<UserAddSellProductResponse> addSellProduct(
            @Field("user_id") String userID,
            @Field("product_name") String productName,
            @Field("product_year") String productYear,
            @Field("product_serial") String productSerial,
            @Field("product_condition") String productCondition,
            @Field("asked_amount") String askedAmount,
            @Field("contact_number") String contactNumber,
            @Field("product_details") String productDetails,
            @Field("encoded_image") String productImage
    );

    @FormUrlEncoded
    @POST("add-loan-product-user.php")
    Call<UserAddLoanProductResponse> addLoanProduct(
            @Field("user_id") String userID,
            @Field("product_name") String productName,
            @Field("product_year") String productYear,
            @Field("product_serial") String productSerial,
            @Field("product_condition") String productCondition,
            @Field("asked_amount") String askedAmount,
            @Field("contact_number") String contactNumber,
            @Field("product_details") String productDetails,
            @Field("encoded_image") String productImage
    );
//    @GET("fetch-loan-products-active.php")
//    Call<ActiveLoanProductListResponse> fetchAllActiveLoanProducts();
//
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
