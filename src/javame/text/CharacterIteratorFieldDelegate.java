/*
 * @(#)CharacterIteratorFieldDelegate.java	1.6 06/10/10
 *
 * Copyright  1990-2006 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version
 * 2 only, as published by the Free Software Foundation. 
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included at /legal/license.txt). 
 * 
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA 
 * 
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa
 * Clara, CA 95054 or visit www.sun.com if you need additional
 * information or have any questions. 
 */
package javame.text;

import java.util.Vector;

/**
 * CharacterIteratorFieldDelegate combines the notifications from a Format into
 * a resulting <code>AttributedCharacterIterator</code>. The resulting
 * <code>AttributedCharacterIterator</code> can be retrieved by way of the
 * <code>getIterator</code> method.
 * 
 * @version 1.6 10/10/06
 */
class CharacterIteratorFieldDelegate implements Format.FieldDelegate {
	/**
	 * Array of AttributeStrings. Whenever <code>formatted</code> is invoked for
	 * a region > size, a new instance of AttributedString is added to
	 * attributedStrings. Subsequent invocations of <code>formatted</code> for
	 * existing regions result in invoking addAttribute on the existing
	 * AttributedStrings.
	 */
	private Vector attributedStrings;
	/**
	 * Running count of the number of characters that have been encountered.
	 */
	private int size;

	CharacterIteratorFieldDelegate() {
		attributedStrings = new Vector();
	}

	public void formatted(Format.Field attr, Object value, int start, int end,
			StringBuffer buffer) {
		if (start != end) {
			if (start < size) {
				// Adjust attributes of existing runs
				int index = size;
				int asIndex = attributedStrings.size() - 1;

				while (start < index) {
					AttributedString as = (AttributedString) attributedStrings
							.elementAt(asIndex--);
					int newIndex = index - as.length();
					int aStart = Math.max(0, start - newIndex);

					as.addAttribute(attr, value, aStart, Math.min(end - start,
							as.length() - aStart)
							+ aStart);
					index = newIndex;
				}
			}
			if (size < start) {
				// Pad attributes
				attributedStrings.addElement(new AttributedString(buffer
						.toString().substring(size, start)));
				size = start;
			}
			if (size < end) {
				// Add new string
				int aStart = Math.max(start, size);
				AttributedString string = new AttributedString(buffer
						.toString().substring(aStart, end));

				string.addAttribute(attr, value);
				attributedStrings.addElement(string);
				size = end;
			}
		}
	}

	public void formatted(int fieldID, Format.Field attr, Object value,
			int start, int end, StringBuffer buffer) {
		formatted(attr, value, start, end, buffer);
	}

	/**
	 * Returns an <code>AttributedCharacterIterator</code> that can be used to
	 * iterate over the resulting formatted String.
	 * 
	 * @pararm string Result of formatting.
	 */
	public AttributedCharacterIterator getIterator(String string) {
		// Add the last AttributedCharacterIterator if necessary
		// assert(size <= string.length());
		if (string.length() > size) {
			attributedStrings.addElement(new AttributedString(string
					.substring(size)));
			size = string.length();
		}
		int iCount = attributedStrings.size();
		AttributedCharacterIterator iterators[] = new AttributedCharacterIterator[iCount];

		for (int counter = 0; counter < iCount; counter++) {
			iterators[counter] = ((AttributedString) attributedStrings
					.elementAt(counter)).getIterator();
		}
		return new AttributedString(iterators).getIterator();
	}
}
