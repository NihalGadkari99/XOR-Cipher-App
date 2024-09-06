#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_xorcipherapp_MainActivity_xorEncrypt(JNIEnv *env, jobject, jstring input, jint key) {
    const char *inputStr = env->GetStringUTFChars(input, nullptr);  // Get C string from Java string
    std::string result;

    // XOR each character of the input string with the provided key
    for (int i = 0; inputStr[i] != '\0'; ++i) {
        result += std::to_string(inputStr[i] ^ key);
    }

    env->ReleaseStringUTFChars(input, inputStr);  // Release the input string memory

    // Return the encrypted string
    return env->NewStringUTF(result.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_xorcipherapp_MainActivity_xorDecrypt(JNIEnv *env, jobject, jstring input, jint key) {
    // Use the same XOR logic for decryption (it's symmetric)
    const char *inputStr = env->GetStringUTFChars(input, nullptr);  // Get C string from Java string
    std::string result;

    // XOR each character of the input string with the provided key (same logic as encryption)
    for (int i = 0; inputStr[i] != '\0'; ++i) {
        result += std::to_string(inputStr[i] ^ key);
    }

    env->ReleaseStringUTFChars(input, inputStr);  // Release the input string memory

    // Return the decrypted string
    return env->NewStringUTF(result.c_str());
}
