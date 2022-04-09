package com.example.foodorder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.*
import com.example.foodorder.R

@Composable
fun Loading() {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            /** Lotti file location specified */
            val composition =
                rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading3))

            /** Add extra properties to lottie file object*/
            val progress =
                animateLottieCompositionAsState(composition.value, iterations = Int.MAX_VALUE)

            /** Lotti File Animation*/
            LottieAnimation(
                composition = composition.value,
                progress = progress.value
            )
        }
    }
}