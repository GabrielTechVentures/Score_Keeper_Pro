package ro.gabrieltechventures.scorekeeperpro.player_list

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel
)
{
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
                Icon(imageVector = Icons.Default.Add, contentDescription ="Fab" )
            }
        }
        ){
        Text(text = "", modifier = Modifier.padding(it))
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
                       value = "",//TODO
                       onValueChange ={} )//TODO

                    Row(modifier=Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Button(
                            colors =ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Red),
                            onClick = { /*TODO*/ }
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

}