package com.corporation8793.medicinal_herb

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout

class Common {
    fun replaceText(text : String) : String{
        val regex = Regex("&.*;")
        val matchResult: MatchResult? = regex.find(text)
//        println("match value: ${matchResult?.value}")
        var result = text
        matchResult?.groupValues?.forEach {
            Log.e("match value", it)
            result = result.replace(it,"")
        }

        return result.replace("<p>","").replace("</p>","")
                .replace("<ul>","").replace("</ul>","")
                .replace("<li>","").replace("</li>","")
                .replace("<br>","").replace("<br />","")
                .replace("<strong>","").replace("</strong>","")

    }

    public fun showAccessTerms(context : Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_access_term)


        var webview = dialog.findViewById<WebView>(R.id.webview)
        webview.webViewClient = WebViewClient()
        webview.getSettings().setJavaScriptEnabled(true)
        webview.getSettings().getLoadsImagesAutomatically()
        webview.getSettings().getUseWideViewPort()
        webview.loadUrl("https://blog.naver.com/wisi8793/222738671066")
//
        dialog.show()
        dialog.findViewById<LinearLayout>(R.id.ok_btn).setOnClickListener {
            dialog.dismiss()
        }


        val display =(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)

        // Generate custom width and height and
        // add to the dialog attributes
        // we multiplied the width and height by 0.5,
        // meaning reducing the size to 50%
        val mLayoutParams = WindowManager.LayoutParams()
        mLayoutParams.width = (size.x * 0.9f).toInt()
        mLayoutParams.height = (size.y * 0.9f).toInt()
//
        dialog.window?.attributes = mLayoutParams
    }


}