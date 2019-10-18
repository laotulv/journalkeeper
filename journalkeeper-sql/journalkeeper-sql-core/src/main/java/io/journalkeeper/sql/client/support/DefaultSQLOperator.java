/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.journalkeeper.sql.client.support;

import io.journalkeeper.sql.client.BatchSQLOperator;
import io.journalkeeper.sql.client.SQLClient;
import io.journalkeeper.sql.client.SQLOperator;
import io.journalkeeper.sql.client.domain.ResultSet;
import io.journalkeeper.sql.client.helper.ParamHelper;
import io.journalkeeper.sql.exception.SQLException;

import java.util.concurrent.ExecutionException;

/**
 * DefaultSQLOperator
 * author: gaohaoxiang
 * date: 2019/8/7
 */
public class DefaultSQLOperator implements SQLOperator {

    private SQLClient client;

    public DefaultSQLOperator(SQLClient client) {
        this.client = client;
    }

    @Override
    public Object insert(String sql, Object... params) {
        try {
            return client.insert(sql, ParamHelper.toList(params)).get();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public int update(String sql, Object... params) {
        try {
            return (int) client.update(sql, ParamHelper.toList(params)).get();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public int delete(String sql, Object... params) {
        try {
            return (int) client.delete(sql, ParamHelper.toList(params)).get();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public ResultSet query(String sql, Object... params) {
        try {
            return client.query(sql, ParamHelper.toList(params)).get();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public BatchSQLOperator beginBatch() {
        return new DefaultBatchSQLOperator(client);
    }

    protected SQLException convertException(Throwable e) {
        if (e instanceof SQLException) {
            return (SQLException) e;
        } else if (e instanceof ExecutionException) {
            return convertException(((ExecutionException) e).getCause());
        }
        return new SQLException(e);
    }
}