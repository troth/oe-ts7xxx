require linux-omap.inc

SRCREV = "f5691bc8f65a08a6cb9900a5c7099a36b2b9b7a7"

PV = "2.6.25"
PR = "r3"

COMPATIBLE_MACHINE = "(davinci-dvevm|davinci-sffsdr)"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/khilman/linux-davinci.git;protocol=git \
           file://8250_davinci.patch;patch=1 \
           file://defconfig"

SRC_URI_append_davinci-sffsdr = " file://sffsdr.patch;patch=1"

S = "${WORKDIR}/git"
