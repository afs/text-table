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

import java.io.FileInputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.io.Reader ;
import java.util.Arrays ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;

import org.apache.commons.csv.CSVFormat ;
import org.apache.commons.csv.CSVParser ;
import org.apache.commons.csv.CSVRecord ;
import org.seaborne.texttable.DataTable ;
import org.seaborne.texttable.DataTableFormatter ;
import org.seaborne.texttable.Layout ;
import org.seaborne.texttable.Row ;

public class DevCSV {
    // read CSV - print it.
    // Case of no header.
    
    public static void main(String ...argv) throws IOException {
        // Replace with proper handling.
        List<String> args = Arrays.asList(argv) ;
        
        boolean HEADER = true ;
        
        if ( args.size() > 0 && args.get(0).equals("--no-header") ) {
            HEADER = false ;
            args = args.subList(1, args.size()) ;
        }
        
        if ( args.size() == 0 ) 
            args = Arrays.asList("data.csv") ;
        
        for ( String fn : args ) {
            DataTable table = csvFile2DataTable(fn, HEADER) ;

            Layout layout = Layout.PLAIN ;
            if ( ! HEADER )
                layout = Layout.X ;

            DataTableFormatter.output(table, layout) ;
        }
    }
    
    
//  DataTable.Builder builder = DataTable.create().addColumn("COL1").addColumn("COL2") ;
//  Row.Builder rb = Row.create().add("A").add("B") ;
//  builder.addDataRow(rb.build()) ;
//  DataTable table = builder.build() ;

    public static DataTable dataTable() {
      DataTable.Builder builder = DataTable.create().addColumn("COL1").addColumn("COL2") ;
      Row.Builder rb = Row.create().add("A").add("B") ;
      builder.addDataRow(rb.build()) ;
      return  builder.build() ;
    }   
    
    public static DataTable csvFile2DataTable(String filename, boolean headerRow) {
        try ( Reader reader = new InputStreamReader(new FileInputStream("data.csv"), "UTF-8");
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withSkipHeaderRecord(false)) ) {
            DataTable.Builder builder = DataTable.create() ;

            Iterator<CSVRecord> iter = parser.iterator() ;

            if ( headerRow ) {
                Map<String, Integer> map = parser.getHeaderMap() ;
                if ( map != null ) { 
                    map.forEach((c,i)->builder.addColumn(c)) ;
                } else {
                    CSVRecord csvr = iter.next() ;
                    csvr.forEach((c)->{
                        builder.addColumn(c) ;
                    }) ;
                }
            }

            iter.forEachRemaining((r)->{
                Row.Builder rb = Row.create() ;
                r.forEach((cell)-> {
                    if ( cell == null ) cell = "" ;
                    rb.add(cell);
                }) ;
                builder.addDataRow(rb.build()) ;
            }) ;
            return builder.build() ;
        } catch (IOException ex) {
            System.err.println("Failed to read the CSV file: "+ex.getMessage());
            return null ;
        }
    }
}