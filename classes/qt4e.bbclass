DEPENDS_prepend = "${@["qt4-embedded ", ""][(bb.data.getVar('PN', d, 1) == 'qt4-embedded')]}"
inherit qmake2

QT_DIR_NAME = "qtopia"
# override variables set by qmake-base to compile Qt/Embedded apps
#
export QMAKESPEC = "${STAGING_DATADIR}/qtopia/mkspecs/${TARGET_OS}-oe-g++"
export OE_QMAKE_INCDIR_QT = "${STAGING_INCDIR}/qtopia"
export OE_QMAKE_LIBDIR_QT = "${STAGING_LIBDIR}"
export OE_QMAKE_LIBS_QT = "qt"
export OE_QMAKE_LIBS_X11 = ""
export OE_QMAKE_EXTRA_MODULES = "network"
EXTRA_QMAKEVARS_PRE += " QT_LIBINFIX=E "

# Qt4 uses atomic instructions not supported in thumb mode
ARM_INSTRUCTION_SET = "arm"
