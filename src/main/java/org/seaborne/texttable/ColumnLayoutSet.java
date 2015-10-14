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

import java.util.HashMap ;
import java.util.Map ;

/** A collection of column layouts, addressed by column name */ 
public class ColumnLayoutSet {

    public static class Builder {
        ColumnLayout defaultColumn = ColumnLayout.DEFAULT ;
        Map<String, ColumnLayout> columns = new HashMap<>() ;

        public Builder defCol(String string, Alignment align) {
            return defCol(string, align, 1, 1) ;
        }
        
        public Builder defCol(String string, Alignment left, int padLeft, int padRight) {
            columns.put(string, new ColumnLayout(left, padLeft, padRight)) ;
            return this ;
        }
        
        public Builder setDefault(ColumnLayout dftColumn) {
            defaultColumn = dftColumn ;
            return this ;
        }
        
        public ColumnLayoutSet build() {
            return new ColumnLayoutSet(defaultColumn, new HashMap<>(columns)) ;
        }
    }

    private ColumnLayout defaultColumn;
    private Map<String, ColumnLayout> columns;
    
    public ColumnLayoutSet(ColumnLayout defaultColumn, HashMap<String, ColumnLayout> columnList) {
        this.defaultColumn = defaultColumn ;
        this.columns = columnList ;
    }

    public ColumnLayout get(String colname) {
        if ( colname == null )
            return defaultColumn ;
        ColumnLayout col = columns.get(colname) ;
        if ( col == null )
            col = defaultColumn ;
        return col ; 
    }
    
    public static Builder create() {
        return new Builder() ;
    }

}
