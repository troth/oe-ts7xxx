DESCRIPTION = "LXDE Panel"
SECTION = "x11"
DEPENDS = ""

SRC_URI = "${SOURCEFORGE_MIRROR}/lxde/${PN}-${PV}.tar.gz"

inherit autotools

FILES_${PN} += "${datadir}/desktop-directories/"
