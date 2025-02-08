cmake_minimum_required(VERSION 3.16)
project(imgui-java-Linux-64 LANGUAGES C CXX)

option(freetype "Enable FreeType support" false) #TODO check freetype shenanigans (check options)
option(local "Local build" false) #TODO check freetype shenanigans (check options)

message("ROOT DIRECTORY: ${CMAKE_SOURCE_DIR}")
message("FREETYPE: ${freetype}")
message("LOCAL: ${local}")

# Set output directories
set(LIBS_DIR "${CMAKE_SOURCE_DIR}/../libsNative/linux64")
set(LIB_NAME "libimgui-java64.so")

# Set compiler options
set(C_CXX_FLAGS -Wall -O2 -mfpmath=sse -msse -fmessage-length=0 -m64 -fPIC)
set(C_FLAGS "${C_CXX_FLAGS}")
set(CXX_FLAGS "${C_CXX_FLAGS}" -DIMGUI_ENABLE_TEST_ENGINE)
set(LINKER_FLAGS -shared -m64 -Wl,-wrap,memcpy)

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
target_compile_options(imgui_java_objs PRIVATE "$<$<COMPILE_LANGUAGE:C>:${C_FLAGS}>" "$<$<COMPILE_LANGUAGE:CXX>:${CXX_FLAGS}>")

# Create shared library
add_library(imgui-java64 SHARED $<TARGET_OBJECTS:imgui_java_objs>)
set_target_properties(imgui-java64 PROPERTIES OUTPUT_NAME "imgui-java64")
target_link_options(imgui-java64 PRIVATE ${LINKER_FLAGS})

# Set output location
set_target_properties(imgui-java64 PROPERTIES RUNTIME_OUTPUT_DIRECTORY ${LIBS_DIR} LIBRARY_OUTPUT_DIRECTORY ${LIBS_DIR})

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
if(CMAKE_BUILD_TYPE STREQUAL "Release")
    add_custom_command(TARGET imgui-java64 POST_BUILD COMMAND strip --strip-unneeded "$<TARGET_FILE:imgui-java64>")
endif()

# Build with Ninja
set(CMAKE_GENERATOR "Ninja" CACHE STRING "Generator to use" FORCE)
