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
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public final class URITest implements ClassTesting<URI> {

    // URI(String str)..................................................................................................

    @Test
    public void testMissingProtocol() throws Exception {
        this.newUriAndCheck("example.com/path");
    }

    @Test
    public void testEmptyProtocol() throws Exception {
        this.newUriAndCheck("://example.com/path");
    }

    @Test
    public void testEmptyHost() throws Exception {
        this.newUriAndCheck("http:///path");
    }

    @Test
    public void testEmptyHostPort() throws Exception {
        this.newUriAndCheck("http://:999/path");
    }

    @Test
    public void testInvalidPort() throws Exception {
        this.newUriAndCheck("http://example.com:-1/path");
    }

    @Test
    public void testInvalidPort2() throws Exception {
        this.newUriAndCheck("http://example.com:99999/path");
    }

    @Test
    public void testMixedCaseProtocolHostPortEmptyPath() throws Exception {
        this.newUriAndCheck("HTtp://example.com:99");
    }

    @Test
    public void testProtocolHostPortEmptyPath() throws Exception {
        this.newUriAndCheck("http://example.com:99");
    }

    @Test
    public void testProtocolHostPortEmptyPath2() throws Exception {
        this.newUriAndCheck("http://example.com:88/");
    }

    @Test
    public void testProtocolHostEmptyPath() throws Exception {
        this.newUriAndCheck("http://example.com/");
    }

    @Test
    public void testProtocolHostEmptyQuery() throws Exception {
        this.newUriAndCheck("http://example.com?");
    }

    @Test
    public void testProtocolHostQuery1() throws Exception {
        this.newUriAndCheck("http://example.com?Q");
    }

    @Test
    public void testProtocolHostQuery2() throws Exception {
        this.newUriAndCheck("http://example.com?abc=def");
    }

    @Test
    public void testProtocolHostQuery3() throws Exception {
        this.newUriAndCheck("http://example.com?abc=def&ghi=jkl");
    }

    @Test
    public void testProtocolHostPortQuery() throws Exception {
        this.newUriAndCheck("http://example.com:123?abc=def");
    }

    @Test
    public void testProtocolHostEmptyFragment() throws Exception {
        this.newUriAndCheck("http://example.com#");
    }

    @Test
    public void testProtocolHostFragment() throws Exception {
        this.newUriAndCheck("http://example.com#fragment123");
    }

    @Test
    public void testProtocolHostQueryFragment() throws Exception {
        this.newUriAndCheck("http://example.com?q1=r2#fragment123");
    }

    @Test
    public void testProtocolHostPathQueryFragment() throws Exception {
        this.newUriAndCheck("http://example.co/path123m?q1=r2#fragment123");
    }

    private void newUriAndCheck(final String url) throws Exception {
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

        if (null != jre) {
            this.checkUrl(jre, new URL(url));
        }
    }

    // URI(String scheme, String ssp, String fragment)..................................................................

    @Test
    public void testSchemaSspNullSchemeHost() throws Exception {
        this.newSchemeSspFragmentAndCheck(null, "host1", "");
    }

    @Test
    public void testSchemaSspEmptyScheme() throws Exception {
        this.newSchemeSspFragmentAndCheck("", "host2", "");
    }

    @Test
    public void testSchemeSspHost() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host2", "");
    }

    @Test
    public void testSchemeSspHostPort() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host3:80", "");
    }

    @Test
    public void testSchemeSspHostNonStandardPort() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host3:789", "");
    }

    @Test
    public void testSchemeSspHostNullFragment() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host4", null);
    }

    @Test
    public void testSchemeSspHostFragment() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host5", "a");
    }

    @Test
    public void testSchemeSspHostFragment2() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host5", "xyz");
    }

    @Test
    public void testSchemeSspHostPath() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host6/path7", "");
    }

    @Test
    public void testSchemeSspHostPathEmptyQuery() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host8/path9?", "");
    }

    @Test
    public void testSchemeSspHostPathQuery() throws Exception {
        this.newSchemeSspFragmentAndCheck("http://", "host1/path2?q=rst", "");
    }

    private void newSchemeSspFragmentAndCheck(final String scheme,
                                              final String ssp,
                                              final String fragment) throws Exception {
        java.net.URI jre;
        try {
            jre = new java.net.URI(scheme, ssp, fragment);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URI(scheme, ssp, fragment);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if (null != jre) {
            this.check(jre, new URI(scheme, ssp, fragment));
        }
    }

    // URI(String scheme, String host, String path, String fragment)....................................................

    @Test
    public void testSchemeHostPathFragmentNullSchemeHost() throws Exception {
        this.newSchemeHostPathFragmentAndCheck(null, "host1", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentEmptyScheme() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("", "host2", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSchemeHost() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host2", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSchemeHostPort() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host3:80", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostNonStandardPort() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host3:789", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostNullFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host4", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host5", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostFragment2() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host5", "/path", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostPath() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host6", "/path7", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostPathEmptyQueryFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host8", "/path9?", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostPathQueryEmptyFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host8", "/path9?q", "");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostPathQueryFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host1", "/path2", "fragment");
    }

    @Test
    public void testSchemeHostPathFragmentSspHostPathFragment() throws Exception {
        this.newSchemeHostPathFragmentAndCheck("http://", "host1", "/path2", "fragment");
    }

    private void newSchemeHostPathFragmentAndCheck(final String scheme,
                                                   final String host,
                                                   final String path,
                                                   final String fragment) throws Exception {
        java.net.URI jre;
        try {
            jre = new java.net.URI(scheme, host, path, fragment);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URI(scheme, host, path, fragment);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if (null != jre) {
            this.check(jre, new URI(scheme, host, path, fragment));
        }
    }

    // URI(String scheme, String authority, String path, String query, String fragment).................................

    @Test
    public void testSchemeAuthorityPathQueryFragmentNullSchemeAuthority() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck(null, "host1", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentEmptyScheme() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("", "host2", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragment() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host2", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentNullAuthority() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", null, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentEmptyAuthority() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentAuthority() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host2", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentPort() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host3:80", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentNonStandardPort() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host3:789", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentNullPath() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host4", null, "", "");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentEmptyPath() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "", "", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentSlashPath() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/", "", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentPath() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path6", "", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentPath2() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path6/path7", "", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentNullQuery() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host4", "/path", null, "");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentEmptyQuery() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentQuery() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentQuery2() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "Q=Z", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentQuery3() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "Q=Z&R=S", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentNullFragment() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host4", "/path", "Q", null);
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentFragment() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeAuthorityPathQueryFragmentFragment2() throws Exception {
        this.newSchemeAuthorityPathQueryFragmentAndCheck("http:", "host5", "/path", "Q", "fragment");
    }

    private void newSchemeAuthorityPathQueryFragmentAndCheck(final String scheme,
                                                             final String authority,
                                                             final String path,
                                                             final String query,
                                                             final String fragment) throws Exception {
        java.net.URI jre;
        try {
            jre = new java.net.URI(scheme, authority, path, query, fragment);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URI(scheme, authority, path, query, fragment);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if (null != jre) {
            this.check(jre, new URI(scheme, authority, path, query, fragment));
        }
    }

    // URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment)...........

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullSchemeHostPort() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck(null, "user1:password2", "host1", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentEmptyScheme() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("", "user1:password2", "host2", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragment() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host2", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullUserInfo() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", null, "host1", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentEmptyUserInfo() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "", "host1", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentUserInfo() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host1", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullHost() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", null, 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentEmptyHost() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentInvalidPort() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host3:80", -1, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentInvalidPort2() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host3:80", 65536, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNonStandardPort() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host3:789", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullPath() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host4", 123, null, "", "");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentEmptyPath() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "", "", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentSlashPath() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/", "", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentPath() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path6", "", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentPath2() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path6/path7", "", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullQuery() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host4", 123, "/path", null, "");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentEmptyQuery() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentQuery() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentQuery2() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "Q=Z", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentQuery3() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "Q=Z&R=S", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentNullFragment() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host4", 123, "/path", "Q", null);
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentFragment() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "Q", "fragment");
    }

    @Test
    public void testSchemeUserInfoHostPortPathQueryFragmentFragment2() throws Exception {
        this.newSchemeUserInfoHostPortPathQueryFragmentAndCheck("http://", "user1:password2", "host5", 123, "/path", "Q", "fragment");
    }

    private void newSchemeUserInfoHostPortPathQueryFragmentAndCheck(final String scheme,
                                                                    final String userInfo,
                                                                    final String host,
                                                                    final int port,
                                                                    final String path,
                                                                    final String query,
                                                                    final String fragment) throws Exception {
        java.net.URI jre;
        try {
            jre = new java.net.URI(scheme, userInfo, host, port, path, query, fragment);
        } catch (final Exception thrown) {
            jre = null;

            // jre threw assert the same for emu
            try {
                new URI(scheme, userInfo, host, port, path, query, fragment);
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if (null != jre) {
            this.check(jre, new URI(scheme, userInfo, host, port, path, query, fragment));
        }
    }

    // normalize........................................................................................................

    @Test
    public void testNormalizeEmptyPath() throws Exception {
        this.normalizeAndCheck("http://host:123");
    }

    @Test
    public void testNormalizeSlashPath() throws Exception {
        this.normalizeAndCheck("http://host:123/");
    }

    @Test
    public void testNormalize() throws Exception {
        this.normalizeAndCheck("http://host:123/path");
    }

    @Test
    public void testNormalize2() throws Exception {
        this.normalizeAndCheck("http://host:123/path1/path2");
    }

    @Test
    public void testNormalizeNormalized() throws Exception {
        this.normalizeAndCheck("http://host:123/path1/path2/../path3");
    }

    private void normalizeAndCheck(final String uri) throws Exception {
        this.check(new java.net.URI(uri).normalize(),
                new URI(uri).normalize());
    }

    // resolve........................................................................................................

    @Test
    public void testResolveEmptyPath() throws Exception {
        this.resolveAndCheck("http://host:123", "http://host5:6");
    }

    @Test
    public void testResolveSlashPath() throws Exception {
        this.resolveAndCheck("http://host:123/", "http://host5:6/");
    }

    @Test
    public void testResolve() throws Exception {
        this.resolveAndCheck("http://host:123/path", "http://host5:6/path7");
    }

    @Test
    public void testResolve2() throws Exception {
        this.resolveAndCheck("http://host:123/path1/path2", "http://host5:6/path7");
    }

    @Test
    public void testResolveRelative() throws Exception {
        this.resolveAndCheck("http://host:123/path1/path2", "/path567");
    }

    private void resolveAndCheck(final String uri,
                                 final String resolve) throws Exception {
        this.check(new java.net.URI(uri).resolve(resolve),
                new URI(uri).resolve(resolve));
    }

    // relativize........................................................................................................

    @Test
    public void testRelativizeEmptyPath() throws Exception {
        this.relativizeAndCheck("http://host1:123", "http://host3:456/path7");
    }

    @Test
    public void testRelativizeSlashPath() throws Exception {
        this.relativizeAndCheck("http://host1:123/", "http://host3:456/path7");
    }

    @Test
    public void testRelativize() throws Exception {
        this.relativizeAndCheck("http://host1:123/path2", "http://host3:456/path7");
    }

    @Test
    public void testRelativize2() throws Exception {
        this.relativizeAndCheck("http://host1:123/path2", "http://host3:456/path7");
    }

    @Test
    public void testRelativizeEmptyPath2() throws Exception {
        this.relativizeAndCheck("http://host1:123/path2", "http://host3:456");
    }

    @Test
    public void testRelativizeSlashPath2() throws Exception {
        this.relativizeAndCheck("http://host1:123/path2", "http://host3:456/");
    }

    private void relativizeAndCheck(final String uri,
                                    final String relativize) throws Exception {
        this.check(new java.net.URI(uri).relativize(new java.net.URI(relativize)),
                new URI(uri).relativize(new URI(relativize)));
    }
    // .................................................................................................................

    private void check(final java.net.URI jre,
                       final URI emul) throws Exception {
        assertEquals(jre.getScheme(), emul.getScheme(), () -> "scheme " + jre);

        assertEquals(jre.getUserInfo(), emul.getUserInfo(), () -> "userInfo " + jre);
        assertEquals(jre.getRawUserInfo(), emul.getRawUserInfo(), () -> "userInfo " + jre);

        assertEquals(jre.getHost(), emul.getHost(), () -> "host " + jre);
        assertEquals(jre.getPort(), emul.getPort(), () -> "port " + jre);

        assertEquals(jre.getPath(), emul.getPath(), () -> "path " + jre);

        assertEquals(jre.getQuery(), emul.getQuery(), () -> "query " + jre);
        assertEquals(jre.getRawQuery(), emul.getRawQuery(), () -> "raw query " + jre);

        assertEquals(jre.getFragment(), emul.getFragment(), () -> "fragment " + jre);
        assertEquals(jre.getRawFragment(), emul.getRawFragment(), () -> "raw fragment " + jre);

        assertEquals(jre.getAuthority(), emul.getAuthority(), () -> "authority " + jre);
        assertEquals(jre.getRawAuthority(), emul.getRawAuthority(), () -> "raw authority " + jre);

        assertEquals(jre.getSchemeSpecificPart(), emul.getSchemeSpecificPart(), () -> "getSchemeSpecificPart " + jre);
        assertEquals(jre.getRawSchemeSpecificPart(), emul.getRawSchemeSpecificPart(), () -> "getRawSchemeSpecificPart " + jre);

        assertEquals(jre.isAbsolute(), emul.isAbsolute(), () -> "isAbsolute " + jre);
        assertEquals(jre.isOpaque(), emul.isOpaque(), () -> "isOpaque " + jre);

        assertEquals(jre.toASCIIString(), emul.toASCIIString(), () -> "toASCIIString " + jre);
        assertEquals(jre.toString(), emul.toString(), () -> "toString " + jre);

        java.net.URL jreUrl;
        try {
            jreUrl = jre.toURL();
        } catch (final Exception thrown) {
            jreUrl = null;

            // jre threw assert the same for emu
            try {
                emul.toURL();
                fail("Expected " + thrown.getClass().getSimpleName());
            } catch (final Exception expected) {
                expected.printStackTrace();
                assertEquals(thrown.getClass().getSimpleName(), expected.getClass().getSimpleName());
            }
        }

        if (null != jreUrl) {
            this.checkUrl(jreUrl, emul.toURL());
        }
    }

    private void checkUrl(final java.net.URL jre,
                          final URL emul) {
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
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<URI> type() {
        return URI.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
