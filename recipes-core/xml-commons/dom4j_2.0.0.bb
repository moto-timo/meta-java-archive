DESCRIPTION = "dom4j is a simple and flexible Java library for working with XML, XPath and XSLT"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b29f89c253cdecf9ca3ef8ef55c12580"

HOMEPAGE = "http://dom4j.org"

DEPENDS = "fastjar-native xerces-j xalan-j xpp2 xpp3 jaxen"

SRCREV = "a4d39926ff08656e4cf86c37f3246029c4e9122b"

SRC_URI = "\
        git://github.com/dom4j/${BPN} \
	http://apache.org/dist/ws/jaxme/source/ws-jaxme-0.5.2-src.tar.gz;name=jaxme \
	"
S = "${WORKDIR}/git"

inherit java-library

do_compile() {
  mkdir -p build

  oe_makeclasspath cp -s xercesImpl xalan2 xpp2 xpp3 jaxen
	scp="src/main/java:${WORKDIR}/ws-jaxme-0.5.2/src/api"

  javac -version
  javac -help
  javac -1.7 -sourcepath $scp -cp $cp -d build `find src/main/java -name "*.java" -and -not -wholename "*datatype*"`
  (cd src/main/java && find org -name "*.properties" -exec cp {} ../build/{} \;)

	rm -rf build/org/w3c
	rm -rf build/javax

  fastjar -C build -c -f ${JARFILENAME} .
}


SRC_URI[jaxme.md5sum] = "084ebfe4a816058f8ff6bd731fa70df4"
SRC_URI[jaxme.sha256sum] = "0415d721259acf95c564fb84606bb17f6227c1cc444e89b78d1cd9903c1c88dc"
