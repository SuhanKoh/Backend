/*
 * MIT License
 *
 * Copyright (c) 2016-2018 The FredBoat Org https://github.com/FredBoat/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fredboat.backend.quarterdeck.db.migrations.cache;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by napster on 17.04.18.
 */
public class V3__ChangeCompositeIndexColumnOrder implements JdbcMigration {

    //this is not the actual old table, just a precaution
    //language=PostgreSQL
    private static final String DROP_TABLE_SEARCH_RESULTS
            = "DROP TABLE IF EXISTS public.search_results;";

    //language=PostgreSQL
    private static final String CREATE_TABLE_SEARCH_RESULTS
            = "CREATE TABLE public.search_results "
            + "( "
            + "    search_term          TEXT COLLATE pg_catalog.\"default\" NOT NULL, "
            + "    provider             SearchProvider NOT NULL, "
            + "    looked_up            BIGINT, "
            + "    serialized_result    BYTEA NOT NULL, "
            + "    CONSTRAINT search_results_pkey PRIMARY KEY (search_term, provider) "
            + ");";


    @Override
    public void migrate(Connection connection) throws Exception {

        try (Statement createSearchResults = connection.createStatement()) {
            createSearchResults.execute(DROP_TABLE_SEARCH_RESULTS);
        }

        try (Statement createSearchResults = connection.createStatement()) {
            createSearchResults.execute(CREATE_TABLE_SEARCH_RESULTS);
        }
    }

}
