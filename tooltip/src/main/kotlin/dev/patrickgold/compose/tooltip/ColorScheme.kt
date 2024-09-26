package dev.patrickgold.compose.tooltip

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.luminance

/**
 * Checks whether a Material3 [ColorScheme] is light
 */
internal val ColorScheme.isLight: Boolean
    get() = this.background.luminance() > 0.5
