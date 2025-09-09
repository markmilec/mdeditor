package xute.markdeditor.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import xute.markdeditor.MarkDEditorViewModel
import xute.markdeditor.Styles.TextComponentStyle
import xute.markdeditor.datatype.DraftDataItemModel
import xute.markdeditor.components.TextComponentItem.MODE_OL
import xute.markdeditor.components.TextComponentItem.MODE_UL

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    item: DraftDataItemModel,
    viewModel: MarkDEditorViewModel,
    index: Int
) {
    var text by remember { mutableStateOf(item.content) }
    val focusRequester = remember { FocusRequester() }

    Row(modifier = modifier) {
        when (item.mode) {
            MODE_UL -> Text(text = "\u2022")
            MODE_OL -> {
                val number = viewModel.getOrderedListNumber(index)
                Text(text = "$number.")
            }
        }
        BasicTextField(
            value = text ?: "",
            onValueChange = { newText ->
                text = newText
                viewModel.updateItem(item, newText)
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        viewModel.setFocusedItem(item)
                    }
                },
            textStyle = getTextStyle(item.style)
        )
    }
}

@Composable
private fun getTextStyle(style: Int): TextStyle {
    return when (style) {
        TextComponentStyle.H1 -> TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TextComponentStyle.H2 -> TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
        TextComponentStyle.H3 -> TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        TextComponentStyle.H4 -> TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        TextComponentStyle.H5 -> TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        TextComponentStyle.BLOCKQUOTE -> TextStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
        else -> TextStyle.Default
    }
}
