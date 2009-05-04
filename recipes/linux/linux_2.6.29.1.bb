require linux.inc

PR = "r0"

S = "${WORKDIR}/linux-2.6.29.1"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_ts7390 = "1"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-2.6.29.1.tar.bz2 \
           file://defconfig"

SRC_URI_append_ts7390 = " \
        file://0001-ep93xx_syscon_devcfg.patch;patch=1 \
        file://0002-ep93xx_uart_clk.patch;patch=1 \
        file://0003-ts72xx_base.patch;patch=1 \
        file://0004-ts72xx_force_machine-id.patch;patch=1 \
        file://0005-ts7200_physmap_fix.patch;patch=1 \
        file://0006-ep93xx_cpuinfo.patch;patch=1 \
        file://0007-ts72xx_sbcinfo.patch;patch=1 \
        file://0008-ep93xx_gpio_leds.patch;patch=1 \
        file://0009-ep93xx_eth.patch;patch=1 \
        file://0010-ts72xx_wdt.patch;patch=1 \
        file://0011-ts7200_nor_flash.patch;patch=1 \
        file://0012-ts72xx_max197.patch;patch=1 \
        file://0013-ep93xx_gpio_i2c.patch;patch=1 \
        file://0014-ts72xx_console.patch;patch=1 \
        file://0015-ts72xx_pata.patch;patch=1 \
        file://0016-ts72xx_ts_ser1.patch;patch=1 \
        file://0017-ts72xx_dio_keypad.patch;patch=1 \
        file://0018-ep93xx_spi.patch;patch=1 \
        file://0019-ep93xx_cpufreq.patch;patch=1 \
        file://0020-ts72xx_ts_eth100.patch;patch=1 \
        file://0021-ep93xx_pm.patch;patch=1 \
        file://0022-ts7200_cf_ide.patch;patch=1 \
        file://0023-ts7250_nand.patch;patch=1 \
        file://leds_netdev_trigger.patch;patch=1 \
	"
