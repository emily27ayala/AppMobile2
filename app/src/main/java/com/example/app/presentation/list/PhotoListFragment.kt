package com.example.app.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.presentation.Singletons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PhotoListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PhotoAdapter(listOf(), ::onClickedPhoto)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_list_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.photo_recyclerview)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@PhotoListFragment.adapter
        }
        Singletons.photoApi.getPhotoList().enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val photoResponse= response.body()!!
                    adapter.updateList(photoResponse)
                }
            }

        })

    }
    private fun onClickedPhoto(id: Int) {
        findNavController().navigate(R.id.navigateToPhotoDetailFragment, bundleOf(
            "photoId" to (id)+1
        ))
    }
}