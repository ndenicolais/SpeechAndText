# ComposeSpeechAndText
> <b>Author: Nicola De Nicolais</b>

## 📍 Description
Android application built with Kotlin and Jetpack Compose that shows how to use the functions:<br>
- **Speech-to-Text (STT)** that allow to receive the input voice through the reception from the microphone and to convert the audio into text and display it inside the box with the possibility to save the text inside a file with the extension ". txt".<br>
- **Text-to-Speech (TTS)** that allow to insert a text inside the box and convert the text to audio with the option to choose the tone and speed of the voice that will play the text inserted. The entry will read the text in the language configured within the code.<br>

## ✏️ Customization
You can custom the Speech-to-Text (STT) function by editing the settings about File's folder:

#### File's folder
Change the path  where the text file will be saved.
If you want you can also avoid creating a folder in which to save files but let them be saved directly in the default folder


You can custom the Text-to-Speech (TTS) function by editing the settings about Language, Speech rate and Pitch:

#### Language
Change the value of *txtToSpeech.language* to set your prefered language according the available languages listed in this doc:

https://docs.oracle.com/javase/7/docs/api/java/util/Locale.html

#### Pitch

Change the value of *pitch's Slider* to change how fast your device reads aloud.

#### Speech rate

Change the value of *speechRate's Slider* to alter the tone of the spoken voice.

## 🛠️ Permissions
### Manifest
Permissions required in the manifest to use the functions:
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET"/>
```

## 📎 Screen preview
<p float="left">
<img height="500em" src="screenshots/Screenshot01.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot02.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot03.png" title="SpeechToText's screen preview">
