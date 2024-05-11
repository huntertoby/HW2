package com.example.hw2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw2.ui.theme.HW2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HW2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumnDemo()
                }
            }
        }
    }
}

data class ImageInfo(
    val resourceId: Int,
    val country: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)


val imagesList = listOf(
    ImageInfo(
        com.example.hw2.R.drawable.tokyotower,
        "日本",
        "東京鐵塔",
        "東京鐵塔於1958年竣工，高333米，是東京的象徵性建築，提供城市全景觀賞。",
        35.6586,
        139.7454
    ), ImageInfo(
        com.example.hw2.R.drawable.paristower,
        "法國",
        "艾菲爾鐵塔",
        "艾菲爾鐵塔由古斯塔夫·艾菲爾設計，1889年完工，是巴黎最著名的地標之一。",
        48.8584,
        2.2945
    ), ImageInfo(
        com.example.hw2.R.drawable.statueofliberty,
        "美國",
        "自由女神像",
        "自由女神像由法國送給美國，1886年揭幕，象徵自由和民主，位於紐約市。",
        40.6892,
        -74.0445
    ), ImageInfo(
        com.example.hw2.R.drawable.bigben,
        "英國",
        "大笨鐘",
        "大笨鐘是倫敦西敏寺的鐘樓鐘聲，是英國政治活動的象徵，建於1859年。",
        51.5007,
        -0.1246
    ), ImageInfo(
        com.example.hw2.R.drawable.greatwall,
        "中國",
        "萬里長城",
        "萬里長城是古代中國的防禦工程，全長超過2萬公里，展現古代中國的建築功力。",
        40.4319,
        116.5704
    ), ImageInfo(
        com.example.hw2.R.drawable.jesus_christ,
        "巴西",
        "救世主耶穌基督像",
        "巴西里約熱內盧的救世主耶穌基督像，高30米，位於科科瓦多山頂，是巴西的標誌性雕像。",
        -22.9519,
        -43.2105
    ), ImageInfo(
        com.example.hw2.R.drawable.leaningtower,
        "義大利",
        "比薩斜塔",
        "比薩斜塔是義大利比薩的鐘樓，因地基問題而著名的傾斜，始建於1173年。",
        43.7228,
        10.3966
    ), ImageInfo(
        com.example.hw2.R.drawable.redsquare,
        "俄羅斯",
        "莫斯科紅場",
        "莫斯科紅場是俄羅斯的歷史和政治心臟，聞名於其壯觀的建築群和克里姆林宮。",
        55.7539,
        37.6208
    ), ImageInfo(
        com.example.hw2.R.drawable.windmill,
        "荷蘭",
        "風車",
        "荷蘭風車是荷蘭最具代表性的風景之一，曾廣泛用於排水和磨面，至今仍風光明媚。",
        51.9715,
        4.2810
    ), ImageInfo(
        com.example.hw2.R.drawable.tapei101,
        "台灣",
        "台北101高塔",
        "台北101高塔曾是世界上最高的建築，具有現代工程技術的典範，是台北的象徵。",
        25.0330,
        121.5645
    )
)

var showDetails: Boolean = false

@Composable
fun LazyColumnDemo() {
    var selectedCountry by remember { mutableStateOf<ImageInfo?>(null) }
    var showDetails by remember { mutableStateOf(false) }  // 將 showDetails 改為 Compose 可追踪的狀態

    if (showDetails && selectedCountry != null) {
        DetailedView(selectedCountry!!, onBack = { showDetails = false })
    } else {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = imagesList, itemContent = { item ->
                Button(
                    onClick = {
                        selectedCountry = item
                        showDetails = true
                    }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = androidx.compose.ui.graphics.Color.White
                    ), elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp, pressedElevation = 8.dp
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.country, fontSize = 48.sp, fontWeight = FontWeight.Bold
                    )
                }
            })
        }
    }
}


@Composable
fun DetailedView(imageInfo: ImageInfo, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageInfo.resourceId),
            contentDescription = imageInfo.name.toString(),
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier, verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = imageInfo.country,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Column(
            modifier = Modifier, verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = imageInfo.name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }


        Text(
            text = imageInfo.description,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(2f)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )

        val context = LocalContext.current

        Row(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val uri =
                        Uri.parse("geo:${imageInfo.latitude},${imageInfo.longitude}?q=${imageInfo.latitude},${imageInfo.longitude}(${imageInfo.name})")
                    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                }, modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Map")
            }
            Button(
                onClick = {
                    onBack()
                }, modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DetailedView(imagesList.get(0),onBack = { showDetails = false })
}
