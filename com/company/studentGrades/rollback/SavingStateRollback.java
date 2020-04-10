package com.company.studentGrades.rollback;

import com.company.studentGrades.State;
import com.company.studentGrades.exceptions.RollbackException;

import java.util.ArrayList;
import java.util.List;

public class SavingStateRollback implements IRollback {
    List<State> savingStates = new ArrayList<>();
    List<State> cancelingStates = new ArrayList<>();


    @Override
    public void saveCurrentStateAndDoRollback(State state) throws RollbackException {
        addNewCancelingState(state);
        popLastSavingState().doRollback();
    }

    private State popLastSavingState() throws RollbackException {
        return popLastState(savingStates);
    }

    @Override
    public void saveCurrentStateAndCancelLastRollback(State state) throws RollbackException {
        addNewSavingState(state);
        popLastCancelingState().doRollback();
    }

    @Override
    public void saveState(State state) {
        cancelingStates.clear();
        addNewSavingState(state);
    }

    private void addNewSavingState(State state) {
        savingStates.add(state);
    }

    private void addNewCancelingState(State state) {
        cancelingStates.add(state);
    }


    private State popLastCancelingState() throws RollbackException {
        return popLastState(cancelingStates);
    }

    private State popLastState(List<State> states) throws RollbackException {
        try {
            return states.remove(states.size() - 1);
        }
        catch(IndexOutOfBoundsException e) {
            throw new RollbackException("Rollback without previous actions.", e);
        }

    }
}
