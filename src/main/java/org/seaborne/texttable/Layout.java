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

/** Layout for a table formatter */
public class Layout {
    /** Create a layout, starting from a base layout and give it a ColumnLayoutSet */ 
    public static Layout create(Layout baseLayout, ColumnLayoutSet colSet) {
        return new Layout(baseLayout, colSet, false) ;
    }
    
    /** Create a layout, starting from a base layout and  a flag for row numbers. */
    public static Layout create(Layout baseLayout, boolean includeRowNumber) {
        return new Layout(baseLayout, null, includeRowNumber) ;
    }

    /** Create a layout, starting from a base layout, a ColumnLayoutSet and a flag for row numbers. */ 
    public static Layout create(Layout baseLayout, ColumnLayoutSet colSet, boolean includeRowNumber) {
        return new Layout(baseLayout, colSet, includeRowNumber) ;
    }

    // Top of box.
    protected String          sTopLeft        = "+";
    protected String          sTopSep         = "-";
    protected String          sTopRight       = "+";
    protected String          sTopLine        = "-";

    // The divider from header to body.
    protected String          sDividerLeft    = "+";
    protected String          sDividerSep     = "=";
    protected String          sDividerRight   = "+";
    protected String          sDividerLine    = "=";

    // Bottom of box.
    protected String          sBottomLeft     = "+";
    protected String          sBottomSep      = "-";
    protected String          sBottomRight    = "+";
    protected String          sBottomLine     = "-";

    // Text lines
    protected String          sHeaderLeft     = "|";
    protected String          sHeaderSep      = "|";
    protected String          sHeaderRight    = "|";

    protected String          sBodyLeft       = "|";
    protected String          sBodySep        = "|";
    protected String          sBodyRight      = "|";

    protected boolean         bTopBorder      = true;
    protected boolean         bBottomBorder   = true;
    protected boolean         bHeaderSeparate = true;
    protected boolean         bHeaderDivider  = true;

    protected ColumnLayout    defaultColumn   = ColumnLayout.DEFAULT;
    protected ColumnLayoutSet columnSet       = null;
    protected boolean         addRowNumbers   = false ;
    
    private Layout() {}
    
    protected Layout(Layout base, ColumnLayoutSet columnSet, boolean addRowNumbers) {
        this(base) ;
        this.columnSet = columnSet ;
        this.addRowNumbers = addRowNumbers ;
    }
    
    public ColumnLayoutSet getColumnLayoutSet() { return columnSet ; }
    public boolean rowNumbers()                 { return addRowNumbers ; }

    public ColumnLayout getColumn(String name) {
        if ( name == null || columnSet == null )
            return defaultColumn ;
        ColumnLayout c = columnSet.get(name);
        return c;
    }
    
    protected Layout(Layout base) {
        this.sTopLeft = base.sTopLeft ;
        this.sTopSep = base.sTopSep ;
        this.sTopRight = base.sTopRight ;
        this.sTopLine = base.sTopLine ;

        // The divider from header to body.
        this.sDividerLeft = base.sDividerLeft ;
        this.sDividerSep = base.sDividerSep ;
        this.sDividerRight = base.sDividerRight ;
        this.sDividerLine = base.sDividerLine ;

        // Bottom of box.
        this.sBottomLeft = base.sBottomLeft ;
        this.sBottomSep = base.sBottomSep ;
        this.sBottomRight = base.sBottomRight ;
        this.sBottomLine = base.sBottomLine ;

        // Text lines
        this.sHeaderLeft = base.sHeaderLeft ;
        this.sHeaderSep = base.sHeaderSep ;
        this.sHeaderRight = base.sHeaderRight ;

        this.sBodyLeft = base.sBodyLeft ;
        this.sBodySep = base.sBodySep ;
        this.sBodyRight = base.sBodyRight ;

        this.bTopBorder = base.bTopBorder ;
        this.bBottomBorder = base.bBottomBorder ;
        this.bHeaderSeparate = base.bHeaderSeparate ;
        this.bHeaderDivider = base.bHeaderDivider ;
        
        this.defaultColumn = base.defaultColumn ;
        this.columnSet = base.columnSet ;
        this.addRowNumbers = base.addRowNumbers ;
    }
    
    // @formatter:off
    public String getTopLeft()          { return sTopLeft;  }
    public String getTopSep()           { return sTopSep;   }
    public String getTopRight()         { return sTopRight; }
    public String getTopLine()          { return sTopLine;  }

    public String getDividerLeft()      { return sDividerLeft;  }
    public String getDividerSep()       { return sDividerSep;   }
    public String getDividerRight()     { return sDividerRight; }
    public String getDividerLine()      { return sDividerLine;  }
    
    public String getBottomLeft()       { return sBottomLeft;   }
    public String getBottomSep()        { return sBottomSep;    }
    public String getBottomRight()      { return sBottomRight;  }
    public String getBottomLine()       { return sBottomLine;   }
    
    public String getHeaderLeft()       { return sHeaderLeft;   }
    public String getHeaderSep()        { return sHeaderSep;    }
    public String getHeaderRight()      { return sHeaderRight;  }
    
    public String getBodyLeft()         { return sBodyLeft;     }
    public String getBodySep()          { return sBodySep;      }
    public String getBodyRight()        { return sBodyRight;    }

    public boolean hasTopBorder()       { return bTopBorder;    }
    public boolean hasBottomBorder()    { return bBottomBorder; }
    public boolean hasHeader()          { return bHeaderSeparate ; }
    public boolean hasHeaderDivider()   { return bHeaderDivider ; }
    
    // @formatter:on
    
    /** The PLAIN style - default form. */
    public static Layout PLAIN  = new Layout();

    /** The PLAIN style - default form. */
    public static Layout PLAIN2  = new Layout();
    static {
        PLAIN2.sTopLeft        = "-";
        PLAIN2.sTopRight       = "-";

        PLAIN2.sDividerLeft    = "=";
        PLAIN2.sDividerRight   = "=";

        PLAIN2.sBottomLeft     = "-";
        PLAIN2.sBottomRight    = "-";
    }
    
    /** The PLAIN style but with angled corners to the box */
    public static Layout ANGLED = new Layout(PLAIN) ;
    static {
        ANGLED.sTopLeft      = "/";
        ANGLED.sTopRight     = "\\";
        ANGLED.sBottomLeft   = "\\";
        ANGLED.sBottomRight  = "/";
    }

    /** The style output my MySQL  */
    public static Layout MYSQL = new Layout(PLAIN) ;
    static {
        MYSQL.sTopSep = "+" ;
        MYSQL.sBottomSep = "+" ;
        MYSQL.sDividerSep = "+" ;
        MYSQL.sDividerLine = "-" ;
    }
    
    /** COMPACT - A light style with no borders, just a header/body divider */
    public static Layout COMPACT = new Layout(PLAIN) ;
    static {
        COMPACT.sTopLeft = "" ;
        COMPACT.sTopSep = " " ;
        COMPACT.sTopRight = "" ;
        
        COMPACT.sHeaderLeft = "" ;
        COMPACT.sHeaderSep = " " ;
        COMPACT.sHeaderRight = "" ;

        COMPACT.sDividerLeft = "" ;
        COMPACT.sDividerSep = " " ;
        COMPACT.sDividerRight = "" ;
        COMPACT.sDividerLine = "-" ;

        COMPACT.sBodyLeft = "" ;
        COMPACT.sBodySep = " " ;
        COMPACT.sBodyRight = "" ;
        
        COMPACT.sBottomLeft = "" ;
        COMPACT.sBottomSep = " " ;
        COMPACT.sBottomRight = "" ;
        
        COMPACT.bTopBorder = false ;
        COMPACT.bBottomBorder = false ;
    }
    
    /** Minimal - no adornment */
    public static Layout MINIMAL = new Layout(COMPACT) ;
    static {
        MINIMAL.bTopBorder = false ;
        MINIMAL.bBottomBorder = false ;
        MINIMAL.bHeaderDivider = false ;
        MINIMAL.bHeaderSeparate = false ;
        MINIMAL.defaultColumn = new ColumnLayout(Alignment.LEFT, 0, 1) ;
    }    

    /** Markdown, with outer border.
     * This isn't perfect - alignment in markdown is via the header divider - but it
     * does at least produce a basic template to further refine.
     */ 
    public static Layout MARKDOWN = new Layout(PLAIN) ;
    static {
        MARKDOWN.bTopBorder = false ;
        MARKDOWN.bBottomBorder = false ;
        MARKDOWN.sHeaderSep = "|" ;
        MARKDOWN.sDividerLeft = "|" ;
        MARKDOWN.sDividerSep = "|" ;
        MARKDOWN.sDividerLine = "-" ;
        MARKDOWN.sDividerRight = "|" ;
    }    

    /** Light single line, unicode style.<br/>
     * Caution: this assumes the display font is rendered correctly.
     * Even using the same monospaced font and the same output, it may be
     * can be misaligned in some display sitautions and not others. 
     */
    public static Layout SINGLE = new Layout() ;
    static {
        SINGLE.sTopLeft      = "┌";
        SINGLE.sTopSep       = "┬";
        SINGLE.sTopRight     = "┐";
        SINGLE.sTopLine      = "─";

        SINGLE.sDividerLeft  = "├";
        SINGLE.sDividerSep   = "┼";
        SINGLE.sDividerRight = "┤";
        SINGLE.sDividerLine  = "─";

        SINGLE.sBottomLeft   = "└";
        SINGLE.sBottomSep    = "┴";
        SINGLE.sBottomRight  = "┘";
        SINGLE.sBottomLine   = "─";

        SINGLE.sHeaderLeft   = "│";
        SINGLE.sHeaderSep    = "│";
        SINGLE.sHeaderRight  = "│";

        SINGLE.sBodyLeft     = "│";
        SINGLE.sBodySep      = "│";
        SINGLE.sBodyRight    = "│";
    }

    /** Double line, unicode style.<br/>
     * Caution: this assumes the display font is rendered correctly.
     * Even using the same monospaced font and the same output, it may be
     * can be misaligned in some display sitautions and not others. 
     */
    public static Layout DOUBLE = new Layout() ;
    static {
        DOUBLE.sTopLeft      = "╔";
        DOUBLE.sTopSep       = "╦";
        DOUBLE.sTopRight     = "╗";
        DOUBLE.sTopLine      = "═";

        DOUBLE.sDividerLeft  = "╠";
        DOUBLE.sDividerSep   = "╬";
        DOUBLE.sDividerRight = "╣";
        DOUBLE.sDividerLine  = "═";

        DOUBLE.sBottomLeft   = "╚";
        DOUBLE.sBottomSep    = "╩";
        DOUBLE.sBottomRight  = "╝";
        DOUBLE.sBottomLine   = "═";

        DOUBLE.sHeaderLeft   = "║";
        DOUBLE.sHeaderSep    = "║";
        DOUBLE.sHeaderRight  = "║";

        DOUBLE.sBodyLeft     = "║";
        DOUBLE.sBodySep      = "║";
        DOUBLE.sBodyRight    = "║";
    }
    
    /** Doubl elines outside, single lines internally<br/>
     * Caution: this assumes the display font is rendered correctly.
     * Even using the same monospaced font and the same output, it may be
     * can be misaligned in some display sitautions and not others. 
     */
    public static Layout DOUBLESINGLE = new Layout(SINGLE) ;
    static {
        DOUBLESINGLE.sTopLeft      = "╔";
        DOUBLESINGLE.sTopSep       = "╤";
        DOUBLESINGLE.sTopRight     = "╗";
        DOUBLESINGLE.sTopLine      = "═";

        DOUBLESINGLE.sDividerLeft  = "╟";
        DOUBLESINGLE.sDividerRight = "╢";

        DOUBLESINGLE.sBottomLeft   = "╚";
        DOUBLESINGLE.sBottomSep    = "╧";
        DOUBLESINGLE.sBottomRight  = "╝";
        DOUBLESINGLE.sBottomLine   = "═";
        
        DOUBLESINGLE.sHeaderLeft   = "║";
        DOUBLESINGLE.sHeaderRight  = "║";

        DOUBLESINGLE.sBodyLeft     = "║";
        DOUBLESINGLE.sBodyRight    = "║";
    }
}
