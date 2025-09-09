package xute.markdeditor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import xute.markdeditor.MarkDEditorViewModel
import xute.markdeditor.datatype.DraftDataItemModel

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    item: DraftDataItemModel,
    viewModel: MarkDEditorViewModel
) {
    var caption by remember { mutableStateOf(item.caption) }

    Column(modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter(item.downloadUrl),
            contentDescription = null,
        )
        BasicTextField(
            value = caption ?: "",
            onValueChange = { newCaption ->
                caption = newCaption
                viewModel.updateImageCaption(item, newCaption)
            }
        )
    }
}
