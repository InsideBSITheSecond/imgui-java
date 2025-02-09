#!/bin/bash

# Set base directory and navigate to project root
echo "[VFT] Setting base directory and navigating to project root..."
BASEDIR=$(dirname "$0")
cd "$BASEDIR"/../.. || exit 1
echo "[VFT] Navigated to $(pwd)"

rm -rf /tmp/imgui

# Check if vendor type argument is provided
if [ -z "$1" ]; then
    echo "[VFT] Vendor type is required"
    exit 1
fi

VTYPE=$1
echo "[VFT] Vendor type set to '$VTYPE'"

# Define library directory and version
LIBDIR=build/vendor/freetype
VERSION=2.13.3

# Clean and create library directory, then extract FreeType source
echo "[VFT] Cleaning and creating library directory, then extracting FreeType source..."
rm -rf $LIBDIR
mkdir -p $LIBDIR
tar -xzf ./vendor/freetype-$VERSION.tar.gz -C $LIBDIR --strip-components=1
if [ $? -ne 0 ]; then
    echo "[VFT] Failed to extract FreeType source"
    exit 1
fi
cd $LIBDIR || exit 1
echo "[VFT] FreeType unzipped to $LIBDIR"

# Common configuration flags
COMMON_FLAGS="--enable-static --disable-shared --without-zlib --without-bzip2 --without-png --without-harfbuzz --without-brotli"

# Function to configure and build FreeType
build_freetype() {
    cflags=$1
    prefix=$2
    output_dir=$3

    echo "[VFT] Cleaning previous builds..."
    make clean

    echo "[VFT] Configuring FreeType with CFLAGS='$cflags' and PREFIX='$prefix'..."
    ./configure CFLAGS="$cflags" $COMMON_FLAGS $prefix
    if [ $? -ne 0 ]; then
        echo "[VFT] Failed to configure FreeType"
        exit 1
    fi

    echo "[VFT] Building FreeType..."
    make
    if [ $? -ne 0 ]; then
        echo "[VFT] Failed to build FreeType"
        exit 1
    fi

    echo "[VFT] Checking if the generated library exists..."
    if [ ! -f objs/.libs/libfreetype.a ]; then
        echo "[VFT] File objs/.libs/libfreetype.a not found!"
        exit 1
    fi

    echo "[VFT] Copying the generated library to $output_dir..."
    cp objs/.libs/libfreetype.a "$output_dir"
    if [ $? -ne 0 ]; then
        echo "[VFT] Failed to copy library to $output_dir"
        exit 1
    fi
    echo "[VFT] Library copied to $output_dir"
}

# Ensure necessary directories exist
echo "[VFT] Ensuring necessary directories exist..."
mkdir -p lib tmp

# Determine build process based on vendor type
case "$VTYPE" in
    windows)
        build_freetype "-DFT2_BUILD_LIBRARY" "--host=x86_64-w64-mingw32 --prefix=/usr/x86_64-w64-mingw32" "lib/libfreetype.a"
        ;;
    linux)
        build_freetype "-fPIC" "" "lib/libfreetype.a"
        ;;
    macos)
        MACOS_VERSION=10.15

        build_freetype "-arch x86_64 -mmacosx-version-min=$MACOS_VERSION" "" "tmp/libfreetype-x86_64.a"
        build_freetype "-arch arm64 -mmacosx-version-min=$MACOS_VERSION" "" "tmp/libfreetype-arm64.a"

        echo "[VFT] Creating universal library using lipo..."
        lipo -create -output lib/libfreetype.a tmp/libfreetype-x86_64.a tmp/libfreetype-arm64.a
        if [ $? -ne 0 ]; then
            echo "[VFT] Failed to create universal library with lipo"
            exit 1
        fi
        echo "[VFT] Universal library created at lib/libfreetype.a"
        ;;
    *)
        echo "[VFT] Unknown vendor type: $VTYPE"
        exit 1
        ;;
esac



echo "[VFT] Script completed successfully."
