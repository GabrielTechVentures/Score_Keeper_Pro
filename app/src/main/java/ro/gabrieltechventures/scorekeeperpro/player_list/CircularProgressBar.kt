package ro.gabrieltechventures.scorekeeperpro.player_list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBar(
    percentage:Float,
    currentScore:Int=0,
    maximumScore:Int=0,
    fontSize:TextUnit=20.sp,
    radius:Dp=65.dp,
    color:Color= Color.Red,
    strokeWidth:Dp=12.dp,
    animDuration:Int=1200,
    animDelay:Int=0,

)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        CircularProgressIndicator(
            progress = percentage,
            modifier = Modifier.size(radius * 2f),
            color = color,
            strokeWidth = strokeWidth
        )
        Text(
            text = "$currentScore/$maximumScore",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
  /*  var animationPlayed by remember{ mutableStateOf(false) }
    val curPercentage= animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
    )
    LaunchedEffect(key1 = true)
    {
            animationPlayed=true
    }
    Box(

        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius*2f)
    ) {*/
    /*    Canvas(modifier =Modifier.size(radius*2f))
        {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360*percentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }*/
    /*
        Text(
            text = "${currentScore}/$maximumScore",
            //"${(curPercentage.value* maximumScore+0.5).toInt()}/$maximumScore",//daca il las asa cand adaug scor nu imi face adunarea corect la scorul total adica daca am 500 si mai pun 10 totalul in loc de 510 imi da 515
            //(curPercentage.value-curPercentage.value-1 * currentScore).toInt()}/$maximumScore il facusem asa dar cand face animatia nu mai creste textul cu scorul si as fi vrut sa creasca
            color= Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    //}*/
}