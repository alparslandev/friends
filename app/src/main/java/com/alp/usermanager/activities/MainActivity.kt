package com.alp.usermanager.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alp.usermanager.R
import com.alp.usermanager.service.Api
import com.alp.usermanager.service.IDataService
import com.alp.usermanager.service.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFriends()
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

    private fun refreshFriends(friends: List<User>?) {
        if (friends == null || friends.isEmpty()) {
            empty_view.visibility = View.VISIBLE
            rv_friends.visibility = View.GONE
        }
        // todo load friends with Adapter
    }

    private fun getDummyFriends(): MutableList<User> {
        return mutableListOf(
            User(1, "nonummy.ipsum@vulputate.edu", "Townsend", "", "Townsend"),
            User(2, "non.sapien.molestie@sit.co.uk", "Cohen", "", "Connor"),
            User(3, "feugiat@metusfacilisis.com", "Irwin", "", "Gabriel"),
            User(4, "gravida@nonvestibulum.ca", "Irwin", "", "Salazar"),
            User(5, "ivamus@malesuada.ca", "Coffey", "", "Leo"),
            User(6, "quis.lectus@semper.edu", "Johns", "", "Isaiah"),
            User(7, "semper@pede.edu", "Tate", "", "Lorem ipsum")
        )
    }

    fun showError(msg: String) { // TODO Move this to BaseActivity
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
    }
}