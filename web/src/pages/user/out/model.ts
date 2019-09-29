import { AnyAction, Reducer } from 'redux'
import { login as _login } from './service'

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
    login: Effect;
  };
  reducers: {
    loginSave: Reducer<StateType>;
  };
}

const Model: ModelType = {
  namespace: 'out2',
  state: {
    out2Data: {
      username: 'null',
      count: 0
    }
  },
//如果是同步行为会直接通过Reducers改变State;如果是异步行为会先触发effects,然后流向reducers最终改变state
  effects: {
    * login({ payload, callback }, { call, put }) {
      const response = yield call(_login, payload)
      console.log(response)
      yield put({
        type: 'loginSave',
        payload: response
      })
      callback && callback(response)
    },
  },

  reducers: {
    loginSave(state, { payload }) {
      // console.log(state)
      console.log(payload)
      const count = state.out2Data.count + 1
      return {
        ...state, out2Data: {
          ...state.out2Data,
          username: payload.message,
          count
        }
      }
    },
  },
}

export default Model
