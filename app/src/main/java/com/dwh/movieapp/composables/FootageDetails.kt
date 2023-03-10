package com.dwh.movieapp.utils.utils

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity
import com.dwh.domain.models.get.movies.details.MoviesDetails
import com.dwh.domain.models.get.series.details.SeriesDetails
import com.dwh.movieapp.R
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.ui.theme.YellowStarIcon
import mx.com.satoritech.web.APIConstants

@Composable
fun FootageDetails(
    footageDetails: Any,
    navController: NavController
) {

    val headerHeight = 275.dp
    val scroll: ScrollState = rememberScrollState(0)

    Box(Modifier.fillMaxSize()) {
        FootageDetailsContent(footageDetails, scroll)
        FootageHeaderImage(footageDetails, headerHeight, scroll, navController)
    }
}

@Composable
fun FootageHeaderImage(
    footageDetails: Any,
    headerHeight: Dp,
    scroll: ScrollState,
    navController: NavController
) {

    var imageUrl = ""

    if(footageDetails is MoviesDetails) {
        imageUrl = footageDetails.backdropPath ?: ""
    } else if(footageDetails is SeriesDetails) {
        imageUrl = footageDetails.backdropPath ?: ""
    } else if(footageDetails is MovieEntinty) {
        imageUrl = footageDetails.backdropPath ?: ""
    } else if(footageDetails is SerieEntity) {
        imageUrl = footageDetails.backdropPath ?: ""
    } else if(footageDetails is TopRatedMoviesEntity) {
        imageUrl = footageDetails.backdropPath ?: ""
    } else if(footageDetails is TopRatedSeriesEntity) {
        imageUrl = footageDetails.backdropPath ?: ""
    }

    Log.w("Image", imageUrl)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 2f
            }
    ) {
        if (imageUrl.isNotEmpty()) {
            DetailsFootageImage(APIConstants.SERVER_IMAGE_URL + imageUrl)
        } else {
            DetailsFootageImage("https://picsum.photos/400")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .background(DarkBlueBackground, CircleShape)
                    .padding(5.dp)
                    .size(25.dp)
                    .clickable { navController.popBackStack() },
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
fun FootageDetailsContent(
    footageDetails: Any?,
    scroll: ScrollState
) {

    var title = ""
    var releaseDate = ""
    var synopsis = ""
    var rating = 0.0

    if(footageDetails is MoviesDetails) {
        title = footageDetails.title ?: ""
        releaseDate = footageDetails.releaseDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    } else if(footageDetails is SeriesDetails) {
        title = footageDetails.name ?: ""
        releaseDate = footageDetails.firstAirDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    } else if(footageDetails is MovieEntinty) {
        title = footageDetails.title ?: ""
        releaseDate = footageDetails.releaseDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    } else if(footageDetails is SerieEntity) {
        title = footageDetails.name ?: ""
        releaseDate = footageDetails.firstAirDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    } else if(footageDetails is TopRatedMoviesEntity) {
        title = footageDetails.title ?: ""
        releaseDate = footageDetails.releaseDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    } else if(footageDetails is TopRatedSeriesEntity) {
        title = footageDetails.name ?: ""
        releaseDate = footageDetails.firstAirDate ?: ""
        synopsis = footageDetails.overview ?: ""
        rating = footageDetails.voteAverage ?: 0.0
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(DarkBlueBackground)
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(300.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium, color = Color.White)) {
                        append(title)
                    }
                    withStyle(SpanStyle(fontSize = 18.sp,fontWeight = FontWeight.Light, color = Color.White)) {
                        append(" ($releaseDate)")
                    }
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_rating_star),
                    contentDescription = "",
                    tint = YellowStarIcon
                )
                CustomText(text = "$rating", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            CustomText(text = stringResource(R.string.synopsis), fontWeight = FontWeight.Medium, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            CustomText(text = synopsis, fontSize = 18.sp)
        }
    }
}