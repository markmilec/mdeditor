package com.armchairsoftware.mdeditor

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.armchairsoftware.mdeditor.EditorControlBar
import com.armchairsoftware.mdeditor.Styles.TextComponentStyle.*
import com.armchairsoftware.mdeditor.components.TextComponentItem.MODE_OL
import com.armchairsoftware.mdeditor.components.TextComponentItem.MODE_PLAIN
import com.armchairsoftware.mdeditor.datatype.DraftDataItemModel
import com.armchairsoftware.mdeditor.models.DraftModel
import com.armchairsoftware.mdeditor.utilities.FilePathUtils

class MainActivity : AppCompatActivity(), EditorControlBar.EditorControlListener {
    private lateinit var mdEditor: MDEditor
    private lateinit var editorControlBar: EditorControlBar

    private val openGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val filePath = FilePathUtils.getPath(this, it)
                addImage(filePath)
            }
        }

    private val requestStoragePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                Toast.makeText(this, "Permission not granted to access images.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mdEditor = findViewById(R.id.mdEditor)
        mdEditor.configureEditor(
            "https://your-api-server.com/api/v2/",
            "",
            true,
            "Start Here...",
            BLOCKQUOTE
        )
        mdEditor.loadDraft(getDraftContent())
        editorControlBar = findViewById(R.id.controlBar)
        editorControlBar.setEditorControlListener(this)
        editorControlBar.setEditor(mdEditor)
    }

    private fun getDraftContent(): DraftModel {
        val contentTypes = arrayListOf(
            DraftDataItemModel().apply {
                itemType = DraftModel.ITEM_TYPE_TEXT
                content = "Kajal Aggarwal filmography"
                mode = MODE_PLAIN
                style = H1
            },
            DraftDataItemModel().apply {
                itemType = DraftModel.ITEM_TYPE_TEXT
                style = NORMAL
                mode = MODE_OL
                content = "2009 – Filmfare Award for Best Actress – Telugu for Magadheera"
            },
            DraftDataItemModel().apply {
                itemType = DraftModel.ITEM_TYPE_IMAGE
                downloadUrl =
                    "https://cdn.shopify.com/s/files/1/0166/3704/products/78008-3_grande.jpg"
                caption = "Cute Pink Photo"
            }
        )
        return DraftModel(contentTypes)
    }

    private fun addImage(filePath: String) {
        mdEditor.insertImage(filePath)
    }

    private fun openGallery() {
        openGalleryLauncher.launch("image/*")
    }

    override fun onInsertImageClicked() {
        requestStoragePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onInserLinkClicked() {
        mdEditor.addLink("Click Here", "https://your-website.com")
    }

    fun printStack(view: View) {
        sendMail()
    }

    private fun sendMail() {
        val dm = mdEditor.getDraft()
        val json = Gson().toJson(dm)
        Log.d("MDEditor", json)
    }
}
