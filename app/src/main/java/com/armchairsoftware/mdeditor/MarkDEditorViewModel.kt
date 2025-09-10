package xute.markdeditor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xute.markdeditor.models.DraftModel
import xute.markdeditor.datatype.DraftDataItemModel
import xute.markdeditor.datatype.toDraftDataItemModelKt
import xute.markdeditor.components.TextComponentItem.MODE_OL
import xute.markdeditor.components.TextComponentItem.MODE_UL
import xute.markdeditor.Styles.TextComponentStyle
import xute.markdeditor.utilities.MarkDownConverter

class MarkDEditorViewModel : ViewModel() {
    private val _draft = MutableStateFlow(DraftModel())
    val draft: StateFlow<DraftModel> = _draft

    private val _focusedItem = MutableStateFlow<DraftDataItemModel?>(null)
    val focusedItem: StateFlow<DraftDataItemModel?> = _focusedItem

    fun setDraft(draft: DraftModel) {
        _draft.value = draft
    }

    fun setFocusedItem(item: DraftDataItemModel) {
        _focusedItem.value = item
    }

    fun updateItem(item: DraftDataItemModel, newContent: String) {
        val newItems = _draft.value.items.map {
            if (it == item) {
                it.toDraftDataItemModelKt().copy(content = newContent).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun updateImageCaption(item: DraftDataItemModel, newCaption: String) {
        val newItems = _draft.value.items.map {
            if (it == item) {
                it.toDraftDataItemModelKt().copy(caption = newCaption).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun getOrderedListNumber(index: Int): Int {
        var count = 0
        for (i in 0..index) {
            val item = _draft.value.items[i]
            if (item.itemType == DraftModel.ITEM_TYPE_TEXT && item.mode == MODE_OL) {
                count++
            }
        }
        return count
    }

    fun setNormalText() {
        val focused = _focusedItem.value ?: return
        val newItems = _draft.value.items.map {
            if (it == focused) {
                it.toDraftDataItemModelKt().copy(style = TextComponentStyle.NORMAL).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun setHeading(level: Int) {
        val focused = _focusedItem.value ?: return
        val newItems = _draft.value.items.map {
            if (it == focused) {
                it.toDraftDataItemModelKt().copy(style = level).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun toggleUnorderedList() {
        val focused = _focusedItem.value ?: return
        val newItems = _draft.value.items.map {
            if (it == focused) {
                val newMode = if (it.mode == MODE_UL) 0 else MODE_UL
                it.toDraftDataItemModelKt().copy(mode = newMode).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun toggleOrderedList() {
        val focused = _focusedItem.value ?: return
        val newItems = _draft.value.items.map {
            if (it == focused) {
                val newMode = if (it.mode == MODE_OL) 0 else MODE_OL
                it.toDraftDataItemModelKt().copy(mode = newMode).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun toggleBlockquote() {
        val focused = _focusedItem.value ?: return
        val newItems = _draft.value.items.map {
            if (it == focused) {
                val newStyle = if (it.style == TextComponentStyle.BLOCKQUOTE) TextComponentStyle.NORMAL else TextComponentStyle.BLOCKQUOTE
                it.toDraftDataItemModelKt().copy(style = newStyle).toDraftDataItemModel()
            } else {
                it
            }
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun insertHorizontalRule() {
        val focused = _focusedItem.value
        val newItems = _draft.value.items.toMutableList()
        val hrItem = DraftDataItemModel()
        hrItem.itemType = DraftModel.ITEM_TYPE_HR
        if (focused != null) {
            val index = newItems.indexOf(focused)
            newItems.add(index + 1, hrItem)
        } else {
            newItems.add(hrItem)
        }
        _draft.value.items = ArrayList(newItems)
    }

    fun insertImage() {
        // TODO: Implement
    }

    fun insertLink() {
        // TODO: Implement
    }

    fun getMarkdown(): String {
        return MarkDownConverter.toMarkdown(_draft.value)
    }
}
