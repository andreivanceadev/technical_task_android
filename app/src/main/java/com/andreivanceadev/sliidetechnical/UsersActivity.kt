package com.andreivanceadev.sliidetechnical

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreivanceadev.sliidetechnical.databinding.UsersLayoutBinding
import com.andreivanceadev.sliidetechnical.models.AddUserEvent
import com.andreivanceadev.sliidetechnical.models.DeleteUserEvent
import com.andreivanceadev.sliidetechnical.models.LoadUsersEvent
import com.andreivanceadev.sliidetechnical.models.UserItem
import com.andreivanceadev.sliidetechnical.models.state.AppUIState
import com.andreivanceadev.sliidetechnical.ui.DialogCreator
import com.andreivanceadev.sliidetechnical.ui.SimpleDividerItemDecoration
import com.andreivanceadev.sliidetechnical.ui.users.AddUserDialog
import com.andreivanceadev.sliidetechnical.ui.users.UsersRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UsersActivity : AppCompatActivity(R.layout.users_layout) {

    companion object {
        const val ADD_USER_TAG = "Add User Dialog"
    }

    private lateinit var binding: UsersLayoutBinding

    private val viewModel: UsersViewModel by lazy { ViewModelProvider(this)[UsersViewModel::class.java] }
    private val disposables = CompositeDisposable()

    private val adapter = UsersRecyclerViewAdapter().apply {

        setCallback {
            showRemoveUserDialog(it)
        }
    }

    private fun showRemoveUserDialog(user: UserItem) {
        DialogCreator.showDualChoiceDialog(this,
            getString(R.string.dialog_remove_user_message),
            R.string.ok,
            R.string.cancel,
            { _, _ ->
                viewModel.sendEvent(DeleteUserEvent(application, user))
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UsersLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.sendEvent(LoadUsersEvent(application))

        binding.rvUsers.addItemDecoration(SimpleDividerItemDecoration(this))
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        binding.fabAddUser.setOnClickListener {
            showAddUserDialog()
        }

    }

    private fun showAddUserDialog() {
        val addUserDialog = AddUserDialog { viewModel.sendEvent(AddUserEvent(application, it)) }
        addUserDialog.show(supportFragmentManager, ADD_USER_TAG)
    }

    override fun onStart() {
        super.onStart()

        disposables.add(
            viewModel.appStateObservable
                .observeOn(mainThread())
                .subscribe(
                    this::render
                )
        )
    }

    private fun render(appUIState: AppUIState) {
        binding.loading.visibility = if (appUIState.loading) VISIBLE else GONE
        adapter.setModel(appUIState.users ?: listOf())
        appUIState.error?.run {
            DialogCreator.showErrorDialog(this@UsersActivity, this)
        }
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}