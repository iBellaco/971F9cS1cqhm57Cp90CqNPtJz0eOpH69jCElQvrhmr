package com.example.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

fun String.parseHtmlColorToAnnotatedString(): AnnotatedString {
    val result = buildAnnotatedString {
        var currentIndex = 0
        val tagRegex = Regex("<font color='(.*?)'>(.*?)</font>")
        val matches = tagRegex.findAll(this@parseHtmlColorToAnnotatedString)
        
        for (match in matches) {
            val colorStr = match.groupValues[1]
            val text = match.groupValues[2]
            
            append(this@parseHtmlColorToAnnotatedString.substring(currentIndex, match.range.first))
            
            val color = try {
                Color(android.graphics.Color.parseColor(colorStr))
            } catch (e: Exception) {
                Color.Unspecified
            }
            
            pushStyle(SpanStyle(color = color))
            append(text)
            pop()
            
            currentIndex = match.range.last + 1
        }
        
        if (currentIndex < this@parseHtmlColorToAnnotatedString.length) {
            append(this@parseHtmlColorToAnnotatedString.substring(currentIndex))
        }
    }
    return result
}
