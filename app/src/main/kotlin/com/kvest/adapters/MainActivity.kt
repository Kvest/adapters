package com.kvest.adapters

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kvest.adapters.ext.observe
import com.kvest.adapters.ext.withViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val timeDao = (application as App).timeDao
        val factory = MainViewModel.createFactory(timeDao)

        list.layoutManager = LinearLayoutManager(this)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        withViewModel<MainViewModel>(factory) {
            //val adapter = UsualAdapter(this@MainActivity, this)
            val adapter = DatabindingAdapter(
                    this@MainActivity,
                    TimeEntityDiffCallback,
                    R.layout.binding_list_item,
                    BR.item) {
                setVariable(BR.handler, this@withViewModel)
            }
            list.adapter = adapter

            actionAdd.setOnClickListener {
                this.addTime()
            }

            observe(times) { newItems ->
                adapter.submitList(newItems)
            }

            observe(selectEvent) {
                it?.let {
                    Toast.makeText(
                            this@MainActivity,
                            "selected ${it.value} with id ${it.id}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}