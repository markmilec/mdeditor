package com.armchairsoftware.mdeditor.datatype

data class DraftDataItemModelKt(
    val itemType: Int,
    val mode: Int,
    val style: Int,
    val content: String?,
    val downloadUrl: String?,
    val caption: String?
) {
    fun toDraftDataItemModel(): DraftDataItemModel {
        val item = DraftDataItemModel()
        item.itemType = itemType
        item.mode = mode
        item.style = style
        item.content = content
        item.downloadUrl = downloadUrl
        item.caption = caption
        return item
    }
}
