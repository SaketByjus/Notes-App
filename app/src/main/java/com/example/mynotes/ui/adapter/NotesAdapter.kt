package com.example.mynotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Model.Notes
import com.example.mynotes.databinding.FragmentEditNoteBinding
import com.example.mynotes.databinding.ItemNotesBinding
import com.example.mynotes.ui.fragment.HomeFragment
import com.example.mynotes.ui.fragment.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder> (){
    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.itemTitle.text=data.title
        holder.binding.itemNote.text=data.notes
        holder.binding.itemDate.text=data.date


        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
   }


    override fun getItemCount()= notesList.size

}