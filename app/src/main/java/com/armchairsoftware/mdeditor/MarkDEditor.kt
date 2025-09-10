package com.armchairsoftware.mdeditor

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.armchairsoftware.mdeditor.components.HorizontalDividerComponent
import com.armchairsoftware.mdeditor.components.ImageComponent
import com.armchairsoftware.mdeditor.components.TextComponent
import com.armchairsoftware.mdeditor.datatype.DraftDataItemModel
import com.armchairsoftware.mdeditor.models.DraftModel

@Composable
fun MDEditor(
    viewModel: MDEditorViewModel = viewModel()
) {
    val draft by viewModel.draft.collectAsState()
    LazyColumn {
        itemsIndexed(draft.items) { index, item ->
            when (item.itemType) {
                DraftModel.ITEM_TYPE_TEXT -> {
                    TextComponent(item = item, viewModel = viewModel, index = index)
                }
                DraftModel.ITEM_TYPE_IMAGE -> {
                    ImageComponent(item = item, viewModel = viewModel)
                }
                DraftModel.ITEM_TYPE_HR -> {
                    HorizontalDividerComponent()
                }
            }
        }
    }
}