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

package dev;

import org.seaborne.texttable.* ;

public class DevTextLayout {
    // Packages
    // https://github.com/iNamik/Java-Text-Table-Formatter
    // http://bethecoder.com/applications/products/ascii-table.html
    
    // https://ozh.github.io/ascii-tables/
    //  Fro description of styles.

    // https://en.wikipedia.org/wiki/Box-drawing_character
    // String.length -> codepoint length

    /*
     * Tabs.
     * "github markdown" (no bounding top/bottom)
     * Multiline text?
     *
     * Layout - with and without top and bottom.
     *   LayoutBoundary(left,sep,line,right)
     *   LayoutData(left,sep,right)
     *   Padding in layout not columns?
     *   Layout -> FormatterLayout (copy)
     *      Set widths.
     *      Set padding.
     *      
     *  DataTable : 
     *    
     * Builders.
     *   Columns.
     *   Body.
     *   Formatter + options.
     *   
     *   SpreadSheet style
     *      Cols are "", A, B, C
     *      Rows are numbered.
     * Format - produce a list of cells per row.? 
     * widths - "add default columns."
     */
    
    public static void main(String ... argv) {
        /*
         * Table table = 
             TableBuilder.create()
               .addColumn("h1")
               .addColumn("h2", LEFT)
               .addRow("foo", "bar")
               .build() ;
           ColumnSet colSet = ColumnSet.build()
              .addCol("h2", LEFT)
              
           // Modifies the table settings. 
           Layout layout =
             LayoutBuilder.create(table, BaseLayout)
               .setTablePad(1) 
               .setTablePadLeft(1)
               .setTablePadRight(1)
               
               .setCol(1, padLeft, padRight)
               .setCol(col, padLeft, padRight, align)
               .build() ;
           TableFormattter(table, layout)    
        */
        
        TextTable table = new TextTable() ;
        table.addColumn("col1", Alignment.LEFT) ;
        table.addColumn("col2", Alignment.CENTRE) ;
        table.addColumn("col3") ;
//        table.addColumn("col4", Alignment.CENTRE, 2, 2) ;

        String [][] d = {{"column1" , "foo"}, {null, "short"}, {"x", null, "y"}, {"column2" , "abcdefghijklmnop", "baz"} } ;
        
        for ( String[] s : d ) {
            table.addDataRow(TextRow.row(s)) ;
        }
        
        TextTableFormatter.output(table);
        
        TextTableFormatter.output(table, Layout.MYSQL) ;
        
        TextTableFormatter.output(table, Layout.COMPACT) ;
        
//        TextTableFormatter.output(table, Layout.PLAIN);
//        TextTableFormatter.output(table, Layout.PLAIN2) ;
//        TextTableFormatter.output(table, Layout.SINGLE);
//        TextTableFormatter.output(table, Layout.DOUBLE);
    }
}
