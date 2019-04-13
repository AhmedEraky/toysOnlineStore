/*
 * The MIT License
 *
 * Copyright 2019 Ibrahim Yousre (ib.yousre@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iti.model.cfg.transaction;

import org.hibernate.Session;

/**
 * This represents a unit of work that should run within a transactional context
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 * @param <T> The result of this unit of work
 */
public interface UnitOfWork<T> {

    /**
     * Implement your work here and it will run within a transactional context
     * by calling <code>{@link TransactionManager#runInTransaction}</code>. You
     * should obtain hibernate session by calling
     * {@link org.hibernate.SessionFactory#getCurrentSession}
     *
     * @return result of this unit of work
     * @throws Exception
     */
    T execute(Session session) throws Exception;

}
