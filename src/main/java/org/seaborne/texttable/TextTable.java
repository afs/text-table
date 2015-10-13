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

public class TextTable {

    // Builder pattern.
    
    private List<Column> columns ; 
    private List<TextRow> rows;

    public TextTable() {
        this.columns = new ArrayList<>() ;
        this.rows = new ArrayList<>()  ;
    }
    
    public void addColumn(String string) {
        addColumn(string, Alignment.RIGHT, 1, 1);
    }

    public void addColumn(String string, Alignment align) {
        addColumn(string, align, 1, 1);
    }
    
    public void addColumn(String header, Alignment align, int padLeft, int padRight) {
        Column col = new Column(header, align, padLeft, padRight) ;
        columns.add(col) ;
    }
    
    public void addDataRow(TextRow row) {
        // Check length.
        rows.add(row) ;
    }
    
    public List<TextRow> rows() {
        return rows ;
    }

    public List<Column> getColumns() {
        return columns ; 
    }

    public Column getColumn(int i) {
        Column col = (i < columns.size()) ? columns.get(i) : Column.DEFAULT ;
        return col ; 
    }

    
    public TextRow getHeaderRow() {
        //Yuk.
        int N = columns.size() ;
        String[] c = new String[N] ;
        
        for ( int i = 0 ; i < N ; i++ ) {
            c[i] = columns.get(i).getHeader() ;
        }
        return TextRow.row(c) ;
    }
}
