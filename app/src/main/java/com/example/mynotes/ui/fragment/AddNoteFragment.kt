package com.example.mynotes.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mynotes.Model.Notes
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentAddNoteBinding
import java.text.SimpleDateFormat


class AddNoteFragment : Fragment() {

    lateinit var binding:FragmentAddNoteBinding
    lateinit var dateString: String
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentAddNoteBinding.inflate(layoutInflater,container,false)

        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat("MMM dd, yyyy   h:mm a")
        dateString= sdf.format(date)
        binding.txtTime.setText(dateString)

        binding.btncreate.setOnClickListener {
            createNotes(it)
        }



        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.editTextCreate.text.toString()
        val note=binding.editTextNote.text.toString()
        val datetime =dateString.toString()

        val data= Notes(null, title = title,notes=note, date = datetime)
        viewModel.addNotes(data)
       // Toast.makeText(requireActivity(), "Done", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(com.example.mynotes.R.id.action_addNoteFragment_to_homeFragment)


    }


}