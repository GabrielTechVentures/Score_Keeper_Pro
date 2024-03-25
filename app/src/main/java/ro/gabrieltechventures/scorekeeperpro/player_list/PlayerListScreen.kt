package ro.gabrieltechventures.scorekeeperpro.player_list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.gabrieltechventures.scorekeeperpro.ui.theme.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel
)
{
    val players= viewModel.players.collectAsState(initial = emptyList())

    val context: Context = LocalContext.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            event->
            when(event){
                is UiEvent.showToast->{
                    Toast.makeText(context,event.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    Scaffold(
        modifier=Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                Text(text = "Score Keeper Pro", modifier = Modifier.fillMaxWidth(), fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Red)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(4.dp),
                shape = CircleShape,
                containerColor = Color.Red,
                contentColor = Color.White,
                onClick = {viewModel.onEvent(PlayerListEvent.onAddPlayerBtnClick)  }) {
             Text(text ="+"

                 , fontSize = 22.sp, fontWeight = FontWeight.Bold)


            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Red,
                modifier = Modifier.height(54.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    Button(
                        colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                        onClick = { viewModel.onEvent(PlayerListEvent.onCustomizeScoreBtnPressed) }
                    ) {

                        Text(text = "Customize score", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                        onClick ={ viewModel.onEvent(PlayerListEvent.onResetScoreButtonClick) }
                    ) {

                        Text(text = "Reset score", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        ){
        
        LazyColumn(
            modifier = Modifier.padding(it)
        ){
            items(players.value){
                player->
                PlayerItem(player = player, onEvent = viewModel::onEvent)
            }
        }
    }
    if (viewModel.showAddPlayerDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 300.dp, height = 250.dp),
            title = { Text(text = "Add player", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)},
            onDismissRequest = { viewModel.onEvent(PlayerListEvent.addPlayerDismiss)},
            confirmButton = {
                Column(modifier=Modifier.fillMaxSize()) {
                   OutlinedTextField(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                       colors = TextFieldDefaults.outlinedTextFieldColors(
                           focusedLabelColor = Color.Red,
                           unfocusedLabelColor = Color.White,
                           focusedTextColor = Color.Red,
                           unfocusedTextColor = Color.White,
                           cursorColor = Color.Blue,
                           focusedBorderColor = Color.Red,
                           unfocusedBorderColor = Color.White

                       ),
                       label = { Text(text = "Add player's name...")},
                       value = viewModel.name,
                       onValueChange ={viewModel.onEvent(PlayerListEvent.onNameChange(it))} )

                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { viewModel.onEvent(PlayerListEvent.onAddPlayerDoneBtnPressed) }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Done, contentDescription ="DoneBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Done")
                        }

                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { viewModel.onEvent(PlayerListEvent.addPlayerDismiss) }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Close, contentDescription ="CloseBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Close")
                        }
                    }
                }

            })
    }

    if (viewModel.showCustomizeScoreDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 300.dp, height = 250.dp),
            title = { Text(text = "Set maximum score", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)},
            onDismissRequest = { viewModel.onEvent(PlayerListEvent.onCustomizeScoreDialogDismiss)},
            confirmButton = {
                Column(modifier=Modifier.fillMaxSize()) {
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
                    {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(0.9f)
                                .padding(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = Color.Red,
                                unfocusedLabelColor = Color.White,
                                focusedTextColor = Color.Red,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.Blue,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.White

                            ),
                            label = { Text(text = "Set maximum score...") },
                            value = viewModel.maximumScore,
                            onValueChange = { viewModel.onEvent(PlayerListEvent.onCustomizeScoreChange(it)) })
                        IconButton(onClick = { viewModel.onEvent(PlayerListEvent.onInfoButtonClick) }, modifier = Modifier.weight(0.1f)) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Info, contentDescription ="infoBtn" )

                        }
                    }
                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = {
                                viewModel.onEvent(PlayerListEvent.onCustomizeDoneBtnClick)
                            }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Done, contentDescription ="DoneBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Done")
                        }

                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { viewModel.onEvent(PlayerListEvent.onCustomizeScoreDialogDismiss) }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Close, contentDescription ="CloseBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Close")
                        }
                    }
                }
            })
    }

    if (viewModel.showAddScoreDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 300.dp, height = 250.dp),
            title = { Text(text = "Add player's points", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)},
            onDismissRequest = { viewModel.onEvent(PlayerListEvent.onAddScoreDialogDismiss)},
            confirmButton = {
                Column(modifier=Modifier.fillMaxSize()) {
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
                    {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(0.9f)
                                .padding(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = Color.Red,
                                unfocusedLabelColor = Color.White,
                                focusedTextColor = Color.Red,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.Blue,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.White

                            ),
                            label = { Text(text = "Player's earned points...") },
                            value = viewModel.pointsEarned,

                            onValueChange = { viewModel.onEvent(PlayerListEvent.onEarnedPointsChange(it)) })

                    }
                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = {
                                viewModel.onEvent(PlayerListEvent.onAddScoreDoneBtnClick)
                            }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Done, contentDescription ="DoneBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Done")
                        }

                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { viewModel.onEvent(PlayerListEvent.onAddScoreDialogDismiss) }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Close, contentDescription ="CloseBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Close")
                        }
                    }
                }
            })
    }

    if(viewModel.showResetScoreDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 350.dp, height = 180.dp),
            title = {Text(text = "Are you sure you want to reset the score?", fontSize = 24.sp)},
            onDismissRequest = {viewModel.onEvent(PlayerListEvent.onResetScoreDialogDismiss) },
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Button(colors =ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red),

                        onClick = { viewModel.onEvent(PlayerListEvent.onResetScoreConfirmClick) }) {
                        Text(text = "Yes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        colors =ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red),
                        onClick = { viewModel.onEvent(PlayerListEvent.onResetScoreDialogDismiss) }) {
                        Text(text = "No",fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            })

    }
    if(viewModel.showDeletePlayerDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 350.dp, height = 180.dp),
            title = {Text(text = "Are you sure you want to delete this player?", fontSize = 24.sp)},
            onDismissRequest = {viewModel.onEvent(PlayerListEvent.onDeletePlayerDialogDismiss) },
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Button(colors =ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red),

                        onClick = { viewModel.onEvent(PlayerListEvent.onDeletePlayerConfirmClick) }) {
                        Text(text = "Yes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        colors =ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red),
                        onClick = { viewModel.onEvent(PlayerListEvent.onDeletePlayerDialogDismiss) }) {
                        Text(text = "No",fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            })

    }
    if (viewModel.showUpdatePlayerDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 300.dp, height = 340.dp),
            title = { Text(text = "Edit player", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)},
            onDismissRequest = { viewModel.onEvent(PlayerListEvent.onUpdatePlayerDialogDismiss)},
            confirmButton = {
                Column(modifier=Modifier.fillMaxSize()) {
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
                    {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(0.9f)
                                .padding(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = Color.Red,
                                unfocusedLabelColor = Color.White,
                                focusedTextColor = Color.Red,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.Blue,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.White

                            ),
                            label = { Text(text = "Edit player's name...") },
                            value = viewModel.name,
                            onValueChange = { viewModel.onEvent(PlayerListEvent.onNameChange(it)) })

                    }
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
                    {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(0.9f)
                                .padding(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = Color.Red,
                                unfocusedLabelColor = Color.White,
                                focusedTextColor = Color.Red,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.Blue,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.White

                            ),
                            label = { Text(text = "Edit player's won games") },
                            value = viewModel.gamesWon,
                            onValueChange = { viewModel.onEvent(PlayerListEvent.onGamesWonChange(it)) })//nu imi afiseaza schimbarea si cand dau done imi ia decat prima cifra AICI E PROBLEMA

                    }

                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = {
                                viewModel.onEvent(PlayerListEvent.onEditDoneBtnClick)
                            }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Done, contentDescription ="DoneBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Done")
                        }

                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { viewModel.onEvent(PlayerListEvent.onUpdatePlayerDialogDismiss) }
                        ) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Close, contentDescription ="CloseBtn" )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "Close")
                        }
                    }
                }
            })
    }
    if(viewModel.showFinishGameDialog)
    {
        AlertDialog(
            modifier = Modifier.size(width = 400.dp, height = 220.dp),
            title = {Text(text = "${viewModel.winnerPlayer.name.capitalize()} currently holds the highest score. Would you like to end this game?",
                fontSize = 20.sp)},
            onDismissRequest = {viewModel.onEvent(PlayerListEvent.onFinishGameDialogDimiss) },
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Button(colors =ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red),

                        onClick = { viewModel.onEvent(PlayerListEvent.onFinishGameConfirmBtnPressed) }) {
                        Text(text = "Yes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        colors =ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red),
                        onClick = { viewModel.onEvent(PlayerListEvent.onFinishGameDialogDimiss) }) {
                        Text(text = "No",fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            })

    }

}