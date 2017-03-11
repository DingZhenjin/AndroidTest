package com.seuic.util;

public class HexUtil {
	
	public static String trackByteToHexString( byte[] bytes, boolean uppercase ) {
		if ( bytes == null ) {
			return null;
		}
		StringBuffer strBuf = new StringBuffer();
		char char_a = 'a';
		if ( uppercase ) {
			char_a = 'A';
		}
		for ( int i = 0; i < bytes.length; i++ ) {
			byte low = ( byte )( bytes[i]  & 0x0f );
			if ( low >= 0x0a && low <= 0x0f ) {
				if ( low == 0x0D ) {
					strBuf.append( '=' );
				} else {
					char char_low = (char) (low - 0x0a + char_a);
					strBuf.append( char_low );
				}
			} else {
				strBuf.append( low );
			}
		}
		return strBuf.toString();
	}
	
	public static String trackByteToHexString( byte[] bytes ) {
		return trackByteToHexString( bytes, true );
	}
	
	public static String unzipByteToHexString( byte[] bytes, boolean uppercase ) {
		if ( bytes == null ) {
			return null;
		}
		StringBuffer strBuf = new StringBuffer();
		char char_a = 'a';
		if ( uppercase ) {
			char_a = 'A';
		}
		for ( int i = 0; i < bytes.length; i++ ) {
			byte low = ( byte )( bytes[i]  & 0x0f );
			if ( low >= 0x0a && low <= 0x0f ) {
				char char_low = (char) (low - 0x0a + char_a);
				strBuf.append( char_low );
			} else {
				strBuf.append( low );
			}
		}
		return strBuf.toString();
	}
	
	public static String unzipByteToHexString( byte[] bytes ) {
		return unzipByteToHexString( bytes, true );
	}
	
	public static String byteToHexString( byte[] bytes, boolean uppercase ) {
		
		if ( bytes == null ) {
			return null;
		}
		StringBuffer strBuf = new StringBuffer();
		char char_a = 'a';
		if ( uppercase ) {
			char_a = 'A';
		}
		for ( int i = 0; i < bytes.length; i++ ) {
			byte high = ( byte )( ( bytes[i] & 0xf0 ) >> 4 );
			byte low = ( byte )( bytes[i]  & 0x0f );
			if ( high >= 0x0a && high <= 0x0f ) {
				char char_high = (char) (high - 0x0a + char_a);
				strBuf.append( char_high );
			} else {
				strBuf.append( high );
			}
			if ( low >= 0x0a && low <= 0x0f ) {
				char char_low = (char) (low - 0x0a + char_a);
				strBuf.append( char_low );
			} else {
				strBuf.append( low );
			}
		}
		return strBuf.toString();
	}
	
	public static String byteToHexString( byte[] bytes ) {
		return byteToHexString( bytes, true );
	}
	
	public static String byteToHexString( byte[] bytes, int length, boolean uppercase ) {
		
		if ( bytes == null ) {
			return null;
		}
		StringBuffer strBuf = new StringBuffer();
		char char_a = 'a';
		if ( uppercase ) {
			char_a = 'A';
		}
		for ( int i = 0; i < length; i++ ) {
			byte high = ( byte )( ( bytes[i] & 0xf0 ) >> 4 );
			byte low = ( byte )( bytes[i]  & 0x0f );
			if ( high >= 0x0a && high <= 0x0f ) {
				char char_high = (char) (high - 0x0a + char_a);
				strBuf.append( char_high );
			} else {
				strBuf.append( high );
			}
			if ( low >= 0x0a && low <= 0x0f ) {
				char char_low = (char) (low - 0x0a + char_a);
				strBuf.append( char_low );
			} else {
				strBuf.append( low );
			}
		}
		return strBuf.toString();
	}
	
	public static String byteToHexString( byte[] bytes, int length ) {
		return byteToHexString( bytes, length, true );
	}
	
	public static int hexCharToIntValue( char hexChar ) throws NumberFormatException {
		if ( ( hexChar >= '0' ) && ( hexChar <= '9' ) ) {
			return hexChar - '0';
		} else if ( ( hexChar >= 'A' ) && ( hexChar <= 'F' ) ) {
			return ( hexChar - 'A' ) + 10;
		} else if ( ( hexChar >= 'a' ) && ( hexChar <= 'f' ) ) {
			return ( hexChar - 'a' ) + 10;
		} else {
			throw new NumberFormatException( "hexChar Format Error" );
		}
	}
	
	public static byte[] hexStringToByteArray( String hexString ) throws NumberFormatException {
		if ( null == hexString ) {
			return null;
		}
		if ( ( hexString.length() % 2 ) != 0 ) {
			throw new NumberFormatException( "hexString length Error" );
		}
		int byte_len = hexString.length() / 2;
		byte[] tmp = new byte[byte_len];
		int index = 0;
		for ( int i = 0; i < byte_len; i++ ) {
			try {
				int value;
				value = hexCharToIntValue(hexString.charAt(index++));
				value = value << 4;
				value += hexCharToIntValue(hexString.charAt(index++));
				tmp[i] = ( byte )value;
			} catch ( NumberFormatException e ) {
				throw e;
			}
		}
		return tmp;
	}
	
	public static byte[] intToHexByteArray( int value ) {
		String value_str = Integer.toHexString( value );
		if ( 0 != ( value_str.length() & 0x01 ) ) {
			value_str = "0" + value_str;
		}
		return HexUtil.hexStringToByteArray(value_str);
	}
	
	public static byte[] intToBcdByteArray( int value ) {
		String value_str = Integer.toString( value );
		if ( 0 != ( value_str.length() & 0x01 ) ) {
			value_str = "0" + value_str;
		}
		return HexUtil.hexStringToByteArray(value_str);
	}
	
	public static String intToBcdString( int value, int stringLen ) {
		String tmp = Integer.toString( value );
		if ( tmp.length() > stringLen  ) {
			return null;
		}
		String valueString = "";
		for ( int i = tmp.length(); i < stringLen; i++ ) {
			valueString += "0";
		}
		valueString += tmp;
		
		return valueString;
	}
	
	public static String bcdAmountToRmbString( byte[] byte_amount ) {
		if ( byte_amount == null || byte_amount.length < 6 ) {
			return null;
		}
		StringBuffer amountStrBuf = new StringBuffer();
		boolean is_start = false;
		for ( int i = 0; i < 5; i++ ) {
			int tmp = ( ( byte_amount[i] & 0xf0 ) >> 4 );
			if ( tmp > 0 || is_start == true ) {
				amountStrBuf.append( (char)('0' + tmp) );
				is_start = true;
			}
			tmp = ( byte_amount[i] & 0x0f );
			if ( tmp > 0 || is_start == true ) {
				amountStrBuf.append( (char)('0' + tmp) );
				is_start = true;
			}
		}
		if ( is_start != true ) {
			amountStrBuf.append( "0." );
		} else {
			amountStrBuf.append( '.' );
		}
		int tmp = ( ( byte_amount[5] & 0xf0 ) >> 4 );
		amountStrBuf.append( (char)('0' + tmp) );
		tmp = ( byte_amount[5] & 0x0f );
		amountStrBuf.append( (char)('0' + tmp) );
		return amountStrBuf.toString();
	}
	
	public static byte[] hexByteToBcdByte( byte[] hexByte ) {

		if ( null == hexByte ) {
			return null;
		}
		if ( ( hexByte.length % 2 ) != 0 ) {
			return null;
		}
		byte[] bcdByte = new byte[hexByte.length >> 1];
		for ( int i = 0; i < hexByte.length; i++ ) {
			if ( ( i & 0x01 ) == 0 ) {
				bcdByte[i>>1] = (byte) ( ( int )( ( hexByte[i] & 0x0f ) ) << 4 );
			} else {
				bcdByte[i>>1] |= ( hexByte[i] & 0x0f );
			}
		}
		return bcdByte;
	}
	
	public static int hexByteToInt( byte[] hex, int pos, int length ) {
		int value = 0;
		for ( int i = 0; i < length; i++ ) {
			int tmp = hex[pos+i];
			if ( tmp < 0 ) {
				tmp = 0x80 + ( int )( hex[pos+i] & 0x7f );
			}
			value <<= 8;
			value += tmp;
		}
		return value;
	}
	
	public static int hexByteToInt( byte[] hex, int length ) {
		return hexByteToInt( hex, 0, length );
	}
	
	public static int hexByteToInt( byte[] hex ) {
		return hexByteToInt( hex, hex.length );
	}
	
	public static int bcdByteToInt( byte[] bcd, int pos, int length ) {
		int value = 0;
		for ( int i = 0; i < length; i++ ) {
			int tmp = bcd[pos+i];
			if ( tmp < 0 ) {
				tmp = 0x80 + ( int )( bcd[pos+i] & 0x7f );
			}
			int high = ( tmp & 0xF0 ) >> 4;
			int low = ( tmp & 0x0F );
			value *= 10;
			value += high;
			value *= 10;
			value += low;
		}
		return value;
	}
	
	public static int bcdByteToInt( byte[] bcd, int length ) {
		return bcdByteToInt( bcd, 0, length );
	}
	
	public static int bcdByteToInt( byte[] bcd ) {
		return bcdByteToInt( bcd, bcd.length );
	}
	
	public static byte[] asciiNumArrayToHexByteArray( byte[] ascii, int length ) throws NumberFormatException {
		if ( null == ascii || ascii.length <= 0 || length <= 0 ) {
			return null;
		}
		if ( ( length & 0x01 ) != 0 || length > ascii.length ) {
			throw new NumberFormatException();
		}
		byte[] hex = new byte[length>>1];
		for ( int i = 0; i < hex.length; i++ ) {
			byte tmp = ascii[i<<1];
			if ( tmp >=( byte )'0' && tmp <= (byte )'9' ) {
				hex[i] = (byte) ( ( (tmp - '0') << 4 ) & 0xf0 );
			} else if ( tmp >=( byte)'A' && tmp <= (byte)'F' ) {
				hex[i] = (byte) ( (tmp - 'A' + 10 ) << 4 );
			} else if ( tmp >=( byte)'a' && tmp <= (byte)'f' ) {
				hex[i] = (byte) ( (tmp - 'a' + 10 ) << 4 );
			} else {
				throw new NumberFormatException();
			}
			tmp = ascii[(i<<1)+1];
			if ( tmp >=( byte)'0' && tmp <= (byte)'9' ) {
				hex[i] |= (byte) ( (tmp - '0') & 0x0f );
			} else if ( tmp >=( byte)'A' && tmp <= (byte)'F' ) {
				hex[i] |= (byte) ( (tmp - 'A' + 10 ) & 0x0f );
			} else if ( tmp >=( byte)'a' && tmp <= (byte)'f' ) {
				hex[i] |= (byte) ( (tmp - 'a' + 10 ) & 0x0f );
			} else {
				throw new NumberFormatException();
			}
		}
		return hex;
	}
	
	public static byte[] asciiNumArrayToHexByteArray( byte[] ascii ) throws NumberFormatException {
		return asciiNumArrayToHexByteArray( ascii, ascii.length );
	}
}
