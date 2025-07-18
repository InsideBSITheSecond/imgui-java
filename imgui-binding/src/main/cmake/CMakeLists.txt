cmake_minimum_required(VERSION 3.16)
project(imgui-java-Linux-64 LANGUAGES C CXX)

option(freetype "Enable FreeType support" false) #TODO check freetype shenanigans (check options)
option(local "Local build" false) #TODO check freetype shenanigans (check options)

set(libDir "../libsNative/linux64" CACHE STRING "Output Lib Directory")
set(libName "imgui-java64.so" CACHE STRING "Output Lib Name")

# Set output directories
set(OUT_DIR "${CMAKE_SOURCE_DIR}/${libDir}")

# Set compiler options
set(C_CXX_FLAGS "${CMAKE_C_CXX_FLAGS} ")
string(APPEND C_CXX_FLAGS "${ccxxAdd} -fPIC -Wall -g -O0 -no-pie") # -fsanitize=address -fno-omit-frame-pointer

set(CMAKE_C_FLAGS "${C_CXX_FLAGS} ${cAdd}")

set(CMAKE_CXX_FLAGS "${C_CXX_FLAGS} ")
string(APPEND CMAKE_CXX_FLAGS "${cxxAdd} -DFT2_BUILD_LIBRARY -DIMGUI_TEST_ENGINE_ENABLE_COROUTINE_STDTHREAD_IMPL") ##

set(CMAKE_SHARED_LINKER_FLAGS "${LINKER_FLAGS} ")
string(APPEND CMAKE_SHARED_LINKER_FLAGS "${lAdd}") # -lasan


message("ROOT DIRECTORY: ${CMAKE_SOURCE_DIR}")
message("FREETYPE: ${freetype}")
message("LOCAL: ${local}")
message("OUT: ${OUT_DIR} -> ${libName}")
message("C_FLAGS: ${CMAKE_C_FLAGS}")
message("CXX_FLAGS: ${CMAKE_CXX_FLAGS}")
message("LINKER_FLAGS: ${CMAKE_SHARED_LINKER_FLAGS}")
# https://stackoverflow.com/questions/65494567/linking-c-library-with-a-c-dll-unresolved-external-symbol-mingw-vsnprintf

# Set include directories
include_directories(jni-headers jni-headers/linux .)

# Find all C and C++ source files
file(GLOB_RECURSE C_SOURCES "*.c")
file(GLOB_RECURSE CPP_SOURCES "*.cpp")

# Exclude CMake-generated files (like CMakeCXXCompilerId.cpp)
list(FILTER C_SOURCES EXCLUDE REGEX "CMakeFiles/.*")
list(FILTER CPP_SOURCES EXCLUDE REGEX "CMakeFiles/.*")

# Compile object files
add_library(imgui_java_objs OBJECT ${C_SOURCES} ${CPP_SOURCES})

# Create shared library
add_library(imgui-java64 SHARED $<TARGET_OBJECTS:imgui_java_objs>)
set_target_properties(imgui-java64 PROPERTIES OUTPUT_NAME "imgui-java64")

# Set output location
set_target_properties(imgui-java64 PROPERTIES OUTPUT_NAME ${libName} RUNTIME_OUTPUT_DIRECTORY ${OUT_DIR} LIBRARY_OUTPUT_DIRECTORY ${OUT_DIR})

if (freetype)
    set(FREETYPE_VENDOR_DIR "${CMAKE_SOURCE_DIR}/misc/freetype")

    if (NOT EXISTS "${FREETYPE_VENDOR_DIR}")
        message(FATAL_ERROR "${FREETYPE_VENDOR_DIR} doesn't exist! Run 'buildSrc/scripts/vendor_freetype.sh' for your platform beforehand!")
    endif()

    include_directories("${FREETYPE_VENDOR_DIR}")
    include_directories("${FREETYPE_VENDOR_DIR}/include")
    target_link_directories(imgui-java64 PRIVATE "${FREETYPE_VENDOR_DIR}/lib")
    target_link_libraries(imgui-java64 PRIVATE freetype)

    message("enabled freetype uwu ${FREETYPE_VENDOR_DIR}")
endif()

# Strip debug symbols in Release mode
#if(CMAKE_BUILD_TYPE STREQUAL "Release")
#    add_custom_command(TARGET imgui-java64 POST_BUILD COMMAND strip --strip-unneeded "$<TARGET_FILE:imgui-java64>")
#endif()

# Build with Ninja
set(CMAKE_GENERATOR "Ninja" CACHE STRING "Generator to use" FORCE)
