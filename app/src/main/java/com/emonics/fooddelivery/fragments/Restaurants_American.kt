package com.emonics.fooddelivery.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emonics.fooddelivery.Activity.MenuActivity

import com.emonics.fooddelivery.R
import com.emonics.fooddelivery.adapters.MyItemRecyclerViewAdapter
import com.emonics.fooddelivery.callback.ClickListener
import com.emonics.fooddelivery.placeholder.PlaceholderContent
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception


class Restaurants_American : Fragment() {
    private var columnCount = 1
    private var json_name: Int = R.raw.american
    private var adapter: MyItemRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            json_name = it.getInt("JSON", R.raw.american)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurants__list_american, container, false) as RecyclerView

        val items: ArrayList<PlaceholderContent.PlaceholderItem> = ArrayList()


        // parse json
        // read json from raw
        try {
            val in_s = resources.openRawResource(json_name)

            val b = ByteArray(in_s.available())
            in_s.read(b)

            val json = String(b)
            val list = JSONArray(json)
            for(i in 0..list.length() - 1) {
                val item: JSONObject = list[i] as JSONObject

                val id = item["image"] as String
                val content = item["name"] as String
                val detail = item["address"] as String

                items.add(PlaceholderContent.PlaceholderItem(id, content, detail))
            }

        } catch(ex: Exception) {

        }

        // Set the adapter
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

        }

        adapter = MyItemRecyclerViewAdapter(items)
        view.adapter = adapter

        adapter!!.setOnItemClickListener(object : ClickListener<PlaceholderContent.PlaceholderItem> {
            override fun onClick(
                view: View?,
                data: PlaceholderContent.PlaceholderItem,
                position: Int
            ) {
                val restaurantName = data.toString()
                when (restaurantName) {
                    "Chicago's Pizza", "McDonald's", "Zaxby's" -> {
                        val bundle =  Bundle()
                        bundle.putString("data", "American")
                        val intent = Intent(activity, MenuActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    "Fried Rice", "Panda Wu", "Lee Wu" -> {
                        val bundle =  Bundle()
                        bundle.putString("data", "Chinese")
                        val intent = Intent(activity, MenuActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    else -> {
                        val bundle =  Bundle()
                        bundle.putString("data", "Thai")
                        val intent = Intent(activity, MenuActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }

            }

        })

        return view
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int, json: Int) =
            Restaurants_American().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putInt("JSON", json)
                }
            }
    }
}