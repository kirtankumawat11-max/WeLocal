package com.example.first_clone.auth

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.first_clone.R
import com.example.first_clone.Utils
import com.example.first_clone.databinding.FragmentSignInBinding
import com.example.first_clone.databinding.FragmentSplashBinding
import org.w3c.dom.Text



class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(layoutInflater)


        setStatusBarColor()
        getUserNumber()

        onContinueButtonClick()

        return binding.root
    }


    // onViewCreated it makes page scrollable when we open keyboard
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val imeHeight = insets.getInsets(
                WindowInsetsCompat.Type.ime()
            ).bottom

            v.setPadding(0, 0, 0, imeHeight)
            insets
        }
    }


    private fun onContinueButtonClick(){

        binding.btnContinue.setOnClickListener {
            val number = binding.etUserNumber.text.toString()

            if (number.isEmpty() || number.length != 10){
                Utils.showToast(requireContext(),"Please entre valid phone number")
            }
            else{
                val bundle = Bundle()
                bundle.putString("number",number)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)
            }
        }
    }




    private fun getUserNumber() {

        binding.etUserNumber.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(number: CharSequence?, start: Int, before: Int, count: Int) {
                val len = number?.length

                if (len == 10 ){
                    binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green))
                }
                else{
                    binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.grayish_blue))
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }


    private fun setStatusBarColor(){
        activity?.window?.apply{
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.yellow)
            statusBarColor=statusBarColors
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}