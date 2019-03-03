package com.alp.usermanager.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.alp.usermanager.R
import com.alp.usermanager.adapters.FriendsAdapter
import com.alp.usermanager.service.Api
import com.alp.usermanager.service.IDataService
import com.alp.usermanager.service.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FriendsAdapter.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFriends()

        refreshFriends(getDummyFriends()) // TODO remove this
    }

    @SuppressLint("CheckResult")
    private fun getFriends() {
        Api.getDataClient().create(IDataService::class.java)
            .getFriends()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { friends -> refreshFriends(friends) },
                { error -> showError(error.message.toString()) })

    }

    private fun refreshFriends(friends: List<User>) {
        if (friends.isEmpty()) {
            empty_view.visibility = View.VISIBLE
            rv_friends.visibility = View.GONE
        }

        rv_friends.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        rv_friends.adapter = FriendsAdapter(friends.toMutableList(), this)
    }

    private fun getDummyFriends(): MutableList<User> {
        return mutableListOf(
            User(1, "nonummy.ipsum@vulputate.edu", "Townsend", "https://m.media-amazon.com/images/M/MV5BMTQzMzg1ODAyNl5BMl5BanBnXkFtZTYwMjAxODQ1._V1_UX214_CR0,0,214,317_AL_.jpg", "Townsend"),
            User(2, "non.sapien.molestie@sit.co.uk", "Cohen", "https://images.businessoffashion.com/profiles/asset/1577151615231141/selena-gomez-1577151796839511.png?auto=format%2Ccompress&crop=top&fit=crop&h=576&w=1024", "Connor"),
            User(3, "feugiat@metusfacilisis.com", "Irwin", "https://isbh.tmgrup.com.tr/sbh/2018/01/17/keanu-reeves-kimdir-1516180917853.jpg", "Gabriel"),
            User(4, "gravida@nonvestibulum.ca", "Irwin", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTqrfEVHsxa2YDemb16M6xJ5nJtj5o_5BCiLhEbp5BtBJNhYm9", "Salazar"),
            User(5, "ivamus@malesuada.ca", "Coffey", "https://isbh.tmgrup.com.tr/sbh/2018/01/18/brad-pitt-kimdir-1516264639053.jpg", "Leo"),
            User(6, "quis.lectus@semper.edu", "Johns", "https://bursakuaforler.com/wp-content/uploads/2018/02/emma-stone-kizil-sac.jpg", "Isaiah"),
            User(7, "semper@pede.edu", "Tate", "", "Lorem ipsum")
        )
    }


    override fun onClick(user: User) {
        TODO("to FriendDetail")
    }

    fun showError(msg: String) { // TODO Move this to BaseActivity
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
    }
}