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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.training.retrofitrestclient.api.Service
import th.co.cdgs.training.retrofitrestclient.recycler.Friend
import th.co.cdgs.training.retrofitrestclient.recycler.FriendListRecyclerAdapter


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var dataSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Generating your new friend", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //TODO generate new friend

            val f = Friend("My Friend", "000000001", false)
            getRetrofitService().updateFriendList(dataSize, f).enqueue(object : Callback<Friend> {
                override fun onFailure(call: Call<Friend>, t: Throwable) {
                    Log.e(TAG, "failure to call update friend", t)
                }

                override fun onResponse(call: Call<Friend>, response: Response<Friend>) {
                    Log.d(TAG, "updated ${response.body()?.name}")
                    inquiryFriendList()
                }

            })


        }


        var dataList = ArrayList<Friend>()
        for (i in 1..10) {
            dataList.add(Friend("Name $i", "$i", i % 2 == 0))
        }

        //TODO inquiry friendlist
        inquiryFriendList()



        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = FriendListRecyclerAdapter(dataList) { friend, position ->
                Snackbar.make(recyclerView, "Deleting friend", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                //TODO clicked item to delete

                getRetrofitService().delete(position).enqueue(object : Callback<Any> {
                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        Log.e(TAG, "failure to delete friend", t)
                    }

                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        inquiryFriendList()
                    }

                })

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

                //TODO clicked refresh menu
                inquiryFriendList()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun getRetrofitService() : Service {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://android101-d5d24.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Service::class.java)
    }

    private fun inquiryFriendList() {

        getRetrofitService().friendList().enqueue(object : Callback<List<Friend?>?> {
            override fun onFailure(call: Call<List<Friend?>?>, t: Throwable) {
                Log.e(TAG, "failure to call friend list", t)
            }

            override fun onResponse(call: Call<List<Friend?>?>, response: Response<List<Friend?>?>) {
                val adapter = recyclerView.adapter
                if (adapter is FriendListRecyclerAdapter) {
                    val dataList = response.body() as List<Friend?>
                    dataSize = dataList.size

                    adapter.dataList = dataList
                    adapter.notifyDataSetChanged()
                }
            }
        })

    }


}
