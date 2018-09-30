package szeptunm.corner.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import szeptunm.corner.R

abstract class ToolbarFragment : BaseFragment() {

    @get:LayoutRes
    abstract override val layoutResource: Int

    val toolbar: Toolbar
        get() = viewDataBinding.root.findViewById(R.id.toolbar)

    private val actionBar: ActionBar?
        get() = baseActivity?.supportActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            hideKeyboard(requireActivity())
            requireFragmentManager().popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setupToolbar()
        return view
    }

    private fun setupToolbar() {
        val toolbar = toolbar
        toolbar.setNavigationIcon(R.drawable.ic_close_white)
        val title = toolbarTitle()
        toolbar.title = title ?: ""
        setActionBar(toolbar)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun toolbarTitle(): String? {
        return null
    }

    private fun setActionBar(toolbar: Toolbar) {
        baseActivity?.setSupportActionBar(toolbar)
    }
}
