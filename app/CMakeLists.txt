# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("imageweightloss")

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/native-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

message(suihw ${PROJECT_SOURCE_DIR}/src/main/cpp/include)
#引入头文件
include_directories( ${PROJECT_SOURCE_DIR}/src/main/cpp/include/ )

#引入jpeglib库
add_library(
        jpeg
        SHARED
        IMPORTED
)
set_target_properties(
        jpeg
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/src/main/cpp/otherSharedLib/${CMAKE_ANDROID_ARCH_ABI}/libjpeg.so
)
add_library(
        turbojpeg
        SHARED
        IMPORTED
)
set_target_properties(
        turbojpeg
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/src/main/cpp/otherSharedLib/${CMAKE_ANDROID_ARCH_ABI}/libturbojpeg.so
)
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

find_library(
        graphics-lib
        jnigraphics)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        native-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        ${graphics-lib}
        jpeg
        turbojpeg)
