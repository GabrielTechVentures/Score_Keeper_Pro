package ro.gabrieltechventures.scorekeeperpro.player_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import ro.gabrieltechventures.scorekeeperpro.data.Player

@Composable
fun PlayerItem(
    player: Player
)
{

    Card(modifier=Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
            Column(

            ){
                Text(text = player.name)
            }
    }

}