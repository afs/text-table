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

import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.Iterator ;
import java.util.List ;

/** A row of data items - numbered from one */ 
public class Row implements Iterable<Object>{
    public static Row row(Object[] data) {
        return new Row(Arrays.asList(data)) ;
    }
    
    /** Build one row */
    public static class Builder {
        private final List<Object> cells = new ArrayList<>() ;

        public Builder add(Object obj) {
            cells.add(obj);
            return this;
        }
        public Row build() { 
            return new Row(cells) ;
        }
    }
    
    public Builder build() {
        return new Builder() ;
    }
    
    protected final List<Object> cells ; 
    
    public Row(List<Object> data) {
        this.cells = new ArrayList<>(data) ;
    }

    @Override
    public Iterator<Object> iterator() {
        return cells.iterator() ;
    }
    
    public Object get(int rowNum) {
        if ( rowNum < 1 || rowNum > cells.size() )
            return null ;
        return cells.get(rowNum-1);
    }

    public int length() { return cells.size() ; }

    @Override
    public String toString() { return cells.toString() ; }

}
