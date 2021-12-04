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
import walkingkooka.predicate.Predicates;
import walkingkooka.text.CharSequences;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;
public final class URLEncoderTest extends JavaNetTestCase<URLEncoder> {

    // encode...........................................................................................................

    @Test
    public void testEncodeNullFails() {
        assertThrows(NullPointerException.class, () -> java.net.URLEncoder.encode(null));
        assertThrows(NullPointerException.class, () -> URLEncoder.encode(null));
    }

    @Test
    public void testEncodeA() {
        this.encodeAndCheck("A");
    }

    @Test
    public void testEncodeUnprintable() {
        this.encodeAndCheck("\0");
    }

    @Test
    public void testEncodeMixedAscii() {
        this.encodeAndCheck("ABC123");
    }

    @Test
    public void testEncodeAscii() {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 255; i++) {
            b.append((char)i);
        }
        this.encodeAndCheck(b.toString());
    }

    private void encodeAndCheck(final String s) {
        this.checkEquals(java.net.URLEncoder.encode(s),
                URLEncoder.encode(s),
                () -> "encode " + CharSequences.quoteAndEscape(s));
    }

    // encodeEncodings..................................................................................................

    @Test
    public void testEncodeEncodingNullStringFails() {
        this.encodeEncodingFails(null, "UTF8", NullPointerException.class);
    }

    @Test
    public void testEncodeEncodingNullEncodingFails() {
        this.encodeEncodingFails("%20", null, NullPointerException.class);
    }

    @Test
    public void testEncodeEncodingInvalidEncodingFails() {
        this.encodeEncodingFails("%20", "ABC123", UnsupportedEncodingException.class);
    }

    private void encodeEncodingFails(final String s,
                                     final String encoding,
                                     final Class<? extends Throwable> thrown) {
        assertThrows(thrown, () -> java.net.URLEncoder.encode(s, encoding));
        assertThrows(thrown, () -> URLEncoder.encode(s, encoding));
    }

    @Test
    public void testEncodeEncodingUtf8A() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("A", "UTF-8");
    }

    @Test
    public void testEncodeEncodingUtf8Unprintable() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("\0", "UTF8");
    }

    @Test
    public void testEncodeEncodingUtf8MixedAscii() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("ABC123", "UTF-8");
    }

    @Test
    public void testEncodeEncodingUtf8Ascii()throws UnsupportedEncodingException {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 255; i++) {
            b.append((char)i);
        }
        this.encodeEncodingAndCheck(b.toString(), "UTF-8");
    }

    @Test
    public void testEncodeEncodingUtf16A() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("A", "UTF-16");
    }

    @Test
    public void testEncodeEncodingUtf16Unprintable() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("\0", "UTF16");
    }

    @Test
    public void testEncodeEncodingUtf16MixedAscii() throws UnsupportedEncodingException{
        this.encodeEncodingAndCheck("ABC123", "UTF-16");
    }

    @Test
    public void testEncodeEncodingUtf16Ascii()throws UnsupportedEncodingException {
        final StringBuilder b = new StringBuilder();
        for(int i = 0; i < 255; i++) {
            b.append((char)i);
        }
        this.encodeEncodingAndCheck(b.toString(), "UTF-16");
    }

    private void encodeEncodingAndCheck(final String s,
                                        final String encoding) throws UnsupportedEncodingException {
        this.checkEquals(java.net.URLEncoder.encode(s, encoding),
                URLEncoder.encode(s, encoding),
                () -> "encode " + CharSequences.quoteAndEscape(s) + " encoding: " + encoding + " encoding: " + encoding);
    }
    
    // ShadedClassTesting...............................................................................................

    @Override
    public Class<URLEncoder> type() {
        return URLEncoder.class;
    }

    @Override
    public Predicate<Method> requiredMethods() {
        return Predicates.always();
    }
}
