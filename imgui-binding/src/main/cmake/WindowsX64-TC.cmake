# Define the system type
set(CMAKE_SYSTEM_NAME Windows)
set(CMAKE_SYSTEM_PROCESSOR X86_64)

# Specify the compilers
set(CMAKE_C_COMPILER /usr/bin/x86_64-w64-mingw32-gcc)
set(CMAKE_CXX_COMPILER /usr/bin/x86_64-w64-mingw32-g++)
set(CMAKE_RC_COMPILER /usr/bin/x86_64-w64-mingw32-windres)

#fake push

# Define sysroot (optional)
#set(CMAKE_SYSROOT /path/to/sysroot)

# Specify search paths
set(CMAKE_FIND_ROOT_PATH /usr/x86_64-w64-mingw32)

# Tell CMake to prefer the sysroot paths for libraries and includes
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)
