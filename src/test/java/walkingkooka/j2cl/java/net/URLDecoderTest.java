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
import walkingkooka.text.CharSequences;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class URLDecoderTest implements ClassTesting<URLDecoder> {

    // encode...........................................................................................................

    @Test
    public void testDecodeA() {
        this.decodeAndCheck("A");
    }

    @Test
    public void testDecodeUnprintable() {
        this.decodeAndCheck("\0");
    }

    @Test
    public void testDecodeSpace() {
        this.decodeAndCheck(" ");
    }

    @Test
    public void testDecodeMixedAscii() {
        this.decodeAndCheck("ABC123");
    }

    @Test
    public void testDecodeAscii() {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 127; i++) {
            b.append((char)i);
            b.append('\n');
        }
        this.decodeAndCheck(b.toString());
    }

    private void decodeAndCheck(final String s) {
        final String encoded = URLEncoder.encode(s);

        assertEquals(java.net.URLDecoder.decode(java.net.URLEncoder.encode(s)),
                URLDecoder.decode(encoded),
                () ->  "decode " + CharSequences.quoteAndEscape(encoded) + " originally " + CharSequences.quoteAndEscape(s));
    }

    // encode...........................................................................................................

    @Test
    public void testDecodeEncodingUtf8A() throws UnsupportedEncodingException {
        this.decodeEncodingAndCheck("A", "UTF-8");
    }

    @Test
    public void testDecodeEncodingUtf8Unprintable() throws UnsupportedEncodingException{
        this.decodeEncodingAndCheck("\0", "UTF8");
    }

    @Test
    public void testDecodeEncodingUtf8MixedAscii() throws UnsupportedEncodingException{
        this.decodeEncodingAndCheck("ABC123", "UTF-8");
    }

    @Test
    public void testDecodeEncodingUtf8Ascii()throws UnsupportedEncodingException {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 255; i++) {
            b.append((char)i);
            b.append('\n');
        }
        this.decodeEncodingAndCheck(b.toString(), "UTF-8");
    }

    @Test
    public void testDecodeEncodingUtf16A() throws UnsupportedEncodingException{
        this.decodeEncodingAndCheck("A", "UTF-16");
    }

    @Test
    public void testDecodeEncodingUtf16Unprintable() throws UnsupportedEncodingException{
        this.decodeEncodingAndCheck("\0", "UTF16");
    }

    @Test
    public void testDecodeEncodingUtf16MixedAscii() throws UnsupportedEncodingException{
        this.decodeEncodingAndCheck("ABC123", "UTF-16");
    }

    @Test
    public void testDecodeEncodingUtf16Ascii()throws UnsupportedEncodingException {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 255; i++) {
            b.append((char)i);
            b.append('\n');
        }
        this.decodeEncodingAndCheck(b.toString(), "UTF-16");
    }

    private void decodeEncodingAndCheck(final String s,
                                        final String encoding) throws UnsupportedEncodingException {
        final String encoded = URLEncoder.encode(s, encoding);

        assertEquals(java.net.URLDecoder.decode(java.net.URLEncoder.encode(s, encoding), encoding),
                URLDecoder.decode(encoded, encoding),
                () ->  "decode " + CharSequences.quoteAndEscape(encoded) + " encoding: " + encoding + " originally " + CharSequences.quoteAndEscape(s));
    }
    
    @Override
    public Class<URLDecoder> type() {
        return URLDecoder.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
