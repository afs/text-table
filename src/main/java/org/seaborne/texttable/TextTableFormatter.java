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

public class TextTableFormatter {

    public static void output(TextTable table) {
        output(System.out, table, Layout.PLAIN) ;
    }
    

    public static void output(TextTable table, Layout layout) {
        output(System.out, table, layout) ;
    }
    
    private static Writer asBufferedUTF8(OutputStream out) {
        Writer w =  new OutputStreamWriter(out, StandardCharsets.UTF_8) ;
        return new BufferedWriter(w) ;
    }
        
    // Not printStream - we want to control the charset. 
    public static void output(OutputStream outputStream, TextTable table, Layout layout) {
        Writer out = /*IO.*/asBufferedUTF8(outputStream) ;
        
        try {
            // Phase one.  Widths.
            //int N = table.getHeaderRow().size() ;
            int N = 10 ; 
            List<Integer> widths = new ArrayList<>() ;
            width(widths, table.getHeaderRow()) ;
            for (TextRow row : table.rows() ) {
                width(widths, row) ;
            }

            // Produce a "formatter object" ?
            //  For each row:
            //     left,middle,right.
            //     For each cell, width, before pad, after pad, 
            
            // Phase 2 : output 
            outputBoundaryRow(out, table, widths, layout.getTopLeft(), layout.getTopSep(), layout.getTopLine(), null, layout.getTopRight()) ;
            
            outputHeaderRow(out, table, widths, layout.getHeaderLeft(), layout.getHeaderSep(), table.getHeaderRow(), layout.getHeaderRight()) ;
            
            outputBoundaryRow(out, table, widths, layout.getDividerLeft(), layout.getDividerSep(), layout.getDividerLine() , null, layout.getDividerRight()) ;
            for (TextRow row : table.rows() ) {
                outputBodyRow(out, table, widths, layout.getBodyLeft(), layout.getBodySep(), row, layout.getBodyRight()) ;
            }
            outputBoundaryRow(out, table, widths, layout.getBottomLeft(), layout.getBottomSep() , layout.getBottomLine(), null, layout.getBottomRight()) ;
        }
        catch (IOException ex) { exception(ex); }

        flush(out) ;
        
    }

    private static void outputBoundaryRow(Writer out, TextTable table, List<Integer> widths, String left, String sep, String line, List<Column> columns, String right) throws IOException {
        out.write(left) ;
        boolean firstCol = true ;
        
        for ( int i = 0 ; i < widths.size() ; i++ ) {
            int width = widths.get(i) ;
            if ( ! firstCol )
                out.write(sep) ;
            Column col = table.getColumn(i) ;
            int x = width + col.getPadLeft() + col.getPadRight() ;
            outputN(out, line, x) ;
            firstCol = false ;
        }
        out.write(right) ;
        out.write('\n') ;
    }

    private static void outputHeaderRow(Writer out, TextTable table, List<Integer> widths, String left, String sep, TextRow row, String right) throws IOException {
        outputRow(out, table, widths, left, sep, row, Alignment.RIGHT, right); 
    }
    
    private static void outputBodyRow(Writer out, TextTable table, List<Integer> widths, String left, String sep, TextRow row, String right) throws IOException {
        outputRow(out, table, widths, left, sep, row, null, right); 
    }
    
    private static void outputRow(Writer out, TextTable table, List<Integer> widths, String left, String sep, TextRow row, Alignment forceAlign, String right) throws IOException {
        out.write(left) ;
        boolean firstCol = true ;
        int N = widths.size() ;
        for ( int i = 0 ; i < N ; i++ ) {
            if ( ! firstCol )
                out.write(sep) ;
            Column col = table.getColumn(i) ;
            String s = "" ;
            if ( i < row.cells.size() )
                s = row.cells.get(i) ;
            outputPadded(out, widths.get(i), s, col, forceAlign) ;
            firstCol = false ;
        }
        out.write(right) ;
        out.write('\n') ;
    }

    private static void outputPadded(Writer out, int width, String s, Column column, Alignment forceAlign)  throws IOException {
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

    private static void width(List<Integer> widths, TextRow row) {
        int i = 0 ;
        for ( String text : row ) {
            int w = ( text == null ? 0 : text.length() ) ;
            if ( i < widths.size() ) {
                if ( widths.get(i) < w )
                    widths.set(i, w) ;
            } else
                    widths.add(w) ;
            i++ ;
        }
    }
    
    /** Throw a RuntimeIOException - this function is guaranteed not to return normally */
    private static void exception(IOException ex) {
        throw new RuntimeException(ex) ;
    }

    /** Throw a RuntimeIOException - this function is guaranteed not to return normally */
    private static void exception(String msg, IOException ex) {
        throw new RuntimeException(msg, ex) ;
    }
    
    private static void flush(OutputStream out) { 
        if ( out == null )
            return ;
        try { out.flush(); } catch (IOException ex) { exception(ex) ; }
    }
    
    private static void flush(Writer out) {
        if ( out == null )
            return ;
        try { out.flush(); } catch (IOException ex) { exception(ex) ; } 
    }

}
