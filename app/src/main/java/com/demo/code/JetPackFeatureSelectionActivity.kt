package com.demo.code

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.dataStore.activities.DataStoreActivity
import com.demo.code.databinding.ActivityJetpackSelectionBinding
import com.demo.code.lifecycle.activities.ExoplayerActivity
import com.demo.code.liveData.activity.LiveDataActivity
import com.demo.code.navigation.activities.SelectionScreenActivity
import com.demo.code.paging.activities.Paging3Activity
import com.demo.extensions.intent.openActivity

class JetPackFeatureSelectionActivity : BaseActivity() {

    private lateinit var binding: ActivityJetpackSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetpackSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            this.jetpackNavigationId.setOnClickListener {
                openActivity(SelectionScreenActivity::class.java)
            }
            this.jetPackLifeCycleAwareId.setOnClickListener {
                openActivity(ExoplayerActivity::class.java)
            }
            this.jetPackPaging3Id.setOnClickListener{
                openActivity(Paging3Activity::class.java)
            }
            this.jetDataStoreId.setOnClickListener{
                openActivity(DataStoreActivity::class.java)
            }
            this.jetLiveDataId.setOnClickListener{
                openActivity(LiveDataActivity::class.java)
            }
        }
    }

}