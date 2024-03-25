package ro.gabrieltechventures.scorekeeperpro.player_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.gabrieltechventures.scorekeeperpro.data.Player

@Composable
fun PlayerItem(
    player: Player,
    onEvent:(PlayerListEvent)->Unit
)
{
    Card(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        ) {
            Column(
            modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
                        text = player.name.capitalize(),
                        fontSize = 32.sp, color = Color.Black
                    )
                    Button(modifier = Modifier.padding(end = 24.dp, top = 4.dp, bottom = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White,
                        ),

                        onClick = { onEvent(PlayerListEvent.onAddScoreBtnClick(player)) }) {
                        Text(
                            text = "Add score", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }//player.currentScore.toFloat()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                    /* verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween*/
                ) {

                    val percentage =
                        if (player.maximumScore.toFloatOrNull() != null && player.maximumScore.toFloat() != 0f) {
                            1f / player.maximumScore.toFloat() * 50f
                        } else {
                            0f
                        }

                    if (player.hasWon)
                    {
                        PulsatingButton { onEvent(PlayerListEvent.onWinnerBtnPressed(player))
                        }

                    }
                    else
                    {
                        Spacer(modifier = Modifier.width(110.dp))
                    }


                    CircularProgressBar(
                        percentage = player.percentage,
                        currentScore = player.currentScore.toIntOrNull() ?: 0,
                        maximumScore = player.maximumScore.toIntOrNull() ?: 0,
                    )


                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black),

                        onClick = { onEvent(PlayerListEvent.onUpdatePlayerClick(player)) }
                    )
                    {
                        Icon(

                            imageVector = Icons.Default.Edit,
                            contentDescription = "EditBtn",
                        )
                    }//iconbtn
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black),
                        modifier = Modifier.padding(end = 16.dp),
                        onClick = { onEvent(PlayerListEvent.onDeletePlayerClick(player)) }
                    )
                    {
                        Icon(

                            imageVector = Icons.Default.Delete,
                            contentDescription = "deleteBtn",
                        )
                    }//iconbtn


                }//row
                Text(
                    text = "Games won: ${player.gamesWon}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }


            }//column
    }

