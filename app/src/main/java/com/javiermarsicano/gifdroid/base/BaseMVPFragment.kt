package com.javiermarsicano.gifdroid.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.javiermarsicano.gifdroid.base.mvp.MVPPresenter
import com.javiermarsicano.gifdroid.base.mvp.MVPView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseMVPFragment<in V : MVPView, P : MVPPresenter<V>> : Fragment(), MVPView {

    private var mActivity: BaseActivity? = null

    protected abstract fun getPresenter(): P

    private var fragmentResultSubscription: Disposable? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId(), container, false)
        setHasOptionsMenu(true)
        view?.isClickable = true
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPresenter().onBindView(this as V)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
        }
    }

    override fun onDestroy() {
        removeFragmentResultSubscription()

        getPresenter().onDestroy()
        super.onDestroy()
    }

    private fun removeFragmentResultSubscription() {
        if (fragmentResultSubscription?.isDisposed == false) {
            fragmentResultSubscription?.dispose()
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(resId: Int) {
    }

    override fun onError(message: String?) {
        Timber.e("An error occurred: $message")
    }

    override fun hideKeyboard() {
        if (mActivity != null) mActivity!!.hideKeyboard()
    }

    protected fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

    /**
     * Adds this [Disposable] to [compositeDisposable] to insure that it will be disposed when
     * the view is destroyed avoiding any memory leaks.
     */
    protected fun Disposable.bindToLifecycle() = apply { compositeDisposable.add(this) }


}
