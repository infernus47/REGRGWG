# YouTube Audio Android

Отдельный Android-проект на Kotlin + Jetpack Compose + Media3 + NewPipeExtractor + FFmpegKit.

## Что реализовано (каркас)
- Поиск (экран)
- Скачивание в оффлайн (экран «Загрузки»)
- Повтор: выкл / трек / очередь (в плеере)
- История / Библиотека / Очередь
- **Редактор** — экран обрезки трека (ползунки диапазона, превью, сохранение обрезанной копии). Для реального нарезания используется `FFmpegKit`.

## Как собрать APK

Вариант 1: Android Studio (рекомендуется)
1. Скачать проект папкой `yt-audio-android/`
2. Открыть в Android Studio (Giraffe/Koala+) — он сам предложит скачать Gradle/SDK
3. Build > Generate Signed Bundle / APK... > APK

Вариант 2: GitHub Actions
1. Закинуть проект в GitHub
2. Добавить workflow `assembleRelease` (gradlew assembleRelease)
3. Скачать APK из артефактов

## Зависимости
- `androidx.media3:*` — воспроизведение и фоновый плеер
- `com.github.TeamNewPipe:NewPipeExtractor` — извлечение аудио без прямых API-ключей
- `com.arthenica:ffmpeg-kit-full` — обрезка/нарезка и конвертация
- `coil`, `datastore`, `navigation-compose`

## Файлы
Сохранение: `Android/data/com.youtubelaudio.app/files/` или `Music/YouTubeAudio/` (нужны разрешения на Android 13+ через MediaStore).

## Примечания
Сборка APK требует Android SDK и JDK 17. В этой среде их нет, поэтому я собрал готовый проект-каркас — его можно сразу открыть в Android Studio и собрать.
