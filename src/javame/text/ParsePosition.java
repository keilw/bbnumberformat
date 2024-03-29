
        /*
         * 
         * @(#)ParsePosition.java	1.22 06/10/10
         * 
         * Portions Copyright  2000-2006 Sun Microsystems, Inc. All Rights
         * Reserved.  Use is subject to license terms.
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

        /*
         * (C) Copyright Taligent, Inc. 1996, 1997 - All Rights Reserved
         * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
         *
         *   The original version of this source code and documentation is copyrighted
         * and owned by Taligent, Inc., a wholly-owned subsidiary of IBM. These
         * materials are provided under terms of a License Agreement between Taligent
         * and Sun. This technology is protected by multiple US and International
         * patents. This notice and attribution to Taligent may not be removed.
         *   Taligent is a registered trademark of Taligent, Inc.
         *
         */

        package javame.text;

        /**
         * <code>ParsePosition</code> is a simple class used by <code>Format</code>
         * and its subclasses to keep track of the current position during parsing.
         * The <code>parseObject</code> method in the various <code>Format</code>
         * classes requires a <code>ParsePosition</code> object as an argument.
         *
         * <p>
         * By design, as you parse through a string with different formats,
         * you can use the same <code>ParsePosition</code>, since the index parameter
         * records the current position.
         *
         * @version     1.22, 10/10/06
         * @author      Mark Davis
         * @see         java.text.Format
         */

        public class ParsePosition {

            /**
             * Input: the place you start parsing.
             * <br>Output: position where the parse stopped.
             * This is designed to be used serially,
             * with each call setting index up for the next one.
             */
            int index = 0;
            int errorIndex = -1;

            /**
             * Retrieve the current parse position.  On input to a parse method, this
             * is the index of the character at which parsing will begin; on output, it
             * is the index of the character following the last character parsed.
             */
            public int getIndex() {
                return index;
            }

            /**
             * Set the current parse position.
             */
            public void setIndex(int index) {
                this .index = index;
            }

            /**
             * Create a new ParsePosition with the given initial index.
             */
            public ParsePosition(int index) {
                this .index = index;
            }

            /**
             * Set the index at which a parse error occurred.  Formatters
             * should set this before returning an error code from their
             * parseObject method.  The default value is -1 if this is not set.
             * @since 1.2
             */
            public void setErrorIndex(int ei) {
                errorIndex = ei;
            }

            /**
             * Retrieve the index at which an error occurred, or -1 if the
             * error index has not been set.
             * @since 1.2
             */
            public int getErrorIndex() {
                return errorIndex;
            }

            /**
             * Overrides equals
             */
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;
                if (!(obj instanceof  ParsePosition))
                    return false;
                ParsePosition other = (ParsePosition) obj;
                return (index == other.index && errorIndex == other.errorIndex);
            }

            /**
             * Returns a hash code for this ParsePosition.
             * @return a hash code value for this object
             */
            public int hashCode() {
                return (errorIndex << 16) | index;
            }

            /**
             * Return a string representation of this ParsePosition.
             * @return  a string representation of this object
             */
            public String toString() {
                return getClass().getName() + "[index=" + index
                        + ",errorIndex=" + errorIndex + ']';
            }
        }
