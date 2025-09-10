package com.armchairsoftware.mdeditor.datatype

fun DraftDataItemModel.toDraftDataItemModelKt(): DraftDataItemModelKt {
    return DraftDataItemModelKt(
        itemType = itemType,
        mode = mode,
        style = style,
        content = content,
        downloadUrl = downloadUrl,
        caption = caption
    )
}
