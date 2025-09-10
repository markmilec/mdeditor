package xute.markdeditor

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import xute.markdeditor.components.HorizontalDividerComponent
import xute.markdeditor.components.ImageComponent
import xute.markdeditor.components.TextComponent
import xute.markdeditor.datatype.DraftDataItemModel
import xute.markdeditor.models.DraftModel

@Composable
fun MarkDEditor(
    viewModel: MarkDEditorViewModel = viewModel()
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