/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.stuff.generic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class BaseTestCase {

    public static final String UNSUPPORTED_CHOICE_NAME = "unsupported";
    public static final String SUPPORTED_CHOICE_NAME = "SUPPORTED";

    protected void assertListEquals(List a, Object[] b) {
        assertEquals(a, Arrays.asList(b));
    }

    protected List<Object> list(Object... args) {
        return Arrays.asList(args);
    }

    protected void assertMatches(String regex, String actual) {
        String message = String.format("stringa '%s' non corrisponde a pattern '%s'", actual, regex);
        assertTrue(message, actual.matches(regex));
    }

    private String tempDir = null;

    protected String getTempDir() throws Exception {
        if (null == tempDir) {
            File temp = File.createTempFile("aaa", "b");
            temp.deleteOnExit();
            tempDir = temp.getParent();
        }
        return tempDir;
    }

    protected void assertFileHasSameLength(String expected, File actual) throws Exception {
        assertFileHasSameLength(new File(expected), actual);
    }

    protected void assertFileHasSameLength(File expected, File actual) throws Exception {
        assertEquals(expected.length(), actual.length());
    }

    protected void assertFileHasSameContents(File expected, File actual) throws Exception {
        assertFileHasSameContents(expected.getAbsolutePath(), actual);
    }

    protected void assertFileHasSameContents(String expected, File actual) throws Exception {
        assertFileHasSameLength(expected, actual);

        InputStream expectedStream = new FileInputStream(expected);
        InputStream actualStream = new FileInputStream(actual);
        try {
            assertSameContents(expectedStream, actualStream);
        } finally {
            expectedStream.close();
            actualStream.close();
        }
    }

    protected void assertSameContents(InputStream expectedStream, InputStream actualStream)
            throws IOException {
        int expectedChar = 0;
        int count = 0;
        while (-1 != (expectedChar = expectedStream.read())) {
            int actualChar = actualStream.read();
            String message = String.format("stream differiscono alla posizione %d", count);
            assertEquals(message, expectedChar, actualChar);
            count++;
        }
        assertEquals("actual stream is longer than expected", -1, actualStream.read());
    }

    protected void assertFileExists(File expected) {
        String message = String.format("non esiste il file %s", expected.getAbsolutePath());
        assertTrue(message, expected.exists());
    }

    protected void assertFileExists(String fileName) {
        assertFileExists(new File(fileName));
    }

    protected void assertFileDoesNotExist(String fileName) {
        File file = new File(fileName);
        String message = String.format("il file %s non dovrebbe esistere", file.getAbsolutePath());
        assertFalse(message, file.exists());
    }

    protected Timestamp buildDate(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        return new Timestamp(calendar.getTimeInMillis());
    }

    protected Set<Object> set(Object... args) {
        HashSet<Object> result = new HashSet<Object>();
        for (int i = 0; i < args.length; i++) {
            result.add(args[i]);
        }
        return result;
    }

    protected void assertWithinLastSeconds(Timestamp timestamp) {
        long diff = System.currentTimeMillis() - timestamp.getTime();
        assertTrue("timestamp too old: " + timestamp, diff < 3000);
    }

    protected void assertOutputLines(List expectedLines, StringWriter output) {
        String[] actualLines = splitLines(output);
        assertEquals("output ha numero di linee errato", expectedLines.size(), actualLines.length);
        for (int i = 0; i < actualLines.length; i++) {
            assertEquals("mismatch line " + i, expectedLines.get(i), actualLines[i]);
        }
    }

    protected String[] splitLines(StringWriter output) {
        return output.toString().split(System.getProperty("line.separator"));
    }

    protected void assertIsInterface(Class klass) {
        assertTrue("Class " + klass.getName() + " is not an interface ", klass.isInterface());
    }

}
