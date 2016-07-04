SUMMARY = "XPath library written in Java"
DESCRIPTION = "XPath library written in Java"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a2e66c2e2482aa23c11342f1469fa3b6"

HOMEPAGE = "http://jaxen.org/"

DEPENDS = "fastjar-native xerces-j xom"

SRCREV = "7d7755ac8b19daa2ff6f319f432b864cc72d89b6"

SRC_URI = "\
        git://github.com/codehaus/${BPN} \
	http://www.jdom.org/dist/binary/archive/jdom-1.1.tar.gz;name=jdom \
	"

S = "${WORKDIR}/git/jaxen"

inherit java-library

do_compile() {
  mkdir -p build

  oe_makeclasspath cp -s xercesImpl xom
	scp="src/java/main:${WORKDIR}/jdom-1.1/src/java"

  javac -sourcepath $scp -cp $cp -d build `find src/java/main -name "*.java" -and -not -wholename "*dom4j*"`
  (cd src/java/main && find org -name "*.properties" -exec cp {} ../../../build/{} \;)

	rm -rf build/org/jdom

  fastjar -C build -c -f ${JARFILENAME} .
}


SRC_URI[jdom.md5sum] = "22745cbaaddb12884ed8ee09083d8fe2"
SRC_URI[jdom.sha256sum] = "a13549087141be24ad176b659afdc2c675f1ffa5288ff999a193d6d44a282056"