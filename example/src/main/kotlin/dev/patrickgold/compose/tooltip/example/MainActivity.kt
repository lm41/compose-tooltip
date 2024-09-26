package dev.patrickgold.compose.tooltip.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.patrickgold.compose.tooltip.example.ui.theme.ComposeTooltipExampleTheme
import dev.patrickgold.compose.tooltip.tooltip

enum class Theme {
    AUTO,
    LIGHT,
    DARK;
}

class MainActivity : ComponentActivity() {
    private var theme by mutableStateOf(Theme.AUTO)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = when (theme) {
                Theme.AUTO -> isSystemInDarkTheme()
                Theme.LIGHT -> false
                Theme.DARK -> true
            }
            ComposeTooltipExampleTheme(isDarkTheme) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(stringResource(R.string.app_name)) },
                                actions = {
                                    Row {
                                        IconButton(
                                            onClick = { exampleActionCallback("search") },
                                            modifier = Modifier.tooltip("Search examples")
                                        ) {
                                            Icon(Icons.Outlined.Search, "Search examples")
                                        }
                                        IconButton(
                                            onClick = { exampleActionCallback("more") },
                                            modifier = Modifier.tooltip("More options")
                                        ) {
                                            Icon(Icons.Outlined.MoreVert, "More options")
                                        }
                                    }
                                }
                            )
                        },
                    ) { contentPadding ->
                        Column(Modifier.padding(contentPadding)) {
                            Spacer(Modifier.height(92.dp))
                            ThemeChooserBox()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ThemeChooserBox() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "App theme:",
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = { theme = Theme.AUTO },
                    colors = with (ButtonDefaults) {
                        if (theme == Theme.AUTO) buttonColors() else outlinedButtonColors()
                    },
                ) {
                    Text(text = "System")
                }
                Button(
                    onClick = { theme = Theme.LIGHT },
                    colors = with (ButtonDefaults) {
                        if (theme == Theme.LIGHT) buttonColors() else outlinedButtonColors()
                    },
                ) {
                    Text(text = "Light")
                }
                Button(
                    onClick = { theme = Theme.DARK },
                    colors = with (ButtonDefaults) {
                        if (theme == Theme.DARK) buttonColors() else outlinedButtonColors()
                    },
                ) {
                    Text(text = "Dark")
                }
            }
        }
    }

    private fun exampleActionCallback(text: String) {
        Toast.makeText(this, "Example button '$text'", Toast.LENGTH_SHORT).show()
    }
}
