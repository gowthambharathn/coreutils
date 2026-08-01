package infinity.developers.engineui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import infinity.developers.engineui.ui.theme.EngineUITheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import infinity.developers.coreutils.Database.Api.SecureDB
import infinity.developers.coreutils.Database.Storage.TableManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EngineUITheme {
                Main()
            }
        }
    }
}
@Composable
fun Main(){
    val context = LocalContext.current
    SecureDB.init(context)
    SecureDB.get().open()

    val sql = TableManager.createTable(
        "users",
        listOf(
            "id INTEGER PRIMARY KEY AUTOINCREMENT",
            "name TEXT",
            "password TEXT"
        )
    )

    SecureDB.get().exec(sql)

    SecureDB.get().insert(
        "users",
        mapOf("name" to "Gowtham", "password" to "1234")
    )

    val data = SecureDB.get().query("SELECT * FROM users")
    Log.d("qwertyuiop", data.toString())
}
