require gdb-cross.inc

inherit cross

PR = "r3"

SRC_URI += "file://sim-install-makefile.patch;patch=1 \
	file://sim-install-makefile-common.patch;patch=1"
