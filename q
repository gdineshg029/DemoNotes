[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex 53115c6..5f3cf35 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -32,6 +32,11 @@[m [mandroid {[m
     kotlinOptions {[m
         jvmTarget = '1.8'[m
     }[m
[32m+[m
[32m+[m[32m    buildFeatures {[m
[32m+[m[32m        dataBinding true[m
[32m+[m[32m        viewBinding true[m
[32m+[m[32m    }[m
 }[m
 [m
 dependencies {[m
[36m@@ -39,18 +44,29 @@[m [mdependencies {[m
     //dependency to our core module[m
     implementation project(':core')[m
 [m
[32m+[m
[32m+[m
[32m+[m
[32m+[m[32m    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"[m
[32m+[m
[32m+[m[32m    implementation 'androidx.core:core-ktx:1.7.0'[m
[32m+[m
[32m+[m[32m    implementation 'androidx.appcompat:appcompat:1.4.0'[m
[32m+[m[32m    implementation 'com.google.android.material:material:1.4.0'[m
[32m+[m[32m    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'[m
     implementation "androidx.recyclerview:recyclerview:1.1.0"[m
 [m
[32m+[m[32m    implementation 'android.arch.lifecycle:extensions:1.1.1'[m
[32m+[m
     implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"[m
     implementation "androidx.navigation:navigation-ui-ktx:$nav_version"[m
 [m
[31m-    implementation 'android.arch.lifecycle:extensions:1.1.1'[m
[31m-[m
     implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"[m
     implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"[m
 [m
     implementation "androidx.room:room-runtime:$room_version"[m
     implementation 'androidx.legacy:legacy-support-v4:1.0.0'[m
[32m+[m
     kapt "androidx.room:room-compiler:$room_version"[m
     implementation "androidx.room:room-ktx:$room_version"[m
 [m
[36m@@ -60,13 +76,6 @@[m [mdependencies {[m
     kapt "com.google.dagger:dagger-android-processor:$daggerVersion"[m
 [m
 [m
[31m-    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"[m
[31m-    implementation 'androidx.core:core-ktx:1.7.0'[m
[31m-    implementation 'androidx.appcompat:appcompat:1.4.0'[m
[31m-    implementation 'com.google.android.material:material:1.4.0'[m
[31m-    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'[m
[31m-    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'[m
[31m-    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'[m
     testImplementation 'junit:junit:4.+'[m
     androidTestImplementation 'androidx.test.ext:junit:1.1.3'[m
     androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'[m
