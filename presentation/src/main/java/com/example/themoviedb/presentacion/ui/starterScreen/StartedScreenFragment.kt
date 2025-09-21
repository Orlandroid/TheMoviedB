package com.example.themoviedb.presentacion.ui.starterScreen

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentStartedScreenBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.pagerFadeTransition


class StartedScreenFragment :
    BaseFragment<FragmentStartedScreenBinding>(R.layout.fragment_started_screen) {
    override fun setUpUi() {
        binding.composeView.setContent {
            StartedScreen()
        }
    }

    @Composable
    private fun StartedScreen(modifier: Modifier = Modifier) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            UpComingMovies()
            Spacer(Modifier.height(24.dp))
            NowPlaying()
            Spacer(Modifier.height(24.dp))
            Popular()
            Spacer(Modifier.height(24.dp))
            TrendingPersons()
        }
    }

    @Composable
    private fun UpComingMovies() {
        val images = listOf(
            R.drawable.note,
            R.drawable.actor,
            R.drawable.artist,
            R.drawable.blogger,
            R.drawable.cameraman,
            R.drawable.crew,
            R.drawable.electrician,
            R.drawable.escritor,
            R.drawable.films_director,
            R.drawable.makeup_artist,
            R.drawable.productor,
            R.drawable.writing,
        )
        val pagerState = rememberPagerState(pageCount = { images.size })
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(16.dp),
            pageSpacing = 16.dp,
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            Box(Modifier.pagerFadeTransition(page = page, pagerState = pagerState)) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Upcoming movies",
            textAlign = TextAlign.Center
        )
    }

    @Composable
    private fun NowPlaying() {
        Text(text = "Now Playing", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                BaseCardContent(
                    contextDescription = "Movie title example",
                    image = R.drawable.crew,
                    imageBackground = Color.Red
                )
            }
        }
    }

    @Composable
    private fun Popular() {
        Text(text = "Popular", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        LazyRow(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                BaseCardContent(
                    contextDescription = "Movie title example",
                    image = R.drawable.note,
                    imageBackground = Color.Blue
                )
            }
        }
    }

    @Composable
    private fun BaseCardContent(
        contextDescription: String,
        @DrawableRes image: Int,
        imageBackground: Color,
        imageHeight: Dp = 150.dp
    ) {
        Card(
            modifier = Modifier.width(IntrinsicSize.Max),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .background(imageBackground)
            )
            Text(text = contextDescription)
        }
    }

    @Composable
    private fun TrendingPersons() {
        Text(text = "Trending persons", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        LazyRow(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) { image ->
                Image(
                    painter = painterResource(id = R.drawable.note),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun StartedScreenPreview() {
        StartedScreen()
    }


}