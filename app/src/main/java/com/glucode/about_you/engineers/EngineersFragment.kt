package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : Fragment() {
    private lateinit var binding: FragmentEngineersBinding
    var engineers: List<Engineer> = MockData.engineers
    lateinit var adapter: EngineersRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        adapter = EngineersRecyclerViewAdapter(engineers) { engineer ->
            goToAbout(engineer)
        }
        binding.list.adapter = adapter
        setHasOptionsMenu(true)
        setUpEngineersList()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_years -> {
                engineers = engineers.sortedBy { it.quickStats.years }
            }

            R.id.action_coffees -> {
                engineers = engineers.sortedBy { it.quickStats.coffees }
            }

            R.id.action_bugs -> {
                engineers = engineers.sortedBy { it.quickStats.bugs }
            }
        }
        adapter.updateEngineers(engineers)
        return super.onOptionsItemSelected(item)
    }

    private fun setUpEngineersList() {
        binding.list.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}