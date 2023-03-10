package com.dwh.movieapp.utils.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import androidx.compose.runtime.*

@Composable
fun BroadcastEventManager(
    context: Context,
    action: String,
    systemEvent: (Boolean?) -> Unit
) {
    val currentOnSystemEvent by rememberUpdatedState(systemEvent)

    DisposableEffect(context, action){
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                currentOnSystemEvent(isOnline(context))
            }
        }
        IntentFilter(action).also {
            context.registerReceiver(broadcastReceiver, it)
        }
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    return (networkInfo != null && networkInfo.isConnected)
}

