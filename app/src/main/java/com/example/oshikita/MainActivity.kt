package com.example.oshikita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oshikita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Member>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMembers.setHasFixedSize(true)

        list.addAll(getListMembers())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMembers(): ArrayList<Member> {
        val dataFullname = resources.getStringArray(R.array.data_fullname)
        val dataNickname = resources.getStringArray(R.array.data_nicknames)
        val dataFanbase = resources.getStringArray(R.array.data_fanbase)
        val dataJiko = resources.getStringArray(R.array.data_jiko)
        val dataGeneration = resources.getIntArray(R.array.data_generation)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listMember = ArrayList<Member>()
        for(i in dataFullname.indices) {
            val name = dataFullname[i]
            val nicknames = dataNickname[i].split(", ").toTypedArray()
            val fanbase = dataFanbase[i]
            val generation = dataGeneration[i]
            val jiko = dataJiko[i]
            val description = dataDescription[i]
            val photo = dataPhoto.getResourceId(i, -1)

            listMember.add(Member(
                fullname = name,
                nicknames = nicknames,
                fanbase = fanbase,
                generation = generation,
                jiko = jiko,
                description = description,
                photo = photo
            ))
        }

        return listMember
    }

    private fun showRecyclerList() {
        binding.rvMembers.layoutManager = LinearLayoutManager(this)
        val listMemberAdapter = ListMemberAdapter(list)
        binding.rvMembers.adapter = listMemberAdapter
        listMemberAdapter.setOnItemClickCallback(object: ListMemberAdapter.OnItemCLickCallback {
            override fun onItemClicked(member: Member) {
                showSelectedMember(member)
            }
        })
    }

    private fun showSelectedMember(member: Member) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MEMBER, member)
        startActivity(intent)
    }
}