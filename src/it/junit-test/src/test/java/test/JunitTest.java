/*
 * Copyright © 2020 Miroslav Pokorny
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
 */
package test;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Assert;
import org.junit.Test;

import java.net.ConnectException;
import java.net.URI;
import java.net.URL;

@J2clTestInput(JunitTest.class)
public class JunitTest {

    @Test
    public void testNewUrlString() throws Exception {
        final URL url = new URL("http://host123:456/path789");

        Assert.assertEquals("protocol", "http", url.getProtocol());
        Assert.assertEquals("host", "host123", url.getHost());
        Assert.assertEquals("port", 456, url.getPort());
        Assert.assertEquals("path", "/path789", url.getPath());
        Assert.assertEquals("ref", null, url.getRef());
    }

    @Test
    public void testNewUriString() throws Exception {
        final URI uri = new URI("http://host123:456/path789");

        Assert.assertEquals("scheme", "http", uri.getScheme());
        Assert.assertEquals("host", "host123", uri.getHost());
        Assert.assertEquals("port", 456, uri.getPort());
        Assert.assertEquals("path", "/path789", uri.getPath());
    }

    @Test
    public void testUrlUriRoundtrip() throws Exception {
        final URL url = new URL("http://host123:456/path789");

        Assert.assertEquals(url, url.toURI().toURL());
    }

    @Test
    public void testConnectException() {
        new ConnectException("ignored message");
    }
}
