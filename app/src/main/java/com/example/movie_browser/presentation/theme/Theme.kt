import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DeepNight = Color(0xFF101015)
val ShadowPurple = Color(0xFF1B1B2F)
val MovieCyan = Color(0xFF00BFA5)
val FadedGray = Color(0xFFA0A0B0)

val DarkMovieScheme = darkColorScheme(
    primary = MovieCyan,
    background = DeepNight,
    surface = ShadowPurple,
    onPrimary = Color.Black,
    onBackground = FadedGray,
    onSurface = Color.White
)

@Composable
fun MovieBrowserTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkMovieScheme,
        content = content
    )
}