package com.example.oshikita

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.oshikita.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var member: Member

    companion object {
        const val EXTRA_MEMBER = "extra_member"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        member = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Member>(EXTRA_MEMBER, Member::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Member>(EXTRA_MEMBER)
        } ?: Member()

        showData(member)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                val sendIntent = Intent(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, member.description)
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, null))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showData(member: Member) {
        supportActionBar?.title = member.nicknames[0]

        binding.tvGeneration.text = "JKT48 generasi ${member.generation}"
        binding.tvFullname.text = member.fullname
        binding.tvNickname.text = member.nicknames.joinToString(", ")
        binding.tvFanbase.text = member.fanbase
        binding.tvJiko.text = member.jiko
        binding.tvDescription.text = member.description
        binding.imgPhoto.setImageResource(member.photo)
    }
}