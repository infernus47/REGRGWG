package com.youtubelaudio.app.ui

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cut
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.youtubelaudio.app.ui.screen.LibraryScreen

@Composable
fun SearchScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Поиск YouTube (только аудио)")
        var query by remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.weight(1f), label = { Text("Запрос") })
            Button(onClick = { /* TODO */ }, modifier = Modifier.padding(start = 8.dp)) { Text("Найти") }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(emptyList<String>()) { item ->
                Row(modifier = Modifier.fillMaxWidth().clickable { }, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item)
                        Text("канал • 0:00")
                    }
                    IconButton(onClick = { /* download */ }) { Icon(Icons.Filled.Download, contentDescription = "Скачать") }
                    IconButton(onClick = { /* play */ }) { Icon(Icons.Filled.PlayArrow, contentDescription = "Играть") }
                }
            }
        }
    }
}

@Composable
fun DownloadsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Загрузки")
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(emptyList<String>()) { item ->
                Row(modifier = Modifier.fillMaxWidth().clickable { }, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) { Text(item) }
                    IconButton(onClick = { val encoded = Uri.encode(item); navController.navigate("editor/$encoded") }) {
                        Icon(Icons.Filled.Cut, contentDescription = "Обрезать")
                    }
                    IconButton(onClick = { /* play */ }) { Icon(Icons.Filled.PlayArrow, contentDescription = "Играть") }
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("История")
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Пока пусто")
    }
}

@Composable
fun PlayerScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Плеер")
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Трек • Повтор: выкл / трек / очередь")
    }
}

@Composable
fun LibraryScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Библиотека")
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Очередь и избранное")
    }
}

@Composable
fun EditorScreen(navController: NavController, encodedPath: String) {
    val path = Uri.decode(encodedPath)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Обрезка трека")
        Spacer(modifier = Modifier.padding(8.dp))
        Text(path)
        Text("Выбор диапазона (начало/конец), предпросмотр, сохранение копии")
        Button(onClick = { /* export */ }) { Text("Сохранить обрезанную копию") }
    }
}
