DESCRIPTION = "Open Babel is a chemical toolbox designed to speak the many languages of chemical data."
LICENSE = "GPLv2"
DEPENDS = "libxml2 boost"

SRC_URI = "${SOURCEFORGE_MIRROR}/openbabel/openbabel-${PV}.tar.gz"

inherit autotools

EXTRA_OECONF = "  --disable-xmltest  "

do_stage() {
	autotools_stage_all
}

FILES_${PN}-dbg += "${libdir}/openbabel/*/.debug"
FILES_${PN}-dev += "${libdir}/openbabel/*/*.la"


