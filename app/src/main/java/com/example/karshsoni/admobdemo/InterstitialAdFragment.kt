package com.example.karshsoni.admobdemo

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.interstitial_fragment.*
import java.util.zip.Inflater

class InterstitialAdFragment : Fragment() {

    lateinit var mInterstitialAd: InterstitialAd

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.interstitial_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mInterstitialAd = InterstitialAd(activity)
//        mInterstitialAd.adUnitId = resources.getString(R.string.test_interstitial_ad_unit_id)
        mInterstitialAd.adUnitId = resources.getString(R.string.test_interstitial_video)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        btnShowAd.setOnClickListener {
            mInterstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }
            }
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }
    }
}