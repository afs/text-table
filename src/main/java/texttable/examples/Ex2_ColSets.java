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

package texttable.examples;

import org.seaborne.texttable.* ;

public class Ex2_ColSets {
    public static void main(String ... argv) {
        // Define data
        DataTable table = DataTable.create()
            .addColumn("COL1") 
            .addColumn("COL2")
            .addColumn("COL3")
            .addDataRow("column1" , "foo")
            .addDataRow("column2" , "abcdefghijklmnop")
            .addDataRow("column3" , "1 2 3", "baz")
            .build() ;
        
        // Define column formatting. 
        ColumnLayoutSet colSet = ColumnLayoutSet.create()
            .defCol("COL1", Alignment.LEFT)
            .defCol("COL2", Alignment.CENTER)
            .defCol("COL3", Alignment.RIGHT)
            .build() ;
        
        Layout layout = Layout.create(Layout.PLAIN, colSet) ;

        print("Plain, with ColSet", layout, table) ;
        print("Plain, colset, numbered", Layout.create(Layout.PLAIN, colSet, true), table) ;

        print("MYSQL, numbered", Layout.create(Layout.MYSQL, true), table) ;

        print("Minimal, numbered", Layout.create(Layout.MINIMAL, true), table) ;
    }
    
    private static boolean First = true ; 

    private static void print(String name, Layout layout, DataTable table) {
        if ( ! First ) 
            System.out.println() ;
        System.out.println("vvvv............... Layout = "+name) ;
        System.out.println() ;
        DataTableFormatter.output(table, layout);
        System.out.println() ;
        System.out.println("^^^^............... Layout = "+name) ;
        System.out.println() ;
        First = false ;
    }
}
