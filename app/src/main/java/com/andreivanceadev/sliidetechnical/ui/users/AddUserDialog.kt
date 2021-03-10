package com.andreivanceadev.sliidetechnical.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.andreivanceadev.sliidetechnical.R
import com.andreivanceadev.sliidetechnical.databinding.DialogAddUserBinding
import com.andreivanceadev.sliidetechnical.models.UserItem

class AddUserDialog(private val addUserCallback: (UserItem) -> Unit) : DialogFragment() {

    private lateinit var binding: DialogAddUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddUserBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setWindowAnimations(R.style.Slide)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.btn_ok) {
                addUserCallback(
                    UserItem(
                        0,
                        binding.etName.text.toString(),
                        binding.etEmail.text.toString(),
                        "",
                        binding.tvGender.text.toString(),
                        binding.tvStatus.text.toString()
                    )
                )
            }
            dismiss()
            return@setOnMenuItemClickListener true
        }
        binding.toolbar.title = getString(R.string.add_user_title)
        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }
        binding.tvGender.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu_popup,
                arrayOf("Male", "Female")
            )
        )
        binding.tvStatus.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu_popup,
                arrayOf("Active", "Inactive")
            )
        )
    }
}