SECTION = "console/network"
DEPENDS = "virtual/db libpcre postfix-native"
LICENSE = "IPL"

SRC_URI = "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
	   file://${FILESDIR}/makedefs.patch;patch=1 \
	   file://${FILESDIR}/install.patch;patch=1 \
	   file://main.cf_2.0 \
	   file://volatiles \
	   file://postfix \
	   file://internal_recipient \
	   "

S = "${WORKDIR}/postfix-${PV}"

inherit update-rc.d

INITSCRIPT_NAME = "postfix"
INITSCRIPT_PARAMS = "start 58 3 4 5 . stop 13 0 1 6 ."

#FIXME: this is broken because the native build won't work on systems where
# native bdb does not require libpthread.  ARM doesn't require libpthread
# because it uses an assembler mutex implementation.
LIBBDB_EXTRA = "-lpthread"
LIBBDB_EXTRA_arm = ""
LIBBDB_EXTRA_armeb = ""

export SYSLIBS = "-lpcre -ldb ${LIBBDB_EXTRA} -lnsl -lresolv ${LDFLAGS}"
export EXPORT = "AUXLIBS='-lpcre' CCARGS='-DHAS_PCRE ${CFLAGS}' OPT='' DEBUG='-g'"
export CC_append = " -DHAS_PCRE ${CFLAGS}"
export EXTRA_OEMAKE = "-e"
export POSTCONF = "${STAGING_BINDIR_NATIVE}/postconf"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS
	oe_runmake makefiles
	oe_runmake
}

do_install () {
	sh ./postfix-install 'install_root=${D}' -non-interactive
	rm -rf ${D}${localstatedir}/spool/postfix
        mv ${D}${sysconfdir}/postfix/main.cf ${D}${sysconfdir}/postfix/sample-main.cf
	install -d ${D}${localstatedir}/tmp
	install -d ${D}${sysconfdir}/default/volatiles
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/main.cf_2.0 ${D}${localstatedir}/tmp/main_cf.sh
        install -m 644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/01_postfix
        install -m 755 ${WORKDIR}/postfix ${D}${sysconfdir}/init.d/postfix
        install -m 644 ${WORKDIR}/internal_recipient ${D}${sysconfdir}/postfix/internal_recipient
	mv ${D}${sbindir}/sendmail ${D}${sbindir}/sendmail.${PN}
}

pkg_postinst () {
	update-alternatives --install ${sbindir}/sendmail sendmail sendmail.${PN} 40
        grep postfix /etc/group || addgroup postfix
        grep postdrop /etc/group || addgroup postdrop
        grep vmail /etc/group || addgroup vmail
        grep postfix /etc/passwd || adduser --disabled-password --home=/var/spool/postfix --ingroup postfix -g "Postfix" postfix
        grep vmail /etc/passwd || adduser --disabled-password --home=/var/spool/vmail --ingroup vmail -g "Postfix" vmail
	chgrp postdrop /usr/sbin/postqueue
	chgrp postdrop /usr/sbin/postdrop
	chmod g+s /usr/sbin/postqueue
	chmod g+s /usr/sbin/postdrop
	/var/tmp/main_cf.sh >/etc/postfix/main.cf
	rm -f /var/tmp/main_cf.sh
	chmod 644 /etc/postfix/main.cf
	[ -d /var/spool/postfix ] && rmdir /var/spool/postfix
	/etc/init.d/populate-volatile.sh update
	touch /etc/aliases
	newaliases
}

pkg_postrm () {
	update-alternatives --remove sendmail sendmail.${PN}
}

# Exclude .debug directories from the main package
FILES_${PN} = "${sysconfdir} ${localstatedir} ${bindir}/* ${sbindir}/* \
               ${libexecdir}/postfix/*"
FILES_${PN}-dbg += "${libexecdir}/postfix/.debug"
