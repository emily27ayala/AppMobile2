package com.example.app.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.app.R
import com.example.app.presentation.Singletons
import com.example.app.presentation.api.PhotoDetailResponse
import com.example.app.presentation.list.PhotoListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PhotoDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var imgView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photo_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName= view.findViewById(R.id.photo_detail_name)
        imgView= view.findViewById(R.id.photo_detail_img)
        callApi()
    }
    private fun callApi(){
        val id = arguments?.getInt("photoId")?:-1
        Singletons.photoApi.getPhotoDetail(id).enqueue(object : Callback<PhotoDetailResponse>{
            override fun onResponse(
                call: Call<PhotoDetailResponse>,
                response: Response<PhotoDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    textViewName.text = response.body()!!.author
                    loadImage(Glide.with(),response.body()!!.download_url,imgView)
                }
            }
            override fun onFailure(call: Call<PhotoDetailResponse>, t: Throwable) {

            }

        })
    }
    fun loadImage(glide: RequestManager, url: String?, view: ImageView?) {
        glide.load(url).into(requireView() as ImageView)
    }

}