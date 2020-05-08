package th.co.cdgs.training.retrofitrestclient

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import th.co.cdgs.training.retrofitrestclient.recycler.Friend
import th.co.cdgs.training.retrofitrestclient.recycler.FriendListRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

            // TODO fab handler

        }


        // TODO this is mocking data

        var dataList = ArrayList<Friend>()
        for (i in 1..10) {
            dataList.add(Friend("Name $i", "$i", i % 2 == 0))
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = FriendListRecyclerAdapter(dataList) { friend, position ->

                // TODO item clicked

            }
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_refresh -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
