package com.armchairsoftware.mdeditor.utilities

import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.BLOCKQUOTE
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.H1
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.H2
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.H3
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.H4
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.H5

object MarkDownFormat {

    fun getTextFormat(heading: Int, content: String?): String {
        val pref = when (heading) {
            H1 -> "# "
            H2 -> "## "
            H3 -> "### "
            H4 -> "#### "
            H5 -> "##### "
            BLOCKQUOTE -> "> "
            else -> ""
        }
        return "\n$pref$content\n"
    }

    fun getImageFormat(url: String?): String {
        return "\n<center>![Image]($url)</center>"
    }

    fun getCaptionFormat(caption: String?): String {
        return if (caption != null) "<center>$caption</center>\n\n\n" else "\n\n\n"
    }

    fun getLineFormat(): String {
        return "\n\n---\n\n"
    }

    fun getULFormat(content: String?): String {
        return "  - $content\n"
    }

    fun getOLFormat(indicator: String, content: String?): String {
        return "  $indicator $content\n"
    }
}
