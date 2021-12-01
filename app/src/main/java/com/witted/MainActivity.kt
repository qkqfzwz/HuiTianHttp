package com.witted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.witted.vm.ApiViewModel

class MainActivity : AppCompatActivity() {

    private  val TAG="MainActivity"

    private val mViewModel by viewModels<ApiViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.hospitalLiveData.observeState(this){
            onSuccess {
                Log.i(TAG, "netTest:_success "+it.toString())
            }
            onFailed{ code,msg ->
                Log.i(TAG, "netTest: _fail"+code+msg)
            }
            onException {
                Log.i(TAG, "netTest: _onException"+it.message)
            }
            onEmpty {
                Log.i(TAG, "netTest: _onEmpty")
            }
            onComplete {
                Log.i(TAG, "netTest: _onComplete")
            }

        }


        mViewModel.getHospital("202092")

    }
}