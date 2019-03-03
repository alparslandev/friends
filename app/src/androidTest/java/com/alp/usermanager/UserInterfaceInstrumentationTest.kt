package com.alp.usermanager

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.alp.usermanager.activities.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserInterfaceInstrumentationTest {

    @Rule
    @JvmField
    val rule  = ActivityTestRule(LoginActivity::class.java)



    private val mail_tobe_typed="alp.develioglu@gmail.com"
    private val correct_password ="Alp1234."
    private val wrong_password = "Alp1234"


    @Test
    fun checkLoginButtonEnability() {
        Log.e("@Test","Performing login success test")
        Espresso.onView((ViewMatchers.withId(R.id.et_mail)))
            .perform(ViewActions.typeText(mail_tobe_typed))

        Espresso.onView(ViewMatchers.withId(R.id.et_password))
            .perform(ViewActions.typeText(correct_password))

        Espresso.onView(ViewMatchers.withId(R.id.btn_primary))
            .perform(ViewActions.click())

        //Espresso.onView(withId(R.id.btn_primary))
        //    .check(matches(withText(R.string.login_success)))
    }

    @Test
    fun login_failure(){
        Log.e("@Test","Performing login failure test")
        Espresso.onView((withId(R.id.et_mail)))
            .perform(ViewActions.typeText(mail_tobe_typed))

        Espresso.onView(withId(R.id.et_password))
            .perform(ViewActions.typeText(wrong_password))

        Espresso.onView(withId(R.id.btn_primary))
            .perform(ViewActions.click())

        //Espresso.onView(withId(R.id.login_result))
        //    .check(matches(withText(R.string.login_failed)))
    }

}