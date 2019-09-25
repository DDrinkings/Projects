import { AnyAction, Reducer } from 'redux'
import { createIn as _createIn } from './service'

export interface StateType {
}

export type Effect = (
    action: AnyAction,
    effects: AnyAction,
) => void;

export interface ModelType {
    namespace: string;
    state: StateType;
    effects: {
        createIn: Effect;
    };
    reducers: {
        createInSave: Reducer<StateType>;
    };
}

const Model: ModelType = {
    namespace: 'inQuery',
    state: {},

    effects: {
        * createIn({ payload, callback }, { call, put }) {
            const response = yield call(_createIn, payload)
            callback(response)
        },
    },

    reducers: {
        createInSave(state, { payload }) {
            return { ...state, }
        },
    },
}

export default Model
