package orlando.leyva.practicaautenticacionleyvao.presentacion.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import orlando.leyva.practicaautenticacionleyvao.presentacion.model.Artist
import orlando.leyva.practicaautenticacionleyvao.ui.theme.Black

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(viewmodel: HomeViewmodel = viewModel(), auth: FirebaseAuth, navigateToInitial: () -> Unit) {
    val artists = viewmodel.artist.collectAsState()

    Column(Modifier.fillMaxSize().background(Black).systemBarsPadding()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Popularartist", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Button(onClick = { 
                auth.signOut() 
                navigateToInitial()
            }) {
                Text("Log Out")
            }
        }

        LazyRow {
            items(artists.value){
                ArtistItem(it)
            }
        }
    }
}

@Composable
fun ArtistItem(artist: Artist){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.size(60.dp).clip(CircleShape),
            model = artist.image,
            contentDescription = null,
        )
        Spacer(Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(),
            color = Color.White
        )
    }
}
