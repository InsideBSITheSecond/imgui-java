#!/bin/bash

echo "[BUILD] Setting base directory and navigating to project root..."
BASEDIR=$(dirname "$0")
cd "$BASEDIR"/../.. || exit 1
echo "[BUILD] Navigated to $(pwd)"

# Check if vendor type argument is provided
if [ -z "$1" ]; then
    echo "[BUILD] Vendor type is required"
    exit 1
fi

VTYPE=$1
echo "[BUILD] Vendor type set to '$VTYPE'"

# Make the vendor FreeType script executable and run it
echo "[BUILD] Making vendor FreeType script executable and running it..."
chmod +x buildSrc/scripts/vendor_freetype.sh
buildSrc/scripts/vendor_freetype.sh "$VTYPE"
if [ $? -ne 0 ]; then
    echo "[BUILD] Vendor FreeType script failed"
    exit 1
fi
echo "[BUILD] Vendor FreeType script completed successfully"

echo "[BUILD] Copying freetype in build directory"
mkdir -p /tmp/imgui/jni/misc/freetype
echo directory: $(pwd)
cp -r build/vendor/freetype/include /tmp/imgui/jni/misc/freetype/include/

# Create the destination directory for imgui libraries
echo "[BUILD] Creating destination directory for imgui libraries..."
mkdir -p /tmp/imgui/dst
echo "[BUILD] Directory /tmp/imgui/dst created successfully"

# Function to check if a file exists
check_file_exists() {
    if [ ! -f "$1" ]; then
        echo "[BUILD] File $1 not found!"
        exit 1
    fi
}

# Determine build process based on vendor type
case "$VTYPE" in
    windows)
        echo "[BUILD] Running Gradle task for Windows..."
        ./gradlew imgui-binding:generateLibs -Denvs=windows -Dfreetype=true
        if [ $? -ne 0 ]; then
            echo "[BUILD] Gradle task for Windows failed"
            exit 1
        fi

        echo "[BUILD] Checking if the generated DLL exists..."
        check_file_exists /tmp/imgui/libsNative/windows64/imgui-java64.dll

        echo "[BUILD] Copying the generated DLL to the destination directory..."
        cp /tmp/imgui/libsNative/windows64/imgui-java64.dll /tmp/imgui/dst/imgui-java64.dll
        if [ $? -ne 0 ]; then
            echo "[BUILD] Failed to copy DLL to /tmp/imgui/dst/imgui-java64.dll"
            exit 1
        fi
        echo "[BUILD] DLL copied to /tmp/imgui/dst/imgui-java64.dll successfully"
        ;;
    linux)
        echo "[BUILD] Running Gradle task for Linux..."
        echo "[BUILD] pwd: $(pwd)"
        ./gradlew imgui-binding:generateLibs -Denvs=linux -Dfreetype=true
        if [ $? -ne 0 ]; then
            echo "[BUILD] Gradle task for Linux failed"
            exit 1
        fi

        echo "[BUILD] Checking if the generated SO file exists..."
        check_file_exists /tmp/imgui/libsNative/linux64/libimgui-java64.so

        echo "[BUILD] Copying the generated SO file to the destination directory..."
        cp /tmp/imgui/libsNative/linux64/libimgui-java64.so /tmp/imgui/dst/libimgui-java64.so
        if [ $? -ne 0 ]; then
            echo "[BUILD] Failed to copy SO file to /tmp/imgui/dst/libimgui-java64.so"
            exit 1
        fi
        echo "[BUILD] SO file copied to /tmp/imgui/dst/libimgui-java64.so successfully"
        ;;
    macos)
        echo "[BUILD] Running Gradle task for macOS and macOS ARM..."
        ./gradlew imgui-binding:generateLibs -Denvs=macos,macosarm64 -Dfreetype=true
        if [ $? -ne 0 ]; then
            echo "[BUILD] Gradle task for macOS failed"
            exit 1
        fi

        echo "[BUILD] Checking if the generated DYLIB files exist..."
        check_file_exists /tmp/imgui/libsNative/macosx64/libimgui-java64.dylib
        check_file_exists /tmp/imgui/libsNative/macosxarm64/libimgui-java64.dylib

        echo "[BUILD] Creating a universal library using lipo..."
        lipo -create -output /tmp/imgui/dst/libimgui-java64.dylib /tmp/imgui/libsNative/macosx64/libimgui-java64.dylib /tmp/imgui/libsNative/macosxarm64/libimgui-java64.dylib
        if [ $? -ne 0 ]; then
            echo "[BUILD] Failed to create universal library with lipo"
            exit 1
        fi
        echo "[BUILD] Universal library created at /tmp/imgui/dst/libimgui-java64.dylib successfully"
        ;;
    *)
        echo "[BUILD] Unknown vendor type: $VTYPE"
        exit 1
        ;;
esac

echo "[BUILD] Script completed successfully."
