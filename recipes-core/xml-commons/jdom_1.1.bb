SUMMARY = "Parses, manipulates, and outputs XML using standard Java constructs"
DESCRIPTION = "Parses, manipulates, and outputs XML using standard Java constructs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=897c700e4c122d8f4ef8f6d253a6f744"

HOMEPAGE = "http://jdom.org/"

DEPENDS = "fastjar-native jaxen"

SRC_URI = "\
	http://www.jdom.org/dist/binary/archive/jdom-${PV}.tar.gz \
	"

inherit java-library

do_compile() {
  mkdir -p build

  oe_makeclasspath cp -s jaxen

  javac -sourcepath src/java -cp $cp -d build `find src/java -name "*.java"`

  fastjar -C build -c -f ${JARFILENAME} .
}


SRC_URI[md5sum] = "22745cbaaddb12884ed8ee09083d8fe2"
SRC_URI[sha256sum] = "a13549087141be24ad176b659afdc2c675f1ffa5288ff999a193d6d44a282056"
