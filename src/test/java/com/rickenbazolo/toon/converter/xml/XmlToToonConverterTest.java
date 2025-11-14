package com.rickenbazolo.toon.converter.xml;

import com.rickenbazolo.toon.Toon;
import com.rickenbazolo.toon.exception.XmlParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for XmlToToonConverter.
 */
class XmlToToonConverterTest {

    @Test
    void testSimpleXmlToToon() throws XmlParseException {
        String xml = "<user><id>123</id><name>Alice</name><email>alice@example.com</email></user>";
        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        assertTrue(toon.contains("user"));
        assertTrue(toon.contains("id"));
        assertTrue(toon.contains("name"));
        assertTrue(toon.contains("email"));
        System.out.println("Simple XML to TOON:");
        System.out.println(toon);
    }

    @Test
    void testXmlWithAttributes() throws XmlParseException {
        String xml = "<product id=\"P001\" category=\"electronics\">" +
                     "<name>Laptop</name>" +
                     "<price currency=\"USD\">999.99</price>" +
                     "</product>";
        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        assertTrue(toon.contains("product"));
        assertTrue(toon.contains("@id") || toon.contains("id"));
        System.out.println("\nXML with Attributes to TOON:");
        System.out.println(toon);
    }

    @Test
    void testXmlWithRepeatedElements() throws XmlParseException {
        String xml = "<catalog>" +
                     "<book><id>1</id><title>The Great Gatsby</title><year>1925</year></book>" +
                     "<book><id>2</id><title>1984</title><year>1949</year></book>" +
                     "</catalog>";
        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        assertTrue(toon.contains("catalog"));
        assertTrue(toon.contains("book"));
        System.out.println("\nXML with Repeated Elements to TOON:");
        System.out.println(toon);
    }

    @Test
    void testXmlWithoutAttributes() throws XmlParseException {
        String xml = "<user><id>123</id><name>Bob</name></user>";

        XmlToToonOptions options = XmlToToonOptions.builder()
            .includeAttributes(false)
            .build();

        String toon = Toon.fromXml(xml, options);

        assertNotNull(toon);
        assertFalse(toon.contains("@"));
        System.out.println("\nXML without Attributes to TOON:");
        System.out.println(toon);
    }

    @Test
    void testXmlWithCustomPrefix() throws XmlParseException {
        String xml = "<product id=\"P001\"><name>Laptop</name></product>";

        XmlToToonOptions options = XmlToToonOptions.builder()
            .attributePrefix("_attr_")
            .build();

        String toon = Toon.fromXml(xml, options);

        assertNotNull(toon);
        assertTrue(toon.contains("_attr_id") || toon.contains("id"));
        System.out.println("\nXML with Custom Prefix to TOON:");
        System.out.println(toon);
    }

    @Test
    void testXmlWithMixedContent() throws XmlParseException {
        String xml = "<message><text>Hello World</text><timestamp>2025-11-11</timestamp></message>";
        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        assertTrue(toon.contains("message"));
        System.out.println("\nXML with Mixed Content to TOON:");
        System.out.println(toon);
    }

    @Test
    void testArrayDetectionNever() throws XmlParseException {
        String xml = "<catalog>" +
                     "<book><id>1</id><title>Book 1</title></book>" +
                     "<book><id>2</id><title>Book 2</title></book>" +
                     "</catalog>";

        XmlToToonOptions options = XmlToToonOptions.builder()
            .arrayDetection(XmlToToonOptions.ArrayDetection.NEVER)
            .build();

        String toon = Toon.fromXml(xml, options);

        assertNotNull(toon);
        System.out.println("\nXML with Array Detection NEVER to TOON:");
        System.out.println(toon);
    }

    @Test
    void testRssFeedExample() throws XmlParseException {
        String xml = "<rss version=\"2.0\">" +
                     "<channel>" +
                     "<title>Tech News</title>" +
                     "<item><title>AI Breakthrough</title><link>https://example.com/ai</link></item>" +
                     "<item><title>New Framework</title><link>https://example.com/framework</link></item>" +
                     "</channel>" +
                     "</rss>";

        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        System.out.println("\nRSS Feed to TOON:");
        System.out.println(toon);
    }

    @Test
    void testInvalidXml() {
        String xml = "<user><id>123</name></user>"; // Invalid: mismatched tags

        assertThrows(XmlParseException.class, () -> {
            Toon.fromXml(xml);
        });
    }

    @Test
    void testEmptyXml() throws XmlParseException {
        String xml = "<empty/>";
        String toon = Toon.fromXml(xml);

        assertNotNull(toon);
        System.out.println("\nEmpty XML to TOON:");
        System.out.println(toon);
    }
}
