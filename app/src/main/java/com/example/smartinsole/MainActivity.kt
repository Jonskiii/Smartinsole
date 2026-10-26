package com.example.smartinsole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var timeSeconds by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            timeSeconds++
        }
    }

    val timeString = String.format("%02d:%02d:%02d", timeSeconds / 3600, (timeSeconds % 3600) / 60, timeSeconds % 60)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("DEVICE: Smart Insole L+R", color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("BATTERY: 84% ⚡", color = Color(0xFF00E5FF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(timeString, color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Light)
        Text("ACTIVITY TIME", color = Color.Gray, fontSize = 14.sp, letterSpacing = 2.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(title = "MAX SPEED", value = "28.4", unit = "km/h", modifier = Modifier.weight(1f))
            StatCard(title = "AVG SPEED", value = "12.6", unit = "km/h", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(title = "MAX JUMP", value = "68", unit = "cm", modifier = Modifier.weight(1f))
            StatCard(title = "AVG JUMP", value = "42", unit = "cm", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(title = "JUMP COUNT", value = "42", unit = "reps", modifier = Modifier.weight(1f))
            StatCard(title = "STEPS", value = "3,450", unit = "steps", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("VIEW JUMPSHOT MAP", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun StatCard(title: String, value: String, unit: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(value, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(unit, color = Color(0xFF00E5FF), fontSize = 12.sp, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}