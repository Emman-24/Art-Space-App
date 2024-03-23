package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtWorkLayout()
            }
        }
    }
}

fun getArt(): List<Art> {
    return listOf(
        Art("La Gioconda", "Leonardo da Vinci", 1503, R.drawable.gioconda),
        Art("El caminante sobre un mar de nubes", "Caspar David Friedrich", 1818, R.drawable.caminante),
        Art("El grito", "Edvard Munch", 1893, R.drawable.grito)
    )
}

@Composable
fun ArtWorkLayout() {
    val artList = remember { mutableStateOf(getArt()) }
    val currentArtIndex = remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxHeight()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        val currentArt = artList.value[currentArtIndex.intValue]
        ArtWorkWall(currentArt)
        ArtWorkDescription(currentArt)
        DisplayController(currentArtIndex, artList.value.size)
    }
}

@Composable
fun ArtWorkWall(art: Art) {
    Card(
        modifier = Modifier
            .padding(30.dp)
            .size(330.dp, 410.dp),
        elevation = CardDefaults.cardElevation(14.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(40.dp)

        ) {
            Image(
                painter = painterResource(id = art.image),
                alignment = Alignment.Center,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ArtWorkDescription(art: Art) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Column(
            Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = art.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Light,

                )
            Row {
                Text(
                    text = art.painter,
                    modifier = Modifier.padding(end = 7.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "(${art.year})")
            }
        }
    }
}

@Composable
fun DisplayController(currentArtIndex: MutableState<Int>, artListSize: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            if (currentArtIndex.value > 0)
                currentArtIndex.value--
        }) {
            Text(text = "Previous", modifier = Modifier.padding(horizontal = 15.dp))
        }
        Button(onClick = {
            if (currentArtIndex.value < artListSize - 1)
                currentArtIndex.value++
        }) {
            Text(text = "Next", modifier = Modifier.padding(horizontal = 24.dp))
        }
    }
}


@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun ArtPreviewTablet() {
    ArtWorkLayout()
}

@Preview(showSystemUi = true)
@Composable
fun ArtPreviewPhone() {
    ArtWorkLayout()
}