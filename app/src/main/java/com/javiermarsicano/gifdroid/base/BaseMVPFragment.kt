package com.javiermarsicano.gifdroid.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.javiermarsicano.gifdroid.R
import com.javiermarsicano.gifdroid.base.mvp.MVPPresenter
import com.javiermarsicano.gifdroid.base.mvp.MVPView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseMVPFragment<VB : ViewBinding, in V : MVPView, P : MVPPresenter<V>> : Fragment(), MVPView {

    private var mActivity: BaseActivity? = null

    protected abstract fun getPresenter(): P

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding!!


    private var fragmentResultSubscription: Disposable? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * @return view binding for the screen
     */
    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _viewBinding = createViewBinding(inflater, container)
        setHasOptionsMenu(true)
        view?.isClickable = true
        return viewBinding.root
    }

    protected open fun bindViews() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresenter().onBindView(this as V)
        bindViews()
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
        _viewBinding = null
        super.onDestroy()
    }

    private fun removeFragmentResultSubscription() {
        if (fragmentResultSubscription?.isDisposed == false) {
            fragmentResultSubscription?.dispose()
        }
    }

    override fun onError(resId: Int) {
    }

    override fun onError(message: String?) {
        Timber.e("An error occurred: $message")
        Toast.makeText(context, context?.getString(R.string.generic_error, message), Toast.LENGTH_SHORT).show()
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
