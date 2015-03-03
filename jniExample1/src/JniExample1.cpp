#include "JniExample1.h"
#include <string.h>

JNIEXPORT jint JNICALL Java_JniExample1_intMethod (JNIEnv *env, jobject obj, jint num){
	return num * num;
}


//cl -IC:\apps\J2ee6\jdk\include -IC:\apps\J2ee6\jdk\include\win32 -LD JniExample1.cpp -FeJniExample1.dll