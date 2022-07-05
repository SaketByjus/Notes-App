package com.example.mynotes.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mynotes.Model.Notes
import com.example.mynotes.R
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentEditNoteBinding
import java.text.SimpleDateFormat

class EditNoteFragment : Fragment() {

    lateinit var binding: FragmentEditNoteBinding
    lateinit var dateString:String
    val argNotes by navArgs<EditNoteFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentEditNoteBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)


        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat("MMM dd, yyyy   h:mm a")
        dateString= sdf.format(date)
        binding.txtTime1.setText(dateString)

        binding.editTextEdit.setText(argNotes.data.title)
        binding.editText.setText(argNotes.data.notes)
        binding.btnEdit.setOnClickListener{
            saveEdittedNote(it)
        }


        return binding.root
    }

    private fun saveEdittedNote(it: View?) {
        val title = binding.editTextEdit.text.toString()
        val note=binding.editText.text.toString()
        val datetime =dateString.toString()

        val data= Notes(argNotes.data.id, title = title,notes=note, date = datetime)
        viewModel.updateNotes(data)
        //Toast.makeText(requireActivity(), "Saved", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(com.example.mynotes.R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete)
        {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // Delete selected note from database
                    viewModel.deleteNotes(argNotes.data.id!!)
                    Navigation.findNavController(requireView()).navigate(com.example.mynotes.R.id.action_editNoteFragment_to_homeFragment)
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        return super.onOptionsItemSelected(item)
    }

}