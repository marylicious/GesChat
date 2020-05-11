# Geschat 

Geschat is an Android native app that allows users to subscribe to online meetings called "Chats". It was build using Android with Java as client, while the backend was created with Firebase (especially Firebase Auth and Firestore)


### How to download the app and install the app
Click this [link](https://www.mediafire.com/file/8bbdzzuw7w1y4um/app-debug.apk/file) to download the `.apk`. This is not the most recent build but it contains almost all the interfaces.

- You need an Android device with a version greater than Android 4.4 (Android KitKat). The app was created and tested in tablets and phones but technically it should be able to run in any Android device
- As the app is not firmed, you will need to set up dev options to allow instalation from unstrusted sources. Check here for more instruction:
https://developer.android.com/studio/debug/dev-options
- Once you have done this, you can install Geschat as any other app with the Android Installer. 

### If you wish to contribute or recycle the code for personal uses

The was created using Android Studio, for this reason follows the typical structure. 

- All the activities are defined in  `GesChat/app/src/main/AndroidManifest.xml` 
- The relevant code outside of gradle bundles is on `https://github.com/marylicious/GesChat/tree/master/app/src/main/java/com/example/geschat` , all those java files specify the different actions of each 
- The folder `ui` has all the xml that is rendered on the views. 
- The folder `adapters` has RecyclerView adapters, and the folder `models` has all the data models that are needed for the app. 
