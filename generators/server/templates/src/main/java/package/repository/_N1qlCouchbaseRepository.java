<%#
 Copyright 2013-2017 the original author or authors from the JHipster project.

 This file is part of the JHipster project, see https://jhipster.github.io/
 for more information.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-%>
package <%=packageName%>.repository;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Couchbase specific {@link org.springframework.data.repository.Repository} interface uses N1QL for all requests
 */
@NoRepositoryBean
public interface N1qlCouchbaseRepository<T, ID extends Serializable> extends CouchbasePagingAndSortingRepository<T, ID> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    List<T> findAll();

    @Query("SELECT count(*) FROM #{#n1ql.bucket} WHERE #{#n1ql.filter}")
    long count();

    @Query("DELETE FROM #{#n1ql.bucket} WHERE #{#n1ql.filter} returning #{#n1ql.fields}")
    T removeAll();

    default void deleteAll() {
        removeAll();
    }
}
