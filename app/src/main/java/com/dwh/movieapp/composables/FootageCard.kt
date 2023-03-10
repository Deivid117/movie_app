package com.dwh.movieapp.utils.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dwh.domain.models.entities.*
import com.dwh.domain.models.get.movies.popular.upcoming.MovieResults
import com.dwh.domain.models.get.series.popular.upcoming.SerieResults
import com.dwh.movieapp.ui.theme.DarkBlueCard
import com.dwh.movieapp.R
import com.dwh.movieapp.ui.theme.YellowStarIcon
import mx.com.satoritech.web.APIConstants

@Composable
fun FootageCard(
    navController: NavController,
    footageItem: Any,
    route: String,
    notClickable: Boolean = false
) {

    var title = ""
    var releaseDate = ""
    var rating = 0.0
    var footageId = 0
    var imageUrl = ""

    if(footageItem is MovieResults) {
        title = footageItem.title ?: ""
        releaseDate = footageItem.releaseDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is SerieResults) {
        title = footageItem.name ?: ""
        releaseDate = footageItem.firstAirDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is MovieEntinty) {
        title = footageItem.title ?: ""
        releaseDate = footageItem.releaseDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is SerieEntity) {
        title = footageItem.name ?: ""
        releaseDate = footageItem.firstAirDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is UpcomingMoviesEntity) {
        title = footageItem.title ?: ""
        releaseDate = footageItem.releaseDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is TopRatedMoviesEntity) {
        title = footageItem.title ?: ""
        releaseDate = footageItem.releaseDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    } else if(footageItem is TopRatedSeriesEntity) {
        title = footageItem.name ?: ""
        releaseDate = footageItem.firstAirDate ?: ""
        rating = footageItem.voteAverage ?: 0.0
        footageId = footageItem.id ?: 0
        imageUrl = footageItem.backdropPath ?: ""
    }

    Card(
        Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable {
                if(notClickable) return@clickable else navController.navigate("$route/$footageId")
            },
        backgroundColor = DarkBlueCard,
        shape = RoundedCornerShape(15.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            if(imageUrl.isNotEmpty()){
                CardFootageImage(APIConstants.SERVER_IMAGE_URL + imageUrl)
            } else {
                CardFootageImage("https://picsum.photos/100")
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 15.dp)) {
                CustomText(text = title, fontWeight = FontWeight.Medium, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(5.dp))
                CustomText(text = releaseDate, fontWeight = FontWeight.Light, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(id = R.drawable.ic_rating_star),
                        contentDescription = "",
                        tint = YellowStarIcon
                    )
                    CustomText(text = "$rating", fontSize = 15.sp)
                }
            }
        }
    }
}