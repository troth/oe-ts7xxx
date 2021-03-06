require linux.inc

PR = "r4"

S = "${WORKDIR}/linux-2.6.29"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_boc01 = "1"
DEFAULT_PREFERENCE_canyonlands = "1"
DEFAULT_PREFERENCE_tosa = "1"
DEFAULT_PREFERENCE_vortex86sx = "1"
DEFAULT_PREFERENCE_atngw100 = "1"
DEFAULT_PREFERENCE_micro2440 = "1"
DEFAULT_PREFERENCE_ep93xx = "1"
DEFAULT_PREFERENCE_cm-x270 = "1"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-2.6.29.tar.bz2 \
           file://defconfig"

SRC_URI_append_boc01 = "\
	file://boc01.dts \
	file://001-090114-sqn11x0-usb-hack.patch;patch=1 \
	file://004-081205-usb.patch;patch=1 \
	file://005-090226-isl12024.patch;patch=1 \
	file://007-081217-lm73.patch;patch=1 \
	file://008-081208-spi.patch;patch=1 \
	file://011-090115-gpio.patch;patch=1 \
	file://012-090219-capsense.patch;patch=1 \
	file://013-090306-lcd.patch;patch=1 \
	"

SRC_URI_append_canyonlands = " \
        file://0001-powerpc-4xx-Add-PPC4xx-PCIe-MSI-support.patch;patch=1 \
        "

SRC_URI_append_micro2440 = " \
	file://0001-S3C-Backported-the-s3c2410-touchscreen-from-openmok.patch;patch=1 \
	file://0002-S3C-Backported-openmoko-s-touchscreen-filters.patch;patch=1 \
	file://0003-VENDOR-armworks-logo.patch;patch=1 \
	file://0004-920T-Use-specific-920t-mtune.patch;patch=1 \
	file://0005-920T-Temp-fix-for-the-40-relocation-binutils-pro.patch;patch=1 \
	file://0006-S3C-Allow-the-machine-code-to-get-the-BBT-table-fro.patch;patch=1 \
	file://0007-MINI2440-Add-machine-support.patch;patch=1 \
	file://0008-MINI2440-Delays-command-check-response-on-SD.patch;patch=1 \
	file://0009-MINI2440-Rename-the-SoC-tty-names.patch;patch=1 \
	file://0010-MINI2440-creates-a-mini2440_defconfig-file.patch;patch=1 \
	file://0011-MINI2440-Add-touchscreen-support.patch;patch=1 \
	file://0012-GRO-Disable-GRO-on-legacy-netif_rx-path.patch;patch=1 \
	"

SRC_URI_append_tosa = " \
        file://0001-pxa-make-second-argument-of-clk_add_alias-a-name-in.patch;patch=1 \
        file://0002-spi-pxa2xx-spi-set-default-cs_control-to-null_cs_co.patch;patch=1 \
        "

SRC_URI_append_ep93xx = " \
        file://add-edb9301.patch;patch=1 \
	file://edb9301-fix-machine-id.patch;patch=1 \
	"

SRC_URI_append_cm-x270 = "\
	file://0001-xm_x2xx-config-fix-up-CMDLINE.patch;patch=1 \
	file://0002-cm-x270-nand-change-name-of-device.patch;patch=1 \
	file://0003-cm-x2xx.c-add-support-for-sharp-VGA-display-panel.patch;patch=1 \
	"	

CMDLINE_cm-x270 = "console=${CMX270_CONSOLE_SERIAL_PORT},38400 monitor=8 bpp=16 mem=64M mtdparts=physmap-flash.0:256k(boot)ro,0x180000(kernel),0x230000(root),-(config);cm-x270-nand:64m(app),-(data) rdinit=/sbin/init root=mtd4 rootfstype=jffs2"

