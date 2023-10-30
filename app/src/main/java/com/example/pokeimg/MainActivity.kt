package com.example.pokeimg

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.pokeimg.ui.theme.PokeimgTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idState: MutableState<Int> = mutableStateOf(1)

        setContent {
            PokeimgTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PokemonInfo(idState.value)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            repeat(50) {
                delay(5000)

                idState.value += 1

                if (idState.value > 100) {
                    idState.value = 100
                }
            }
        }
    }

    @Composable
    fun PokemonInfo(id: Int) {
        Column {
            Text(text = "ID: $id")
            Image(
                painter = rememberImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"),
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(5.dp)
            )
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PokeimgTheme {
            PokemonInfo(1)
        }
    }
}
