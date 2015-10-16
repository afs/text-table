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

import java.io.* ;
import java.nio.charset.StandardCharsets ;
import java.util.ArrayList ;
import java.util.List ;

/** Engine to format and output a Table.
 * It brings togther a DataTable and a Layout.
 * It is a two-pass algorithm - in order to work out column widths, 
 * it needs to preprocess the data.   
 */ 
public class DataTableFormatter {

    public static void output(DataTable table) {
        output(System.out, table, Layout.PLAIN) ;
    }
    

    public static void output(DataTable table, Layout layout) {
        output(System.out, table, layout) ;
    }
    
    /** The format of the row numbers column (if included) */
    private static ColumnLayout column0 = new ColumnLayout(Alignment.LEFT, 1, 1) ;
    
    // Not printStream - we want to control the charset. 
    public static void output(OutputStream outputStream, DataTable table, Layout layout) {
        Writer out = /*IO.*/asBufferedUTF8(outputStream) ;
        
        try {
            // Phase one.  Widths.
            List<Integer> widths = new ArrayList<>() ;
            // Dummy. Later, row number.
            int x = table.getRows() ;
            widths.add(numWidth(x)) ;
            table.exec((c,r,data) -> width(table, layout, widths, r)) ;
    
            // This makes strings; trade off of having an in-memory copy vs calling format twice per cell.
            
            //System.out.println("Widths: "+widths) ;
    
            // Phase 2 : output 
            if ( layout.hasTopBorder() )
                outputBoundaryRow(out, table, layout, widths, layout.getTopLeft(), layout.getTopSep(), layout.getTopLine(), null, layout.getTopRight()) ;
            
            // Header as header or body style.
            if ( layout.hasHeader() ) {
                outputHeaderRow(out, table, layout, widths, 
                                layout.getHeaderLeft(), layout.getHeaderSep(), layout.getHeaderRight()) ;
            }
            else
                outputBodyRow(out, table, layout, widths, 0, 
                              layout.getHeaderLeft(), layout.getHeaderSep(), layout.getHeaderRight()) ;
    
            if ( layout.hasHeaderDivider() )
                outputBoundaryRow(out, table, layout, widths, layout.getDividerLeft(), layout.getDividerSep(), layout.getDividerLine() , null, layout.getDividerRight()) ;
    
            for (int rowNum = 1 ; rowNum <= table.getRows() ; rowNum++ )
                outputBodyRow(out, table, layout, widths, rowNum, 
                              layout.getBodyLeft(), layout.getBodySep(), layout.getBodyRight()) ;
            if ( layout.hasBottomBorder() )
                outputBoundaryRow(out, table, layout, widths, layout.getBottomLeft(), layout.getBottomSep() , layout.getBottomLine(), null, layout.getBottomRight()) ;
            out.flush() ;
        }
        catch (IOException ex) { exception(ex); }
    }


    private static void outputBoundaryRow(Writer out, DataTable table, Layout layout, List<Integer> widths, String left, String sep, String line, List<ColumnLayout> columns, String right) throws IOException {
        out.write(left) ;
        boolean firstCol = true ;
        int index = layout.rowNumbers() ? 0 : 1 ;
        
        for ( int i = index ; i < widths.size() ; i++ ) {
            int width = widths.get(i) ;
            if ( ! firstCol )
                out.write(sep) ;
            String colName = table.getColumn(i) ;
            ColumnLayout col = layout.getColumn(colName) ;
            int x = width + col.getPadLeft() + col.getPadRight() ;
            outputN(out, line, x) ;
            firstCol = false ;
        }
        out.write(right) ;
        out.write('\n') ;
    }

    private static void outputHeaderRow(Writer out, DataTable table, Layout layout, List<Integer> widths, String left, String sep, String right) throws IOException {
        outputRow(out, table, layout, widths, 0, left, sep, Alignment.RIGHT, right); 
    }
    
    private static void outputBodyRow(Writer out, DataTable table, Layout layout, List<Integer> widths, int rowNumber, String left, String sep, String right) throws IOException {
        outputRow(out, table, layout, widths, rowNumber, left, sep, null, right); 
    }
    
    private static void outputRow(Writer out, DataTable table, Layout layout, List<Integer> widths, int rowNumber, String left, String sep, Alignment forceAlign, String right) throws IOException {
        out.write(left) ;
        boolean firstCol = true ;
        int N = table.getColumns() ;
        if ( layout.rowNumbers() ) {
            String s = "" ;
            if ( rowNumber > 0 )
                s = Integer.toString(rowNumber) ;
            outputPadded(out, widths.get(0), s, column0, null) ;
            firstCol = false ;
        }
        for ( int i = 1 ; i <= N ; i++ ) {
            if ( ! firstCol )
                out.write(sep) ;
            Object x = table.cell(i, rowNumber) ;
            String colName = table.getColumn(i) ;
            ColumnLayout col = layout.getColumn(colName) ;
            String s = col.getFormatter().format(i, rowNumber, x) ;
            outputPadded(out, widths.get(i), s, col, forceAlign) ;
            firstCol = false ;
        }
        out.write(right) ;
        out.write('\n') ;
    }

    private static void outputPadded(Writer out, int width, String s, ColumnLayout column, Alignment forceAlign)  throws IOException {
        if ( s == null )
            s = "" ;
        int before = 0 ;
        int after = 0 ;
        Alignment align = column.getAlignment() ;
        if ( forceAlign != null )
            align = forceAlign ; 
        
        switch(align) {
            case CENTRE :
            case CENTER :
                after = ( width - s.length() ) / 2 ;
                before = ( width - s.length() - after ) ;
                break;
            case LEFT :
                after = width - s.length() ;
                break;
            case RIGHT :
                before = width - s.length() ;
                break;
        }
        // Spacing.
        before += column.getPadLeft() ;
        after += column.getPadRight() ;
        
        outputN(out, " ", before) ;
        out.write(s) ;
        outputN(out, " ", after) ;
    }

    private static void outputN(Writer out, String ch, int num) throws IOException {
        for ( int i = 0 ; i < num ; i++ ) {
            out.write(ch) ;
        }
    }

    private static void width(DataTable table, Layout layout, List<Integer> widths, int rowNumber) {
        int columnNum = 1 ;
        Row row = table.getRow(rowNumber) ;
        for ( Object text : row ) {
            Object data = table.cell(columnNum, rowNumber) ; 
            widthCell(table, layout, widths, columnNum, rowNumber, data) ;
            columnNum++ ;
        }
    }
    
    private static void widthCell(DataTable table, Layout layout, List<Integer> widths, int columnNum, int rowNumber, Object data) {
        String colName = table.getColumn(columnNum) ;
        ColumnLayout col = layout.getColumn(colName) ;
        String s = col.getFormatter().format(columnNum, rowNumber, data) ;
        int w = s.length() ;
        //System.out.printf("[C=%d(%s), R=%d] %d : %s [%s]\n",columnNum, colName, rowNumber, w, s, col) ;
        if ( columnNum < widths.size() ) {
            if ( widths.get(columnNum) < w )
                widths.set(columnNum, w) ;
        } else
                widths.add(w) ;
        
    }
    
    /** Throw a RuntimeException - this function is guaranteed not to return normally */
    private static void exception(IOException ex) {
        throw new RuntimeException(ex) ;
    }

    /** Throw a RuntimeException - this function is guaranteed not to return normally */
    private static void exception(String msg, IOException ex) {
        throw new RuntimeException(msg, ex) ;
    }


    private static Writer asBufferedUTF8(OutputStream out) {
        Writer w =  new OutputStreamWriter(out, StandardCharsets.UTF_8) ;
        return new BufferedWriter(w) ;
    }


    /** Width of a number */
    private static int numWidth(final int n)
    {
        int l;
        int x = Math.abs(n);
        for ( l = 0 ; x > 0 ; ++l )
            x /= 10;
        if ( n < 0 )
            l++;
        return l;
    }
}
