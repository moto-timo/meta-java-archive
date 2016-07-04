SUMMARY = "Tree-based API for processing XML with Java"
DESCRIPTION = "Tree-based API for processing XML with Java"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3f18f166096348ef9d457214a2484816"

HOMEPAGE = "http://xom.nu"

SRCNAME_jaxen = "jaxen"
SRCREV_jaxen = "7d7755ac8b19daa2ff6f319f432b864cc72d89b6"
PV_jaxen = "1.1.6"

SRC_URI = "\
	http://www.cafeconleche.org/XOM/${BPN}-${PV}-src.tar.gz;name=archive \
	git://github.com/codehaus/${SRCNAME_jaxen};name=jaxen \
        file://0001-remove-sun-import.patch \
	"

S = "${WORKDIR}/XOM"

inherit java-library

DEPENDS = "fastjar-native xerces-j xalan-j"

do_compile() {
    mkdir -p build

    oe_makeclasspath cp -s xercesImpl xalan2
    cp=build:$cp

    mv ${WORKDIR}/git/jaxen ${WORKDIR}/jaxen-${PV_jaxen}
    rm -rf ${WORKDIR}/git
    scp="${WORKDIR}/jaxen-${PV_jaxen}/src/java/main"

    javac -sourcepath src:$scp -cp $cp -d build `find src -name "*.java" -and -not \( -wholename "*tests*" -or -wholename "*samples*" -or -wholename "*tools*" \)`
    javac -sourcepath fatsrc:$scp -cp $cp -d build `find fatsrc -name "*.java" -and -not \( -wholename "*tests*" -or -wholename "*samples*" -or -wholename "*tools*" \)`
    javac -sourcepath src15:$scp -cp $cp -d build `find src15 -name "*.java" -and -not \( -wholename "*tests*" -or -wholename "*samples*" -or -wholename "*tools*" \)`

    (cd src && find . -name "*.properties" -exec cp {} ../build/{} \;)

    # Remove Jaxen classes from build
    rm -rf build/org/jaxen
    rm -rf build/org/w3c

    fastjar -C build -c -f ${JARFILENAME} .
}

SRC_URI[archive.md5sum] = "9f3a2ae827a9f6826fe76e4b7b0c22b3"
SRC_URI[archive.sha256sum] = "39985c2debb8044005fd07b09cb06f10e75652b3c048c9b4431434215332bc85"
