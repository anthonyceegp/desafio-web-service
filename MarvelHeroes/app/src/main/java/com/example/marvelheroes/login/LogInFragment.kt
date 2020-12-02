package com.example.marvelheroes.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.marvelheroes.HomeActivity
import com.example.marvelheroes.R

class LogInFragment : Fragment() {
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        initLogInButton(view)
        initCreateButton(view)
    }

    private fun initLogInButton(view: View) {
        val loginButton = view.findViewById<Button>(R.id.log_in_button)
        loginButton.setOnClickListener {
            val intent = Intent(this.context, HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun initCreateButton(view: View) {
        val createAccountButton = view.findViewById<Button>(R.id.create_account_button)
        createAccountButton.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }
}