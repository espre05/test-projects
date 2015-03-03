////////////////////////////////////////////////////////////////////////
//
// FileIO.java
//
// This file was generated by MapForce 2011.
//
// YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
// OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
//
// Refer to the MapForce Documentation for further details.
// http://www.altova.com/mapforce
//
////////////////////////////////////////////////////////////////////////

package com.altova.text;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class FileIO {
	private java.io.InputStream m_inStream = null;
	private java.io.OutputStream m_outStream = null;
	private java.io.Reader m_Reader = null;
    private String	m_Encoding = "";
	private boolean	m_bBigEndian = false;
	private boolean m_bBOM = false;
    private Charset	m_Charset;

    private int m_BufferSize = 32 * 512; // avg readahead is 16-32 blocks

	public FileIO(java.io.InputStream inStream, String encoding, boolean bBigEndian, boolean bBOM) 
	{
		m_inStream = inStream;
		m_outStream = null;
		m_Encoding = encoding;
		m_bBigEndian = bBigEndian;
		m_bBOM = bBOM;

		if( m_Encoding == null )
			m_Encoding = "UTF-8";

		if( m_Encoding.length() == 0)
			m_Encoding = "UTF-8";

		int unisize = getUnicodeSizeFromEncodingName( m_Encoding );

		if( unisize < 2 )
			m_bBigEndian = false;

		// provide alternative charsets here
		m_Charset = Charset.forName( formNativeEncodingName( m_Encoding, m_bBigEndian ) );
	}
	
	public FileIO(java.io.Reader reader)
	{
		m_Reader = reader;
	}
	
	public FileIO(java.io.OutputStream outStream, String encoding, boolean bBigEndian, boolean bBOM) 
	{
		m_outStream = outStream;
		m_inStream = null;
		m_Encoding = encoding;
		m_bBigEndian = bBigEndian;
		m_bBOM = bBOM;

		if( m_Encoding == null )
			m_Encoding = "UTF-8";

		if( m_Encoding.length() == 0)
			m_Encoding = "UTF-8";

		int unisize = getUnicodeSizeFromEncodingName( m_Encoding );

		if( unisize < 2 )
			m_bBigEndian = false;

		// provide alternative charsets here
		m_Charset = Charset.forName( formNativeEncodingName( m_Encoding, m_bBigEndian ) );
	}
	
    public StringBuffer readToEnd() throws IOException 
	{
		int bufferSize = m_BufferSize;
    	StringBuffer result = new StringBuffer(bufferSize);
    	java.io.Reader reader = null;
        char[] buffer = new char[m_BufferSize];
        
		if (m_inStream != null)
    	{
			// check BOM
			BufferedInputStream fistream = new BufferedInputStream(m_inStream, m_BufferSize);
			fistream.mark(5); // not 6!!!
			byte[] header = new byte[3];
			fistream.read(header, 0, 3);
			fistream.reset();

			int unisize = getUnicodeSizeFromEncodingName( m_Encoding );
			int encbo = getEncodingAndByteOrderFromBOM( header );

			boolean bHasBOM = (encbo & 0x10) != 0;
			boolean bIsBigEndian = (encbo & 0x20) != 0;
			int enc_size = (encbo & 0xf);
			int bom_size = (encbo & 0xf00) >> 8;

			if( enc_size != 0 && enc_size != unisize )
			{
				if( enc_size == 1 ) {
					System.out.println("Warning: input encoding is UTF-8, not '" + m_Encoding + "'");
					m_Encoding = "UTF-8";
					m_Charset = Charset.forName( formNativeEncodingName( m_Encoding, m_bBigEndian ) );
					unisize = 1;
				} else
				if( enc_size == 2 ) {
					System.out.println("Warning: input encoding is UTF-16 or UCS-2, not '" + m_Encoding + "'");
					m_Encoding = "UTF-16";
					m_bBigEndian = bIsBigEndian;
					m_Charset = Charset.forName( formNativeEncodingName( m_Encoding, m_bBigEndian ) );
					unisize = 2;
				}
			}

			if( unisize > 1 && bIsBigEndian != m_bBigEndian && encbo != 0 )
			{
				System.out.println("Warning: input byte order is " + getByteOrderName(bIsBigEndian) + ", not " + getByteOrderName(m_bBigEndian));
				m_bBigEndian = bIsBigEndian;
				m_Charset = Charset.forName( formNativeEncodingName( m_Encoding, m_bBigEndian ) );
			}

			m_bBOM = bHasBOM;

			if( bom_size > 0 )
				fistream.skip( bom_size );

			// read
			if (m_inStream instanceof java.io.FileInputStream)
				bufferSize = (int) ((java.io.FileInputStream) m_inStream).getChannel().size();
			
			result = new StringBuffer(bufferSize);
			
			
			reader = new BufferedReader(new InputStreamReader(fistream, m_Charset));
		}	
		else
			reader = m_Reader;
		
        int read = reader.read(buffer);
        while (-1 < read) {
            result.append(buffer, 0, read);
            read = reader.read(buffer);
        }
		if (m_Reader == null)
			reader.close(); // don't close reader unless it is ours; user openeth, user closeth
        return result; 
    }
        
	public OutputStreamWriter openWriteStream() throws IOException {
		if( m_bBOM )
		{
			int unisize = getUnicodeSizeFromEncodingName( m_Encoding );
			if( unisize == 1 )
			{
				m_outStream.write( 0xef );
				m_outStream.write( 0xbb );
				m_outStream.write( 0xbf );
			}
			else
			if( unisize == 2 )
			{
				if( m_bBigEndian ) {
					m_outStream.write( 0xfe );
					m_outStream.write( 0xff );
				} else {
					m_outStream.write( 0xff );
					m_outStream.write( 0xfe );
				}
			}
		}
		
		return new OutputStreamWriter( m_outStream, m_Charset );
	}
	
	public void writeToEnd(StringBuffer buff) throws IOException {
		OutputStreamWriter writer = openWriteStream();
		writer.write(buff.toString(), 0, buff.length());
		writer.close();
	}

	// Returns
	//	bits 0-3:		encoding char size in bytes (1 = UTF-8, 2 = UTF-16 or UCS-2)
	//	bit  4:			if BOM is included
	//	bit  5:			if BigEndian (set) or LittleEndian (cleared)
	//  bits 8-11:		BOM size in bytes
	private static int getEncodingAndByteOrderFromBOM( byte[] source )
	{
		if( source == null ) return 0;
		if( source.length < 2 ) return 0;

		if( ((source[0] & 0xff) == 0xfe) && ((source[1] & 0xff) == 0xff) )
			return 0x232;	// UTF-16BE with BOM

		if( ((source[0] & 0xff) == 0xff) && ((source[1] & 0xff) == 0xfe) )
			return 0x212;	// UTF-16LE with BOM

		if( source.length < 3 ) return 0;

		if( ((source[0] & 0xff) == 0xef) // & is because chars are signed!
				&& ((source[1] & 0xff) == 0xbb) && ((source[2] & 0xff) == 0xbf) )
			return 0x311;	// UTF-8 with BOM

		return 0;
	}

	private static int getUnicodeSizeFromEncodingName( String encoding )
	{
		if( encoding == null ) return 0;
		encoding = encoding.toUpperCase();

		if( encoding.indexOf("UTF-8") >= 0 )
			return 1;

		if( encoding.indexOf("UTF-16") >= 0 || encoding.indexOf("UCS-2") >= 0 )
			return 2;

		return 0;
	}

	private static String formNativeEncodingName( String encoding, boolean bBigEndian )
	{
		int unisize = getUnicodeSizeFromEncodingName( encoding );
		if( unisize > 1 )
			return bBigEndian ?
						encoding + "BE" : encoding + "LE";

		return encoding;
	}

	private static String getByteOrderName( boolean bBigEndian )
	{
		return bBigEndian ?
					"Big Endian" : "Little Endian";
	}
}