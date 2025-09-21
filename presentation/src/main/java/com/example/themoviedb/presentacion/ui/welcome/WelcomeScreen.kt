package com.example.themoviedb.presentacion.ui.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themoviedb.R


@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onGuestClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TMDb Logo (replace with your own logo resource)
        Icon(
            painter = painterResource(id = R.drawable.moviedbsvg),
            contentDescription = "TMDb Logo",
            modifier = Modifier.size(80.dp),
            tint = Color.Unspecified // Keep original logo colors
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome to MovieApp",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Login for a personalized experience or continue as a guest.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login button
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Login with TMDb")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Guest button
        OutlinedButton(
            onClick = onGuestClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue as Guest")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "You can always log in later from settings.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }


}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    WelcomeScreen(onGuestClick = {}, onLoginClick = {})
}