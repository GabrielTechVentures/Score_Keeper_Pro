package ro.gabrieltechventures.scorekeeperpro.player_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
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
fun PlayerListScreen()
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
                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="Fab" )
            }
        }
        ){
        Text(text = "", modifier = Modifier.padding(it))
    }
}