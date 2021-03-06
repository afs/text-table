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

public class Ex1_ProvidedLayouts {
    public static void main(String ... argv) {
        // Define data
        DataTable table = DataTable.create()
            .addColumn("col1") 
            .addColumn("col2")
            .addColumn("col3")
            .addDataRow("column1" , "foo")
            .addDataRow("column2" , "abcdefghijklmnop")
            .addDataRow("column3" , "1 2 3", "baz")
            .build() ;
        
        print("Plain", Layout.PLAIN, table) ;
        
        print("Markdown", Layout.MARKDOWN, table) ;
        
        print("Plain2", Layout.PLAIN2, table) ;

        print("MYSQL", Layout.MYSQL, table) ;

        print("Compact", Layout.COMPACT, table) ;

        print("Minimal", Layout.MINIMAL, table) ;
    }
    
    private static boolean First = true ; 

    private static void print(String name, Layout layout, DataTable table) {
        if ( ! First ) 
            System.out.println() ;
        System.out.println("vvvv............... Layout '"+name+"'") ;
        System.out.println() ;
        DataTableFormatter.output(table, layout);
        System.out.println() ;
        System.out.println("^^^^............... Layout '"+name+"'") ;
        System.out.println() ;
        First = false ;
    }
}
