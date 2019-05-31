package omar.example.end.network

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductsRemoteRepository {

    private val productsApi: ProductsApi

    init {
        val retrofit = Retrofit.Builder().baseUrl(ProductsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        productsApi = retrofit.create(ProductsApi::class.java)
    }

    fun getProducts(): Single<ProductsApi.ProductsResponse> {
        return productsApi.getProducts()
    }
}