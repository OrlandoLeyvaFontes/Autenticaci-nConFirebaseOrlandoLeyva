package orlando.leyva.practicaautenticacionleyvao.presentacion.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import orlando.leyva.practicaautenticacionleyvao.presentacion.model.Artist

class HomeViewmodel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _artist = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artist

    init {
        getArtists()
    }

    private fun getArtists() {
        viewModelScope.launch {

            try {

                val result = db.collection("artists")
                    .get()
                    .await()
                    .documents
                    .mapNotNull {
                        it.toObject(Artist::class.java)
                    }

                Log.d("FIRESTORE", "Cantidad: ${result.size}")

                _artist.value = result

            } catch (e: Exception) {

                Log.e("FIRESTORE", "Error", e)
            }
        }
    }

    private suspend fun getAllArtists(): List<Artist> {
        return try {
            db.collection("artists")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Artist::class.java)
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
