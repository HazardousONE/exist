/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-03 Wolfgang M. Meier
 *  wolfgang@exist-db.org
 *  http://exist.sourceforge.net
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *  
 *  $Id$
 */
package org.exist.storage;

import org.dbxml.core.data.Value;
import org.exist.util.ByteConversion;
import org.exist.util.UTF8;

/**
 * @author wolf
 */
public class ElementValue extends Value {

	public static final byte ELEMENT = 0;
	public static final byte ATTRIBUTE = 1;
	public static final byte ATTRIBUTE_ID = 2;
	
	public static final String[] type = { "element", "attribute", "id" };

	ElementValue(short collectionId) {
		data = new byte[2];
		ByteConversion.shortToByte(collectionId, data, 0);
		len = 2;
		pos = 0;
	}

	ElementValue(byte type, short collectionId) {
		data = new byte[3];
		ByteConversion.shortToByte(collectionId, data, 0);
		data[2] = type;
		len = 3;
		pos = 0;
	}

	ElementValue(byte type, short collectionId, short symbol) {
		len = 5;
		data = new byte[len];
		ByteConversion.shortToByte(collectionId, data, 0);
		data[2] = type;
		ByteConversion.shortToByte(symbol, data, 3);
		pos = 0;
	}

	ElementValue(byte type, short collectionId, short symbol, short nsSymbol) {
		len = 7;
		data = new byte[len];
		ByteConversion.shortToByte(collectionId, data, 0);
		data[2] = type;
		ByteConversion.shortToByte(symbol, data, 3);
		ByteConversion.shortToByte(nsSymbol, data, 5);
		pos = 0;
	}

	ElementValue(byte type, short collectionId, String idValue) {
		len = 3 + UTF8.encoded(idValue);
		data = new byte[len];
		ByteConversion.shortToByte(collectionId, data, 0);
		data[2] = type;
		UTF8.encode(idValue, data, 3);
	}

	short getCollectionId() {
		return ByteConversion.byteToShort(data, 0);
	}
}
