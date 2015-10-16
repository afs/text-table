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

import java.util.ArrayList ;
import java.util.List ;

/** A text table is a 2D array with a distinguished header row.
 *  Columns are number from 1 to N.
 *  Rows are number from 1 to N.
 *  The header row is row 0.
 *  The row number is column 0.
 */
public class DataTable {

    // A table is a data part and can be printed in different styles, controlled by a Layout.
    
    public static Builder create() { return new Builder() ; } 
    
    public static class Builder {
        // Zero based
        private List<String> columns = new ArrayList<>() ;
        private int numColumns = 0 ;
        private List<Row> rows = new ArrayList<>() ;
        
        public Builder addColumn(String string) {
            columns.add(string) ;
            numColumns = Math.max(columns.size(), numColumns) ;
            return this ;
        }
        
        public Builder addDataRow(Object ... data) {
            addDataRow(Row.row(data)) ;
            return this ;
        }
        
        public Builder addDataRow(Row row) {
            numColumns = Math.max(row.length(), numColumns) ;
            rows.add(row) ;
            return this ;
        }

        public Builder labelColumns() {
            int start = columns.size()  ;
            int N = numColumns ;
            for ( int i = start ; i < N ; i++ ) {
                String s = numToColLabel(i) ; // "A", "B", .."AA", "BB"..
                addColumn(s) ;
            }
            return this ;
        }

        private String numToColLabel(int i) {
            if ( i <= 26 )
                return ""+(char)(i+'A')  ;
            int j = i/26 ;
            return ""+(char)(j+'A')+(char)(i+'A')  ;
        }
        
        public DataTable build() {
            return new DataTable(columns, numColumns, rows) ;
        }
    }
    
    private List<String> columnNames ;
    private Row header ;
    private int numColumns = 0 ;
    private List<Row> rows;

    private DataTable(List<String> columns, 
                      int numColumns,
                      List<Row> rows) {
        this.columnNames = new ArrayList<>(columns) ;
        this.header = makeHeaderRow(columns) ;
        this.numColumns = numColumns ;
        this.rows = new ArrayList<>(rows) ;
    }
    
    public Row getHeaderRow() {
        return getRow(0) ;
    }

    public Row getRow(int rowNum) {
        if ( rowNum == 0 )
            return header ;
        if ( rowNum < 1 || rowNum > rows.size() )
            return null ;
        return rows.get(rowNum-1) ;
    }
    
    public Object cell(int colNum, int rowNum) {
        if ( colNum == 0 ) {
            if ( rowNum < 0 || rowNum > rows.size() )
                return null ;
            return rowNum ;
        }
        
        if ( rowNum == 0 ) {
            if ( colNum < 1 || colNum > header.length() )
                return null ;
            return header.get(colNum) ;
        }
        Row row = rows.get(rowNum-1) ;
        
        return row.get(colNum) ;
    }
    
    public List<Row> rows() {
        return rows ;
    }

    public int getColumns() {
        return numColumns ; 
    }

    public int getRows() {
        return rows.size() ;
    }

    public String getColumn(int i) {
        if ( i < 1 || i > columnNames.size() )
            return null ;
        return columnNames.get(i-1) ;
    }
    
    interface Action { void action(int col, int row, Object data) ; } 
    
    public void exec(Action action) {
        execHeader(action) ;
        execBody(action) ;
    }
    
    public void execHeader(Action action) {
        execRow(0, action) ;
    }
    
    public void execBody(Action action) {
        for ( int i = 1 ; i <= getRows() ; i++ )
            execRow(i, action); 
    }

    public void execRow(int rowNum, Action action) {
        for ( int j = 1 ; j <= getColumns() ; j++ ) {
            Object data = cell(rowNum, j) ;
            action.action(j, rowNum, data) ;
        }
    }

    private static Row makeHeaderRow(List<String> columns) {
        int N = columns.size() ;
        String[] c = new String[N] ;
        
        for ( int i = 0 ; i < N ; i++ ) {
            c[i] = columns.get(i) ;
        }
        return Row.row(c) ;
    }

    // Debug output
    @Override
    public String toString() {
        String s = header.toString() ;
        
        for ( int i = 1 ; i <= getRows() ; i++ )
            s = s + "::"+getRow(i) ;
        
        return s ;
    }
}
