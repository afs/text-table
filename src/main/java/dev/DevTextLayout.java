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
    // String.length -> codepoint length
    /*
     * Document
     * Rename Layout as? "TableFormat"? 
     * Tabs.
     * "github markdown" (no bounding top/bottom)
     * Multiline text?
     * Whole table view first
     * Align by decimal point -> 
     *
     *  SpreadSheet style
     *      Cols are "", A, B, C
     *      Rows are numbered.
     *   Call formatter once (caching formatter)
     *  "as spreadsheet" flag to formatter or on layout
     *     Need stateful layout instance 
     */
    
    public static void main(String ... argv) {
        
        // Define data
        DataTable table = DataTable.create()
            //.addColumn("col1") 
            //.addColumn("col2")
            //.addColumn("col3")
            .addDataRow("column1" , "foo")
            .addDataRow("column2" , 123)
            .addDataRow("column3" , "abcdefghijklmnop", "baz")
            .labelColumns()
            .build() ;
        
        // Define column formatting. 
        ColumnLayoutSet colSet = ColumnLayoutSet.create()
            .defCol("col1", Alignment.LEFT)
            .defCol("col2", Alignment.CENTER)
            .defCol("col3", Alignment.RIGHT)
            .build() ;
        
        // Combine into a Layout.
        Layout layout = Layout.create(Layout.PLAIN, true) ;
        
//        System.out.println(table) ;
//        System.out.println() ;
        
        DataTableFormatter.output(table, layout); 
        System.exit(0) ;
        DataTableFormatter.output(table, Layout.PLAIN2);
        DataTableFormatter.output(table, Layout.create(Layout.PLAIN2, true));
        System.exit(0) ;
        System.out.println() ;
        DataTableFormatter.output(table);   // Default layout
        
//        System.out.println("**") ;
//        TextTableFormatter.output(table, Layout.MYSQL) ;
//        System.out.println("**") ;
//        TextTableFormatter.output(table, Layout.COMPACT) ;
        
        System.out.println("**") ;
        DataTableFormatter.output(table, Layout.MINIMAL) ;
        System.out.println("**") ;
        
//        TextTableFormatter.output(table, Layout.SINGLE);
//        TextTableFormatter.output(table, Layout.DOUBLE);
//        TextTableFormatter.output(table, Layout.DOUBLESINGLE);
    }
}
