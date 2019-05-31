package omar.example.end.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import omar.example.end.App
import omar.example.end.R
import omar.example.end.model.Product
import omar.example.end.network.ProductsApi
import omar.example.end.network.ProductsApi.Companion.BASE_URL
import omar.example.end.ui.ProductAdapter
import omar.example.end.util.GridSpacingItemDecoration
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)
        presenter.setView(this)

        setupToolbar()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    /*** View Implementation ***/

    override fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showProducts(products: List<Product>) {
        val prodAdapter = ProductAdapter(products)
        recycler.swapAdapter(prodAdapter, true)

        tvProductsCount.text = getString(R.string.products_count, products.size)
    }


    /*** Private Methods ***/

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        toolbarTitle.text = getString(R.string.products_list_title)
    }


    private fun setupRecyclerView() {
        recycler.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, resources.getInteger(R.integer.products_list_span_count))
            adapter = ProductAdapter(listOf())
            addItemDecoration(
                GridSpacingItemDecoration(
                    resources.getInteger(R.integer.products_list_span_count),
                    resources.getDimensionPixelSize(R.dimen.product_list_spacing),
                    true
                )
            )
        }
    }
}
