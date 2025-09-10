package xute.markdeditor
package armchairsoftware.mdeditor

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import xute.markdeditor.Styles.TextComponentStyle
import armchairsoftware.mdeditor.Styles.TextComponentStyle

@Composable
fun EditorControlBar(
    modifier: Modifier = Modifier,
    viewModel: MarkDEditorViewModel
) {
    Row(modifier = modifier) {
        IconButton(onClick = { viewModel.setNormalText() }) {
            Icon(Icons.Default.Edit, contentDescription = "Normal Text")
        }
        IconButton(onClick = { viewModel.setHeading(TextComponentStyle.H1) }) {
            Icon(Icons.Default.DateRange, contentDescription = "Heading")
        }
        IconButton(onClick = { viewModel.toggleUnorderedList() }) {
            Icon(Icons.Default.List, contentDescription = "Unordered List")
        }
        IconButton(onClick = { viewModel.toggleBlockquote() }) {
            Icon(Icons.Default.Info, contentDescription = "Blockquote")
        }
        IconButton(onClick = { viewModel.insertLink() }) {
            Icon(Icons.Default.Add, contentDescription = "Insert Link")
        }
        IconButton(onClick = { viewModel.insertHorizontalRule() }) {
            Icon(Icons.Default.Done, contentDescription = "Insert Horizontal Rule")
        }
        IconButton(onClick = { viewModel.insertImage() }) {
            Icon(Icons.Default.Home, contentDescription = "Insert Image")
        }
    }
}
