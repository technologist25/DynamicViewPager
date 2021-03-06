package com.example.infiniteviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.infiniteviewpager.adaper.EndlessViewPagerAdapter
import com.example.infiniteviewpager.model.CardInfo
import com.example.infiniteviewpager.model.CardStatus
import com.example.infiniteviewpager.pagetransformer.CardAlphaPageTransformer
import com.example.infiniteviewpager.pagetransformer.CardDropPageTransformer
import com.example.infiniteviewpager.pagetransformer.CardMarginPageTransformer
import com.rd.PageIndicatorView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val data = listOf(
                CardInfo(last4Digits = "4444", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "5555", status = CardStatus.ACTIVE),

                CardInfo(last4Digits = "1111", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "2222", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "3333", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "4444", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "5555", status = CardStatus.ACTIVE),

                CardInfo(last4Digits = "1111", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "2222", status = CardStatus.FROZEN)
        )*/

        val data = listOf(
                CardInfo(null, status = CardStatus.FROZEN)/* last - 1  */,
                CardInfo(null, status = CardStatus.ACTIVE)/* last  */,

                CardInfo(last4Digits = "1111", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "2222", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "3333", status = CardStatus.ACTIVE),
                CardInfo(last4Digits = "4444", status = CardStatus.FROZEN),
                CardInfo(last4Digits = "5555", status = CardStatus.ACTIVE),

                CardInfo(null, status = CardStatus.ACTIVE) /* first */,
                CardInfo(null, status = CardStatus.FROZEN) /* second */
        )

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val pageIndicatorView = findViewById<PageIndicatorView>(R.id.pageIndicatorView)

        with(viewPager2) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    pageIndicatorView.selection = position - 2
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == 1)
                            setCurrentItem(data.size - 3, false)
                        else if (currentItem == data.size - 2)
                            setCurrentItem(2, false)
                    }
                }
            })
            with(CompositePageTransformer()) {
                addTransformer(CardMarginPageTransformer())
                addTransformer(CardDropPageTransformer())
                addTransformer(CardAlphaPageTransformer())
                setPageTransformer(this)
            }
            adapter = EndlessViewPagerAdapter(resources, data)
            offscreenPageLimit = 2
            setCurrentItem(2, false)
        }

        pageIndicatorView.apply {
            count = data.size - 4
            selection = 0
        }

    }
}