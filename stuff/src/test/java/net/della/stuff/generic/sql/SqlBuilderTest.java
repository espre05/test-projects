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

import static org.junit.Assert.*;

import net.della.stuff.generic.sql.SqlBuilder;

import org.junit.Test;

public class SqlBuilderTest {
    @Test
    public void where() throws Exception {
        assertEquals(" and ( bla bla bla ) ", SqlBuilder.and("where bla bla bla"));
        assertEquals("", SqlBuilder.and("bla bla bla"));
        assertEquals(" and ( (a) ( c ) ) ", SqlBuilder.and("where (a) ( c )"));
        assertEquals(
                " and (  (c.validation_date is not null or 1) AND ((c.category_id in (select id  from categories CA LEFT JOIN users_categories UC on (CA.id = UC.category_id)  WHERE (UC.user_id = 3498 OR CA.is_public) ) and c.content_type_id in (select content_type_id from users_content_types where user_id = 3498)) or 0) AND (c.user_id = 3498 or 0) ) ",
                SqlBuilder
                        .and(" where (c.validation_date is not null or 1) AND ((c.category_id in (select id  from categories CA LEFT JOIN users_categories UC on (CA.id = UC.category_id)  WHERE (UC.user_id = 3498 OR CA.is_public) ) and c.content_type_id in (select content_type_id from users_content_types where user_id = 3498)) or 0) AND (c.user_id = 3498 or 0)"));

    }
}
