package omar.example.end.ui.main

import omar.example.end.model.Product

interface MainContract {

    interface View {
        fun showLoading(show: Boolean)

        fun showProducts(products: List<Product>)
    }

    interface Presenter {
        fun setView(view: View)

        fun start()

        fun destroy()
    }

}