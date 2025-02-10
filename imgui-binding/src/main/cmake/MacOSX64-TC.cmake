# Define the system type
set(CMAKE_SYSTEM_NAME Darwin)
set(CMAKE_SYSTEM_PROCESSOR x86_64)

# Specify the compilers
set(CMAKE_C_COMPILER /usr/bin/clang)
set(CMAKE_CXX_COMPILER /usr/bin/clang++)

# Define sysroot (optional)
set(CMAKE_SYSROOT ${CMAKE_OSX_SYSROOT})

# Specify search paths
set(CMAKE_FIND_ROOT_PATH /Library/Developer/CommandLineTools/SDKs ${CMAKE_SYSROOT})

# Use macOS-specific flags
set(CMAKE_C_FLAGS "-Wall -Wextra -Wpedantic")
set(CMAKE_CXX_FLAGS "-Wall -Wextra -Wpedantic -std=c++17") # Adjust C++ standard as needed

# Enable macOS frameworks
set(CMAKE_FIND_FRAMEWORK FIRST)
set(CMAKE_FIND_APPBUNDLE NEVER)

# Tell CMake to prefer the sysroot paths for libraries and includes
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)
