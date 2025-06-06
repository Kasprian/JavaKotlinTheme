package com.example.javakotlintheme

import com.intellij.ide.ui.LafManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.EditorColorsScheme
import com.intellij.openapi.editor.colors.impl.EditorColorsManagerImpl
import com.intellij.openapi.editor.colors.impl.EditorColorsSchemeImpl
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import java.awt.Color


class ThemeSwitcher : FileEditorManagerListener {
    override fun selectionChanged(event: FileEditorManagerEvent) {
        val file = event.newFile ?: return
        val extension = file.extension ?: return


        val themeName = when (file.extension) {
            "kt" -> "Darcula"
            "java" -> "MyCustomTheme"
            else -> return
        }

        ApplicationManager.getApplication().invokeLater {
            val lafManager = LafManager.getInstance()
            @Suppress("DEPRECATION")
            val currentThemeName = lafManager.currentLookAndFeel?.name

            if (currentThemeName != themeName) {
                val newTheme = lafManager.installedLookAndFeels.find { it.name == themeName }
                if (newTheme != null) {
                    try {
                        @Suppress("DEPRECATION")
                        lafManager.setCurrentLookAndFeel(newTheme)
                        lafManager.updateUI()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
    /*fun switchColorScheme(name: String) {
        val colorsManager = EditorColorsManager.getInstance()
        val targetScheme = colorsManager.allSchemes.find { it.name == name }
        if (targetScheme != null && colorsManager.globalScheme.name != name) {
            colorsManager.setGlobalScheme(targetScheme)
        }
    }*/
}


