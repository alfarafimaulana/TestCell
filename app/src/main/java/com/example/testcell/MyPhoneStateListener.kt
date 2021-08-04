package com.example.testcell

import android.os.Build
import android.telephony.CellSignalStrength
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.util.Log

class MyPhoneStateListener : PhoneStateListener() {
    private var signalStrengthValue: Int = 0
    private var cb: (Int) -> Unit = {}

    fun getSignalStrengthValue(): Int {
        return signalStrengthValue
    }

    fun setCallback(cb: (Int) -> Unit) {
        this.cb = cb
    }

    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
        super.onSignalStrengthsChanged(signalStrength)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            signalStrengthValue = if (signalStrength != null) {
                (signalStrength.gsmSignalStrength * 2) - 113
            } else {
                0
            }
        } else {
            val cells: List<CellSignalStrength> = signalStrength!!.cellSignalStrengths

            cells.forEach {
                signalStrengthValue = it.dbm
            }
        }

        this.cb(signalStrengthValue)
        Log.d("SIGNAL", "Strength: $signalStrengthValue")
    }

}