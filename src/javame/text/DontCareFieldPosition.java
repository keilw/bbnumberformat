        /*
         * @(#)DontCareFieldPosition.java	1.5 06/10/10
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

        /**
         * DontCareFieldPosition defines no-op FieldDelegate. Its
         * singleton is used for the format methods that don't take a
         * FieldPosition.
         */
        class DontCareFieldPosition extends FieldPosition {
            // The singleton of DontCareFieldPosition.
            static final FieldPosition INSTANCE = new DontCareFieldPosition();

            private final Format.FieldDelegate noDelegate = new Format.FieldDelegate() {
                public void formatted(Format.Field attr, Object value,
                        int start, int end, StringBuffer buffer) {
                }

                public void formatted(int fieldID, Format.Field attr,
                        Object value, int start, int end, StringBuffer buffer) {
                }
            };

            private DontCareFieldPosition() {
                super (0);
            }

            Format.FieldDelegate getFieldDelegate() {
                return noDelegate;
            }
        }
