package com.example.testingninik.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.testingninik.R
import com.example.testingninik.adapter.AdapterImageSlider
import com.example.testingninik.adapter.AdapterListEbook
import com.example.testingninik.app.ApiConfig
import com.example.testingninik.databinding.FragmentHomeBinding
import com.example.testingninik.model.ModelImageSlider
import com.example.testingninik.model.ResponModelEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var vpSlider: ViewPager
    lateinit var rvEbook: RecyclerView
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapterListEbook: AdapterListEbook
    private var listEbook: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()
    private lateinit var homeViewModel: HomeViewModel
    var listImage: ArrayList<ModelImageSlider> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listImage.add(ModelImageSlider("https://cdn.pixabay.com/photo/2016/10/14/16/38/book-1740512_960_720.png"))
        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp4664615.jpg"))
        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp8483511.jpg"))
        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp1850842.png"))
        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp8483514.png"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

//        vpSlider = view.findViewById(R.id.vp_slider)
//        rvEbook = view.findViewById(R.id.rv_Ebook)

//
//        val arraySlider= ArrayList<Int>()
//        arraySlider.add(R.drawable.buku1)
//        arraySlider.add(R.drawable.buku2)
//        arraySlider.add(R.drawable.buku3)
//
//        val adapterSlider = AdapterSlider(arraySlider, activity)
//        vpSlider.adapter = adapterSlider
//
//        val layoutManager = LinearLayoutManager(activity)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//
//        rvEbook.adapter= AdapterEbook(arrayEbook)
//        rvEbook.layoutManager = layoutManager
//


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val adapterImageSlider = AdapterImageSlider(requireActivity(), listImage)
        binding.imageSlider.setSliderAdapter(adapterImageSlider)

        ApiConfig.instanceRetrofit.getAllEbook().enqueue(object : Callback<ResponModelEbook> {
            override fun onFailure(call: Call<ResponModelEbook>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponModelEbook>,
                response: Response<ResponModelEbook>
            ) {
                if (response.isSuccessful) {
                    response.body()?.ebook?.let { listEbook.addAll(it) }
                    adapterListEbook = activity?.let { AdapterListEbook(listEbook, it) }!!
                    binding.rvEbook.apply {

                        layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = adapterListEbook

                    }
                }
            }
        }

        )


    }
}