# ComposeSpeechAndText
> <b>Author: Nicola De Nicolais</b>

## 📍 Description
Android application built with Kotlin and Jetpack Compose that shows how to use the functions:<br>
- **Speech-to-Text (STT)** that allow to receive the input voice through the reception from the microphone and to convert the audio into text and display it inside the box with the possibility to save the text inside a file with the extension ". txt".<br>
- **Text-to-Speech (TTS)** that allow to insert a text inside the box and convert the text to audio with the option to choose the tone and speed of the voice that will play the text inserted. The entry will read the text in the language configured within the code.<br>

## ✏️ Customization
You can custom some aspects of app:

#### Save folder
Change the path  where the text of Speech-to-Text file will be saved.
If you want you can also avoid creating a folder in which to save files but let them be saved directly in the default folder

#### Language
Change the value of *txtToSpeech.language* to set your prefered language according the available languages listed in this doc:

https://docs.oracle.com/javase/7/docs/api/java/util/Locale.html

#### Pitch
Change the value of *pitch's Slider* of Text-to-Speech to change how fast your device reads aloud.

#### Speech rate
Change the value of *speechRate's Slider* of Text-to-Speech to alter the tone of the spoken voice.

## 🔺 Manifest Permissions
Permissions required in the AndroidManifest to use the functions:
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET"/>
```


## 🛠️ Package Structures

```
com.denicks21.speechandtext     # Root Package
├── navigation                  # Refers to the interactions that allow you to navigate in the app.
│   ├── NavGraph                # Contains all of app destinations and actions.
│   └── NavScreens              # Contains a sealed class with object corresponds to a screen and its routes.
|
├── screen                      # App screens
|   │   ├── HomePage            #.
|   │   ├── InfoPage            #.
|   │   ├── IntroPage           #.
|   │   ├── SpeechToTextPage    #.
|   │   ├── TextToSpeechPage    #.
│
├── ui                          # UI resources
│   ├── composables             # 
|   │   ├── CustomBackPress     # Component that control and prevent back button action
|   │   ├── CustomDrawer        # Navigation drawer menu with app screens
|   │   ├── CustomTopBar        # Bar that represent the app name and drawer menu
|
├── theme                       # Theme components
|   │   ├── Color               # Color palette used by the app.
|   │   ├── Shape               # Components shapes of Compose used by the app.
|   │   ├── Theme               # Theme used by the app.
|   │   ├── Type                # Typography styles for the fonts used by the app.
├── MainActivity                # Main activity
```

## 📎 Screen preview
<p float="left">
<img height="500em" src="screenshots/Screenshot01.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot02.png" title="SpeechToText's screen preview">
<img height="500em" src="screenshots/Screenshot03.png" title="SpeechToText's screen preview">
