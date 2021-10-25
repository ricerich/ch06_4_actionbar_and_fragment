package com.example.ch06_4_actionbar_and_fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

@Suppress("deprecation")
class MainActivity : AppCompatActivity(), ActionBar.TabListener
{
    lateinit var tab1 : ActionBar.Tab
    lateinit var tab2 : ActionBar.Tab
    lateinit var tab3 : ActionBar.Tab

    //프래그먼트 배열
    var myFragArr = arrayOfNulls<MyTabFragment>(3)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //1.액션바를 만든다
        var bar = this.supportActionBar
        bar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        //2.탭을 만든다
        tab1 = bar.newTab()
        tab1.text = "음악별"

        //3.탭 이벤트 리스너를 꽂는다(탭에 해당하는 프레그먼트를 띄운다)
        tab1.setTabListener(this)

        //4.액션바에 탭을 꽂는다
        bar.addTab(tab1)

        tab2 = bar.newTab()
        tab2.text = "가수별"
        tab2.setTabListener(this)
        bar.addTab(tab2)

        tab3 = bar.newTab()
        tab3.text = "앨범별"
        tab3.setTabListener(this)

        bar.addTab(tab3)


    //5.프레그먼트는 따로 만들어야 한다. 독립적으로~!

    }

    //onCreate 멤버메소드와 같은 급으로

    class MyTabFragment : Fragment()
    {

        //1.프래그먼트 생성 -> 액션바의 탭에서 이름을 가져온다(os의 번들을 통해서)
        //2.프래그먼트 뷰 생성 -> 리니어 레이아웃 만들고, 배경색 정도 바꿔준다다

        var tabName :String? = null
        //1.프래그먼트 생성
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            var data = arguments

            tabName = data!!.getString("tabName")


        }

        //2.프래그먼트 뷰 생성
        override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {

            //Only 코틀린 코드로만 레이아웃 만들기
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                   LinearLayout.LayoutParams.MATCH_PARENT)

            var baseLayout = LinearLayout(super.getActivity())

            baseLayout.orientation = LinearLayout.VERTICAL
            baseLayout.layoutParams = params

            if(tabName == "음악별")
                baseLayout.setBackgroundColor(Color.RED)
            if(tabName == "가수별")
                baseLayout.setBackgroundColor(Color.GREEN)
            if(tabName == "앨범별")
                baseLayout.setBackgroundColor(Color.BLUE)


//            return super.onCreateView(inflater, container, savedInstanceState)
            return baseLayout
        }
    }

    override fun onTabSelected(tab: ActionBar.Tab, ft: FragmentTransaction)
    {
        var myTabFrament : MyTabFragment? = null

        if(myFragArr[tab.position] == null)//프래그먼트 배열 안 없으면, 만드시고
        {
            myTabFrament = MyTabFragment()
            var data = Bundle()
            data.putString("tabName", tab.text.toString())

            myTabFrament.arguments = data

            myFragArr[tab.position] = myTabFrament

        }
        else //배열안에 있으면, 꺼내시고
        {
            myTabFrament = myFragArr[tab.position]
        }

        ft.replace(android.R.id.content, myTabFrament!!)

    }

    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
//        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
//        TODO("Not yet implemented")
    }


}

















