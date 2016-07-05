require jakarta-commons.inc

SUMMARY = "Java Expression Language (JEXL)"
DESCRIPTION = "JEXL is a library intended to facilitate the implementation of dynamic and scripting features in applications and frameworks written in Java."

SRC_URI = "http://archive.apache.org/dist/commons/jexl/source/${BP}-src.tar.gz"

DEPENDS += "commons-logging junit"
RDEPENDS_${PN} = "libcommons-logging-java libjunit-java"

CP = "commons-logging junit"

SRC_URI[md5sum] = "d774d1ec952faa744b2f768b611cf175"
SRC_URI[sha256sum] = "62026d0cb3edecaadfdee1cf3dfe70a27b7f7ae70a219d7cb7c0f37db57877d5"
