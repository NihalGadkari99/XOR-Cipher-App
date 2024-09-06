# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.10.2)

# Set the project name.
project("xorcipherapp")

# Add the native library (native-lib).
add_library( native-lib  # This name MUST match the one in System.loadLibrary()
        SHARED
        native-lib.cpp )  # The path to your native C++ file

# Find the Android log library.
find_library( log-lib
        log )

# Link the log library to your native library.
target_link_libraries( native-lib
        ${log-lib} )