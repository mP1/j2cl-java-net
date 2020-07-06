/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.j2cl.java.net;

import org.junit.jupiter.api.Test;
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class URLTest implements ClassTesting<URL>, HashCodeEqualsDefinedTesting2<URL> {

    // new URL(String)..................................................................................................

    @Test
    public void testMissingProtocol() throws Exception{
        this.newUrlAndCheck("example.com/path");
    }

    @Test
    public void testEmptyProtocol() throws Exception{
        this.newUrlAndCheck("://example.com/path");
    }

    @Test
    public void testEmptyHost() throws Exception{
        this.newUrlAndCheck("http:///path");
    }

    @Test
    public void testEmptyHostPort() throws Exception{
        this.newUrlAndCheck("http://:999/path");
    }

    @Test
    public void testInvalidPort() throws Exception{
        this.newUrlAndCheck("http://example.com:-1/path");
    }

    @Test
    public void testInvalidPort2() throws Exception{
        this.newUrlAndCheck("http://example.com:99999/path");
    }

    @Test
    public void testMixedCaseProtocolHostPortEmptyPath() throws Exception{
        this.newUrlAndCheck("HTtp://example.com:99");
    }

    @Test
    public void testProtocolHostPortEmptyPath() throws Exception{
        this.newUrlAndCheck("http://example.com:99");
    }

    @Test
    public void testProtocolHostPortEmptyPath2() throws Exception{
        this.newUrlAndCheck("http://example.com:88/");
    }

    @Test
    public void testProtocolHostEmptyPath() throws Exception{
        this.newUrlAndCheck("http://example.com/");
    }

    @Test
    public void testProtocolHostEmptyQuery() throws Exception{
        this.newUrlAndCheck("http://example.com?");
    }

    @Test
    public void testProtocolHostQuery1() throws Exception{
        this.newUrlAndCheck("http://example.com?Q");
    }

    @Test
    public void testProtocolHostQuery2() throws Exception{
        this.newUrlAndCheck("http://example.com?abc=def");
    }

    @Test
    public void testProtocolHostQuery3() throws Exception{
        this.newUrlAndCheck("http://example.com?abc=def&ghi=jkl");
    }

    @Test
    public void testProtocolHostPortQuery() throws Exception{
        this.newUrlAndCheck("http://example.com:123?abc=def");
    }

    @Test
    public void testProtocolHostEmptyFragment() throws Exception{
        this.newUrlAndCheck("http://example.com#");
    }

    @Test
    public void testProtocolHostFragment() throws Exception{
        this.newUrlAndCheck("http://example.com#fragment123");
    }

    @Test
    public void testProtocolHostQueryFragment() throws Exception{
        this.newUrlAndCheck("http://example.com?q1=r2#fragment123");
    }

    @Test
    public void testProtocolHostPathQueryFragment() throws Exception{
        this.newUrlAndCheck("http://example.co/path123m?q1=r2#fragment123");
    }

    private void newUrlAndCheck(final String url) throws Exception {
        java.net.URL jre;
        try {
            jre = new java.net.URL(url);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URL(url);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if(null != jre) {
            this.check(jre, new URL(url));
        }
    }

    // new URL(String protocol, String host, String file.................................................................

    @Test
    public void testNullPrototolHostFile() throws Exception {
        this.newProtocolHostFileAndCheck(null, "example1.com", "/file2");
    }

    @Test
    public void testEmptyPrototolHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("", "example1.com", "/file2");
    }

    @Test
    public void testPrototolNullHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("http", null, "/file2");
    }

    @Test
    public void testPrototolEmptyHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("http", "", "/file2");
    }

    @Test
    public void testPrototolHostNullFile() throws Exception {
        this.newProtocolHostFileAndCheck("http", null, null);
    }

    @Test
    public void testProtocolHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("http", "example1.com", "/file2");
    }

    @Test
    public void testCapitalProtocolHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("HTTP", "example1.com", "/file2");
    }

    @Test
    public void testMixedCaseProtocolHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("HTtp", "example1.com", "/file2");
    }

    @Test
    public void testHttpsHostFile() throws Exception {
        this.newProtocolHostFileAndCheck("https", "example3.com", "/file4");
    }

    @Test
    public void testHttpsHostFile2() throws Exception {
        this.newProtocolHostFileAndCheck("https", "example5.com", "/file6");
    }

    private void newProtocolHostFileAndCheck(final String protocol,
                                             final String host,
                                             final String file) throws Exception {
        java.net.URL jre;
        try {
            jre = new java.net.URL(protocol, host, file);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URL(protocol, host, file);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if(null != jre) {
            this.check(jre, new URL(protocol, host, file));
        }
    }
    // new URL(String protocol, String host, int port, String file.......................................................

    @Test
    public void testNullProtocolHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck(null, "example1.com", 80, "/file2");
    }

    @Test
    public void testEmptyProtocolHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("", "example1.com", 80, "/file2");
    }

    @Test
    public void testProtocolNullHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", null, 80, "/file2");
    }

    @Test
    public void testProtocolNullHostNonStandardPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", null, 987, "/file2");
    }

    @Test
    public void testProtocolEmptyHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "", 80, "/file2");
    }

    @Test
    public void testProtocolEmptyHostNonStandardPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "", 987, "/file2");
    }

    @Test
    public void testProtocolHostInvalidPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "example.com", -1, "/file2");
    }

    @Test
    public void testProtocolHostInvalidPortFile2() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "example.com", 65536, "/file2");
    }

    @Test
    public void testProtocolHostPortNullFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "example.com", 80, null);
    }

    @Test
    public void testHttpHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("http", "example1.com", 80, "/file2");
    }

    @Test
    public void testHttpsHostPortFile() throws Exception {
        this.newProtocolHostPortFileAndCheck("https", "example3.com", 90, "/file4");
    }

    @Test
    public void testHttpsHostPortFile2() throws Exception {
        this.newProtocolHostPortFileAndCheck("https", "example5.com", 100, "/file6");
    }

    private void newProtocolHostPortFileAndCheck(final String protocol,
                                                 final String host,
                                                 final int port,
                                                 final String file) throws Exception {
        java.net.URL jre;
        try {
            jre = new java.net.URL(protocol, host, port, file);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URL(protocol, host, port, file);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if(null != jre) {
            this.check(jre, new URL(protocol, host, port, file));
        }
    }

    // new URL(URL, String).............................................................................................

    @Test
    public void testContextRelativeUrl() throws Exception{
        this.newContextUrlAndCheck("http://context", "/path123/path456");
    }

    @Test
    public void testContextRelativeUrlQuery() throws Exception{
        this.newContextUrlAndCheck("http://context", "/path?abc=def");
    }

    @Test
    public void testContextHostPortQuery() throws Exception{
        this.newContextUrlAndCheck("http://context", "http://example.com:123?abc=def");
    }

    private void newContextUrlAndCheck(final String context,
                                       final String url) throws Exception {
        java.net.URL jre;
        try {
            jre = new java.net.URL(new java.net.URL(context), url);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URL(new URL(context), url);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if(null != jre) {
            this.check(jre, new URL(new URL(context), url));
        }
    }

    // .................................................................................................................

    private void check(final java.net.URL jre,
                       final URL emul) throws Exception {
        //assertEquals(jre.getDefaultPort(), emul.getDefaultPort(), () -> "default port " + jre);

        assertEquals(jre.getProtocol(), emul.getProtocol(), () -> "protocol " + jre);

        assertEquals(jre.getUserInfo(), emul.getUserInfo(), () -> "userInfo " + jre);
        assertEquals(jre.getHost(), emul.getHost(), () -> "host " + jre);
        assertEquals(jre.getPort(), emul.getPort(), () -> "port " + jre);

        assertEquals(jre.getPath(), emul.getPath(), () -> "path " + jre);
        assertEquals(jre.getFile(), emul.getFile(), () -> "file " + jre);
        assertEquals(jre.getQuery(), emul.getQuery(), () -> "query " + jre);
        assertEquals(jre.getRef(), emul.getRef(), () -> "ref " + jre);

        assertEquals(jre.getAuthority(), emul.getAuthority(), () -> "authority " + jre);

        assertEquals(jre.toExternalForm(), emul.toExternalForm(), () -> "toExternalForm " + jre);

        assertEquals(jre.toURI().toString(), emul.toURI().toString(), () -> "toURI " + jre);
    }

    // equals...........................................................................................................

    @Test
    public void testSameHostDifferentCase() throws Exception {
        this.checkEquals(new URL("http://HOST123/path45/path67"));
    }

    @Test
    public void testDifferentHost() throws Exception {
        this.checkNotEquals(new URL("http://DIFFERENTHOST/path45/path67"));
    }

    // sameFile...........................................................................................................

    @Test
    public void testSameFileSameUrl() throws Exception {
        final String url = "http://HOST123/path45/path67";
        this.checkEquals(url, url);
    }

    @Test
    public void testSameFileDifferentHost() throws Exception {
        this.checkNotEquals("http://HOST123/path45/path67", "http://DIFFERENTHOST/path45/path67");
    }

    private void sameFileAndCheck(final String url,
                                  final String other) throws Exception {
        assertEquals(new java.net.URL(url).sameFile(new java.net.URL(other)),
                new URL(url).sameFile(new URL(other)));
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<URL> type() {
        return URL.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }

    // HashCodeEqualityTesting..........................................................................................

    @Override
    public URL createObject() {
        try {
            return new URL("http://host123/path45/path67");
        } catch (final MalformedURLException cause) {
            throw new Error(cause.getMessage());
        }
    }
}
