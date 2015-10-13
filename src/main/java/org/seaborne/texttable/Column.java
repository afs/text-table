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

public class Column {
    public static final Column DEFAULT = new Column("", Alignment.RIGHT, 1, 1) ;
    
    private String header ;
    private Alignment alignment ;
    private int padLeft ;
    private int padRight ;

    public String getHeader() {
        return header;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getPadLeft() {
        return padLeft;
    }

    public int getPadRight() {
        return padRight;
    }


    public Column(String header, Alignment alignment, int padLeft, int padRight) {
        super();
        this.header = header;
        this.alignment = alignment;
        this.padLeft = padLeft;
        this.padRight = padRight;
    }
}
