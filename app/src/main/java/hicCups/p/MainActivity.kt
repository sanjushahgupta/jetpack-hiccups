package hicCups.p

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hicCups.p.navigation.navigationNavController
import hicCups.p.ui.theme.HicCupsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppCompatDelegate.MODE_NIGHT_YES
            Login()
        }
    }

}


@Composable
fun Login() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            navigationNavController()

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HicCupsTheme {

    }
}
