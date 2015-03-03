/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.stuff.generic.sql;

import jin.collection.core.ChainedOperation;
import jin.collection.core.Iter;
import net.della.stuff.generic.util.ListUtil;

import org.apache.commons.lang.StringUtils;

public class SqlBuilder {

    public static String and(String sql) {
        return concatenate(sql, "and");
    }

    public static String or(String sql) {
        return concatenate(sql, "or");
    }

    private static String concatenate(String sql, String logicOperator) {
        int whereIndex = sql.toLowerCase().indexOf("where");
        if (whereIndex == -1) {
            return "";
        }
        String clausole = StringUtils.replaceOnce(sql, "where", "");
        return (new StringBuilder()).append(" " + logicOperator + " (").append(clausole).append(" ) ")
                .toString();
    }

    /**
     * Contacatenate with OR
     */
    public static String or(String... clauses) {
        String result = (String) Iter.chain(ListUtil.list(clauses), "", new OrChain());
        return StringUtils.substringAfter(result, "or");
    }

    private static final class OrChain implements ChainedOperation {
        public Object execute(Object element, Object currValue) {
            String clause = (String) element;
            String actual = (String) currValue;
            return actual + or(clause);
        }
    }

    /**
     * Contacatenate with AND
     */
    public static String and(String... clauses) {
        String result = (String) Iter.chain(ListUtil.list(clauses), "", new AndChain());
        return StringUtils.substringAfter(result, "and");
    }

    private static final class AndChain implements ChainedOperation {
        public Object execute(Object element, Object currValue) {
            String clause = (String) element;
            String actual = (String) currValue;
            return actual + and(clause);
        }
    }
}
