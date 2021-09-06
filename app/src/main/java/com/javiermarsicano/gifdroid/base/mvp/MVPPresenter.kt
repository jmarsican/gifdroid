package com.javiermarsicano.gifdroid.base.mvp


interface MVPPresenter<in V : MVPView> {


    /**
     * This should be called in the view's (Activity or Fragment)
     * setContentView() method.
     */

    fun onBindView(view: V)

    /**
     * Resumes the presentation. This should be called in the view's (Activity or Fragment)
     * onResume() method.
     */
    fun onResume()

    /**
     * Pauses the presentation. This should be called in the view's Activity or Fragment)
     * onPause() method.
     */
    fun onPause()


    /**
     * Ends the presentation. This should be called in the view's (Activity or Fragment)
     * onDestroy() or onDestroyView() method respectively.
     */
    fun onDestroy()
}
