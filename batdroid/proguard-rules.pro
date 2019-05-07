###############################################################################

# Save the obfuscation mapping to a file, so a de-obfuscation trace can be made
-printmapping build/mapping.txt

# Reduce the output more
-repackageclasses ''
-allowaccessmodification

# Keep a fixed source file attribute and all line number tables to get line
# numbers in stack traces
-renamesourcefileattribute SourceFile
-keepattributes SourceFile, LineNumberTable

# Remove Android logging calls
-assumenosideeffects class android.util.Log {
	public static int d(...);
	public static int e(...);
	public static int i(...);
	public static int v(...);
	public static int v(...);
}

# Preserve Andorid Libraries
-keep class android.support.**
-dontwarn android.support.**

###############################################################################

# Kotlin
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {<fields>;}
# Remove Kotlin Null Checks
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
	static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

###############################################################################

# Dagger
-dontwarn com.google.errorprone.annotations.**

###############################################################################

# SQLCipher
-keep class net.sqlcipher.** { *; }

###############################################################################

# Turn off needless warnings
-dontwarn java.**
-dontwarn bsh.**
-dontwarn javassist.**
-dontwarn com.google.android.gms.common.**

# Keep dynamically referenced classes
-keep class com.amazonaws.**
-keep class org.spongycastle.**
-keep class org.bouncycastle.**
-keep class com.google.android.**
-keep class com.google.android.gms.dynamite.** { *; }