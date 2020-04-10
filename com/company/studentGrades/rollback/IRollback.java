package com.company.studentGrades.rollback;


import com.company.studentGrades.State;
import com.company.studentGrades.exceptions.RollbackException;

public interface IRollback {

    void saveCurrentStateAndDoRollback(State state) throws RollbackException;

    void saveCurrentStateAndCancelLastRollback(State state) throws RollbackException;

    void saveState(State state) throws RollbackException;

}
