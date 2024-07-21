package com.example.openinappmodel.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinappmodel.MyApplication
import com.example.openinappmodel.R
import com.example.openinappmodel.data.constants.Constants
import com.example.openinappmodel.domain.entities.LinkEntity
import com.example.openinappmodel.ui.adapters.RVAdapter_analytics
import com.example.openinappmodel.ui.adapters.RVAdapter_links
import com.example.openinappmodel.ui.viewmodels.LinksViewModel
import com.example.openinappmodel.ui.viewmodels.LinksViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LinksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LinksFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelFactory : LinksViewModelFactory
    private lateinit var viewModel : LinksViewModel
    private lateinit var linksadapter : RVAdapter_links
    private lateinit var analyticsAdapter : RVAdapter_analytics

    private lateinit var recentlinkslist : List<LinkEntity>
    private lateinit var toplinkslist : List<LinkEntity>

    private lateinit var btnTopLinks : Button
    private lateinit var btnRecentLinks : Button

    private lateinit var tvGreetings : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_links, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModelFactory = LinksViewModelFactory(MyApplication.repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LinksViewModel::class]


        recentlinkslist = emptyList()
        toplinkslist = emptyList()

        linksadapter = RVAdapter_links(recentlinkslist)
        viewModel.getRecentLinks(Constants.BEARER_TOKEN).observe(viewLifecycleOwner, Observer { links ->

            if (links != null) {
                recentlinkslist = links
            }

//            if (links != null) {
//                for (link in links) {
//                    Log.v("API_CHECK", link.urlId.toString())
//                }
//            }
        })

        viewModel.getTopLinks(Constants.BEARER_TOKEN).observe(viewLifecycleOwner, Observer { links ->

            if (links != null) {
                toplinkslist = links
                linksadapter.also {
                    it.list = toplinkslist
                    it.notifyDataSetChanged()
                }
            }
        })

        btnRecentLinks = view.findViewById(R.id.btn_recentlinks)
        btnTopLinks = view.findViewById(R.id.btn_toplinks)

        btnRecentLinks.setOnClickListener(this)
        btnTopLinks.setOnClickListener(this)



        val chart = view.findViewById<LineChart>(R.id.chart)
        val entries = mutableListOf<Entry>()

//        viewModel.getChartData(Constants.BEARER_TOKEN).observe(viewLifecycleOwner, Observer { list ->
//
//        })

        entries.add(Entry(1f, 2.0f))
        entries.add(Entry(2f, 4.0f))
        entries.add(Entry(3f, 6.0f))
        entries.add(Entry(4f, 88.0f))
        entries.add(Entry(5f, 2.0f))

        val dataset = LineDataSet(entries, "label")

        val linedata = LineData(dataset)

        chart.data = linedata
        chart.invalidate()


        val list = mutableListOf<Pair<String, String>>()
        analyticsAdapter = RVAdapter_analytics(list)

        viewModel.get(Constants.BEARER_TOKEN).observe(viewLifecycleOwner, Observer { response ->

            if (response != null) {
                list.add(Pair(response.todayClicks.toString(), "Today's Clicks"))
                list.add(Pair(response.topLocation, "Top Location"))
                list.add(Pair(response.topSource, "Top Source"))
                list.add(Pair(response.startTime, "Best Time"))

                analyticsAdapter.also {
                    it.list = list
                    it.notifyDataSetChanged()
                }
            }

        })


        setUpRV(view)
        context?.let { setBtn(btnTopLinks, it) }

        tvGreetings = view.findViewById(R.id.tv_greetings)
        setGreetings()




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LinksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LinksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setBtn(btn : Button, context: Context) {
        btnRecentLinks.also {
            it.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            it.setTextColor(ContextCompat.getColor(context, R.color.brandeis_blue))
        }
        btnTopLinks.also {
            it.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            it.setTextColor(ContextCompat.getColor(context, R.color.brandeis_blue))
        }
        btn.also {
            it.setBackgroundColor(ContextCompat.getColor(context, R.color.brandeis_blue))
            it.setTextColor(ContextCompat.getColor(context, R.color.white))
        }

    }

    private fun setUpRV(view: View) {

        view.findViewById<RecyclerView>(R.id.rv_analytics).also {
            it.adapter = analyticsAdapter
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            it.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
//            it.isNestedScrollingEnabled = false
        }


        view.findViewById<RecyclerView>(R.id.rv_links).also {
            it.adapter = linksadapter
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.btn_recentlinks -> {
                context?.let { setBtn(btnRecentLinks, it) }
                linksadapter.also {
                    it.list = recentlinkslist
                    it.notifyDataSetChanged()
                }
            }

            R.id.btn_toplinks -> {
                context?.let { setBtn(btnTopLinks, it) }
                linksadapter.also {
                    it.list = toplinkslist
                    it.notifyDataSetChanged()
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setGreetings() {
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

        when(timeOfDay) {
            in 0..11 -> {
                tvGreetings.text = "Good Morning"
            }
            in 12..15 -> {
                tvGreetings.text = "Good Afternoon"
            }
            in 16..20 -> {
                tvGreetings.text = "Good Evening"
            }
            in 21..23 -> {
                tvGreetings.text = "Good Night"
            }
        }
    }
}