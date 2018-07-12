package com.example.karshsoni.admobdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener


class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    override fun onRewardedVideoAdLeftApplication() {
        Log.e(TAG, "onRewardedVideoAdLeftApplication: ");
    }

    override fun onRewardedVideoAdLoaded() {
        Log.e(TAG, "onRewardedVideoAdLoaded: ");
    }

    override fun onRewardedVideoAdOpened() {
        Log.e(TAG, "onRewardedVideoAdOpened: ");
    }

    override fun onRewardedVideoCompleted() {
        Log.e(TAG, "onRewardedVideoCompleted: ");
    }

    override fun onRewarded(p0: RewardItem?) {
        Log.e(TAG, "onRewarded: ");

    }

    override fun onRewardedVideoStarted() {
        Log.e(TAG, "onRewardedVideoStarted: ");
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Log.e(TAG, "onRewardedVideoAdFailedToLoad: ");
    }

    override fun onRewardedVideoAdClosed() {
        Log.e(TAG, "onRewardedVideoAdClosed: ");
    }

    val TAG = "MainActivity"
    val YOUR_FRAGMENT_STRING_TAG = "FragMain"
    val YOUR_FRAGMENT_NAME = "InterstitialFragment"
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, resources.getString(R.string.test_ad_mob_app_id))

        var adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this@MainActivity)
        mRewardedVideoAd.rewardedVideoAdListener = this@MainActivity
        mRewardedVideoAd.userId = resources.getString(R.string.test_rewarded_video)
        mRewardedVideoAd.loadAd(resources.getString(R.string.test_rewarded_video),
                adRequest)

        btnShowFragment.setOnClickListener {
            val fragmentPopped = fragmentManager
                    .popBackStackImmediate(YOUR_FRAGMENT_STRING_TAG, 0)
            if (!fragmentPopped && fragmentManager.findFragmentByTag(YOUR_FRAGMENT_STRING_TAG) == null){
                val manager = fragmentManager
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.interstitialFragment, InterstitialAdFragment(), YOUR_FRAGMENT_STRING_TAG)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        btnVideo.setOnClickListener {

            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            } else {
                Log.e(TAG, "The Video wasn't loaded yet.")
            }
        }

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.e(TAG, "onAdLoaded: ");
                Toast.makeText(this@MainActivity, "onAdLoaded -> Ad Loaded!", Toast.LENGTH_SHORT).show()
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Log.e(TAG, "onAdFailedToLoad: $errorCode");
                Toast.makeText(this@MainActivity, "onAdFailedToLoad", Toast.LENGTH_SHORT).show()
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                Log.e(TAG, "onAdOpened: ");
                Toast.makeText(this@MainActivity, "onAdOpened!", Toast.LENGTH_SHORT).show()
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdLeftApplication() {
                Log.e(TAG, "onAdLeftApplication: ");
                Toast.makeText(this@MainActivity, "onAdLeftApplication", Toast.LENGTH_SHORT).show()
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                Log.e(TAG, "onAdClosed: ");
                Toast.makeText(this@MainActivity, "onAdClosed", Toast.LENGTH_SHORT).show()
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        }

    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(resources.getString(R.string.test_rewarded_video),
                AdRequest.Builder().build())

    }

}
