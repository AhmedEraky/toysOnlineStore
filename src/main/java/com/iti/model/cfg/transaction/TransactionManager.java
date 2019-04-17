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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * This class is a central class to manage hibernate transactions
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
final public class TransactionManager {

    SessionFactory sessionFactory;

    /**
     *
     * @param sessionFactory hibernate session factory to which this transaction
     * manager is associated
     */
    public TransactionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method will run unit of work within hibernate transaction context
     *
     * @param <T>
     * @param unitOfWork the work to be executed within transaction context
     * @return the result of unit of work
     * @throws Exception as thrown by unit of work
     */
    public <T> T runInTransaction(UnitOfWork<T> unitOfWork) throws Exception {
        Session session=sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            return unitOfWork.execute(session);
        } catch (Exception ex) {
            if (tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (tx.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                tx.commit();
            }
        }
    }

}
