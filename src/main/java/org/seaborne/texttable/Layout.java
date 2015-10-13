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

public class Layout {
    // Top of box.
    protected String sTopLeft      = "+";
    protected String sTopSep       = "-";
    protected String sTopRight     = "+";
    protected String sTopLine      = "-";

    // The divider from header to body.
    protected String sDividerLeft  = "+";
    protected String sDividerSep   = "=";
    protected String sDividerRight = "+";
    protected String sDividerLine  = "=";

    // Bottom of box.
    protected String sBottomLeft   = "+";
    protected String sBottomSep    = "-";
    protected String sBottomRight  = "+";
    protected String sBottomLine   = "-";

    // Text lines
    protected String sHeaderLeft   = "|";
    protected String sHeaderSep    = "|";
    protected String sHeaderRight  = "|";

    protected String sBodyLeft     = "|";
    protected String sBodySep      = "|";
    protected String sBodyRight    = "|";

    private Layout() {}
    
    public String getTopLeft() {
        return sTopLeft;
    }
    public String getTopSep() {
        return sTopSep;
    }
    public String getTopRight() {
        return sTopRight;
    }
    public String getTopLine() {
        return sTopLine;
    }
    public String getDividerLeft() {
        return sDividerLeft;
    }
    public String getDividerSep() {
        return sDividerSep;
    }
    public String getDividerRight() {
        return sDividerRight;
    }
    public String getDividerLine() {
        return sDividerLine;
    }
    public String getBottomLeft() {
        return sBottomLeft;
    }
    public String getBottomSep() {
        return sBottomSep;
    }
    public String getBottomRight() {
        return sBottomRight;
    }
    public String getBottomLine() {
        return sBottomLine;
    }
    public String getHeaderLeft() {
        return sHeaderLeft;
    }
    public String getHeaderSep() {
        return sHeaderSep;
    }
    public String getHeaderRight() {
        return sHeaderRight;
    }
    public String getBodyLeft() {
        return sBodyLeft;
    }
    public String getBodySep() {
        return sBodySep;
    }
    public String getBodyRight() {
        return sBodyRight;
    }

    public static Layout PLAIN = new Layout() ;
    public static Layout PLAIN2 = new Layout() ;
    static {
        PLAIN2.sTopLeft      = "/";
        PLAIN2.sTopRight     = "\\";
        PLAIN2.sBottomLeft   = "\\";
        PLAIN2.sBottomRight  = "/";
    }

    public static Layout MYSQL = new Layout() ;
    static {
        MYSQL.sTopSep = "+" ;
        MYSQL.sBottomSep = "+" ;
        MYSQL.sDividerSep = "+" ;
        MYSQL.sDividerLine = "-" ;
    }
    
    public static Layout COMPACT = new Layout() ;
    static {
        // setTop(left, sep, lin, right) ;
        COMPACT.sTopLeft = "" ;
        COMPACT.sTopSep = " " ;
        COMPACT.sTopRight = "" ;
        
        COMPACT.sHeaderLeft = "" ;
        COMPACT.sHeaderSep = " " ;
        COMPACT.sHeaderRight = "" ;

        COMPACT.sDividerLeft = "" ;
        COMPACT.sDividerSep = " " ;
        COMPACT.sDividerRight = "" ;

        COMPACT.sBodyLeft = "" ;
        COMPACT.sBodySep = " " ;
        COMPACT.sBodyRight = "" ;
        
        COMPACT.sBottomLeft = "" ;
        COMPACT.sBottomSep = " " ;
        COMPACT.sBottomRight = "" ;
    }


    // Single, "DOS"
    /*
    0   1   2   3   4   5   6   7   8   9   A   B   C   D   E   F
6                                           ┘   ┐   ┌   └   ┼   
7       ─           ├   ┤   ┴   ┬   │                            
     */

    
    // Light single, unicode.
    public static Layout SINGLE = new Layout() ;
    static {
        SINGLE.sTopLeft      = "┌";
        SINGLE.sTopSep       = "┬";
        SINGLE.sTopRight     = "┐";
        SINGLE.sTopLine      = "─";

        // The divider from header to body.
        SINGLE.sDividerLeft  = "├";
        SINGLE.sDividerSep   = "┼";
        SINGLE.sDividerRight = "┤";
        SINGLE.sDividerLine  = "─";

        // Bottom of box.
        SINGLE.sBottomLeft   = "└";
        SINGLE.sBottomSep    = "┴";
        SINGLE.sBottomRight  = "┘";
        SINGLE.sBottomLine   = "─";

        // Text lines
        SINGLE.sHeaderLeft   = "│";
        SINGLE.sHeaderSep    = "│";
        SINGLE.sHeaderRight  = "│";

        SINGLE.sBodyLeft     = "│";
        SINGLE.sBodySep      = "│";
        SINGLE.sBodyRight    = "│";
    }

    public static Layout DOUBLE = new Layout() ;
    static {
        DOUBLE.sTopLeft      = "╔";
        DOUBLE.sTopSep       = "╦";
        DOUBLE.sTopRight     = "╗";
        DOUBLE.sTopLine      = "═";

        // The divider from header to body.
        DOUBLE.sDividerLeft  = "╠";
        DOUBLE.sDividerSep   = "╬";
        DOUBLE.sDividerRight = "╣";
        DOUBLE.sDividerLine  = "═";

        // Bottom of box.
        DOUBLE.sBottomLeft   = "╚";
        DOUBLE.sBottomSep    = "╩";
        DOUBLE.sBottomRight  = "╝";
        DOUBLE.sBottomLine   = "═";

        // Text lines
        DOUBLE.sHeaderLeft   = "║";
        DOUBLE.sHeaderSep    = "║";
        DOUBLE.sHeaderRight  = "║";

        DOUBLE.sBodyLeft     = "║";
        DOUBLE.sBodySep      = "║";
        DOUBLE.sBodyRight    = "║";
    }
}
