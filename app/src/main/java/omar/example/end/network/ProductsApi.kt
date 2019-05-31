package omar.example.end.network

import io.reactivex.Single
import omar.example.end.model.Product
import retrofit2.http.GET

interface ProductsApi {

    companion object {
        const val BASE_URL = "https://www.endclothing.com/"
        const val PRODUCTS = "media/catalog/example.json"
    }

    @GET(PRODUCTS)
    fun getProducts(): Single<ProductsResponse>

    data class ProductsResponse(val products: List<Product>)
}

