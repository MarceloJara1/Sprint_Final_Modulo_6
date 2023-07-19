package com.example.sprintfinalmodulo6.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.sprintfinalmodulo6.databinding.FragmentSecondBinding
import com.example.sprintfinalmodulo6.viewModel.ViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewModel by activityViewModels()
    private var phoneId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            phoneId = bundle.getString("phoneId")
        }
        phoneId?.let{id->
            viewModel.getPhoneDetailByIdFromInternet(id)
        }
        viewModel.getPhonesDetail().observe(viewLifecycleOwner, Observer {
            var id = it.id
            var name = it.name
            Glide.with(binding.imgDetail).load(it.image).into(binding.imgDetail)
            binding.txtName.text = it.name
            binding.txtPrice.text = "Ahora $${it.price.toString()}"
            binding.txtLastPrice.text = "Antes $${it.lastPrice.toString()}"
            binding.txtDetail.text = it.description
            if(it.credit){
                binding.txtCredit.text = "Acepta Crédito"
            }else{
                binding.txtCredit.text = "Sólo Efectivo"
            }


            binding.btnMail.setOnClickListener {
                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@novaera.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta $name id: $id"
                )
                mintent.putExtra(
                    Intent.EXTRA_TEXT,"“Hola\n" +
                            "Vi la propiedad $name de código $id y me gustaría\n" +
                            "que me contactaran a este correo o al siguiente número\n" +
                            "+569 73445532\n"+
                            "Quedo atento.”"
                )
                try {
                    startActivity(mintent)
                }catch (e:Exception){
                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}