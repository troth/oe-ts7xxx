SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Low-level pixel manipulation library."
LICENSE = "X11"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "http://cairographics.org/releases/pixman-${PV}.tar.gz \
           file://pixman-arm.patch;patch=1 \
	  "

inherit autotools

AUTOTOOLS_STAGE_PKGCONFIG = "1"
do_stage () {
 	autotools_stage_all
}
