/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.seaborne.texttable;

/** Formatting for a single column */ 
public class ColumnLayout {
    
    private static final int dftPadLeft = 1 ;
    private static final int dftPadRight = 1 ;
    private static CellFormatter dftCellFormatter = new CellFormatter() {};
    public static final ColumnLayout DEFAULT = new ColumnLayout(Alignment.RIGHT, dftPadLeft, dftPadRight) ;

    
    private Alignment alignment ;
    private CellFormatter cellFormatter = new CellFormatter() {};
    private int padLeft ;
    private int padRight ;

    public Alignment getAlignment() {
        return alignment;
    }

    public CellFormatter getFormatter() {
        return cellFormatter;
    }
    
    public int getPadLeft() {
        return padLeft;
    }

    public int getPadRight() {
        return padRight;
    }

    public ColumnLayout(Alignment alignment) {
        this(alignment, dftPadLeft, dftPadRight, dftCellFormatter) ;
    }
    
    public ColumnLayout(Alignment alignment, int padLeft, int padRight) {
        this(alignment, padLeft, padRight, dftCellFormatter) ;
    }
    
    public ColumnLayout(Alignment alignment, int padLeft, int padRight, CellFormatter cellFormatter) {
        this.alignment = alignment;
        this.padLeft = padLeft;
        this.padRight = padRight;
        this.cellFormatter = cellFormatter ;
    }
    
    @Override
    public String toString() { return alignment+"["+padLeft+":"+padRight+"]" ; }
}
