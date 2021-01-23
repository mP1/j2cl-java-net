[![Build Status](https://travis-ci.com/mP1/j2cl-java-net.svg?branch=master)](https://travis-ci.com/mP1/j2cl-java-net.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-net/badge.svg?branch=master)](https://coveralls.io/github/mP1/j2cl-java-net?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-net.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-net/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-net.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-net/alerts/)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



# java.net for j2cl

An emulation of some of the classes in the java.net package. More may be added in the future, while many such as `java.net.SocketServer`
will never make any sense within a browser.

Source code were copied from the [Apache Harmony)(https://github.com/apache/harmony) project with numerous patches made
to ensure the behaviour of both the JRE and the emulated class are identical. The `java.net.URL` required some [patches](https://github.com/mP1/j2cl-java-net/blob/master/src/main/java/walkingkooka/j2cl/java/net/URL.java)
to handle several edge cases, such as handling of missing components, such as an empty host. [Tests](https://github.com/mP1/j2cl-java-net/blob/master/src/test/java/walkingkooka/j2cl/java/net/URLTest.java)
are provided to verify both do the same thing with the same parameters.

- MalformedURLException
- URI
- URL
- URLDecoder
- URLEncoder

The `java.net.URLStreamHandler` is absent as one of its primary functionalities is to handle connectivity to a resource
on a network. 



## walkingkooka.j2cl.java.net

- The package `walkingkooka.j2cl.java.net` is shaded to `java.net`.



### URL

URL should only be used to parse `java.lang.String`. All network connectivity and interaction methods have been removed.
Customization depending on the protocol portion of a URL via a  `java.net.URLStreamHandler` is not possible and all methods
with it have been removed.



#### Methods

- `java.net.URLStreamHandler` ctor methods are absent
- `getContent` absent.
- `openConnection` absent.
- `openStream` absent.
- `setURLStreamHandlerFactory` absent.



