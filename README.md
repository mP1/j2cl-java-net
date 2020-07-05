[![Build Status](https://travis-ci.com/mP1/j2cl-java-net.svg?branch=master)](https://travis-ci.com/mP1/j2cl-java-net.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-net/badge.svg?branch=master)](https://coveralls.io/github/mP1/j2cl-java-net?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-net.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-net/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-net.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-net/alerts/)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



# java.net for j2cl

A partial emulation of some of the classes in the java.net package.

- MalformedURLException
- URI
- URL
- URLDecoder
- URLEncoder

## walkingkooka.j2cl.java.net

- The package `walkingkooka.j2cl.java.net` is shaded to `java.net`.



### URL

URL should only be used to parse String urls. All network methods have been removed.



#### Methods

- URLStreamHandler ctor methods are absent
- `getContent` absent.
- `openConnection` absent.
- `openStream` absent.
- `setURLStreamHandlerFactory` absent.



## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/j2cl-java-net.git
```
