package com.example.testcell

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.bmartel.speedtest.SpeedTestReport
import fr.bmartel.speedtest.SpeedTestSocket
import fr.bmartel.speedtest.inter.ISpeedTestListener
import fr.bmartel.speedtest.model.SpeedTestError
import fr.bmartel.speedtest.model.SpeedTestMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.math.BigDecimal

class SpeedTestViewModel : ViewModel() {

    private var speedTestSocket: SpeedTestSocket = SpeedTestSocket()
    private var downloadCb: (BigDecimal) -> Unit = {}
    private var uploadCb: (BigDecimal) -> Unit = {}
    private var latencyCb: (Int) -> Unit = {}
    private var busyCb: (Boolean) -> Unit = {}

    fun setDownloadCallback(cb: (BigDecimal) -> Unit) {
        downloadCb = cb
    }

    fun setUploadCallback(cb: (BigDecimal) -> Unit) {
        uploadCb = cb
    }

    fun setLatencyCallback(cb: (Int) -> Unit) {
        latencyCb = cb
    }

    fun setBusyCallback(cb: (Boolean) -> Unit) {
        busyCb = cb
    }

    fun runTest() {
        busyCb(true)
        viewModelScope.launch(Dispatchers.IO) {
            speedTestSocket.startFixedDownload("http://speedtest.biznetnetworks.com:8080" +
                    "/download?size=10000000", 10000)
        }
    }

    fun runPing() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pb = ProcessBuilder("/system/bin/ping", "-c", "1", "8.8.8.8")
                val p = pb.start()

                p.waitFor()

                val reader = BufferedReader(p.inputStream.reader())
                var latency = 0

                var line = reader.readLine()
                while (line != null) {
                    if (line.startsWith("64 bytes from 8.8.8.8")) {
                        val lineSplit = line.split(" ")
                        val latVal = lineSplit[6]
                        latency = latVal.split("=")[1].toDouble().toInt()
                        break
                    }

                    line = reader.readLine()
                }

                latencyCb(latency)

            } catch (e: Exception) {
                Log.d("PING_TEST", e.message.toString())
            }
        }
    }

    init {
        speedTestSocket.addSpeedTestListener(object : ISpeedTestListener {

            override fun onCompletion(report: SpeedTestReport?) {
                if (report!!.speedTestMode == SpeedTestMode.DOWNLOAD) {
                    downloadCb(report.transferRateOctet / BigDecimal(1000))

                    viewModelScope.launch(Dispatchers.IO) {
                        speedTestSocket.startFixedUpload("http://jakarta.speedtest.telkom.net" +
                                ".id:8080/speedtest/upload",
                            10000000, 10000)
                    }
                } else {
                    uploadCb(report.transferRateOctet / BigDecimal(1000))
                    busyCb(false)
                }
            }

            override fun onError(speedTestError: SpeedTestError?, errorMessage: String?) {
                Log.e("SPEED_TEST", errorMessage.toString())
                busyCb(false)
            }

            override fun onProgress(percent: Float, report: SpeedTestReport?) {
                if (report!!.speedTestMode == SpeedTestMode.DOWNLOAD) {
                    downloadCb(report.transferRateOctet / BigDecimal(1000))
                } else {
                    uploadCb(report.transferRateOctet / BigDecimal(1000))
                }
            }
        })
    }
}