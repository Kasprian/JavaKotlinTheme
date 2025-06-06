package com.example.javakotlintheme

import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Color

class FileTabColorProvider : EditorTabColorProvider {
    override fun getEditorTabColor(project: Project, file: VirtualFile): Color? {
        return when (file.extension) {
            "java" -> Color(200, 255, 200) // Light green
            "kt" -> Color(255, 200, 200)   // Light red
            else -> null
        }
    }
}