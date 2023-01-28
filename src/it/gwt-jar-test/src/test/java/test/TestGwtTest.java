package test;

import com.google.gwt.junit.client.GWTTestCase;

import java.net.ConnectException;
import java.net.URI;
import java.net.URL;

public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testNewUrlString() throws Exception {
        final URL url = new URL("http://host123:456/path789");

        assertEquals("protocol", "http", url.getProtocol());
        assertEquals("host", "host123", url.getHost());
        assertEquals("port", 456, url.getPort());
        assertEquals("path", "/path789", url.getPath());
        assertEquals("ref", null, url.getRef());
    }

    public void testNewUriString() throws Exception {
        final URI uri = new URI("http://host123:456/path789");

        assertEquals("scheme", "http", uri.getScheme());
        assertEquals("host", "host123", uri.getHost());
        assertEquals("port", 456, uri.getPort());
        assertEquals("path", "/path789", uri.getPath());
    }

    public void testUrlUriRoundtrip() throws Exception {
        final URL url = new URL("http://host123:456/path789");

        assertEquals(url, url.toURI().toURL());
    }

    public void testConnectException() {
        new ConnectException("ignored message");
    }
}
