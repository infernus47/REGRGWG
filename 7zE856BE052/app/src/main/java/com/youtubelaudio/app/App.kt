package com.youtubelaudio.app

import android.app.Application
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.localization.Localization

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NewPipe.init(object : Downloader() {
            override fun execute(request: org.schabi.newpipe.extractor.downloader.Request): org.schabi.newpipe.extractor.downloader.Response {
                val client = okhttp3.OkHttpClient.Builder().build()
                val body = request.dataToSend()?.let {
                    okhttp3.RequestBody.create(null, it.toByteArray())
                }
                val req = okhttp3.Request.Builder()
                    .url(request.url())
                    .method(request.httpMethod(), body)
                request.headers().forEach { (key, values) ->
                    values.forEach { req.addHeader(key, it) }
                }
                val resp = client.newCall(req.build()).execute()
                val respBody = resp.body?.string().orEmpty()
                return org.schabi.newpipe.extractor.downloader.Response(resp.code, resp.message, resp.headers.toMultimap(), respBody, resp.request.url.toString())
            }
        }, Localization("ru", "RU"))
    }
}
