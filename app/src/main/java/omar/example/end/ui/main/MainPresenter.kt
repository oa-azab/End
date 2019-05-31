package omar.example.end.ui.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import omar.example.end.network.ProductsApi
import omar.example.end.network.ProductsRemoteRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(private val remoteRepository: ProductsRemoteRepository) :
        MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun start() {
        view.showLoading(true)
        val disposable = remoteRepository.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showLoading(false)
                    view.showProducts(it.products)
                }, {
                    view.showLoading(false)
                    view.showFailure()
                })
        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        compositeDisposable.dispose()
    }
}