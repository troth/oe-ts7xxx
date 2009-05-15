SRC_URI = "http://download.berlios.de/tslib/${BP}.tar.bz2 \
           file://fix_version.patch;patch=1 \
           file://tslib-1.0-symbol-resolve.patch;patch=1"
PR = "r20"

include tslib.inc
