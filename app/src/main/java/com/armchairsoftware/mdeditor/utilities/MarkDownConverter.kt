package com.armchairsoftware.mdeditor.utilities

import com.armchairsoftware.mdeditor.models.DraftModel
import com.armchairsoftware.mdeditor.components.TextComponentItem

object MarkDownConverter {
    fun toMarkdown(draft: DraftModel): String {
        val markdown = StringBuilder()
        var olCount = 0
        for (item in draft.items) {
            when (item.itemType) {
                DraftModel.ITEM_TYPE_TEXT -> {
                    when (item.mode) {
                        TextComponentItem.MODE_PLAIN -> {
                            markdown.append(MarkDownFormat.getTextFormat(item.style, item.content))
                        }
                        TextComponentItem.MODE_UL -> {
                            markdown.append(MarkDownFormat.getULFormat(item.content))
                        }
                        TextComponentItem.MODE_OL -> {
                            olCount++
                            markdown.append(MarkDownFormat.getOLFormat("$olCount.", item.content))
                        }
                    }
                }
                DraftModel.ITEM_TYPE_IMAGE -> {
                    markdown.append(MarkDownFormat.getImageFormat(item.downloadUrl))
                    markdown.append(MarkDownFormat.getCaptionFormat(item.caption))
                }
                DraftModel.ITEM_TYPE_HR -> {
                    markdown.append(MarkDownFormat.getLineFormat())
                }
            }
        }
        return markdown.toString()
    }
}