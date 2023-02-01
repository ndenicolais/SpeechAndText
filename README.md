# ComposeSpeechAndText
> <b>Author: Nicola De Nicolais</b>

## 📍 Description
Android application created using the modern toolkit Jetpack Compose. This application allows you to use two function:<br>
- Speech to Text that allow to receive the input voice through the reception from the microphone and to convert the audio into text and display it inside the box with the possibility to save the text inside a file with the extension ". txt".<br>
- Text to Speech that allow to insert a text inside the box and convert the text to audio with the option to choose the tone and speed of the voice that will play the text inserted. The entry will read the text in the language configured within the code.<br>


## 🛠️ Config
### Gradle
Add navigation's dependency:
```
implementation 'androidx.navigation:navigation-compose:2.5.3'
```

### Manifest
Add permissions for save file and record audio:
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET"/>
```

## ✏️ Customization

You can custom the app by editing:

Language of TextToSpeech

TextToSpeech.kt at line:
```
txtToSpeech.language = Locale.ITALIAN
```

Change ITALIAN to your preferred language.

## 📎 Screen preview
<p float="left">
<img height="500em" src="screenshots/Screenshot01.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot02.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot03.png" title="SpeechToText's screen preview">
